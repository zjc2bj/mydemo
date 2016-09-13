package com.lt.utils.jsonobject;

import cn.zjc.bean.Menu;

import com.lt.utils.JsonClientUtil;

public class JsonDemo {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setName("国内机票");
        menu.setUrl("127.0.0.1:8080");
        Menu submenu = new Menu();
        submenu.setName("出票中心");
        submenu.setUrl("192.168.7.33:8000");
        menu.setSubMenu(submenu);

        String obj2String = JSONObjectUtil.Obj2String(menu);
        System.out.println(obj2String);

        String json = JsonClientUtil.toJson(menu);
        System.out.println(json);

    }

    private static void test1() {
        ActivateUser user = new ActivateUser();
        user.setLoginName("1111");
        user.setStatusCode(2222);

        String security = JSONObjectUtil.ObjToStringOfSecurity(user);
        System.out.println(security);
    }
}
