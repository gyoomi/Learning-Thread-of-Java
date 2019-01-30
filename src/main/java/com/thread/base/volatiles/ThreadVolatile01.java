/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.volatiles;

/**
 * Volatile关键字的作用：使变量在多个线程之间可见。
 *
 * 1. 死循环
 *    示例如下。
 *
 *
 *
 * @author Leon
 * @version 2019/1/30 16:24
 */
public class ThreadVolatile01 {

    public static void main(String[] args) throws Exception {
        ThreadDomain01 td = new ThreadDomain01();
        td.printStringMethod();
        td.setContinuePrint(false);
    }
}

class ThreadDomain01 {

    private boolean isContinuePrint = true;

    public boolean getIsContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        this.isContinuePrint = continuePrint;
    }

    public void printStringMethod() {
        try {
            while (isContinuePrint) {
                System.out.println("Thread = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


