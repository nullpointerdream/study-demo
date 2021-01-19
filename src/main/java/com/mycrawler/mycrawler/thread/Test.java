package com.mycrawler.mycrawler.thread;

public class Test {
 
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                   /* try {
                        System.out.println(Thread.currentThread().isInterrupted());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().isInterrupted());
                    }*/
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("----"+Thread.currentThread().isInterrupted());
                        System.out.println("----"+Thread.currentThread().isInterrupted());
                        break;
                    }

                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());

    }
}