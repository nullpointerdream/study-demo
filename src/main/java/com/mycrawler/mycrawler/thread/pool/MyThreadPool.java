package com.mycrawler.mycrawler.thread.pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-28 09:29
 **/

public class MyThreadPool extends Thread {

    private Boolean destroy=false;

    private int size;

    private int max=20;

    private int queueSize=150;

    private int min=5;


    private int id;

    private List<MyThread> threads =new ArrayList();

    private static LinkedList<Runnable> tasks =new LinkedList<>();

    private final DiscardPolicy discardPolicy;


    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new Exception("Discard This Task.");
    };


    public MyThreadPool(int size){
        this(size,DEFAULT_DISCARD_POLICY);
    }

    public MyThreadPool(int size,DiscardPolicy discardPolicy){
        this.size=size;
        this.discardPolicy= discardPolicy;
        init();
        this.start();
    }
    private void init() {
        for (int i = 0; i < this.size; i++) {
            createThread();
        }
    }



    public void createThread(){
        MyThread myThread = new MyThread("--" + nextId());
        threads.add(myThread);
        myThread.start();
    }

    public void submit(Runnable t) throws Exception {

        synchronized (tasks) {
            if (tasks.size() > queueSize)
                discardPolicy.discard();
            tasks.addLast(t);
            tasks.notifyAll();
        }
    }


    public void shutdown() throws InterruptedException {
        while (!tasks.isEmpty()) {
            Thread.sleep(50);
        }

        synchronized (tasks) {
            int initVal = tasks.size();
            while (initVal > 0) {
                for (MyThread task : threads) {
                    if (task.taskState == TaskState.BLOCK) {
                        task.interrupt();
                        task.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }

        this.destroy = true;
        System.out.println("The thread pool disposed.");
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min,  this.max, this.size, tasks.size());
            try {
                Thread.sleep(1_000L);
                  if (tasks.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createThread();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }

                synchronized (threads) {
                    if (tasks.isEmpty() && size > min) {
                        System.out.println("=========Reduce========");
                        int releaseSize = size - min;
                        for (Iterator<MyThread> it = threads.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0)
                                break;

                            MyThread task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = min;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    private int nextId() {

        return id++;
    }

    static class MyThread extends Thread {

        private TaskState taskState;
        public MyThread(String name){
            super(name);
            taskState=TaskState.FREE;
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            OUT:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable = null;
                synchronized (tasks) {
                    if (tasks.isEmpty()) {
                        try {
                            this.taskState = TaskState.BLOCK;
                            tasks.wait();
                        } catch (InterruptedException e) {
                                break OUT;
                        }

                    }
                    runnable = tasks.removeFirst();
                }
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }




    }
     enum TaskState {
        FREE, RUNNING, BLOCK, DEAD
    }

    public static void main(String[] args) throws Exception {
        MyThreadPool threadPool = new MyThreadPool(10);
        for (int i = 0; i < 40; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                System.out.println("The runnable "+ finalI +" be serviced by " + Thread.currentThread().getName() + " start.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable "+ finalI +"be serviced by " + Thread.currentThread().getName() + " finished.");
            });
        }
    }

    public interface DiscardPolicy {

        void discard() throws Exception;
    }

}

