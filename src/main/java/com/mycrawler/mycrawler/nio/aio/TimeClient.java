package com.mycrawler.mycrawler.nio.aio;

import java.io.IOException;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-29 09:47
 **/

public class TimeClient {
    public static void main(String[] args) throws IOException {
        new Thread(new AsyncTimeClientHandler("127.0.0.1",9999),"time-client").start();
    }
}
