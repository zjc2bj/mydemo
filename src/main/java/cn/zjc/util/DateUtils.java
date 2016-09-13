package cn.zjc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author zhujunchao
 * @see Calendar
 * @see DateFormat
 */
public class DateUtils {

    /**
     * 将日期按照一定得格式 转化为字符串
     * 
     * @param date
     *            日期
     * @param format
     *            转化的格式
     * @return String
     */
    public String date2String(Date date, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        String strDate = sdf.format(date);
        return strDate;
    }

    /**
     * 将字符串转化为日期
     * 
     * @param source
     *            一定格式的日期字符串形式
     * @param format
     *            转化的格式
     * @return Date类型
     */
    public static Date str2Date(String source, String format) {
        try {
            DateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(source);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("转化失败");
        }
    }

    /**
     * 根据日期 获得当月的最初时间 yyyy-mm-00 00:00:00
     * 
     * @param date
     * @return Date
     */
    public Date getMonthStartDate(Date date) {
        // 当前日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 根据当前日历 ，构造月初时间
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        // java.sql.Date startSqlDate = new
        // java.sql.Date(startCalendar.getTimeInMillis());
        // System.out.println("startCalendar"+startSqlDate);
        return startCalendar.getTime();
    }

    /**
     * 根据日期 获得当月的最后时间 yyyy-mm-dd 23:59:59
     * 
     * @param date
     * @return Date
     */
    public Date getMonthEndDate(Date date) {
        // 当前日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 根据当前日历 ，构造月末时间 yyyy-mm-dd 23:59:59
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1, 00, 00, 00);
        endCalendar.add(Calendar.SECOND, -1);

        return endCalendar.getTime();
    }

    /**
     * 根据日期 获得当天的最初时间 yyyy-mm-dd 00:00:00
     * 
     * @param date
     * @return Date
     */
    public Date getDayStartDate(Date date) {
        // 获取日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 根据当前日历 ，构造月初时间
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0,
                0);
        // java.sql.Date startSqlDate = new
        // java.sql.Date(startCalendar.getTimeInMillis());
        // System.out.println("startCalendar"+startSqlDate);
        return startCalendar.getTime();
    }

    /**
     * 根据日期 获得当天的最后时间 yyyy-mm-dd 59:59:59
     * 
     * @param date
     * @return Date
     */
    public Date getDayEndDate(Date date) {
        // 获取日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 根据当前日历 ，构造月初时间
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23,
                59, 59);
        // java.sql.Date startSqlDate = new
        // java.sql.Date(startCalendar.getTimeInMillis());
        // System.out.println("startCalendar"+startSqlDate);
        return startCalendar.getTime();
    }

    /**
     * 将java.util.date日期 转换成java.sql.date的日期
     * 
     * @param date
     * @return java.sql.Date
     */
    public java.sql.Date reverseDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
        return sqlDate;
    }

    /**
     * 将当月所有天 以yyyy-MM-dd 格式 存入 list 集合 [ 2012-01-01, 2012-01-02, ... 2012-01-31
     * ]
     * 
     * @param date
     * @return
     */
    public List<String> getDateList(Date date) {
        Date startDate = getMonthStartDate(date);
        Date endDate = getMonthEndDate(startDate);

        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);
        System.out.println(startCalendar.getTime());
        System.out.println(endCalendar.getTime());

        // 获得年份
        int year = calendar.get(Calendar.YEAR);
        // 获得月份
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("月份：" + month);
        // 获取最后一天天数
        int lastDay = endCalendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("最后一天：" + lastDay);
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= lastDay; i++) {
            String str = year + (month < 10 ? "-0" : "-") + month + (i < 10 ? "-0" : "-") + i;
            list.add(str);
        }
        return list;
    }

    public static String toFriendlyString(Calendar c) {
        if (c != null) {
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss.SSS");
            return df.format(c.getTime());
        }
        return null;
    }

    @Test
    public void test() {
        String source = "2012-03-27 13:21:59";
        Date date = str2Date(source, "yyyy-MM-dd HH:mm:ss");
        /*
         * System.out.println(date);
         * 
         * Date dayStartDate = getDayStartDate(date); Date dayEndDate =
         * getDayEndDate(date); System.out.println(date2String(dayStartDate,
         * "yyyy-MM-dd HH:mm:ss")); System.out.println(date2String(dayEndDate,
         * "yyyy-MM-dd HH:mm:ss"));
         * 
         * Date startDate = getMonthStartDate(date); Date endDate =
         * getMonthEndDate(startDate);
         * 
         * String str1 = date2String(startDate,"yyyy-MM-dd HH:mm:ss"); String
         * str2 = date2String(endDate,"yyyy-MM-dd HH:mm:ss");
         * System.out.println(str1); System.out.println(str2);
         */
        System.out.println(date2String(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(date2String(new Date(), "EEE, M d HH:mm:ss z yyyy"));
        // List<String> dateList = getDateList(date);
        // System.out.println(dateList);
    }
    // "EEE, MMM d HH:mm:ss z yyyy'

    // Sun Jun 29 23:59:59 CST 2014
    /*
     * G：年代标识，表示是公元前还是公元后 y：年份 M：月份 d：日 h：小时，从1到12，分上下午 H：小时，从0到23 m：分钟 s：秒 S：毫秒
     * E：一周中的第几天，对应星期几，第一天为星期日，于此类推 z：时区 D：一年中的第几天 F：这一天所对应的星期几在该月中是第几次出现
     * w：一年中的第几个星期 W：一个月中的第几个星期 a：上午/下午标识 k：小时，从1到24 K：小时，从0到11，区分上下午
     */
}
