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
 * @version 2019/3/4 13:55
 */
public class CyclicBarrierDemo {

    public static CyclicBarrier cb = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        cb.await();
        System.out.println(2);
    }

}
