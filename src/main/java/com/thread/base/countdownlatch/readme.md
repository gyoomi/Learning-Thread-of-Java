## Java并发中的工具类之CountDownLatch
### 一、概述
1. 作用

   CountDownLatch允许一个或多个线程等待其他线程完成操作。
### 二、使用场景
1. 情景假设

   假如有这样一个需求：我们需要解析一个Excel里多个sheet的数据，此时可以考虑使用多
   线程，每个线程解析一个sheet里的数据，等到所有的sheet都解析完之后，程序需要提示解析完
   成。在这个需求中，要实现主线程等待所有线程完成sheet的解析操作，最简单的做法是使用
   join()方法。但是这里我们先不讨论，而是使用CountDownLatch来实现。

2. 代码实现

   ```
   public class CountDownLatchDemo {
   
       public static CountDownLatch cdl = new CountDownLatch(3);
   
       public static void main(String[] args) throws Exception {
           Runnable r = () -> {
               System.out.println("1 step...");
               cdl.countDown();
               System.out.println("2 step...");
               cdl.countDown();
               System.out.println("3 step...");
               cdl.countDown();
           };
           new Thread(r).start();
           cdl.await();
           System.out.println("4 step end!!!");
       }
   }
   ```

   运行结果

   ```
   1 step...
   2 step...
   3 step...
   4 step end!!!
   ```

   如果不适用CountDownLatch来控制的结果就是

   ```
   4 step end!!!
   1 step...
   2 step...
   3 step...
   ```
3. 说明

   CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完
   成，这里就传入N。

   当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法
   会阻塞当前线程，直到N变成零。由于countDown方法可以用在任何地方，所以这里说的N个
   点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，只需要把这个
   CountDownLatch的引用传递到线程里即可。

   如果有某个解析sheet的线程处理得比较慢，我们不可能让主线程一直等待，所以可以使
   用另外一个带指定时间的await方法——await（long time，TimeUnit unit），这个方法等待特定时
   间后，就会不再阻塞当前线程。join也有类似的方法。
4. 注意

   计数器必须大于等于0，只是等于0时候，计数器就是零，调用await方法时不会
   阻塞当前线程。CountDownLatch不可能重新初始化或者修改CountDownLatch对象的内部计数
   器的值。一个线程调用countDown方法happen-before，另外一个线程调用await方法。
### 三、小结
1. CountDownLatch主要提供的机制是当多个（具体数量等于初始化CountDownLatch时count参数的值）线程都达到了预期状态或
   完成预期工作时触发事件，其他线程可以等待这个事件来触发自己的后续工作。值得注意的是，CountDownLatch是可以唤醒多个等待的线程的
2. 是一种进化版本的等待/通知机制，它可以的实现的是多个工作线程完成任务后通知多个等待线程开始工作，之前的都是一个工作线程完成任务通知
   一个等待线程或者一个工作线程完成任务通知所有等待线程
3. 特别适合这种将一个问题分割成N个部分的场景，所有子部分完成后，通知别的一个/几个线程开始工作。
   比如我要统计C、D、E、F盘的文件，可以开4个线程，分别统计C、D、E、F盘的文件，统计完成把文件信息汇总到另一个/几个线程中进行处理



























