package com.mycrawler.mycrawler.juc.future;


/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:28
 **/

public interface FutureTask<T> {
    T call() throws InterruptedException;
}
