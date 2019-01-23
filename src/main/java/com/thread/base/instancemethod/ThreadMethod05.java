/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.instancemethod;

/**
 * interrupt()
 *   这是一个有点误导性的名字，实际上Thread类的interrupt()方法无法中断线程.
 *
 *   也就是说，尽管调用了interrupt()方法，但是线程并没有停止。interrupt()方法的作用实际上是：
 *             在线程受到阻塞时抛出一个中断信号，这样线程就得以退出阻塞状态。
 *             换句话说，没有被阻塞的线程，调用interrupt()方法是不起作用的。
 *
 * @author Leon
 * @version 2019/1/23 17:26
 */
public class ThreadMethod05 {

    public static void main(String[] args) throws Exception {
        Thread05_00 t01 = new Thread05_00();
        t01.start();
        Thread.sleep(400);
        t01.interrupt();
        System.out.println("interrupt occur");
    }
}

class Thread05_00 extends Thread {

    @Override
    public void run() {
        for (int j = 0; j < 50000; j++) {
            System.out.println(j);
        }
    }
}
