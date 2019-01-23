package com.thread.base.staticmethod;

/**
 * Thread类中的静态方法
 *
 * 2、sleep(long millis)
 *    1)作用是在指定的毫秒内让当前"正在执行的线程"休眠（暂停执行）
 *    2)这个"正在执行的线程"是关键，指的是Thread.currentThread()返回的线程
 *    3)sleep代码上下文如果被加锁了，锁依然在，但是CPU资源会让出给其他线程
 *
 * @author Leon
 * @version 2019/1/23 17:26
 */
public class ThreadMethod02 {

    public static void main(String[] args) {
        Thread02_0 t01 = new Thread02_0();
        System.out.println(" main start at " + Thread.currentThread().getName() +  System.currentTimeMillis());
        t01.start();
        System.out.println(" main end at " + Thread.currentThread().getName() +  System.currentTimeMillis());
    }
}

class Thread02_0 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("start at " + Thread.currentThread().getName() +  System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("end at " + Thread.currentThread().getName() + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
