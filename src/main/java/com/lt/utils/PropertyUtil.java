package com.lt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 处理Properties文件的一个基础类
 * 
 * @author 张宇洋
 * 
 */
@SuppressWarnings("unchecked")
public class PropertyUtil {

    /**
     * properites文件名为key一个Map
     */
    private static Map proMap = new HashMap();

    /**
     * 根据文件路径（绝对路径）创建Properties对象并且放入Map容器里
     * 
     * @param filePath
     */
    private static void init(String filePath) {
        Properties property;
        File systemFile = new File(filePath);
        try {
            property = new Properties();
            property.load(new FileInputStream(systemFile));
            String absName = FileUtil.getAbsFileName(filePath);
            if (absName != null)
                proMap.put(absName, property);
        } catch (IOException e) {
        }
    }

    /**
     * 
     * @param key
     *            propertiesd对应的key
     * @param filePath
     *            文件相对路径 如：com/properties/server.properties
     * @return key对应的value
     */
    public static String getProperty(String key, String filePath) {
        Properties property = null;
        String appDir = FileUtil.getClassPath();
        appDir = appDir.replaceAll("\\\\", "/");
        appDir += filePath;
        property = (Properties) proMap.get(FileUtil.getAbsFileName(appDir));
        if (property != null)
            return (String) property.get(key);
        else {
            init(appDir);
            property = (Properties) proMap.get(FileUtil.getAbsFileName(appDir));
            return (String) property.get(key);
        }
    }
}