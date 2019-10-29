package com.mycrawler.mycrawler.thread.syn;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-03-12 15:00
 **/

public class ThreadTest {
    private int i=0;
    MyLock myLock =new MyLock();
    public void a() throws InterruptedException {
        myLock.lock();
        Thread.sleep(100);
        ++i;
        b();
        myLock.unlock();
    }

    public void b() throws InterruptedException {
        myLock.lock();
        Thread.sleep(100);
        ++i;
        myLock.unlock();
    }
    public int getCount(){

        return i;
    }
}
