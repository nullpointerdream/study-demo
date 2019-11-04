package com.mycrawler.mycrawler.thread.future;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 10:32
 **/

public class MakeTea implements Callable<String> {

    private FutureTask<String> futureTask;

    public MakeTea(FutureTask<String> futureTask) {
        this.futureTask = futureTask;
    }

    @Override
    public String call() throws InterruptedException, ExecutionException {
        System.out.println("洗茶壶");
        Thread.sleep(1000);
        System.out.println("洗茶杯");
        Thread.sleep(1000);
        System.out.println("拿茶叶");
        String call = futureTask.get();
        return call +"  开始泡茶龙井";
    }
}
