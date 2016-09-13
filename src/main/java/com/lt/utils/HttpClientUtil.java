package com.lt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({ "unchecked", "unused" })
public class HttpClientUtil {
    private final static int DEFAULT_CONNECTION_TIME_OUT = 3000;

    private final static int DEFAULT_SO_TIME_OUT = 10000;

    private final static String DEFAULT_CHARSET_UTF_8 = "UTF-8";

    public static String requestAsHttpPOST(String urlvalue, Map<String, String> paras, String charset) {
        HttpClient http = new HttpClient();
        HttpConnectionManagerParams managerParams = http.getHttpConnectionManager().getParams();
        managerParams.setConnectionTimeout(DEFAULT_CONNECTION_TIME_OUT);
        managerParams.setSoTimeout(DEFAULT_SO_TIME_OUT);
        PostMethod method = new PostMethod(urlvalue);
        method.setRequestHeader("Connection", "close");
        method.getParams().setHttpElementCharset(charset);
        method.getParams().setContentCharset(charset);
        method.getParams().setCredentialCharset(charset);
        for (String key : paras.keySet()) {
            method.addParameter(key, paras.get(key));
        }
        String result = "";
        try {
            http.executeMethod(method);
            result = method.getResponseBodyAsString();

        } catch (IOException e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }

    public static String requestAsHttpPOST(String urlvalue, Map<String, String> paras, String charset,
            Integer connectionTimeout, Integer soTimeout) {
        HttpClient http = new HttpClient();
        HttpConnectionManagerParams managerParams = http.getHttpConnectionManager().getParams();
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
        PostMethod method = new PostMethod(urlvalue);
        method.setRequestHeader("Connection", "close");
        if (StringUtils.isNotEmpty(charset)) {
            method.getParams().setHttpElementCharset(charset);
            method.getParams().setContentCharset(charset);
            method.getParams().setCredentialCharset(charset);
        } else {
            method.getParams().setHttpElementCharset(DEFAULT_CHARSET_UTF_8);
            method.getParams().setContentCharset(DEFAULT_CHARSET_UTF_8);
            method.getParams().setCredentialCharset(DEFAULT_CHARSET_UTF_8);
        }
        if (paras != null) {
            for (String key : paras.keySet()) {
                method.addParameter(key, paras.get(key));
            }
        }
        String result = "";
        try {
            http.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (IOException e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }

    public static String requestAsHttpPOST(String urlvalue, Map<String, String> paras, String charset, int timeCout) {
        HttpClient http = new HttpClient();
        HttpConnectionManagerParams managerParams = http.getHttpConnectionManager().getParams();
        managerParams.setConnectionTimeout(3000);
        managerParams.setSoTimeout(timeCout);

        PostMethod method = new PostMethod(urlvalue);
        method.setRequestHeader("Connection", "close");
        method.getParams().setHttpElementCharset(charset);
        method.getParams().setContentCharset(charset);
        method.getParams().setCredentialCharset(charset);
        for (String key : paras.keySet()) {
            method.addParameter(key, paras.get(key));
        }
        String result = "";
        try {
            http.executeMethod(method);
            result = method.getResponseBodyAsString();

        } catch (IOException e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }

    public static String requestAsHttpGET(String urlvalue) {
        String inputLine = "";
        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = in.readLine();
            while (line != null) {
                inputLine += line;
                line = in.readLine();
            }
        } catch (SSLHandshakeException d) {
            requestAsHttpGET(urlvalue);
            d.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return inputLine;
    }

    /**
     * 胜意推单 str-->map
     * 
     * @param reqStr
     * @return
     */
    public static Map<String, String> convertStr2Map(String reqStr) {
        String[] params = reqStr.split("&");
        Map<String, String> paramMap = new HashMap<String, String>();

        for (String strItem : params) {
            // 使用第一个“=”分割字符串
            int equalIndex = strItem.indexOf("=");

            String key = strItem.substring(0, equalIndex);
            String value = strItem.substring(equalIndex + 1);

            paramMap.put(key, value);
        }

        return paramMap;
    }

    public static Map<String, String> convertParas2Map(String paras, String regx) {
        Map<String, String> attachValue = new HashMap<String, String>();
        String[] vs = paras.split(regx);
        for (int i = 0; i < vs.length; i++) {
            String v = vs[i];
            String[] ts = v.split("=");
            if (ts.length == 2)
                attachValue.put(ts[0], ts[1]);
        }
        return attachValue;
    }

    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static void main(String[] args) {
        // String reqStr =
        // "http://219.141.233.66:55815/partner/cooperate.in?email=mets1154@mj.cn&goto_url=http://www.51book.com/liantuo/manage/index.in?isLogin=true&input_charset=UTF-8&mobilePhone=13611148345&outer_app_token=51bookFlight&outer_login_name=19020&partner=EASYTOUR&phone=13611148345&phone=13644444444&return_url=http://www.etschina.com/flightbook/Login51BookAPI.ashx&service=user_login&user_name=美景顾问郭斌&user_type=AGENCY_SINGLE_USER&sign=dff2648f85bc44e3b55b9b0f7c877feb&sign_type=MD5";
        // String reqStr =
        // "http://219.141.233.66:55815/partner/cooperate.in?email=315784913%40qq.com&goto_url=http%3A%2F%2F219.141.233.68%3A54675%2Fcaigou%2Fmanage%2Findex.in%3FisLogin%3Dtrue&input_charset=UTF-8&mobilePhone=18600019497&outer_app_token=51bookFlight&outer_login_name=15029&partner=EASYTOUR&phone=18600019497&return_url=http%3A%2F%2Fpreview.etschina.com%2Fflightbook%2FLogin51BookAPI.ashx&service=user_login&user_name=%E6%9D%8E%E8%89%B3&user_type=AGENCY_SINGLE_USER&sign=5cfd77812e4474c2bef503351692cddf&sign_type=MD5";
        String reqStr = "http://127.0.0.1:8080/partner/cooperate.in?email=315784913%40qq.com&goto_url=http%3A%2F%2F219.141.233.68%3A54675%2Fcaigou%2Fmanage%2Findex.in%3FisLogin%3Dtrue&input_charset=UTF-8&mobilePhone=18600019497&outer_app_token=51bookFlight&outer_login_name=15029&partner=EASYTOUR&phone=18600019497&return_url=http%3A%2F%2Fpreview.etschina.com%2Fflightbook%2FLogin51BookAPI.ashx&service=user_login&user_name=%E6%9D%8E%E8%89%B3&user_type=AGENCY_SINGLE_USER&sign=5cfd77812e4474c2bef503351692cddf&sign_type=MD5";
        // String reqStr =
        // "http://127.0.0.1:8080/partner/cooperate.in?service=user_login&partner=HZBK&outer_app_token=HZBK21917_subagency_admin&outer_login_name=13975192338&user_name=%e5%88%98%e6%ac%a2&phone=84720718&mobilePhone=13975192338&user_type=AGENCY_SINGLE_USER&goto_url=http://jp.boktour.com/caigou/manage/newBuyerFromB2BListPolicyFlight.in&return_url=http://login.boktour.com/?SiteType=5&input_charset=UTF-8&sign_type=MD5&sign=1702e29053812a0612046a595a0d2ead";
        requestAsHttpGET(reqStr);
        // String md5 = convertMD5("ZWk0QSFTQEo6Pj9JTzlAd1c9QDZLUT09");
        // System.out.println(md5);
    }
}
