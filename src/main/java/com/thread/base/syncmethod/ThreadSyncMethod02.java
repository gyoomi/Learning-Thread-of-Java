/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.syncmethod;

/**
 * 1：两个synchronized块之间具有互斥性
 * 2：synchronized块获得的是一个对象锁，换句话说，synchronized块锁定的是整个对象
 *    如果线程1访问了一个对象方法A的synchronized块，线程2对于同一对象同步方法B的访问应该是会被阻塞的。
 *    因为线程2访问同一对象的同步方法B的时候将会尝试去获取这个对象的对象锁，但这个锁却在线程1这里
 *
 * @author Leon
 * @version 2019/1/28 17:48
 */
public class ThreadSyncMethod02 {

    public static void main(String[] args) throws Exception {
        ThreadDomain02 td = new ThreadDomain02();
        Thread02_0 t00 = new Thread02_0(td);
        Thread02_1 t01 = new Thread02_1(td);
        t01.start();
        t00.start();
    }

}

class ThreadDomain02 {

    public void methodA() {
        try {
            synchronized (this) {
                System.out.println("Thread - A start at " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("Thread - A end at " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
        synchronized (this) {
            System.out.println("Thread - B start at " + System.currentTimeMillis());
            System.out.println("Thread - B end at " + System.currentTimeMillis());
        }
    }
}

class Thread02_0 extends Thread {

    private ThreadDomain02 td;

    public Thread02_0(ThreadDomain02 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.methodA();
    }
}

class Thread02_1 extends Thread {

    private ThreadDomain02 td;

    public Thread02_1(ThreadDomain02 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.methodB();
    }
}