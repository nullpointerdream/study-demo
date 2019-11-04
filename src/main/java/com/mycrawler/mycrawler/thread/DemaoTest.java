package com.mycrawler.mycrawler.thread;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-04 13:08
 **/

public class DemaoTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=() -> {
            while (true) {
                System.out.println("living");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread thread1 = new Thread(() -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.start();

            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        });
        thread1.setDaemon(false);
        thread1.start();

        Thread.sleep(2000);
       thread1.interrupt();
        while (true){

        }



    }
}
