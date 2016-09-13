package cn.zjc.groovy2;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class GroovyClassLoaderDemo {
    // 1) 解析groovy文件
    /**
     * from source file of *.groovy
     */
    public static void parse() throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        File sourceFile = new File("D:\\TestGroovy.groovy");
        Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
        GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();// proxy
        Long time = (Long) instance.invokeMethod("getTime", new Date());
        System.out.println(time);
        Date date = (Date) instance.invokeMethod("getDate", time);
        System.out.println(date.getTime());
        // here
        instance = null;
        testGroovyClass = null;
    }

    // 2) 如何加载已经编译的groovy文件(.class)
    public static void load() throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\TestGroovy.class"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (;;) {
            int i = bis.read();
            if (i == -1) {
                break;
            }
            bos.write(i);
        }
        Class testGroovyClass = classLoader.defineClass(null, bos.toByteArray());
        // instance of proxy-class
        // if interface API is in the classpath,you can do such as:
        // MyObject instance = (MyObject)testGroovyClass.newInstance()
        GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
        Long time = (Long) instance.invokeMethod("getTime", new Date());
        System.out.println(time);
        Date date = (Date) instance.invokeMethod("getDate", time);
        System.out.println(date.getTime());

        // here
        bis.close();
        bos.close();
        instance = null;
        testGroovyClass = null;
    }

}
