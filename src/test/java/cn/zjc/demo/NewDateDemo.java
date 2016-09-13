package cn.zjc.demo;

import java.util.Date;

public class NewDateDemo {

    public static void main(String[] args) {
        System.out.println(new Date());
        Date date = new Date("2013-02-02 24:00:00");
        System.out.println(date);
    }
}
