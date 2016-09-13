package cn.zjc.demo.timer2;

import java.util.Timer;
import java.util.TimerTask;

public class LocalThread implements Runnable {
    private Thread thread;

    private final Timer timer = new Timer();

    public LocalThread() {
        this.thread = new Thread(this);
    }

    public void excuteTimmer() {
        // 执行定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 终止远程方法调用线程
                System.out.println("强制终止线程" + thread.getName() + " start。。。。");
                thread.interrupt();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.cancel();
                System.out.println("强制终止线程" + thread.getName() + " end。。。。");
            }
        }, 5000);
    }

    public void excute() {
        thread.start();
        excuteTimmer();
    }

    @Override
    public void run() {
        // 执行remote方法
        try {
            System.out.println(Thread.currentThread().getName());
            String returnValue = new Remote().demo();
            System.out.println(returnValue);
            System.out.println("远程方法执行结束。。。取消计时器");
            timer.cancel();
        } catch (Exception e) {
            System.out.println("remote 运行异常！！！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LocalThread().excute();
        System.out.println("aaaaaaaaaaaaa");
    }
}
