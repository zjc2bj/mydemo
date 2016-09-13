package cn.zjc.demo.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class MapDemo {
    public static Map<String, String> map = new HashMap<String, String>();
    static {
        map.put("TBL_TRADE_D07", "2013-08-07");
        map.put("TBL_TRADE_D08", "2013-08-08");
        map.put("TBL_TRADE_D15", "2013-08-15");
        // map.put("TBL_TRADE_D17","2013-08-17");
        map.put("TBL_TRADE_D22", "2013-08-22");
        map.put("TBL_TRADE_D23", "2013-08-23");
        // map.put("TBL_TRADE_D26","2013-08-26");
        // map.put("TBL_TRADE_D27","2013-08-27");
        map.put("TBL_TRADE_D28", "2013-08-28");
        // map.put("TBL_TRADE_D29","2013-08-29");
    }

    // @Test
    public void test1() {
        for (Entry<String, String> entry : map.entrySet()) {
            try {
                String dayName = entry.getKey();
                String dayStr = entry.getValue();
                System.out.println(dayName + "======" + dayStr);
                map.remove(dayName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // @Test
    public static void test2() {
        Set<Entry<String, String>> entrySet = map.entrySet();
        Iterator<Entry<String, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            String dayName = entry.getKey();
            String dayStr = entry.getValue();

            System.out.println(dayName + "======" + dayStr);
            iterator.remove();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Test：" + map);
            return;
        }
        System.out.println("map=" + map);
    }

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            public void run() {
                test2();
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("main：" + map);
    }

    @Test
    public void test4() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");
        List<String> list2 = new ArrayList<String>();
        list2.add("aaaa");
        list2.add("bbbb");
        list2.add("cccc");
        List<String> list3 = new ArrayList<String>();
        list3.add("****");

        map.put("1-1", list1);
        map.put("2-2", list2);
        map.put("3-3", list3);

        System.out.println(map);

        Map<String, List<String>> map2 = new HashMap<String, List<String>>();
        map2.put("a", map.get("1-1"));
        map2.putAll(map);
        System.out.println(map2);
        map.get("1-1").add("4444");
        System.out.println(map2);
    }
}
