package com.thread.base.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/3 16:41
 */
public class SemaphoreDemo {

    public static int THREAD_COUNT = 30;

    public static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

    public static Semaphore sp = new Semaphore(10);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < THREAD_COUNT; i++) {
            pool.execute(() -> {
                try {
                    sp.acquire();
                    System.out.println("save data...");
                    Thread.sleep(1000);
                    sp.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
        System.out.println("main over........");
    }
}
