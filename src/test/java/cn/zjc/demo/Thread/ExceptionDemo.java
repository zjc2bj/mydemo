package cn.zjc.demo.Thread;

public class ExceptionDemo {

    public static void main(String[] args) {
        while (true) {
            System.out.println("start........");
            try {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("thread");
                        System.out.println(1 / 0);
                    }
                }).start();
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("exception...");
            }

        }
    }
}
