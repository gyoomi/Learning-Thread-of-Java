/**
 * Copyright © 2019, LeonKeh
 * <p>
 * All Rights Reserved.
 */

package com.thread.base.cyclicbarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/4 15:21
 */
public class SheetBankWaterCountDemo implements Runnable {

    public CyclicBarrier cb = new CyclicBarrier(4, this);

    public ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    public ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void count() {
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // 计算当前sheet的银流数据，计算代码省略
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    try {
                        // 银流计算完成，插入一个屏障
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        SheetBankWaterCountDemo sheetBankWaterCountDemo = new SheetBankWaterCountDemo();
        sheetBankWaterCountDemo.count();
    }



    @Override
    public void run() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : sheetBankWaterCount.entrySet()) {
            sum += entry.getValue();
        }
        System.out.println("result : " + sum);
    }
}
