package cn.zjc.util;

import java.util.ResourceBundle;

public class ResourceLoader {
    /**
     * 从指定的资源文件中获取对应的值
     */
    public String getText(String bundleName, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
        return bundle.getString(key);
    }

    /**
     * 默认文件名为message.properties | message_zh_CN.properties
     */
    public String getText(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
        return bundle.getString(key);
    }
}
