package cn.zjc.demo;

public class ForDemo {
    public static void main(String[] args) {
        for (int i = 0; isCheck(i); i++) {
            System.out.println("aaaaaaaa");
        }
    }

    public static boolean isCheck(int i) {
        System.out.println(i);
        if (i < 10)
            return true;
        return false;
    }
}
