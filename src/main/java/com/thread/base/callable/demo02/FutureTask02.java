package com.thread.base.callable.demo02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/4/3 23:05
 */
public class FutureTask02 {

    public static void main(String[] args) {
        MyCallable2 callable01 = new MyCallable2(1000);
        MyCallable2 callable02 = new MyCallable2(2000);
        FutureTask<String> task01 = new FutureTask<>(callable01);
        FutureTask<String> task02 = new FutureTask<>(callable02);

        new Thread(task01).start();
        new Thread(task02).start();
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
