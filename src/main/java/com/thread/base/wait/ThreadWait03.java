package com.thread.base.wait;
/**
 *
 * notifyAll()唤醒所有线程
 *  1: 利用Object对象的notifyAll()方法可以唤醒处于同一监视器下的所有处于wait的线程
 *  2: 唤醒的顺序不重要，因为notifyAll()把处于同一资源下wait的线程全部唤醒，至于唤醒的顺序，就和线程启动的顺序一样，是虚拟机随机的
 *
 * @author Leon
 * @version 2019-01-23 20:47
 */
public class ThreadWait03 {

}
