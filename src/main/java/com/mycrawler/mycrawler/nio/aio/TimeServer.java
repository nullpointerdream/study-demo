package com.mycrawler.mycrawler.nio.aio;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-29 09:35
 **/

public class TimeServer {
    public static void main(String[] args) {
        new Thread(new AsyncTimeServerHandler(9999),"time-server").start();
    }
}
