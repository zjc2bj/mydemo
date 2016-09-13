package com.lt.sso;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liantuo.sso.client.SSORequest;
import com.liantuo.sso.client.SSOResponse;
import com.liantuo.sso.client.context.SSOContext;
import com.liantuo.sso.common.bean.ActivateUser;
import com.liantuo.sso.common.bean.SSOReturnCode;
import com.liantuo.sso.common.bean.User;
import com.liantuo.sso.common.util.SSODeserializatorUtil;

@Component
public class SsoInterceptor extends HandlerInterceptorAdapter {

    private final static Logger log = LoggerFactory.getLogger(SsoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 重新验证
        return checkIn(request, response);
    }

    private boolean checkIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user;
        boolean checkin = false;
        SSORequest ssoRequest = new SSORequest();
        ssoRequest.setHttpRequest(request);
        ssoRequest.setHttpResponse(response);
        String token = request.getParameter(SSOContext.getSSOClient().getGlobalToken());
        if (org.springframework.util.StringUtils.hasText(token)) {
            ActivateUser activateUser = SSODeserializatorUtil.StringToUserOfSecurity(token);
            if (activateUser != null) {
                try {
                    SSOContext.getSSOClient().addCookie(ssoRequest, activateUser);
                    // customerPropertyService.createNewCustomerPropertyForLogin(activateUser.getUser().getAgency());
                    checkin = true;
                    SsoUtil.createUserSession(request, activateUser.getUser());
                } catch (Exception e) {
                    e.printStackTrace();
                    checkin = false;
                }
            } else {
                checkin = false;
            }
        } else {
            // 构造sso请求对象
            SSOResponse ssoResponse = SSOContext.getSSOClient().checkIn(ssoRequest);
            response.setCharacterEncoding("UTF-8");
            if (SSOReturnCode.CHECK_IN_SUCCESS.getCode() == ssoResponse.getReturnCode().getCode()) {
                user = ssoResponse.getUser();
                SsoUtil.createUserSession(request, user);
                log.info("--------checkin成功--------");
                // customerPropertyService.createNewCustomerPropertyForLogin(user.getAgency());
                checkin = true;
            } else {
                checkin = false;
            }
        }
        if (!checkin) {
            log.info("--------checkin失败,重新登录--------");
            response.sendRedirect("/bcs/sso.in?returnUrl=" + request.getServletPath());
            return false;
        } else {
            return true;
            // response.sendRedirect(request.getContextPath() +
            // request.getServletPath());
        }
    }

}
