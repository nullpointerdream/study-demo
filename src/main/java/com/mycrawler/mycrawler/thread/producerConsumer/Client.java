package com.mycrawler.mycrawler.thread.producerConsumer;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-10 10:24
 **/

public class Client extends Thread{

    private Ticket ticket;

    public Client(String name,Ticket ticket){
        super(name);
        this.ticket=ticket;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                ticket.sellTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



}
