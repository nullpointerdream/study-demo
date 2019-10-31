package com.mycrawler.mycrawler.design.obverse.thread;

import java.util.stream.Stream;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 13:40
 **/

public class ThreadLifeCycleOberver implements EventListener{

    public void create() {


        Stream.of("aaa","bbb").forEach(bean -> {
            new Thread(new ThreadA(this) {

                @Override
                public void run() {
                    notify(new RunableEvent(RunableState.RUN,Thread.currentThread(),null));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notify(new RunableEvent(RunableState.DONE,Thread.currentThread(),null));
                }
            },bean).start();
        });
    }


    @Override
    public synchronized void event(ThreadA.RunableEvent runableEvent) {
        System.out.println(runableEvent.getThread().getName()+"----"+runableEvent.getRunableState());
    }

    public static void main(String[] args) {
        new ThreadLifeCycleOberver().create();
    }
}
