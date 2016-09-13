package com.lt.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.zjc.util.DateTools;

public class LoggerThread {

    /**
     * 当前线程使用.
     */
    private static ThreadLocal<LoggerThread> threadLocal = new ThreadLocal<LoggerThread>();

    /**
     * 当前线程是否有logger实例
     * 
     * @return true表示存在
     */
    public static boolean existsLoggerByCurrThread() {
        return threadLocal.get() != null;
    }

    /**
     * 获取logger线程
     * 
     * @return
     */
    public static LoggerThread get() {

        if (!existsLoggerByCurrThread()) {
            throw new NullPointerException("Logger is undefined for current Thread.");
        }
        return threadLocal.get();
    }

    /**
     * 初始化日志线程，如果线程已经初始化了，那么就不会被覆盖
     * 
     * @param header
     *            线程第一次调用start是进行初始化，如果过程中再次调用，该值不会修改
     */
    public static void start(String header) {
        LoggerThread thread = new LoggerThread();
        if (existsLoggerByCurrThread()) {
            // 如果当前线程存在Logger,那么就取出
            thread = threadLocal.get();
        } else {
            thread = new LoggerThread();
        }
        // 如果当前线程中已经存在header，那么就不修改
        // if (StringUtils.isBlank(thread.header)) {
        thread.header = header;
        // }
        thread.logger = Logger.getLogger("issuecenter");
        threadLocal.set(thread);
    }

    /**
     * 当前线程打印日志
     * 
     * @param msg
     */
    public static void info(String msg) {
        if (existsLoggerByCurrThread()) {
            try {
                LoggerThread thread = get();
                thread.logger.info(StringUtils.trimToEmpty(thread.header) + " : " + msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 当前线程打印日志
     * 
     * @param msg
     */
    public static void debug(String msg) {
        if (existsLoggerByCurrThread()) {
            try {
                LoggerThread thread = get();
                thread.logger.debug(StringUtils.trimToEmpty(thread.header) + " : " + msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(msg);
        }
    }

    /**
     * 当前线程打印日志
     * 
     * @param msg
     */
    public static void print(String msg) {
        System.out.println(DateTools.getCurrentDateStrForLog() + " " + msg);
    }

    public String header;

    private Logger logger;
}
