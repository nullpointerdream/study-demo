package com.mycrawler.mycrawler.thread.interaput;

import java.io.BufferedInputStream;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-29 16:51
 **/

public class Test {
    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            th.interrupt();
        }).start();
        while(true) {
            System.out.println(th.isInterrupted());
            if(th.isInterrupted()) {
                System.out.println(th.isInterrupted());
                break;
            }

        }
    }
}
