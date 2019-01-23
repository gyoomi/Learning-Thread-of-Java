/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.instancemethod;

/**
 * getPriority()和setPriority(int newPriority)
 * 这两个方法用于获取和设置线程的优先级，优先级高的CPU得到的CPU资源比较多，设置优先级有助于帮"线程规划器"确定下一次选择哪一个线程优先执行。
 * 换句话说，两个在等待CPU的线程，优先级高的线程越容易被CPU选择执行
 *
 * 1)线程默认优先级为5，如果不手动指定，那么线程优先级具有继承性，比如线程A启动线程B，那么线程B的优先级和线程A的优先级相同
 * 2)CPU会尽量将执行资源让给优先级比较高的线程,但是不是绝对会礼让的。
 *
 * @author Leon
 * @version 2019/1/23 17:07
 */
public class ThreadMethod03 {

    public static void main(String[] args) {
        System.out.println("main priority = " + Thread.currentThread().getPriority());
        Thread03 t01 = new Thread03();
        t01.start();
    }

}

class Thread03 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread03 priority = " + this.getPriority());
    }
}
