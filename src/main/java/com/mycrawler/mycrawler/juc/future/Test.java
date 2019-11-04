package com.mycrawler.mycrawler.juc.future;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:35
 **/

public class Test {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService= new FutureService();
        Future<T> submit = futureService.submit(() -> {
            Thread.sleep(10000);
            String s = "hello world";
            return s;
        },System.out::println);
       /* System.out.println("----");
        Thread.sleep(1000);
       while (!submit.done()){
           Thread.sleep(1000);
           System.out.println("no data");
       }
        System.out.println(submit.get());*/
    }
}
