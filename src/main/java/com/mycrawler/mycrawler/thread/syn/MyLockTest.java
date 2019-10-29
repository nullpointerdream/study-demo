package com.mycrawler.mycrawler.thread.syn;

import java.util.concurrent.*;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-12 14:57
 **/

public class MyLockTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        ThreadTest threadTest =new ThreadTest();
        for(int i=0;i<10;i++){
            executorService.submit(()->{
                try {
                    threadTest.a();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        while (Thread.activeCount()>2){
            //System.out.println(Thread.activeCount());
        }
        System.out.println(threadTest.getCount());
    }
}
