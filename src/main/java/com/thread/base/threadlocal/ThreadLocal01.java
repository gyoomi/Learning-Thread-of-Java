package com.thread.base.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal作用总结：
 *
 *      1、ThreadLocal不是集合，它不存储任何内容，真正存储数据的集合在Thread中。ThreadLocal只是一个工具，一个往各个线程的ThreadLocal.ThreadLocalMap中table的某一位置set一个值的工具而已
 *      2、同步与ThreadLocal是解决多线程中数据访问问题的两种思路，前者是数据共享的思路，后者是数据隔离的思路
 *      3、同步是一种以时间换空间的思想，ThreadLocal是一种空间换时间的思想
 *      4、ThreadLocal既然是与线程相关的，那么对于Java Web来讲，ThreadLocal设置的值只在一次请求中有效，是不是和request很像？因为request里面的内容也只在一次请求有效，对比一下二者的区别：
 *      （1）ThreadLocal只能存一个值，一个Request由于是Map形式的，可以用key-value形式存多个值
 *      （2）ThreadLocal一般用在框架，Request一般用在表示层、Action、Servlet
 *
 * @author Leon
 * @version 2019/2/8 23:29
 */
public class ThreadLocal01 {

    public static void main(String[] args) throws Exception {
        Thread01 t01 = new Thread01("A");
        Thread01 t02 = new Thread01("B");
        Thread01 t03 = new Thread01("C");
        t01.start();
        t02.start();
        t03.start();
    }


}

class Tools {
    public static ThreadLocal<String> t1 = new ThreadLocal<>();
}

class Thread01 extends Thread {

    private static AtomicInteger ai = new AtomicInteger();

    public Thread01(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                Tools.t1.set(ai.addAndGet(1) + "");
                System.out.println("ThreadName = " + this.getName() + " getValue() = " + Tools.t1.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
