package com.mycrawler.mycrawler.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadA {
    private static CountDownLatch countDownLatch =new CountDownLatch(1);
    public static void main(String[] args){
        ThreadB b = new ThreadB();
        b.start();

            try{
                System.out.println("Waiting for b to complete...");
               countDownLatch.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
 
            System.out.println("Total is: " + b.total);

    }

   static class ThreadB extends Thread{
        int total;
        @Override
        public void run(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0; i<100 ; i++){
                    total += i;
                }
                System.out.println(total);
                countDownLatch.countDown();

        }
    }
}
 
