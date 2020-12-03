package com.mycrawler.mycrawler.thread.future.completableFuture;

import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 10:55
 **/

public class CompletableFutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // testService();
        serial();
    }

    public static void serial() throws ExecutionException, InterruptedException {

        CompletableFuture<String> f1=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenApply((a)->{
            return a+" aaa";
        }).thenApply(String::toUpperCase);
        System.out.println("lal");
        System.out.println(f1.get());
    }

    public static void testOr(){
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t =1500;
                    try {
                        sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "t1--"+String.valueOf(t);
                });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = 1000;
                    try {
                        sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "t2--"+String.valueOf(t);
                });
        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);

         System.out.println(f3.join());
    }


    public static void testerror(){
        CompletableFuture<Integer>
                f0 = CompletableFuture
                .supplyAsync(()->7/2)
                .thenApply(r->r*10)
                .exceptionally(e->0);
        System.out.println(f0.join());
    }


    public static int testService(){
        // 创建线程池
        Random random = new Random();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建 CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 异步向电商 S1 2 3 询价
        for(int i=0;i<3;i++) {
            int a=i;
            cs.submit(() -> {
                Thread.sleep(1000 * random.nextInt(10));
                return a;
            });
        }
        executor.shutdown();
        // 将询价结果异步保存到数据库
        for (int i=0; i<3; i++) {
            try {
                Future<Integer> take = cs.take();
                System.out.println(take.get());
            } catch (Exception e) {
            }

        }
        return 0;

    }
}
