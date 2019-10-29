package com.mycrawler.mycrawler.thread;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-06-28 10:49
 **/

public class NoVisibility {
    private static int no=10;
    private static boolean ready;


    static class MyThrad extends Thread{
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(no);
        }
    }

    public static void main(String[] args) {

            new MyThrad().start();
            no = 21;
            ready = true;

    }
}
