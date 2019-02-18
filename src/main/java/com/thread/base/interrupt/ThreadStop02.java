package com.thread.base.interrupt;

/**
 * 1.停止线程--暴力停止
 *   示例如下。
 * 2.暴力停止和ThreadDeath异常
 *   调用stop()方法会抛出java.lang.ThreadDeath异常。但是通常情况下，此异常不需要显式捕捉。
 * 3.暴力停止造成的影响
 *   1）stop强制使线程停止工作，可能会使一些清理性的工作得不完成
 *   2）就是造成了对锁定的对象进行了解锁可能造成数据的不同步。
 *
 *
 * @author Leon
 * @version 2019/2/18 23:08
 */
public class ThreadStop02 {

    public static void main(String[] args) throws Exception {
        Thread02 thread02 = new Thread02();
        thread02.start();
        Thread.sleep(8000);
        thread02.stop();
    }
}

class Thread02 extends Thread {

    public int i;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
