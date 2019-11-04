package com.mycrawler.mycrawler.thread.future;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 10:29
 **/

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BoilWater boilWater = new BoilWater();
        FutureTask<String> futureTask=new FutureTask<String>(boilWater);
        FutureTask<String> futureTask1=new FutureTask<String>(new MakeTea(futureTask));
        new Thread(futureTask).start();
        new Thread(futureTask1).start();
        System.out.println(futureTask1.get());
    }
}
