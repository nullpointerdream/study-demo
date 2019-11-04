package com.mycrawler.mycrawler.juc.future;

import org.apache.poi.hssf.record.formula.functions.T;

import java.util.function.Consumer;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:29
 **/

public class FutureService<T> {

    public Future<T> submit(final FutureTask<T> task, Consumer<T> consumer){
        AsynFuture<T> asynFuture = new AsynFuture();
        new Thread(()->{
            // 执行给定的任务
            T result = null;
            try {
                result = task.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 将任务的结果放入asynFuture中
            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        // 使用另一个线程执行任务，可以立即返回Future
        return asynFuture;
    }
}
