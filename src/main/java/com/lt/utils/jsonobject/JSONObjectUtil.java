package com.lt.utils.jsonobject;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * 对象串行化工具类 使用JsonLib实现User和String的相互转换
 * 
 */
public class JSONObjectUtil {
    /**
     * 根据加密的字符串返回User对象
     * 
     * @param str
     * @return
     */
    public static ActivateUser StringToUserOfSecurity(String str) {
        try {
            ActivateUser user = null;
            str = SSOSecureUtil.DataDecrypt(str);
            if (JSONUtils.mayBeJSON(str)) {
                JSONObject jsonObject = JSONObject.fromObject(str);
                user = (ActivateUser) JSONObject.toBean(jsonObject, ActivateUser.class);
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将对象序列化为加密后的字符串
     * 
     * @param user
     * @return
     */
    public static String ObjToStringOfSecurity(ActivateUser user) {
        JSONObject jsonObject = JSONObject.fromObject(user);
        try {
            return SSOSecureUtil.DataEncrypt(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象序列化为字符串
     * 
     * @param user
     * @return
     */
    public static String Obj2String(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        try {
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
