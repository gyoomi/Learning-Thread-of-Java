package com.thread.base.staticmethod;

/**
 * Thread类中的静态方法
 *
 * 3、yield()
 *    暂停当前执行的线程对象，并执行其他线程。这个暂停是会放弃CPU资源的，并且放弃CPU的时间不确定，有可能刚放弃，就获得CPU资源了，也有可能放弃好一会儿，才会被CPU执行
 *    1)yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权
 *    2)当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行
 *
 * @author Leon
 * @version 2019/1/23 17:26
 */
public class ThreadMethod03 {

    public static void main(String[] args) {
        Thread03_0 t01 = new Thread03_0();
        Thread03_1 t02 = new Thread03_1();
        t01.setPriority(Thread.MAX_PRIORITY);
        t02.setPriority(Thread.MIN_PRIORITY);
        t01.start();
        t02.start();
    }
}

class Thread03_0 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread-0 =" + i);
            Thread.yield();
        }
    }
}

class Thread03_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread-1 =" + i);
            Thread.yield();
        }
    }
}
