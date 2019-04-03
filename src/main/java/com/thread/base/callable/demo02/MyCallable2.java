/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.callable.demo02;

import java.util.concurrent.Callable;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/12 10:13
 */
public class MyCallable2 implements Callable<String> {

    private long waitTime;

    public MyCallable2(int waitTime) {
        this.waitTime = waitTime;
    }


    @Override
    public String call() throws Exception {
        Thread.sleep(this.waitTime);
        return Thread.currentThread().getName();
    }
}
