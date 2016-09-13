package cn.zjc.demo;

import org.junit.Assert;
import org.junit.Test;

public class IntegerDemo {

    public boolean validate(Integer value) {
        boolean flag = value != null && value == 1;
        return flag;
        // return false;
    }

    public void testConvers() {
        Integer inter = null;
        int i = inter;
        System.out.println(i);
    }

    @Test
    public void testInt() {
        int i = 'a';
        int i2 = 'A';
        int i3 = ('a' - 'A');
        System.out.println(i);
        System.out.println(i2);
        System.out.println(i3);
    }

    public void testConvers2() {
        Integer inter = new Integer(1);
        int i = 1;
        Assert.assertTrue(inter == i);
    }

    public void testEqual() {

        Integer i1 = 123;
        Integer i2 = 123;

        System.out.println(i1 == i2);

        Integer i3 = 168;
        Integer i4 = 168;

        System.out.println(i3 == i4);

        Integer i5 = 127;
        Integer i6 = 127;

        System.out.println(i5 == i6);

        Integer i7 = 128;
        Integer i8 = 128;

        System.out.println(i7 == i8);
    }

    @Test
    public void testEqual2() {

        Integer i1 = 123;
        Integer i2 = 123;

        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

        Integer i3 = 168;
        Integer i4 = 168;
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));

    }

    public static void main(String[] args) {
        Integer i = null;
        System.out.println(i + "1111");
        System.out.println(String.valueOf(null));
    }
}
