package com.mycrawler.mycrawler.thread.interaput;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-23 21:08
 **/

public class ThreadService {

    Thread thread;
    private boolean flag=true;

    public void excute(Runnable task){
        thread=new Thread(()->{
            Thread damon= new Thread(task);
            damon.setDaemon(true);
            damon.start();
            try {
                damon.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                flag=false;
            }
        });
        thread.start();
    }

    public void shutdown(long second) throws InterruptedException {
        long start=System.currentTimeMillis();
        while (flag){
            if(System.currentTimeMillis()-start>second){
                thread.interrupt();
            }
            Thread.sleep(10);
        }
        System.out.println(System.currentTimeMillis()-start);
    }




}
