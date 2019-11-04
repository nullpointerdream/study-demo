package com.mycrawler.mycrawler.thread.future.completableFuture;

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
            testerror();
    }

    public static void serial() throws ExecutionException, InterruptedException {

        CompletableFuture<String> f1=CompletableFuture.supplyAsync(()->{
            return "hello";
        }).thenApply((a)->{
            return a+" aaa";
        }).thenApply(String::toUpperCase);

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


    public void testService(){
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
// 创建 CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);
// 异步向电商 S1 询价
        cs.submit(()-> 1);
// 异步向电商 S2 询价
        cs.submit(()->3);
// 异步向电商 S3 询价
        cs.submit(()->3);
// 将询价结果异步保存到数据库
        for (int i=0; i<3; i++) {
          //  Integer r = cs.take().get();
          //  executor.execute(()->save(r));
        }
    }
}
