package cn.zjc.demo.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.math.RandomUtils;

public class ThreadPoolDemo {

    private static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        test();
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
        // System.out.println(RandomUtils.nextInt(10000));
    }

    public static void test() throws InterruptedException {
        while (true) {
            try {
                List<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < 20; i++) {
                    list.add(i);
                }
                for (final Integer temp : list) {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println(temp + " start...");
                                Thread.sleep(RandomUtils.nextInt(10000));
                                System.out.println(temp + " end...");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(30000);
            }
        }

    }
}
