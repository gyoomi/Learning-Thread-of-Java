/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.sync;

/**
 * 4、锁的可重入性
 *
 *    对象可以再次获取自己的内部锁。这种锁重入的机制，也支持在父子类继承的环境中
 *
 * @author Leon
 * @version 2019/1/25 10:30
 */
public class ThreadSync04 {

    public static void main(String[] args) throws Exception {
        ThreadDomain4 td = new ThreadDomain4();
        Thread04_0 t01 = new Thread04_0(td);
        t01.start();
    }
}

class ThreadDomain4 {

    public synchronized void methodA() {
        System.out.println("methodA...");
        methodB();
    }

    public synchronized void methodB() {
        System.out.println("methodB...");
        methodC();
    }

    public synchronized void methodC() {
        System.out.println("methodC...");
    }
}

class Thread04_0 extends Thread {

    private ThreadDomain4 td;

    public Thread04_0(ThreadDomain4 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.methodA();
    }
}

