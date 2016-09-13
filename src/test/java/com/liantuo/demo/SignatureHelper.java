package com.liantuo.demo;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 签名生成辅助类
 * 
 * @author NiuXiaoYu
 * 
 */
public class SignatureHelper {

    private static final Logger logger = Logger.getLogger(SignatureHelper.class);

    /**
     * 生成数字签名
     * 
     * @param params
     * @param privateKey
     * @return
     */
    public static String sign(Map<String, ? extends Object> params, String privateKey) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (Entry<String, ? extends Object> entry : params.entrySet()) {
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (name == null || name.equalsIgnoreCase("sign") || name.equalsIgnoreCase("sign_type")
                    || StringUtils.isEmpty(value) || name.equalsIgnoreCase("pnr_txt")
                    || name.equalsIgnoreCase("pata_txt") || name.equalsIgnoreCase("pnr_no")) {
                continue;
            }
            treeMap.put(name, value);
        }

        String content = getSignatureContent(treeMap);
        return sign(content, privateKey);
    }

    /**
     * 使用treeMap拼装content
     * 
     * @param properties
     * @return
     */
    public static String getSignatureContent(Map<String, String> treeMap) {
        StringBuffer content = new StringBuffer();
        for (Entry<String, String> entry : treeMap.entrySet()) {
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();
            content.append("&" + name + "=" + value);
        }

        return content.substring(1);
    }

    /**
     * 根据内容和私有秘钥生成数字签名
     * 
     * @param content
     * @param privateKey
     * @return
     */
    public static String sign(String content, String privateKey) {
        if (privateKey == null) {
            return null;
        }
        String signBefore = content + privateKey;
        logger.info("signBefore=" + signBefore);
        System.out.println("signBefore=" + signBefore);
        return Md5Encrypt.md5(signBefore).toLowerCase();
    }

}
