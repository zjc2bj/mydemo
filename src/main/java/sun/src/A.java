package sun.src;

public class A {
    public void method() {
        for (int i = 0; i < 1000000; i++) {
        }
        System.out.println("method1");
    }

    public void method2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
        }
        System.out.println("method1");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
