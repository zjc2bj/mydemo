package cn.zjc.demo;

import java.util.HashMap;
import java.util.Map;

public class HashDemo {
    public static Map<String, Object> map = new HashMap<String, Object>();

    public static void main(String[] args) {

        HashDemo.map.get("...");

        // String s1 = new String("1111111");
        // String s2 = new String("1111111");
        //
        // System.out.println(s1.hashCode());
        // System.out.println(s2.hashCode());
        //
        // System.out.println(s1 == s2);
        //
        // Object o1 = new Object();
        // Object o2 = new Object();
        //
        // System.out.println(o1.hashCode());
        // System.out.println(o2.hashCode());
    }
}
