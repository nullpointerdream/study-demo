package com.mycrawler.mycrawler.thread.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-24 09:38
 **/

public class SynLock implements AbstartLock {

    private Thread currentThread;

    private boolean states;

    private  Collection<Thread> threadCollection;
    public SynLock(){
        this.states=false;
       this.threadCollection=new ArrayList<>();
    }

    @Override
    public synchronized void lock() {
        threadCollection.add(Thread.currentThread());
        while (states){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadCollection.remove(Thread.currentThread());
        currentThread=Thread.currentThread();
        states=true;
    }

    @Override
    public synchronized void lock(long mills) throws Exception{
            long start=System.currentTimeMillis()+mills;
            long sleep;
            while (states){

                    sleep=start-System.currentTimeMillis();
                    if(sleep<0){
                        throw new Exception(Thread.currentThread().getName()+"超时");
                    }
                    wait(sleep);


            }
        currentThread=Thread.currentThread();
        states=true;
    }

    @Override
    public synchronized void unlock() {
        if(currentThread!=Thread.currentThread()){
            return;
        }
        states=false;
        notifyAll();
        Optional.of(currentThread.getName()+"释放锁").ifPresent(System.out::println);

    }

    @Override
    public Collection<Thread> getCollection() {
        return threadCollection;
    }

    @Override
    public int size() {
        return threadCollection.size();
    }
}
