package cn.zjc.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlDecode {
    public static void main(String[] args) {
        try {
            String decode = URLDecoder.decode("%E9%9F%A6%E6%96%B9%E9%9D%99", "UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
