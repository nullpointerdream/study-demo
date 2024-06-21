package com.mycrawler.mycrawler.juc.future;


/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:30
 **/

public class AsynFuture<T> implements Future<T> {
    private volatile boolean done=false;

    private T data;

    public void done(T data){
        synchronized (this) {
            this.data = data;
            done = true;
            notifyAll();
        }

    }

    @Override
    public boolean done(){
       return done;

    }

    @Override
    public T get() {
        synchronized (this) {
            while (!done) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
