package com.thread.base.wait;

/**
 * wait()和notify()/notifyAll()
 *
 *  1: wait()的作用是使当前执行代码的线程进行等待，将当前线程置入"预执行队列"中，并且wait()所在的代码处停止执行，直到接到通知或被中断。
 *     在调用wait()之前，线程必须获得该对象的锁，因此只能在同步方法/同步代码块中调用wait()方法。
 *  2: notify()的作用是，如果有多个线程等待，那么线程规划器随机挑选出一个wait的线程，对其发出通知notify()，并使它等待获取该对象的对象锁。
 *     注意"等待获取该对象的对象锁"，
 *     这意味着，即使收到了通知，wait的线程也不会马上获取对象锁，必须等待notify()方法的线程释放锁才可以。
 *     和wait()一样，notify()也要在同步方法/同步代码块中调用。
 *
 *     总结一句话：wait()使线程停止运行，notify()使停止运行的线程继续运行。
 *
 *
 *     **********************************************************************************************
 *
 * wait()释放锁以及notify()不释放锁
 *  1.wait()会立即释放锁
 *  2.notify()则会等会所在线程的代码执行完毕之后释放锁
 *
 *     **********************************************************************************************
 *
 * interrupt()打断wait()
 *
 *  1：interrupt()方法的作用不是中断线程，而是在线程阻塞的时候给线程一个中断标识，表示该线程中断。wait()就是"阻塞的一种场景"。
 *
 *
 *
 *
 *
 * @author Leon
 * @version 2019-01-23 20:47
 */
public class ThreadWait01 {

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread01_0 t01 = new Thread01_0(lock);
        Thread01_1 t02 = new Thread01_1(lock);
        t01.start();
        Thread.sleep(3000);
        t02.start();
    }
}

class Thread01_0 extends Thread {

    private Object lock;

    public Thread01_0 (Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("thread01-0 wait start : time = " + System.currentTimeMillis());
                lock.wait();
                System.out.println("thread01-0 wait end: time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread01_1 extends Thread {

    private Object lock;

    public Thread01_1 (Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("thread01-1 notify start : time = " + System.currentTimeMillis());
            lock.notify();
            System.out.println("thread01-1 notify end: time = " + System.currentTimeMillis());
        }
    }
}
