/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.sync;

/**
 * 1.多线程线程安全问题
 *   脏读demo
 *   解决：通过synchronized关键字解决
 *
 * @author Leon
 * @version 2019/1/25 9:46
 */
public class ThreadSycn01 {

    public static void main(String[] args) {
        ThreadDomain td = new ThreadDomain();
        Thread01_0 t00 = new Thread01_0(td);
        Thread01_1 t01 = new Thread01_1(td);
        t00.start();
        t01.start();
    }
}

class ThreadDomain extends Thread {
    private int num = 0;

    public synchronized void addNum(String userName) {
        try {
            if ("a".equals(userName)) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            } else if ("b".equals(userName)) {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(userName + " set : num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread01_0 extends Thread {

    private ThreadDomain td;

    public Thread01_0(ThreadDomain td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.addNum("a");
    }
}

class Thread01_1 extends Thread {

    private ThreadDomain td;

    public Thread01_1(ThreadDomain td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.addNum("b");
    }
}