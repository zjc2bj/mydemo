package cn.zjc.demo;

import org.springframework.util.StringUtils;

public class StringUtilsDemo {
    public static void main(String[] args) {
        String cleanPath = StringUtils.cleanPath("classpath:\\message_zh_CN.properties");
        System.out.println(cleanPath);
    }
}
