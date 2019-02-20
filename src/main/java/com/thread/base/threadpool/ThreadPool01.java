package com.thread.base.threadpool;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 21:34
 */
public class ThreadPool01 {

    public static void main(String[] args) throws Exception {
        LinkedList<Integer> integers = new LinkedList<Integer>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20000));
        for (int i = 0; i < 2000; i++) {
            pool.execute(() -> {
                integers.add(new Random().nextInt());
            });
        }
        Thread.sleep(10000);
        // pool.shutdown();
        System.out.println(integers.size());
    }
}
