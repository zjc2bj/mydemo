package cn.zjc.demo.Thread;

public class ThreadTest extends Thread {
    private int threadNo;

    public ThreadTest(int threadNo) {
        this.threadNo = threadNo;
    }

    /*
     * public static void main(String[] args) throws Exception { for (int i = 1;
     * i < 10; i++) { new ThreadTest(i).start(); Thread.sleep(1); } }
     */

    @Override
    public synchronized void run() {
        while (true) {
            for (int i = 1; i < 10000; i++) {
                System.out.println(Thread.currentThread().hashCode() + " No." + threadNo + ":" + i);
                // Log1.infoLog("No." + threadNo + ":" + i);
            }
        }
    }

}
