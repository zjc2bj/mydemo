package cn.zjc.demo.tycatch;

public class FinallyDemo {

    public static void main(String[] args) {
        String test = test();
        System.out.println(test);
    }

    public static String test() {
        String aa = "aaa";
        try {
            System.out.println("11111111");
            return aa;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally..............");
            aa += "1111";
        }
        return "";
    }
}
