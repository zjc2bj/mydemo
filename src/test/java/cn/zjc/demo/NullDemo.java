package cn.zjc.demo;

import org.junit.Test;

public class NullDemo {
    public static void main(String[] args) {
    }

    public void NullDoubleDemo() {
        System.out.println("--------------");

        Double d1 = null;
        Double d2 = 1.11;

        System.out.println(d1 + d2);
    }

    @Test
    public void NullStringDemo() {
        String str = null;
        String str2 = "1111";
        System.out.println(str2 + str);
        System.out.println(str);
    }

    public void NullIntegerDemo() {

    }
}
