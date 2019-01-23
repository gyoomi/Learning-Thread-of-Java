/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.instancemethod;

/**
 * isAlive:
 * 测试线程是否处于活动状态，只要线程启动且没有终止，方法返回的就是true
 *
 * @author Leon
 * @version 2019/1/23 16:15
 */
public class ThreadMethod02 {

    public static void main(String[] args) throws Exception {
        Thread02 t01 = new Thread02();
        System.out.println("begin = " + t01.isAlive());
        t01.start();
        // t01.wait(2000);
        Thread.sleep(1000);
        System.out.println("end = " + t01.isAlive());
    }
}

class Thread02 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run = " + isAlive());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
