package com.thread.base.reentrantlock;

/**
 * 3、ReentrantLock中方法
 *    1）公平锁与非公平锁；ReentrantLock默认的是非公平锁
 *    2）getHoldCount()方法返回的是当前线程调用lock()的次数（因为其可重入性锁）
 *    3）getQueueLength()方法用于获取正等待获取此锁定的线程估计数。注意"估计"两个字，因为此方法遍历内部数据结构的同时，线程的数据可能动态变化
 *    4）isFair()用来获取此锁是否公平锁
 *    5）hasQueuedThread(Thread thread)用来查询指定的线程是否正在等待获取指定的对象监视器
 *    6)hasQueuedThreads()用于查询是否有线程正在等待获取指定的对象监视器
 *    7)isHeldByCurrentThread()表示此对象监视器是否由当前线程保持
 *    8)isLocked()表示此对象监视器是否由任意线程保持
 *    9)tryLock()方法的作用是，在调用try()方法的时候，如果锁没有被另外一个线程持有，那么就返回true，否则返回false
 *      tryLock(long timeout, TimeUnit unit)是tryLock()另一个重要的重载方法，表示如果在指定等待时间内获得了锁，则返回true，否则返回false
 *
 *      使用它可以使用嗅探的功能
 *      注意：tryLock()只探测锁是否，并没有lock()的功能，要获取锁，还得调用lock()方法
 *
 * @author Leon
 * @version 2019/2/9 22:09
 */
public class ReentrantLock02 {


}
