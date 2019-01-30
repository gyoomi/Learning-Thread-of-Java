package com.thread.base.volatiles;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. 使用原子类进行i++操作
 *
 *    1）原子操作是不能分割的整体，没有其他线程能够中断或检查原子操作中变量。
 *    2）一个原子类型就是原子操作可用的类型，它可以在没有加锁的情况下做到线程安全。
 *
 *   示例如下。
 *
 * @author Leon
 * @version 2019/1/30 16:37
 */
public class ThreadVolatile04 {

    public static void main(String[] args) {
        Thread04 tdService = new Thread04();
        Thread t01 = new Thread(tdService);
        t01.start();
        Thread t02 = new Thread(tdService);
        t02.start();
        Thread t03 = new Thread(tdService);
        t03.start();
        Thread t04 = new Thread(tdService);
        t04.start();
        Thread t05 = new Thread(tdService);
        t05.start();
    }
}

class Thread04 extends Thread {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(count.incrementAndGet());
        }
    }
}
