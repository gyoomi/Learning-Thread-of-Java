/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.sync;

/**
 * 5、异常自动释放锁
 *
 *    1: 当一个线程执行的代码出现异常时，其所持有的锁会自动释放
 *
 * @author Leon
 * @version 2019/1/25 11:32
 */
public class ThreadSync05 {

    public static void main(String[] args) throws Exception {
        ThreadDomain05 td = new ThreadDomain05();
        Thread05_0 td00 = new Thread05_0(td);
        Thread05_0 td01 = new Thread05_0(td);
        td00.start();
        td01.start();
    }
}


class ThreadDomain05 {

    public synchronized void testException() throws Exception {
        System.out.println("Thread " + Thread.currentThread() + " enter....");
        int i = 5000;
        while (true) {
            Thread.sleep(5000);
            int rs = 2 / i;
            i -= 1000;
        }
    }
}

class Thread05_0 extends Thread {

    private ThreadDomain05 td;

    public Thread05_0(ThreadDomain05 td) {
        this.td = td;
    }

    @Override
    public void run(){
        try {
            td.testException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


