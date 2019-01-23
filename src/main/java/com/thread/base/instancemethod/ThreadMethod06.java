package com.thread.base.instancemethod;

/**
 * join()
 *
 * 1: join()方法的作用是等待线程销毁。
 * 2: join()方法反应的是一个很现实的问题，比如main线程的执行时间是1s，子线程的执行时间是10s，但是主线程依赖子线程执行完的结果，这时怎么办？
 *    可以像生产者/消费者模型一样，搞一个缓冲区，子线程执行完把数据放在缓冲区中，通知main线程，main线程去拿，
 *    这样就不会浪费main线程的时间了。另外一种方法，就是join()了
 *
 *
 * @author Leon
 * @version 2019/1/23 17:26
 */
public class ThreadMethod06 {

    public static void main(String[] args) throws Exception {
        Thread06_0 t01 = new Thread06_0();
        t01.start();
        t01.join();
        System.out.println("我想等thread06 线程结束了再结束！！");
    }
}

class Thread06_0 extends Thread {
    @Override
    public void run() {
        try {
            int secondValue = (int)(Math.random() * 10000);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}