package com.mycrawler.mycrawler.thread.future.completableFuture;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 10:55
 **/

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* CompletableFuture<Void> f1=CompletableFuture.runAsync(()->{
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

        CompletableFuture<Smousetring> f3=f1.thenCombine(f2, (__, tf)->{
            System.out.println("T1: 拿到茶叶:" + tf);
            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf;
        });

        System.out.println(f3.get());
*/
        futureTest();
    }



    public static void futureTest() throws ExecutionException, InterruptedException {
        System.out.println(new BigDecimal("12.0").intValue());
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 finished!");
            return "future1 finished!";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future3 finished!");
            return "future3 finished!";
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 finished!");
            return "future2 finished!";
        });

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(future1, future2, future1);
        // 使用thenApply方法聚合所有任务结果到一个字符串中
        CompletableFuture<List<String>> result = allTasks.thenApply(v -> {
                    String join = future1.join();
                    String join2 = future2.join();
                    String join4 = future4.join();
                    return Arrays.asList(join, join2, join4);
                }
        );
        System.out.println(JSON.toJSONString(result.get()));

    }
}
