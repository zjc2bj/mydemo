package cn.zjc.demo.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/** */
/**
 * 定时回调线程类
 * 
 * @author sitinspring(junglesong@gmail.com)
 * 
 * @date 2007-11-6
 */
public class TimedCallBackThread implements Runnable {
    // 一秒的毫秒数常量
    private final static int ONE_SECOND = 1000;

    // 限制时间,以秒为单位
    private final int waitTime;

    // 已经流逝的时间
    private int passedTime;

    private Timer timer;

    private Thread thread;

    // private MvcTcModel model;

    // private MvcTcView view;

    public TimedCallBackThread(int waitTime) {

        this.waitTime = waitTime;
        this.passedTime = 0;

        // 创建并启动定时器
        timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timeListener();
            }
        });
        timer.start();

        // 创建并启动线程来完成任务
        thread = new Thread(this);
        thread.start();
    }

    private void timeListener() {
        passedTime++;

        // 动态显示状态
        int modSeed = passedTime % 3;
        if (modSeed == 0) {
            // view.getLabel2().setText("响应中");
        } else if (modSeed == 1) {
            // view.getLabel2().setText("响应中..");
        } else if (modSeed == 2) {
            // view.getLabel2().setText("响应中.");
        }

        // 如果流逝时间大于规定时间则中断线程
        if (passedTime > waitTime) {
            passedTime = waitTime;
            thread.interrupt();
        }
    }

    public void run() {
        while (passedTime < waitTime) {
            try {
                Thread.sleep(10000);// 模拟一个耗时相应过程
                timer.stop();// 任务完成,停止Timer

                // view.getLabel2().setText(model.getText2());
            } catch (InterruptedException ex) {
                timer.stop();// 线程中断,停止Timer
                // view.getLabel2().setText("在指定时间内未响应");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return;
        }
    }
}
