/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.instancemethod;

/**
 * isDaeMon、setDaemon(boolean on)
 *
 * Java中有两种线程，一种是用户线程，一种是守护线程。守护线程是一种特殊的线程，它的作用是为其他线程的运行提供便利的服务，
 * 最典型的应用便是GC线程。
 * 如果进程中不存在非守护线程了，那么守护线程自动销毁，因为没有存在的必要，为别人服务，结果服务的对象都没了，当然就销毁了。
 *
 * 注意：关于守护线程，有一个细节注意下，setDaemon(true)必须在线程start()之前
 *
 * @author Leon
 * @version 2019/1/23 17:21
 */
public class ThreadMethod04 {

    public static void main(String[] args) throws Exception {
        Thread04_00 t01 = new Thread04_00();
        t01.setDaemon(true);
        t01.start();
        Thread.sleep(5000);
        System.out.println("main 我结束了");
    }
}

class Thread04_00 extends Thread {
    private int a;

    @Override
    public void run() {
        try {
            while (true) {
                ++a;
                System.out.println("a = " + a);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
