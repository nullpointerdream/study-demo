package com.mycrawler.mycrawler.thread.future.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 10:55
 **/

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> f1=CompletableFuture.runAsync(()->{
            try {
                System.out.println("T1: 洗水壶...");
                sleep(8000);
                System.out.println("T1: 烧水...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<String> f2=CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("T2: 洗茶壶...");
                sleep(1000);
                System.out.println("T2: 洗茶杯...");
                sleep(1000);
                System.out.println("拿茶叶..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "龙井";
        });

        CompletableFuture<String> f3=f1.thenCombine(f2, (__, tf)->{
            System.out.println("T1: 拿到茶叶:" + tf);
            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf;
        });

        System.out.println(f3.get());
    }
}
