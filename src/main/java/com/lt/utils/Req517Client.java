package com.lt.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class Req517Client {

    public static String sendHttpRequestByPost(String url, String reqXml) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        client.getParams().setSoTimeout(50 * 1000);
        String rspXml = null;//
        System.out.println("Req517ClientUrl : " + url);
        System.out.println("Req517ClientReqXml : " + reqXml);
        try {
            post.setRequestEntity(new StringRequestEntity(reqXml, "application/x-www-form-urlencoded", "utf-8"));
            int statusCode = client.executeMethod(post);
            System.out.println("statusCode : " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                BufferedInputStream bis = new BufferedInputStream(post.getResponseBodyAsStream());
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while ((count = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, count);
                }
                byte[] strByte = bos.toByteArray();
                rspXml = new String(strByte, 0, strByte.length, "utf-8");
                bos.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接时异常");
        }
        post.releaseConnection();
        client.getHttpConnectionManager().closeIdleConnections(0);
        rspXml = rspXml.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
        System.out.println("rspXml : " + rspXml);
        return rspXml;
    }
}
