## Java并发中的工具类之CyclicBarrier
### 一、概述
CyclicBarrier的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一
组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会
开门，所有被屏障拦截的线程才会继续运行。
### 二、简介
CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数
量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。示例
代码如下所示。

```
public class CyclicBarrierDemo {

    public static CyclicBarrier cb = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        cb.await();
        System.out.println(2);
    }
    
}
```

因为主线程和子线程的调度是由CPU决定的，两个线程都有可能先执行，所以会产生两种
输出，第一种可能输出如下。
```
1
2
```
第二种可能输出如下

```
2
1
```

如果把new CyclicBarrier(2)修改成new CyclicBarrier(3)，则主线程和子线程会永远等待，
因为没有第三个线程执行await方法，即没有第三个线程到达屏障，所以之前到达屏障的两个
线程都不会继续执行。

CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrier-
Action），用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景，如代码
如下所示。

```
public class CyclicBarrierDemo02 {

    public static CyclicBarrier cb = new CyclicBarrier(2, new A());

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        cb.await();
        System.out.println(2);
    }

    static class A extends Thread {

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
```

运行结果

```
3
1
2
```
或者
```
3
2
1
```
### 三、应用场景
CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景。例如，用一个Excel保
存了用户所有银行流水，每个Sheet保存一个账户近一年的每笔银行流水，现在需要统计用户
的日均银行流水，先用多线程处理每个sheet里的银行流水，都执行完之后，得到每个sheet的日
均银行流水，最后，再用barrierAction用这些线程的计算结果，计算出整个Excel的日均银行流
水，如代码如下。


```
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
```

使用线程池创建4个线程，分别计算每个sheet里的数据，每个sheet计算结果是1，再由
BankWaterService线程汇总4个sheet计算出的结果，输出结果如下。

```
result : 4
```

### 四、CyclicBarrier和CountDownLatch的区别
1. CountDownLatch 一个线程(或者多个)，等待另外N个线程完成某个事情之后才能执行；
   CyclicBarrier N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
2. CountDownLatch 一次性的；CyclicBarrier 可以重复使用
3. CountDownLatch基于AQS；CyclicBarrier基于锁和Condition。本质上都是依赖于volatile和CAS实现的





















