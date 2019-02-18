package com.thread.base.threadgroup;

/**
 * 3、线程组自动归属特性
 *        自动归属的意思就是自动归到当前线程组中。
 * 4、获取根线程组
 *        1）根线程组就是系统线程组system
 *        2）在调用其父线程组则会抛出NPE
 * 5、线程组内线程批量停止
 *       示例如下所示。
 *
 *
 * @author Leon
 * @version 2019/2/18 21:48
 */
public class ThreadGroup02 {

    public static void main(String[] args) throws Exception {
        ThreadGroup group = new ThreadGroup("我的线程组");
        for (int i = 0; i < 5; i++) {
            Thread02 thread = new Thread02(group, i + 1 + "");
            thread.start();
        }
        Thread.sleep(5000);
        group.interrupt();
        System.out.println("线程组interrupt了");
    }


}

class Thread02 extends Thread {

    public Thread02(ThreadGroup tg, String name) {
        super(tg, name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName = " + Thread.currentThread().getName() + "开始死循环了");
        while (!this.isInterrupted()) {

        }
        System.out.println("ThreadName = " + Thread.currentThread().getName() + "结束了");
    }
}
