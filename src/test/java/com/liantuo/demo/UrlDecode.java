package com.liantuo.demo;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlDecode {

    public static void main(String[] args) throws Exception {
        String sign = "D4CEA4D5B626DC93AE6D3EC3E63649B3";
        System.out.println(sign.toLowerCase());

        String url = "http://aio.51book.com/partner/cooperate.in?service=user_login&partner=ZJZL01&outer_app_token=ZJZL01_subagency_admin&outer_login_name=admin01&user_name=admin01&user_type=AGENCY_SINGLE_USER&goto_url=http://caigou.51book.com/caigou/manage/index.in&return_url=http://shop.zjctsg.com/login.aspx&input_charset=UTF-8&sign_type=MD5&sign=0354b6b8e4f14ed7a982b773b348c4f8";
        // String url =
        // "http://aio.51book.com/partner/cooperate.in?service=user_login&partner=ZJZL01&outer_app_token=ZJZL01_subagency_admin&outer_login_name=admin&user_name=admin&user_type=AGENCY_SINGLE_USER&goto_url=http://caigou.51book.com/caigou/manage/index.in&return_url=http://shop.zjctsg.com/login.aspx&input_charset=UTF-8&sign_type=MD5&sign=d4cea4d5b626dc93ae6d3ec3e63649b3";
        System.out.println(encode(url));
    }

    private static String decode() throws Exception {
        String url = "http://aio.51book.com/partner/cooperate.in?user_name=1573&input_charset=utf-8&outer_login_name=000157318183114481&outer_app_token=CQBEST_subagency_admin&user_type=AGENCY_SINGLE_USER&service=user_login&partner=CQBEST&return_url=http%3a%2f%2fbest.trvl.com%2fRetailStore%2fProductMange%2fComplexAirProductList&group_id=96042&sign=baa8674ad603c3ecfc0011e20c54135b&goto_url=http%3a%2f%2fcaigou.51book.com%2fcaigou%2fmanage%2findex.in%3fisLogin%3dtrue&sign_type=MD5";

        StringBuffer buffer = new StringBuffer("");
        String[] arr = url.split("\\?");

        buffer.append(arr[0]);
        buffer.append("?");

        String[] params = arr[1].split("&");
        for (int i = 0; i < params.length; i++) {
            String[] values = params[i].split("=");

            if (i > 0)
                buffer.append("&");
            buffer.append(values[0]);
            buffer.append("=");
            buffer.append(URLDecoder.decode(values[1], "UTF-8"));
        }

        return buffer.toString();

    }

    public static String encode(String str) throws Exception {
        StringBuffer buffer = new StringBuffer();
        String[] split = str.split("\\?");
        buffer.append(split[0] + "?");

        String[] params = split[1].split("&");
        for (int i = 0; i < params.length; i++) {
            String[] values = params[i].split("=");

            if (i > 0)
                buffer.append("&");
            buffer.append(values[0]);
            buffer.append("=");
            buffer.append(URLEncoder.encode(values[1], "UTF-8"));
        }

        return buffer.toString();
    }

}
