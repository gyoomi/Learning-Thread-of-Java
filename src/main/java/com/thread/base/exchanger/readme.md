## Java并发中的工具类之Exchanger
### 一、概述
1. Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交
   换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。这两个线程通过
   exchange方法交换数据，如果第一个线程先执行exchange()方法，它会一直等待第二个线程也
   执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产
   出来的数据传递给对方。
2. Exchanger用于在两个线程之间进行数据交换，注意也只能在两个线程之间进行数据交换。
   线程会阻塞在Exchanger的exchange方法上，直到另外一个线程也到了同一个Exchanger的
   exchange方法时，二者进行数据交换，然后两个线程继续执行自身相关的代码。
### 二、使用场景
1. Exchanger可以用于遗传算法，遗传算法里需要选出两个人作为交配对象，这时候会交换
   两人的数据，并使用交叉规则得出2个交配结果。Exchanger也可以用于校对工作，比如我们需
   要将纸制银行流水通过人工的方式录入成电子银行流水，为了避免错误，采用AB岗两人进行
   录入，录入到Excel之后，系统需要加载这两个Excel，并对两个Excel数据进行校对，看看是否
   录入一致，代码如下：
2. 代码
   ```
   public class ExchangerDemo02 {
   
       public static Exchanger<String> exchanger = new Exchanger<>();
   
       static ExecutorService pool = Executors.newFixedThreadPool(2);
   
       public static void main(String[] args) throws Exception {
   
           pool.execute(new Runnable() {
               @Override
               public void run() {
                   try {
                       String A = "银行流水A";
                       exchanger.exchange(A);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           });
   
           pool.execute(new Runnable() {
               @Override
               public void run() {
                   try {
                       String B = "银行流水B";
                       String A = exchanger.exchange(B);
                       System.out.println("A和B的数据是否一致：" + A.equals(B) + "A 录入的是：" + A + " B录入的是：" + B);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           });
   
           pool.shutdown();
           System.out.println("main over");
       }
   }
   
   ```
3. 结果
   ```
   main over
   A和B的数据是否一致：falseA 录入的是：银行流水A B录入的是：银行流水B
   ```
4. 其他

   如果两个线程有一个没有执行exchange()方法，则会一直等待，如果担心有特殊情况发
   生，避免一直等待，可以使用exchange（V x，longtimeout，TimeUnit unit）设置最大等待时长。

