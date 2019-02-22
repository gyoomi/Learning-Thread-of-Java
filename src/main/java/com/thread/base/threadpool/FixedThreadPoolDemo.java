/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.threadpool;

import java.util.concurrent.*;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/22 11:08
 */
public class FixedThreadPoolDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
        MyRunnable r1 = new MyRunnable(10, "thr-1");
        MyRunnable r2 = new MyRunnable(5, "thr-2");
        MyRunnable r3 = new MyRunnable(8, "thr-3");
        pool.submit(r1);
        pool.submit(r2);
        pool.submit(r3);

        pool.shutdown();
    }
}

class MyRunnable implements Runnable {

    private int count;
    private String name;

    public MyRunnable(int count, String name) {
        this.count = count;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
        }
    }
}

