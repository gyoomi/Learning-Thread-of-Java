package com.thread.base.syncstaticmethod;

/**
 * 1. synchronized方法默认锁的对象锁（也就是调用这个方法的类的实例对象）。此作用等于synchronized(this) {}代码块
 * 2. synchronized的静态方法默认锁的是当前类的.java文件对应的那个Class对象。而且全部只可能有一个。所有一个类的所有的静态同步方法
 *    都是同步的。Class对这个类的所有对象实例都是有效的。
 * 3. 以String类型的对象上锁时，注意JVM有字符串常量池的作用的。
 *      String str1 = "a";
 *      String str2 = "a";
 *      synchronized(str1) {}
 *      synchronized(str2) {}
 *    这时看似是两个对象，应该持有两个锁。其实是一个锁。
 *
 * @author Leon
 * @version 2019/1/28 17:48
 */
public class ThreadStatic01 {

    public static void main(String[] args) {
        ThreadDomain00 td = new ThreadDomain00();
        ThreadDomain00 td2 = new ThreadDomain00();
        Thread00 t00 = new Thread00(td);
        Thread01 t01 = new Thread01(td);
        Thread02 t02 = new Thread02(td);
        Thread02 t03 = new Thread02(td2);
//        t01.start();
//        t00.start();
        t02.start();
        t03.start();
    }
}

class ThreadDomain00 {

    public synchronized void methodA() {
        try {
            System.out.println("ThreadDomain00 methodA do start ...");
            Thread.sleep(2000);
            System.out.println("ThreadDomain00 methodA do end ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
        synchronized (this) {
            try {
                System.out.println("ThreadDomain00 methodB do start ...");
                Thread.sleep(1000);
                System.out.println("ThreadDomain00 methodB do end ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void methodC() {
        try {
            System.out.println("ThreadDomain00 methodC do start ...");
            Thread.sleep(2000);
            System.out.println("ThreadDomain00 methodC do end ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread00 extends Thread {

    private ThreadDomain00 td;

    public Thread00(ThreadDomain00 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.methodA();
    }
}

class Thread01 extends Thread {

    private ThreadDomain00 td;

    public Thread01(ThreadDomain00 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.methodB();
    }
}

class Thread02 extends Thread {

    private ThreadDomain00 td;

    public Thread02(ThreadDomain00 td) {
        this.td = td;
    }

    @Override
    public void run() {
        td.methodC();
    }
}
