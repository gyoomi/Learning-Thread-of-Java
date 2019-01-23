/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.instancemethod;

/**
 * run和start的区别
 *
 * @author Leon
 * @version 2019/1/23 15:22
 */
public class ThreadMethod01 {

    public static void main(String[] args) throws Exception {
        Thread01 t01 = new Thread01();
        t01.start();
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("thread = " + Thread.currentThread().getName());
        }
    }
}

class Thread01 extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.println("thread = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
