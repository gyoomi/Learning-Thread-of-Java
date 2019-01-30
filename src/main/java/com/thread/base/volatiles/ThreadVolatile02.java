/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.volatiles;

/**
 * 2. 解决1中的死循环问题
 *    方法：使用volatile关键字。
 *    作用：强制从公共堆栈中获取变量的值，而不是从线程私有的栈中取的变量的值。
 *
 * 3. volatile和synchronized
 *    1)volatile保证了变量的可见性但是并不保证线程的原子性；synchronized保证了原子性，同时也间接地保证了可见性。因为他会将私有内存和公有内存中的变量做同步。
 *    2）多线程访问volatile不会阻塞，而多线程访问synchronized则会阻塞
 *
 * @author Leon
 * @version 2019/1/30 16:37
 */
public class ThreadVolatile02 {

    public static void main(String[] args) throws Exception {
        ThreadDomain02 td = new ThreadDomain02();
        new Thread(td).start();
        Thread.sleep(2000);
        td.setIsContinuePrint(false);
    }
}

class ThreadDomain02 implements Runnable{

    // 重点在这里：volatile
    private volatile boolean isContinuePrint = true;

    public boolean getIsContinuePrint() {
        return isContinuePrint;
    }

    public void setIsContinuePrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
    }

    public void printStringMethod() {
            while (isContinuePrint) {
            }
            System.out.println("死循环结束了");
    }


    @Override
    public void run() {
        printStringMethod();
    }
}
