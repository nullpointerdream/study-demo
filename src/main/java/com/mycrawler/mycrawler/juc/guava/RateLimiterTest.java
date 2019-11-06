package com.mycrawler.mycrawler.juc.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 16:29
 **/

public class RateLimiterTest {
    public static void main(String[] args) {
        //RateLimiter limiter = RateLimiter.create(2.0);
        SimpleLimiter limiter = new SimpleLimiter();
        ExecutorService es = Executors.newFixedThreadPool(1);
        // 记录上一次执行时间
        for (int i = 0; i < 20; i++) {
            // 限流器限流 12
            limiter.acquire();
            // 提交任务异步执行 14
            //
            es.execute(() -> {
                long cur = System.currentTimeMillis();

                // 打印时间间隔：毫秒
                System.out.println((cur) / 1000);
            });
        }
    }
}
