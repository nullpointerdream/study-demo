package com.mycrawler.mycrawler.juc.future;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:27
 **/

public interface Future<T> {
    T get();
     boolean done();

}
