package cn.zjc.demo.Thread;

public class Demo {
    public static void main(String[] args) {
        new Thread(new ThreadTest(1)).start();
        while (true) {
            System.out.println("2222222222222222222222");
        }
    }
}
