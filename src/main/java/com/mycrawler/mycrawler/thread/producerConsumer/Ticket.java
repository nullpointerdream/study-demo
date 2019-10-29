package com.mycrawler.mycrawler.thread.producerConsumer;

import lombok.Data;

/**
 * @program: mycrawler
 * @description: 票
 * @author: 陈家乐
 * @create: 2019-07-10 10:06
 **/
@Data
public class Ticket {
    private int[] ticketSize;

    private Integer index;
    private int tail;
    private int head;

    public Ticket(int size) {
        this.ticketSize = new int[size];
        this.index=0;
        this.tail=0;
        this.head=0;
    }


    public synchronized void addTicket(int tikcetNo) throws InterruptedException {
        while (index>=ticketSize.length){
            wait();
        }
        ticketSize[tail]=tikcetNo;
        tail = (tail + 1) % ticketSize.length;
        index++;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " 加票 票号" + tikcetNo);
    }


    public synchronized void sellTicket() throws InterruptedException {
        while (index<=0){
            wait();
        }
        int tikcetNo = ticketSize[head];
        head = (head + 1) % ticketSize.length;
        index--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " 买票 票号" + tikcetNo);

    }






}
