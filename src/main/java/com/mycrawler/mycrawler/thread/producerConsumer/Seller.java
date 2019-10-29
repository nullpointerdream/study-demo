package com.mycrawler.mycrawler.thread.producerConsumer;

/**
 * @program: mycrawler
 * @description: 售票员
 * @author: 陈家乐
 * @create: 2019-07-10 10:09
 **/

public class Seller extends Thread {

    private Ticket ticket;
    private static int id = 0;

    public Seller(String name,Ticket ticket){
            super(name);
            this.ticket=ticket;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                nextId();
                ticket.addTicket(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static synchronized int nextId() {

        return id++;
    }
}
