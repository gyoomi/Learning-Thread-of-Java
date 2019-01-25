/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.sync;

/**
 * 2.多个对象多个锁
 *
 *   打印顺序和1中不一样。
 *   结论：
 *        1： 关键字synchronized取得的锁都是对象锁，而不是把一段代码或方法（函数）当作锁，哪个线程先执行带synchronized关键字的方法，哪个线程就持有该方法所属对象的锁，
 *        其他线程都只能呈等待状态。但是这有个前提：既然锁叫做对象锁，那么势必和对象相关，所以多个线程访问的必须是同一个对象。
 *        2： 多个线程访问的是多个对象，那么Java虚拟机就会创建多个锁，就像上面的例子一样，创建了两个ThreadDomain13对象，就产生了2个锁。
 *        既然两个线程持有的是不同的锁，自然不会受到"等待释放锁"这一行为的制约，可以分别运行addNum(String userName)中的代码
 *
 * @author Leon
 * @version 2019/1/25 10:10
 */
public class ThreadSync02 {

    public static void main(String[] args) {
        ThreadDomain2 td1 = new ThreadDomain2();
        ThreadDomain2 td2 = new ThreadDomain2();
        Thread02_0 t00 = new Thread02_0(td1);
        Thread02_1 t01 = new Thread02_1(td2);
        t00.start();
        t01.start();
    }
}

class ThreadDomain2 extends Thread {
    private int num = 0;

    public synchronized void addNum(String userName) {
        try {
            if ("a".equals(userName)) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            } else if ("b".equals(userName)) {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(userName + " set : num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread02_0 extends Thread {

    private ThreadDomain2 td;

    public Thread02_0(ThreadDomain2 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.addNum("a");
    }
}

class Thread02_1 extends Thread {

    private ThreadDomain2 td;

    public Thread02_1(ThreadDomain2 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.addNum("b");
    }
}