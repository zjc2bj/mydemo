package cn.zjc.demo;

public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            excute2();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(3333333);
        }
        System.out.println(4444444);
    }

    /*
     * public static int excute() { try { System.out.println(1111111); int i = 1
     * / 0; return i; } catch (Exception e) { e.printStackTrace(); // throw new
     * Exception("不能为零"); } System.out.println(22222222); return 0; }
     */

    public static int excute2() {
        System.out.println(1111111);
        int i = 1;
        excute3();
        return i;
        // System.out.println(22222222);
    }

    public static int excute3() {
        int y = 1 / 0;
        try {
            System.out.println(1111111);
            int i = 1 / 0;
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("aaaaaaaaaaa");
        }
        // System.out.println(22222222);
        // return 0;
    }
}
