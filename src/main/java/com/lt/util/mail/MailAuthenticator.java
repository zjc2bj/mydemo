package com.lt.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * @author guanlichang
 * 
 */
public class MailAuthenticator extends Authenticator {

    // ******************************
    // 由于发送邮件的地方比较多,
    // 下面统一定义用户名,口令.
    // ******************************
    private String HUAWEI_MAIL_USER = null;

    private String HUAWEI_MAIL_PASSWORD = null;

    public MailAuthenticator() {
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(HUAWEI_MAIL_USER, HUAWEI_MAIL_PASSWORD);
    }

    public void setUser(String o) {
        HUAWEI_MAIL_USER = o;
    }

    public String getUser() {
        return HUAWEI_MAIL_USER;
    }

    public void setPassward(String o) {
        HUAWEI_MAIL_PASSWORD = o;
    }

    public String getPassward() {
        return HUAWEI_MAIL_PASSWORD;
    }

}
