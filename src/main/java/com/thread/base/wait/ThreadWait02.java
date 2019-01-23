package com.thread.base.wait;

/**
 *
 * interrupt()打断wait()
 *
 *  1：interrupt()方法的作用不是中断线程，而是在线程阻塞的时候给线程一个中断标识，表示该线程中断。wait()就是"阻塞的一种场景"。
 *
 * @author Leon
 * @version 2019-01-23 20:47
 */
public class ThreadWait02 {

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread02_0 t01 = new Thread02_0(lock);
        t01.start();
        Thread.sleep(2000);
        t01.interrupt();
    }
}

class Thread02_0 extends Thread {
    private Object lock;

    public Thread02_0(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("Thread02_0 run....");
                lock.wait();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread02_0 wait is interrupted");
            e.printStackTrace();
        }
    }
}
