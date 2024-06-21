package com.mycrawler.mycrawler.thread.lock;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-24 09:45
 **/

public class SynLockTest {
    /*public static void main(String[] args) {
        SynLock lock =new SynLock();

        Stream.of("T1","T2","T3","T4").forEach(t->{
            new Thread(()->{
                try {
                    lock.lock(10000);
                    run();
                    lock.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            },t).start();
        });
    }


    public static void run() throws InterruptedException {
        Optional.of(Thread.currentThread().getName()+" 正在执行").ifPresent(System.out::println);
        Thread.sleep(5000);
    }*/
}
