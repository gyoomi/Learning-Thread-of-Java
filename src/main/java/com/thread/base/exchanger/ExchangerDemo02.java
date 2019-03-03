package com.thread.base.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/3 23:22
 */
public class ExchangerDemo02 {

    public static Exchanger<String> exchanger = new Exchanger<>();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {

        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    exchanger.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String A = exchanger.exchange("B");
                    System.out.println("A和B的数据是否一致：" + A.equals(B) + "A 录入的是：" + A + " B录入的是：" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.shutdown();
        System.out.println("main over");
    }
}
