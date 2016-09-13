package com.lt.utils.jsonobject;

import java.util.Date;

/**
 * 客户端SSO活动用户Bean
 * 
 */
public class ActivateUser {

    private String loginName;

    private Date expiryDate;

    private int statusCode;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
