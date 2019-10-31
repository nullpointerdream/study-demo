package com.mycrawler.mycrawler.design.obverse.thread;

import com.sun.xml.internal.rngom.digested.DOneOrMorePattern;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 13:40
 **/

public abstract class ThreadA implements Runnable {

    private EventListener eventListener;

    public ThreadA( EventListener eventListener) {
        this.eventListener = eventListener;
    }


    public void notify(RunableEvent runableEvent){
        eventListener.event(runableEvent);
    }


    enum RunableState{
        RUN,DEAD,DONE
    }

    static class RunableEvent{
        private RunableState runableState;
        private Thread thread;
        private Throwable throwable;

        public RunableEvent(RunableState runableState, Thread thread, Throwable throwable) {
            this.runableState = runableState;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunableState getRunableState() {
            return runableState;
        }

        public void setRunableState(RunableState runableState) {
            this.runableState = runableState;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }
    }
}
