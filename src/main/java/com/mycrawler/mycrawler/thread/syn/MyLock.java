package com.mycrawler.mycrawler.thread.syn;

/**
 * @program: mycrawler
 * @description: 我的锁
 * @author: 陈家乐
 * @create: 2019-03-12 14:55
 **/

public class MyLock {
    private boolean flag=false;
    private Thread thread=null;
    private int n=0;
    public synchronized void lock() throws InterruptedException {
        while (flag&&thread!=Thread.currentThread()){
            wait();
        }
        flag=true;
        n++;
        thread=Thread.currentThread();
    }

    public synchronized void unlock() throws InterruptedException {
        if (thread==Thread.currentThread()){
            n--;
            if(n==0){
                notifyAll();
                flag=false;
            }
        }

    }
}
