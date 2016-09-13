package com.lt.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liantuo.sso.common.bean.User;

public class SsoUtil {
    /** 用户Session名 */
    public final static String user_session_token = "user_session_bcs";

    // 获取主域名
    public static String getDomain(HttpServletRequest request) {
        String serverName = request.getServerName();
        String domain = serverName.substring(serverName.indexOf('.') + 1);
        return domain;
    }

    // 获取用户登录session(泛型)
    public static <T> T getUserLoginSession(HttpServletRequest request, Class<T> c) {
        return (T) request.getSession().getAttribute(user_session_token);

    }

    // 获取用户登录session(泛型)
    public static <T> T getUserLoginSession(HttpSession session, Class<T> c) {

        return (T) session.getAttribute(user_session_token);

    }

    // 获取登录用户
    public static User getUser(HttpServletRequest request) {
        Object attr = request.getSession().getAttribute(user_session_token);
        return attr != null ? (User) attr : null;
    }

    // 创建用户登录session
    public static void createUserSession(HttpServletRequest request, Object session) {
        request.getSession().setAttribute(user_session_token, session);
    }

    // 清除用户登录session
    public static void clearUserLoginSession(HttpServletRequest request) {
        request.getSession().removeAttribute(user_session_token);
    }

}
