package cn.zjc.demo.Thread.intter;

public class ThreadTask1 extends Thread {

    int asseding = 1;

    @SuppressWarnings("static-access")
    @Override
    public void run() {
        while (CH_05.isRunning1) {
            if (asseding <= 3) {
                System.out.println(this.getName() + " ... " + asseding);

                asseding++;
                this.interrupt();
                if (this.interrupted()) {
                    System.out.println("当前第一任务已终止");
                } else {
                    System.out.println("未中断");
                }
            } else {
                CH_05.isRunning1 = false;
                System.out.println("当asseding等于3时，结束第一线程任务");
                System.out.println(Thread.currentThread() + "中断执行任务");
            }
        }
    }
}
