/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/4 14:51
 */
public class CyclicBarrierDemo02 {

    public static CyclicBarrier cb = new CyclicBarrier(2, new A());

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        cb.await();
        System.out.println(2);
    }

    static class A extends Thread {

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
