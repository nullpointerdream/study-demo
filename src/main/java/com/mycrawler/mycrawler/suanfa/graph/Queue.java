package com.mycrawler.mycrawler.suanfa.graph;

public interface Queue<E> {

    //队列是否为空
    boolean isEmpty();

    //队列的大小
    int size();

    //入队
    void enQueue(E element);

    //出队
    E deQueue();
}