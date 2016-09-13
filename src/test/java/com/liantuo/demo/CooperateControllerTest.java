package com.liantuo.demo;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CooperateControllerTest {
    public static void main(String[] args) throws Exception {
        // String url =
        // "http://aio.51book.com/partner/cooperate.in?service=user_login&partner=ZJZL01&outer_app_token=ZJZL01_subagency_admin&outer_login_name=admin01&user_name=admin01&user_type=AGENCY_SINGLE_USER&goto_url=http://caigou.51book.com/caigou/manage/index.in&return_url=http://shop.zjctsg.com/login.aspx&input_charset=UTF-8&sign_type=MD5&sign=0354b6b8e4f14ed7a982b773b348c4f8";
        // String url =
        // "http://aio.51book.com/partner/cooperate.in?service=user_login&partner=ZJZL01&outer_app_token=ZJZL01_subagency_admin&outer_login_name=admin&user_name=admin&user_type=AGENCY_SINGLE_USER&goto_url=http://caigou.51book.com/caigou/manage/index.in&return_url=http://shop.zjctsg.com/login.aspx&input_charset=UTF-8&sign_type=MD5&sign=d4cea4d5b626dc93ae6d3ec3e63649b3";
        // String encodeUrl =
        // "http://aio.51book.com/partner/cooperate.in?user_name=1573&input_charset=utf-8&outer_login_name=000157318183114481&outer_app_token=CQBEST_subagency_admin&user_type=AGENCY_SINGLE_USER&service=user_login&partner=CQBEST&return_url=http%3a%2f%2fbest.trvl.com%2fRetailStore%2fProductMange%2fComplexAirProductList&group_id=96042&sign=baa8674ad603c3ecfc0011e20c54135b&goto_url=http%3a%2f%2fcaigou.51book.com%2fcaigou%2fmanage%2findex.in%3fisLogin%3dtrue&sign_type=MD5";
        // String encodeUrl =
        // "http://aio.51book.com/partner/cooperate.in?email=2116303587@qq.com&goto_url=http://caigou.51book.com/caigou/manage/newBuyerFromB2BListPolicyFlight.in&group_id=89093&input_charset=UTF-8&mobilePhone=18510958025&outer_app_token=QLX_1_subagency_admin&outer_login_name=qianlixue_1&partner=QLX2010&phone=4000051960&return_url=http://old.elutongxing.com/airline/airline/logout&service=user_login&time_stamp=1418779090123&user_name=晓晓&user_type=AGENCY_SINGLE_USER&sign_type=MD5&sign=dd7b79b8a746b95e94858870960c3739";
        // String decodeUrl = decode(encodeUrl);
        String decodeUrl = "http://aio.51book.com/partner/cooperate.in?email=2116303587@qq.com&goto_url=http://caigou.51book.com/caigou/manage/newBuyerFromB2BListPolicyFlight.in&group_id=89093&input_charset=UTF-8&mobilePhone=18510958025&outer_app_token=QLX_1_subagency_admin&outer_login_name=qianlixue_1&partner=QLX2010&phone=4000051960&return_url=http://old.elutongxing.com/airline/airline/logout&service=user_login&time_stamp=1418785979123&user_name=晓晓&user_type=AGENCY_SINGLE_USER&sign_type=MD5&sign=c12c3fee80d3ccb3c61c7fdf5348609c";
        // String decodeUrl =
        // "http://127.0.0.1:8080/partner/cooperate.in?email=2116303587@qq.com&goto_url=http://219.141.233.68:54675/caigou/manage/newBuyerFromB2BListPolicyFlight.in&group_id=89093&input_charset=UTF-8&mobilePhone=18510958025&outer_app_token=QLX_1_subagency_admin&outer_login_name=qianlixue_1&partner=QLX2010&phone=4000051960&return_url=http://old.elutongxing.com/airline/airline/logout&service=user_login&time_stamp=1418785369123&user_name=晓晓&user_type=AGENCY_SINGLE_USER&sign_type=MD5&sign=16344185644ee7c848999252c8242819";

        System.out.println(decodeUrl);
        String encodeUrl = encode(decodeUrl);
        System.out.println(convertUrl(encodeUrl));
    }

    /**
     * 将编码过的url重新生成sign并编码
     * 
     * @param srcUrl
     * @return
     * @throws Exception
     */
    public static String convertUrl(String srcUrl) throws Exception {
        String securityCode = "tdts_@(51).";
        // ---------------------------Url解码----------------------
        // String decodeUrl = decode(srcUrl);

        // ---------------------------重新加密----------------------
        StringBuffer buffer = new StringBuffer();
        String[] split = srcUrl.split("\\?");
        buffer.append(split[0] + "?");

        Map<String, String> paramMap = new HashMap<String, String>();

        String[] params = split[1].split("&");
        for (int i = 0; i < params.length; i++) {
            String[] values = params[i].split("=");

            if (values[0].equals("sign")) {
                continue;
            }

            paramMap.put(values[0], URLDecoder.decode(values[1], "utf-8"));
            if (i > 0)
                buffer.append("&");

            buffer.append(values[0]);
            buffer.append("=");
            buffer.append(values[1]);

        }

        String sign = SignatureHelper.sign(paramMap, securityCode);
        buffer.append("&sign=" + sign);
        // ---------------------------Url编码----------------------
        return buffer.toString();
    }

    /** 解密 */
    public static String decode(String url) throws Exception {

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

    /** 加密 */
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
