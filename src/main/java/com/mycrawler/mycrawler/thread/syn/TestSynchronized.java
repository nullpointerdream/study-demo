package com.mycrawler.mycrawler.thread.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: mycrawler
 * @description: synchronized 4种修饰
 * @author: 陈家乐
 * @create: 2019-03-06 22:38
 **/

public class TestSynchronized {

    public synchronized void test1() throws InterruptedException {
        while (true) {
            System.out.println(111);
            Thread.sleep(1000);
        }
    }

    public  void test2() throws InterruptedException {
        while (true) {
            System.out.println(222);
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        TestSynchronized testSynchronized =new TestSynchronized();
        new Thread(()->{
            try {
                testSynchronized.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                testSynchronized.test2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
