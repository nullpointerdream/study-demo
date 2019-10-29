package com.mycrawler.mycrawler.thread;

import lombok.Synchronized;

import java.util.concurrent.SynchronousQueue;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-23 21:40
 **/

public class SynTest {
    public static void main(String[] args) {
        Object lock=new Object();
        Runnable runnable =()->{
            synchronized(lock){
                try {
                    Thread.sleep(100_1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1=new Thread(runnable);
        Thread t2=new Thread(runnable);
        Thread t3=new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
    }


}
