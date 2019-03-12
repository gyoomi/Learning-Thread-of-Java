/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.callable;

import java.util.concurrent.*;

/**
 * FutureTask 示例
 *
 * @author Leon
 * @version 2019/3/12 10:17
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        MyCallable m01 = new MyCallable(1000);
        MyCallable m02 = new MyCallable(2000);
        FutureTask<String> task01 = new FutureTask<>(m01);
        FutureTask<String> task02 = new FutureTask<>(m02);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task01);
        executorService.execute(task02);
        for (;;) {
            try {
                if (task01.isDone() && task02.isDone()) {
                    System.out.println("All is done!");
                    return;
                }
                if (!task01.isDone()) {
                    System.out.println("Task01 output = " + task01.get() + "  done over.");
                }

                System.out.println("Waiting for task02 to complete");
                String s = task02.get(200L, TimeUnit.MILLISECONDS);
                if (null != s) {
                    System.out.println("Task02 output = " + s);
                }
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            } catch (TimeoutException e) {
            }
        }
    }
}
