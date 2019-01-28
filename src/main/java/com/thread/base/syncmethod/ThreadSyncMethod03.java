/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.syncmethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 3：将任意对象作为对象监视器
 *    1)Java还支持对"任意对象"作为对象监视器来实现同步的功能。任意对象"大多数是实例变量及方法的参数
 *    2）
 *      1、synchronized同步方法
 *          （1）对其他synchronized同步方法或synchronized(this)同步代码块呈阻塞状态
 *          （2）同一时间只有一个线程可以执行synchronized同步方法中的代码
 *      2、synchronized同步代码块
 *          （1）对其他synchronized同步方法或synchronized(this)同步代码块呈阻塞状态
 *          （2）同一时间只有一个线程可以执行synchronized(this)同步代码块中的代码
 *
 * @author Leon
 * @version 2019/1/28 18:21
 */
public class ThreadSyncMethod03 {

    public static void main(String[] args) throws Exception {

    }
}


class ThreadDomain03 {

    private List<String> list = new ArrayList<>();

    public ThreadDomain03() {

    }
}