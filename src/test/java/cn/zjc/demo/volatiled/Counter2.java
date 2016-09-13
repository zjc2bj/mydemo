package cn.zjc.demo.volatiled;

public class Counter2 {

    public volatile static int count = 0;

    public static Object obj = new Object();

    public static void inc() {
        // 这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        count = count + 1;
    }

    public static void main(String[] args) throws Exception {
        // 同时启动1000个线程，去进行i++计算，看看实际结果
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1--" + count);
                    Thread.sleep(2000);
                    System.out.println("1--" + count);
                    Counter2.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("2--" + count);
                    Thread.sleep(1000);
                    System.out.println("2--" + count);
                    Counter2.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                    System.out.println(count);
                    Counter2.inc();
                    System.out.println(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}