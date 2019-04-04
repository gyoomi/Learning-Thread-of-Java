/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.countdownlatch;

/**
 * 主次线程之间的结束顺序关系
 *     1. Main线程是个非守护线程，不能设置成守护线程。
 *     2. Main线程结束，其他线程（指的是用户线程）一样可以正常运行。
 *     3. Main线程结束，其他线程也可以立刻结束，当且仅当这些子线程都是守护线程。
 *
 * @author Leon
 * @version 2019/4/4 9:18
 */
public class CountDownDemo02 {

    public static void main(String[] args) throws Exception {
        Runnable r = () -> {
            try {
                System.out.println("step 1...");
                Thread.sleep(500);
                System.out.println("step 2...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.start();

        System.out.println("main over");
    }
}
