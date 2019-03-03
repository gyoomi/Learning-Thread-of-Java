## Java并发中的工具类之Semaphore
### 一、概述
1. 作用

   Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以
   保证合理的使用公共资源。
2. 理解

   从字面上很难理解Semaphore所表达的含义，只能把它比作是控制流
   量的红绿灯。比如××马路要限制流量，只允许同时有一百辆车在这条路上行使，其他的都必须
   在路口等待，所以前一百辆车会看到绿灯，可以开进这条马路，后面的车会看到红灯，不能驶
   入××马路，但是如果前一百辆中有5辆车已经离开了××马路，那么后面就允许有5辆车驶入马
   路，这个例子里说的车就是线程，驶入马路就表示线程在执行，离开马路就表示线程执行完
   成，看见红灯就表示线程被阻塞，不能执行。
### 二、应用场景
1. 应用场景

   Semaphore可以用于做流量控制，特别是公用资源有限的应用场景，比如数据库连接。假
   如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程
   并发地读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，这
   时我们必须控制只有10个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连
   接。这个时候，就可以使用Semaphore来做流量控制。代码如下：
2. 示例代码

   ```
   public class SemaphoreDemo {
   
       public static int THREAD_COUNT = 30;
   
       public static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
   
       public static Semaphore sp = new Semaphore(10);
   
       public static void main(String[] args) throws Exception {
           for (int i = 0; i < THREAD_COUNT; i++) {
               pool.execute(() -> {
                   try {
                       sp.acquire();
                       System.out.println("save data...");
                       Thread.sleep(1000);
                       sp.release();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               });
           }
           pool.shutdown();
           System.out.println("main over........");
       }
   }
   
   ```
3. 运行结果

   ```
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   main over........
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...
   save data...

   ```

   很明显的两次停顿，三次打印后执行完毕。所以达到了

   在代码中，虽然有30个线程在执行，但是只允许10个并发执行。Semaphore的构造方法
   Semaphore（int permits）接受一个整型的数字，表示可用的许可证数量。Semaphore（10）表示允
   许10个线程获取许可证，也就是最大并发数是10。Semaphore的用法也很简单，首先线程使用
   Semaphore的acquire()方法获取一个许可证，使用完之后调用release()方法归还许可证。还可以
   用tryAcquire()方法尝试获取许可证。
### 三、其他
1. Semaphore分为单值和多值两种
   1. 单值的Semaphore管理的信号量只有1个，该信号量只能被1个，只能被一个线程所获得，意味着并发的代码只能被一个线程运行，这就相当于是一个互斥锁了
   2. 多值的Semaphore管理的信号量多余1个，主要用于控制并发数
2. 其他方法
   - intavailablePermits()：返回此信号量中当前可用的许可证数。
   - intgetQueueLength()：返回正在等待获取许可证的线程数。
   - booleanhasQueuedThreads()：是否有线程正在等待获取许可证。
   - void reducePermits（int reduction）：减少reduction个许可证，是个protected方法。
   - Collection getQueuedThreads()：返回所有等待获取许可证的线程集合，是个protected方法
3. Semaphore可以指定公平锁还是非公平锁
4. acquire方法和release方法是可以有参数的，表示获取/返还的信号量个数
### 四、小结
Semaphore是非常有用的一个组件，它相当于是一个并发控制器，是用于管理信号量的。
构造的时候传入可供管理的信号量的数值，这个数值就是控制并发数量的，
我们需要控制并发的代码，执行前先通过acquire方法获取信号，执行后通过release归还信号 。
每次acquire返回成功后，Semaphore可用的信号量就会减少一个，如果没有可用的信号，
acquire调用就会阻塞，等待有release调用释放信号后，acquire才会得到信号并返回。