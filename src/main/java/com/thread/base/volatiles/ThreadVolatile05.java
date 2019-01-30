package com.thread.base.volatiles;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 4. 使用原子类并不完全安全
 *    示例如下。
 *
 *    原因：
 *       1）addAndGet（）是原子的。但是方法和方法之间的调用不是原子的。
 *       2）所以最后得到的结果是顺序是乱的，但是打印的结果是对的。
 *    解决：
 *      1）在addCount（）方法上加上synchronized关键字，保证方法之间的同步
 *
 *
 * @author Leon
 * @version 2019/1/30 16:37
 */
public class ThreadVolatile05 {

    public static void main(String[] args) throws Exception {
        Thread05_0[] arr = new  Thread05_0[5];
        ThreadDomain05 td = new ThreadDomain05();
        for (int i = 0; i < 5; i++) {
            arr[i] = new Thread05_0(td);
        }
        for (int i = 0; i < 5; i++) {
            arr[i].start();
        }
        Thread.sleep(1000);
        System.out.println(td.count.get());
    }
}

class ThreadDomain05 {

    public AtomicLong count = new AtomicLong();

    public synchronized void addCount() {
        System.out.println(Thread.currentThread().getName() + "加了100后值是：" + count.addAndGet(100));
        count.addAndGet(1);
    }
}

class Thread05_0 extends Thread {

    private ThreadDomain05 td;

    public Thread05_0(ThreadDomain05 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.addCount();
    }
}
