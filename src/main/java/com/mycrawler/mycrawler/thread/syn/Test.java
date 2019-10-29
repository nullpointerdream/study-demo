package com.mycrawler.mycrawler.thread.syn;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    static int i = 0, j = 0;
    static  void one() { i++; j++; }
    static  void two() {
        System.out.println("i=" + i + " j=" + j);
    }


    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger();
        new Thread(()->{
            while (true){
                if(i!=100000) {
                    two();
                }

            }
        }).start();
        new Thread(()->{
            while (true){
                if(i!=100000) {
                    one();
                }

            }
        }).start();
    }
}
