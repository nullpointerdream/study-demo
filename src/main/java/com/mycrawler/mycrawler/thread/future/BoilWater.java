package com.mycrawler.mycrawler.thread.future;

import com.mycrawler.mycrawler.juc.future.FutureTask;

import java.util.concurrent.Callable;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 10:30
 **/

public class BoilWater implements Callable<String> {

    @Override
    public String call() throws InterruptedException {
        System.out.println("洗水壶");
        Thread.sleep(1000);
        System.out.println("烧水");
        Thread.sleep(8000);
        return "完成";
    }
}
