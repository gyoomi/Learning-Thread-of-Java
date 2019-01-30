package com.thread.base.volatiles;

/**
 * 2. volatile是非原子性的
 *    示例如下。
 *
 *    解决方法：给addCount()方法添加synchronized
 *    i++不是原子性的。
 *
 *    结论：volatile关键字保证了从主内存加载到线程工作内存中的值是最新的。但是对于多个线程访问同一个实例变量还是需要加锁。
 *
 * @author Leon
 * @version 2019/1/30 16:37
 */
public class ThreadVolatile03 {

     public static void main(String[] args) throws Exception {
          Thread03[] arrs = new Thread03[100];
          for (int i = 0; i < 100; i++) {
               arrs[i] = new Thread03();
          }
          for (int i = 0; i < 100; i++) {
               arrs[i].start();
          }
     }
}

class Thread03 extends Thread {

     private volatile static int count;

     private static synchronized void addCount() {
          for (int i = 0; i < 100; i++) {
               count++;
          }
          System.out.println("count = " + count);
     }

     @Override
     public void run() {
          addCount();
     }
}
