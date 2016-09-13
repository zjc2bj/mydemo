package cn.zjc.demo.reflect;

public class MethodDemo1 {

    public String userName;

    public MethodDemo2 demo2;

    public void printTest1() {
        System.out.println("MethodDemo1#printTest1......");
    }

    private void printTest2() {
        demo2.test2();
        System.out.println("MethodDemo1#printTest2......");
    }

    public void printTest3() {
        System.out.println("MethodDemo1#printTest3......");
    }
}
