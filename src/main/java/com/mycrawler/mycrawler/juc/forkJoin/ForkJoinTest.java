package com.mycrawler.mycrawler.juc.forkJoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 14:04
 **/

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool =new ForkJoinPool(4);
        Fibonacci fib = new Fibonacci(20);
        // 启动分治任务
        Integer result = forkJoinPool.invoke(fib);
        // 输出结果
        System.out.println(result);
    }
}
