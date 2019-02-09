package com.thread.base.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、ReentrantLock优点
 *    1）多路分支通知
 *    2）嗅探锁定
 * 2、注意
 *   1）ReentrantLock也是对象锁，但是和Synchronized不是同一个锁。同时要出现多个对象锁的话需要Condition对象（可以创建多个）
 *   2）在调用await()和signal()时必须先获取对象监视器。（即lock()方法）
 *   3）ReentrantLock必须要手动释放锁（即调用unlock()方法）
 *   4）Condition的await()方法是释放锁的
 *   5）Condition的signal()方法是不释放锁的
 *
 * @author Leon
 * @version 2019/2/9 21:22
 */
public class ReentrantLock01 {

    public static void main(String[] args) throws Exception {
        ThreadDomain01 td = new ThreadDomain01();
        Thread01 t01 = new Thread01(td);
        t01.start();
        Thread.sleep(1000);
        td.signal();
    }
}

class ThreadDomain01 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public ThreadDomain01() {}

    public void await() {
        try {
            lock.lock();
            System.out.println("await 开始时间：" + System.currentTimeMillis());
            condition.await();
            System.out.println("await 结束时间：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal 开始时间：" + System.currentTimeMillis());
            condition.signal();
            System.out.println("signal 结束时间：" + System.currentTimeMillis());
        } finally {
            lock.unlock();
        }
    }

}

class Thread01 extends Thread {

    private ThreadDomain01 td;

    public Thread01(ThreadDomain01 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.await();
    }
}
