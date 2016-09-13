package cn.zjc.demo.Thread.intter;

public class CH_05 {

    // 声明两个变量
    public static boolean isRunning1 = true;

    public static boolean isRunning2 = true;

    public static void main(String[] args) {
        ThreadTask1 thread1 = new ThreadTask1();
        ThreadTask2 thread2 = new ThreadTask2();
        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();
        //
        while (true) {
            // System.out.println("main...");
            if (!isRunning1 && !isRunning2) {
                break;
            }
        }
        if (thread2.interrupted()) {
            System.out.println("当前第二任务已终止");
        }
    }
}
