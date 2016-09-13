package com.lt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import cn.zjc.util.StringUtil;

public class HttpClinetProxyUtil {

    /**
     * 设置缺省连接超时时间(单位毫秒)
     */
    private final static int DEFAULT_CONNECTION_TIME_OUT = 6000;

    /**
     * 设置缺省读数据超时时间(单位毫秒)
     */
    private final static int DEFAULT_SO_TIME_OUT = 60000;

    /***************************************************************************
     * 设置缺省字符编码
     */
    private final static String DEFAULT_CHARSET_UTF_8 = "UTF-8";

    public static void configProxyInfo(HttpClient client, String proxyHost, Integer proxyPort) throws URIException,
            NullPointerException {
        HostConfiguration hostConfiguration = new HostConfiguration();
        hostConfiguration.setProxy(proxyHost, proxyPort);// 配置代理IP和端口
        // 如果代理需要密码验证，这里设置用户名密码
        // this.client.getState().setProxyCredentials(AuthScope.ANY, new
        // UsernamePasswordCredentials("crk", "crk"));

        client.setHostConfiguration(hostConfiguration);
    }

    public static String executeProxyGet(String tagerUrl, String proxyHost, int proxyPort, String charset,
            Integer connectionTimeout, Integer soTimeout) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        configProxyInfo(client, proxyHost, proxyPort);
        configConnectionTime(connectionTimeout, soTimeout, client);
        HttpMethodBase getMethod = new GetMethod(tagerUrl);
        configMethodInfo(charset, getMethod);
        int status = client.executeMethod(getMethod);
        if (status == HttpStatus.SC_OK) {
            String responseBody = getMethod.getResponseBodyAsString();
            if (StringUtil.isTrimBlank(responseBody)) {
                return responseBody;
            }
        } else {
            return "请求返回结果不是正常状态";
        }
        return null;
    }

    public static String executeProxyPost(String tagerUrl, String proxyHost, int proxyPort, String reqXml,
            String charset, Integer connectionTimeout, Integer soTimeout) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        configProxyInfo(client, proxyHost, proxyPort);
        PostMethod postMethod = new PostMethod(tagerUrl);
        configConnectionTime(connectionTimeout, soTimeout, client);
        // configMethodInfo(charset, postMethod);
        // for (String key : paras.keySet()) {
        // postMethod.addParameter(key, paras.get(key));
        // }

        postMethod.setRequestEntity(new StringRequestEntity(reqXml, "application/x-www-form-urlencoded",
                DEFAULT_CHARSET_UTF_8));
        int statusCode = client.executeMethod(postMethod);
        if (statusCode == HttpStatus.SC_OK) {
            StringBuffer contentBuffer = new StringBuffer();
            InputStream in = postMethod.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, postMethod.getResponseCharSet()));
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                contentBuffer.append(inputLine);
                // contentBuffer.append("/n");
            }
            in.close();
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
            return contentBuffer.toString();
        } else {
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
            return "请求返回不是正常状态";
        }
    }

    private static void configMethodInfo(String charset, HttpMethod getMethod) {
        getMethod.setRequestHeader("Connection", "close");
        if (StringUtil.isTrimBlank(charset)) {
            getMethod.getParams().setHttpElementCharset(charset);
            getMethod.getParams().setContentCharset(charset);
            getMethod.getParams().setCredentialCharset(charset);
        } else {
            getMethod.getParams().setHttpElementCharset(DEFAULT_CHARSET_UTF_8);
            getMethod.getParams().setContentCharset(DEFAULT_CHARSET_UTF_8);
            getMethod.getParams().setCredentialCharset(DEFAULT_CHARSET_UTF_8);
        }
    }

    private static void configConnectionTime(Integer connectionTimeout, Integer soTimeout, HttpClient client) {
        HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
        if (connectionTimeout != null && connectionTimeout.intValue() != 0) {
            managerParams.setConnectionTimeout(connectionTimeout);
        } else {
            managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIME_OUT);
        }
        if (soTimeout != null && soTimeout.intValue() != 0) {
            managerParams.setSoTimeout(soTimeout);
        } else {
            managerParams.setSoTimeout(DEFAULT_SO_TIME_OUT);
        }
    }

    public static String xmlFormat(String xml) {
        if (StringUtil.isTrimBlank(xml)) {
            return xml = xml.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
        } else {
            return xml;
        }
    }

    public static void main(String[] args) throws Exception {
        String strProxy = "192.168.7.18";
        Integer strPort = 808;
        String targetUrl = "http://192.168.6.66:55555/bcs/order/bcsOrder.in";
        HttpClinetProxyUtil.executeProxyGet(targetUrl, strProxy, strPort, null, null, null);
        String reqXml = "";
        HttpClinetProxyUtil.executeProxyPost(targetUrl, strProxy, strPort, reqXml, null, null, null);
    }
}
