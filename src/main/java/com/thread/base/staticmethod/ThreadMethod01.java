package com.thread.base.staticmethod;

/**
 * Thread类中的静态方法
 *
 * Thread类中的静态方法表示操作的线程是"正在执行静态方法所在的代码块的线程"。为什么Thread类中要有静态方法，这样就能对CPU当前正在运行的线程进行操作。
 *
 * 1、currentThread()
 *    1)currentThread()方法返回的是对当前正在执行线程对象的引用
 *    2)线程类的构造方法、静态块是被main线程调用的，而线程类的run()方法才是应用线程自己调用的
 *    3)this.XXX()"和"Thread.currentThread().XXX()"的区别，这个就是最好的例子。必须要清楚的一点就是：当前执行的Thread未必就是Thread本身。
 *
 *
 * @author Leon
 * @version 2019/1/23 17:26
 */
public class ThreadMethod01 {

    public static void main(String[] args) {
        Thread01_0 t01 = new Thread01_0();
        t01.start();
    }
}

class Thread01_0 extends Thread {
    static {
        System.out.println("静态代码块 = " + Thread.currentThread().getName());
    }

    public Thread01_0() {
        System.out.println("构造方法 = " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run = " + Thread.currentThread().getName());
    }
}
