package cn.zjc.demo.Thread.intter;

public class ThreadTask2 extends Thread {

    int descding = 10;

    @SuppressWarnings("static-access")
    @Override
    public void run() {
        while (CH_05.isRunning2) {
            if (descding >= 6) {
                System.out.println("开始执行第二线程任务,进行descding递减任务" + descding);

                descding--;
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                CH_05.isRunning2 = false;
                System.out.println("当asseding等于6时，结束第二线程任务");
                System.out.println(Thread.currentThread() + "中断执行任务");
            }
        }
    }

}
