package cn.zjc.demo.timer;

import java.util.Timer;
import java.util.TimerTask;

public class EggTimer {

    private final Timer timer = new Timer();

    private final int minutes;

    public EggTimer(int minutes) {
        this.minutes = minutes;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            public void run() {
                playSound();
                timer.cancel();
            }

            private void playSound() {
                System.out.println("Your egg is ready!");
            }
        }, minutes * 6 * 1000);// 使用毫秒计数

    }

    public static void main(String[] args) throws InterruptedException {
        /*
         * EggTimer eggTimer = new EggTimer(2); eggTimer.start();
         */
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(111111111);
                System.out.println(Thread.currentThread().getName());
                timer.cancel();
                System.out.println("ok");
            }
        }, 15000);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(222222);
        }
        System.out.println(Thread.currentThread().getName());
        timer.cancel();
    }
}
