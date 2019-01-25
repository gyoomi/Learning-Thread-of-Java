/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.sync;

/**
 * 3.synchronized 对象锁
 *   1：A线程持有Object对象的Lock锁，B线程可以以异步方式调用Object对象中的非synchronized类型的方法
 *   2：A线程持有Object对象的Lock锁，B线程如果在这时调用Object对象中的synchronized类型的方法则需要等待，也就是同步
 *
 * @author Leon
 * @version 2019/1/25 10:27
 */
public class ThreadSync03 {

    public static void main(String[] args) {

    }
}
