package com.thread.base.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/3 14:55
 */
public class CountDownLatchDemo {

    public static CountDownLatch cdl = new CountDownLatch(3);

    public static void main(String[] args) throws Exception {
        Runnable r = () -> {
            System.out.println("1 step...");
            cdl.countDown();
            System.out.println("2 step...");
            cdl.countDown();
            System.out.println("3 step...");
            cdl.countDown();
        };
        new Thread(r).start();
        cdl.await();
        System.out.println("4 step end!!!");
    }
}
