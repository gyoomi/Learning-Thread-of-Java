package com.thread.base.exchanger;

import java.util.EmptyStackException;
import java.util.concurrent.Exchanger;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/3/3 22:46
 */
public class ExchangerDemo {

    public static void main(String[] args) throws Exception {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerThread thread01 = new ExchangerThread("111", exchanger, 2);
        ExchangerThread thread02 = new ExchangerThread("222", exchanger, 2);
        thread01.start();
        thread02.start();
    }

    static class ExchangerThread extends Thread {

        private String str;

        private Exchanger<String> exchanger;

        private int sleepSecond;

        public ExchangerThread(String str, Exchanger<String> exchanger, int sleepSecond) {
            this.str = str;
            this.exchanger = exchanger;
            this.sleepSecond = sleepSecond;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName() + " 启动，原始数据：" + str + ", 时间：" + System.currentTimeMillis());
                Thread.sleep(sleepSecond * 1000);
                str = exchanger.exchange(str);
                System.out.println(this.getName() + " 交换了数据，交换了数据：" + str + ", 时间：" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
