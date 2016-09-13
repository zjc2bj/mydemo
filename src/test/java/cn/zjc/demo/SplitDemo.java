package cn.zjc.demo;

public class SplitDemo {
    public static void main(String[] args) {
        String str = "1";
        String[] split = str.split("\\/");
        System.out.println(split.length);

        str = "1,1,,";
        split = str.split("\\,");
        System.out.println(split.length);
    }
}
