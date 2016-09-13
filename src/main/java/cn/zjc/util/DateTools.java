package cn.zjc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import org.junit.Test;

/**
 * 
 * @author zhujunchao
 * @see Calendar
 * @see DateFormat
 */
public class DateTools {

    /**
     * 获取打印日志的时间字符串
     * 
     * @return String exmple: [ 2014-03-14 08:08:08 ]
     */
    public static String getCurrentDateStrForLog() {
        return "[ " + DateTools.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + " ]  ";
    }

    /**
     * 将日期按照一定得格式 转化为字符串
     * 
     * @param date
     *            日期
     * @param format
     *            转化的格式
     * @return String
     */
    public static String date2String(Date date, String format) {
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
     * 根据起始时间(string) 时间间隔(min) 获取结束时间
     * 
     * @param startTime
     *            起始时间
     * @param intervalTime
     *            时间间隔
     * @return 结束时间
     */
    public static String getStrDate(String startTime, int intervalTime) {
        Date statDate = DateTools.str2Date(startTime, "HH:mm:ss");
        Calendar statCalendar = Calendar.getInstance();
        statCalendar.setTime(statDate);
        statCalendar.add(Calendar.MINUTE, intervalTime);
        String endTime = DateTools.date2String(statCalendar.getTime(), "HH:mm:ss");
        return endTime;
    }

    /**
     * 比较两个时间点(00:00:00)
     * 
     * @param sdate1
     * @param sdate2
     * @return 大于 return >0 等于 return 0 小于 return <0
     */
    public static int compare(String sdate1, String sdate2) {
        String[] arrdate1 = sdate1.split("\\:");
        String[] arrdate2 = sdate2.split("\\:");
        if (arrdate1.length != arrdate2.length || arrdate1.length < 2 || arrdate2.length < 2) {
            throw new RuntimeException("参数格式不一致");
        }
        // 比较时间大小
        for (int i = 0; i < arrdate1.length; i++) {
            if (arrdate1[i].length() != arrdate2[i].length()) {
                throw new RuntimeException("参数格式不一致");
            }
            int temp1 = Integer.parseInt(arrdate1[i].trim());
            int temp2 = Integer.parseInt(arrdate2[i].trim());
            if (temp1 != temp2)
                return temp1 - temp2;
        }
        return 0;
    }

    /**
     * 根据日期 获得当月的最初时间 yyyy-mm-dd 00:00:00
     * 
     * @param date
     * @return Date
     */
    public static Date getMonthStartDate(Date date) {
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
    public static Date getMonthEndDate(Date date) {
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
    public static Date getDayStartDate(Date date) {
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
    public static Date getDayEndDate(Date date) {
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
     * 根据日期 获得前一天的时间
     * 
     * @param date
     * @return Date
     */
    public static Date getBeforeDay(Date date) {
        // 获取日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }

    /**
     * 将java.util.date日期 转换成java.sql.date的日期
     * 
     * @param date
     * @return java.sql.Date
     */
    public static java.sql.Date reverseDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
        return sqlDate;
    }

    /**
     * 将当月所有天 以yyyy-MM-dd 格式 存入 list 集合 [ 2012-01-01,2012-01-02, ... ,2012-01-31
     * ]
     * 
     * @param date
     * @return
     */
    public static List<String> getDateList(Date date) {
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

    // 将一天 每隔一定的时间段(分钟) 封装到list
    public static List<String> getListByTime(String statDate, int intervalTime) {
        Date date = str2Date(statDate, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return null;
    }

    /**
     * 将一天每隔15分钟 封装到list集合 [00:05:00,00:20:00,...,23:50:00]
     * 
     * @return
     */
    public static List<String> getListByTime1() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            list.add(i < 10 ? "0" + i + ":05:00" : i + ":05:00");
            list.add(i < 10 ? "0" + i + ":20:00" : i + ":20:00");
            list.add(i < 10 ? "0" + i + ":35:00" : i + ":35:00");
            list.add(i < 10 ? "0" + i + ":50:00" : i + ":50:00");
        }
        return list;
    }

    /**
     * 将 一天每隔10分钟 封装到list集合 [00:00:00,00:10:00,...,23:50:00]
     * 
     * @return
     */
    public static List<String> getListByTime2() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            list.add(i < 10 ? "0" + i + ":00:00" : i + ":00:00");
            list.add(i < 10 ? "0" + i + ":10:00" : i + ":10:00");
            list.add(i < 10 ? "0" + i + ":20:00" : i + ":20:00");
            list.add(i < 10 ? "0" + i + ":30:00" : i + ":30:00");
            list.add(i < 10 ? "0" + i + ":40:00" : i + ":40:00");
            list.add(i < 10 ? "0" + i + ":50:00" : i + ":50:00");
        }
        return list;
    }

    /**
     * 将一天每隔5分钟 封装到list集合 [00:00:00,00:10:00,...,23:50:00]
     * 
     * @return
     */
    public static List<String> getListByTime3() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            list.add(i < 10 ? "0" + i + ":00:00" : i + ":00:00");
            list.add(i < 10 ? "0" + i + ":05:00" : i + ":05:00");
            list.add(i < 10 ? "0" + i + ":10:00" : i + ":10:00");
            list.add(i < 10 ? "0" + i + ":15:00" : i + ":15:00");
            list.add(i < 10 ? "0" + i + ":20:00" : i + ":20:00");
            list.add(i < 10 ? "0" + i + ":25:00" : i + ":25:00");
            list.add(i < 10 ? "0" + i + ":30:00" : i + ":30:00");
            list.add(i < 10 ? "0" + i + ":35:00" : i + ":35:00");
            list.add(i < 10 ? "0" + i + ":40:00" : i + ":40:00");
            list.add(i < 10 ? "0" + i + ":45:00" : i + ":45:00");
            list.add(i < 10 ? "0" + i + ":50:00" : i + ":50:00");
            list.add(i < 10 ? "0" + i + ":55:00" : i + ":55:00");
        }
        return list;
    }

    /*
     * @Test public void test() throws ParseException{
     * 
     * File file = new File("c:/task.sql"); String parent = file.getParent();
     * System.out.println(parent);
     * 
     * 
     * 
     * String source = "13:21:59"; Date date = str2Date(source, "HH:mm:ss");
     * System.out.println(date);
     * 
     * int compare = compare("00:00:01","00:00:02");
     * System.out.println(compare);
     * 
     * 
     * //Date dayStartDate = getDayStartDate(date); //Date dayEndDate =
     * getDayEndDate(date); //System.out.println(date2String(dayStartDate,
     * "yyyy-MM-dd HH:mm:ss")); System.out.println(date2String(dayEndDate,
     * "yyyy-MM-dd HH:mm:ss"));
     * 
     * Date startDate = getMonthStartDate(date); Date endDate =
     * getMonthEndDate(startDate);
     * 
     * String str1 = date2String(startDate,"yyyy-MM-dd HH:mm:ss"); String str2 =
     * date2String(endDate,"yyyy-MM-dd HH:mm:ss"); System.out.println(str1);
     * System.out.println(str2);
     * 
     * List<String> dateList = getDateList(date); System.out.println(dateList);
     * DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); Date date2 =
     * format.parse(source);
     * 
     * 
     * //当前日历 Calendar calendar = Calendar.getInstance();
     * calendar.setTime(date2);
     * 
     * 
     * 
     * //当月最早时间yyyy-MM-dd 00:00:00 Date startMonthDate =
     * getMonthStartDate(date2); java.sql.Date startDate =
     * reverseDate(startMonthDate);
     * 
     * 
     * 
     * Calendar startCal = Calendar.getInstance();
     * startCal.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
     * 1); startCal.set(calendar.get(Calendar.YEAR),
     * calendar.get(Calendar.MONTH), 1, 0, 0, 0); java.sql.Date startDate = new
     * java.sql.Date(startCal.getTimeInMillis());
     * 
     * 
     * 
     * //当月最晚时间 Date endMonthDate = getMonthEndDate(date2); java.sql.Date
     * endDate = reverseDate(endMonthDate);
     * 
     * 
     * 
     * Calendar endCal = Calendar.getInstance();
     * endCal.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,
     * 1, 0, 0, 0); endCal.add(Calendar.SECOND, -1); java.sql.Date endDate = new
     * java.sql.Date(endCal.getTimeInMillis());
     * 
     * 
     * 
     * //获得年份 int year = calendar.get(Calendar.YEAR); //获得月份 int month =
     * calendar.get(Calendar.MONTH)+1; //获取最后一天天数 int lastDay =
     * endCal.get(Calendar.DAY_OF_MONTH);
     * 
     * List<String> list = new ArrayList<String>(); for(int i=1;i<=lastDay;i++){
     * String str = year+(month<10?"-0":"-")+month+(i<10?"-0":"-")+i;
     * list.add(str); }
     * 
     * 
     * 
     * //将当月所有天的值 封装到list集合[2012-01-01,2012-01-02,...,2012-01-31] List<String>
     * list = getDateList(date2); System.out.println(list);
     * 
     * 
     * Date date = str2Date("2011-12-11 00:10:11", "yyyy-MM-dd HH:mm:ss");
     * System.out.println(date); }
     */
    public static void main(String[] args) {
        /*
         * Date day = getBeforeDay(new Date()); System.out.println(day);
         */
        // yyyy-MM-dd kk:mm:ss
        String source = "2014-02-19 00:21:59";
        Date date = str2Date(source, "yyyy-MM-dd HH:mm:ss");
        System.out.println(date2String(date, "yyyy-MM-dd HH:mm:ss"));
    }
}
