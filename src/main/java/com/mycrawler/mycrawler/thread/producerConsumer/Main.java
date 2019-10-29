package com.mycrawler.mycrawler.thread.producerConsumer;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-10 10:28
 **/

public class Main {
    public static void main(String[] args) {

            Ticket table = new Ticket(3);
            new Seller("sell-4", table).start();
            new Client("client-2", table).start();
            new Client("client-3", table).start();
            new Seller("sell-1", table).start();
           new Seller("sell-2", table).start();
          new Seller("sell-3", table).start();
        }

}
