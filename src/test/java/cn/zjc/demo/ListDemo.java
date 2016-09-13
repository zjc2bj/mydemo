package cn.zjc.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListDemo {
    public static void main(String[] args) {
        // testRemove();

        testEmpt();
        // testRemove2();
    }

    private static void testRemove2() {
        Set<Set<String>> treeSet = new HashSet<Set<String>>();
        Set<String> set1 = new HashSet<String>();
        set1.add("1111");
        set1.add("2222");
        set1.add("3333");
        set1.add("4444");
        set1.add("5555");
        Set<String> set2 = new HashSet<String>();
        set2.add("aaaa");
        set2.add("bbbb");
        set2.add("cccc");
        set2.add("dddd");
        set2.add("eeee");

        treeSet.add(set1);
        treeSet.add(set2);
        System.out.println(treeSet);
        Iterator<Set<String>> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Set<String> next = iterator.next();
            Iterator<String> iterator2 = next.iterator();
            while (iterator2.hasNext()) {
                String next2 = iterator2.next();
                if (next2.equals("3333")) {
                    iterator2.remove();
                }
            }

        }

        for (Iterator<Set<String>> iterator2 = treeSet.iterator(); iterator2.hasNext();) {
            Set<String> next = iterator2.next();
            for (Iterator<String> iterator3 = next.iterator(); iterator3.hasNext();) {
                String next3 = iterator3.next();
                if (next3.equals("4444")) {
                    iterator3.remove();
                }
            }
        }
        System.out.println(treeSet);
    }

    private static void testEmpt() {
        List<String> list = new ArrayList<String>(3);
        list.add(null);
        list.add(null);
        list.add(null);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        //
        Set<String> set = new HashSet<String>();
        set.add(null);
        set.add(null);
        System.out.println(set.size());
        System.out.println(set.isEmpty());

        // Set<String> treeSet = new TreeSet<String>();
        // // treeSet.add("1111");
        // treeSet.add(null);
        // // treeSet.add(null);
        // System.out.println(treeSet.size());
        // System.out.println(treeSet.isEmpty());
        //
        // Iterator<String> iterator = treeSet.iterator();
        // while (iterator.hasNext()) {
        // System.out.println("aaa:" + iterator.next());
        // }

    }

    private static void testRemove() {
        List<String> rules = new ArrayList<String>();
        rules.add("1111");
        rules.add("2222");
        rules.add("3333");
        rules.add("4444");
        rules.add("5555");

        // 除外城市
        for (int i = rules.size() - 1; i >= 0; i--) {
            System.out.println("删除：" + rules.get(i));
            if (i == 3 || i == 1) {
                rules.remove(i);
            }
        }
        System.out.println("list = " + rules);
    }

}
