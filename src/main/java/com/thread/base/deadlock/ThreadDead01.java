package com.thread.base.deadlock;

/**
 * 死锁：
 *     1. 不同线程都在等待不可能释放的锁，从而导致所有任务都无法继续完成
 *     2. Java中死锁是必须避免的。死锁会造成线程假死
 *     3. 一个类可能发生死锁，并不意味着每次都会发生死锁，这只是表示有可能。当死锁出现时，往往是在最糟糕的情况-高负载的情况下
 *     4. 死锁是设计的BUG，问题比较隐晦
 *     5. 避免死锁的方式
 *        1）设计时考虑清楚锁的顺序，尽量减少嵌在的加锁交互数量（尽量不嵌套原则）
 *        2）既然死锁的产生是两个线程无限等待对方持有的锁，那么只要等待时间有个上限不就好了。当然synchronized不具备这个功能。
 *           但是我们可以使用Lock类中的tryLock方法去尝试获取锁，这个方法可以指定一个超时时限，在等待超过该时限之后变回返回一个失败信息
 *
 *
 * @author Leon
 * @version 2019/1/28 17:48
 */
public class ThreadDead01 {

    public static void main(String[] args) {
        Object lock01 = new Object();
        Object lock02 = new Object();
        Thread00 t00 = new Thread00("a", lock01, lock02);
        Thread00 t01 = new Thread00("b", lock01, lock02);
        t00.start();
        t01.start();
    }
}

class Thread00 extends Thread {

    private String user;

    private Object lock01;
    private Object lock02;

    public Thread00(String user, Object lock01, Object lock02) {
        this.user = user;
        this.lock01 = lock01;
        this.lock02 = lock02;
    }

    @Override
    public void run() {
        if ("a".equals(user)) {
            synchronized (lock01) {
                try {
                    System.out.println(user + " start do ...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock02) {
                    System.out.println(user + " start do next ...");
                }
            }
        }
        if ("b".equals(user)) {
            synchronized (lock02) {
                try {
                    System.out.println(user + " start do ...");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock01) {
                    System.out.println(user + " start do next ...");
                }
            }
        }
    }
}
