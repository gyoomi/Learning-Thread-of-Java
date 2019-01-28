/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.syncmethod;

/**
 * sync块：
 *      1、当A线程访问对象的synchronized代码块的时候，B线程依然可以访问对象方法中其余非synchronized块的部分，第一部分的执行结果证明了这一点
 *      2、当A线程进入对象的synchronized代码块的时候，B线程如果要访问这段synchronized块，那么访问将会被阻塞，第二部分的执行结果证明了这一点
 *
 *     结论：
 *          从执行效率的角度考虑，有时候我们未必要把整个方法都加上synchronized，
 *          而是可以采取synchronized块的方式，对会引起线程安全问题的那一部分代码进行synchronized就可以了
 * @author Leon
 * @version 2019/1/28 16:38
 */
public class ThreadSyncMethod01 {

    public static void main(String[] args) throws Exception {
        ThreadDomain01 td = new ThreadDomain01();
        Thread01_0 t01 = new Thread01_0(td);
        Thread01_0 t02 = new Thread01_0(td);
        t01.start();
        t02.start();
    }

}

class ThreadDomain01 {

    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("no-sync threadName = " + Thread.currentThread().getName() + " " + i);
        }
        System.out.println();
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("sync threadName = " + Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

class Thread01_0 extends Thread {

    private ThreadDomain01 td;

    public Thread01_0(ThreadDomain01 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.doLongTimeTask();
    }
}
