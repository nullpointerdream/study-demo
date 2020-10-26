package com.mycrawler.mycrawler.thread.syn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-01 16:27
 **/

public class Hello {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();


        lock.lock();
    }


    public void test() {
        int i = 8;
        while ((i -= 3) > 0);
        System.out.println(i);
        System.out.println("i = " + i);
    }
}
