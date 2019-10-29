package com.mycrawler.mycrawler.nio.upd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-27 15:21
 **/

public class WebUdpClient {

    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel =DatagramChannel.open();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.put("awaacaw".getBytes());
        buf.flip();
        datagramChannel.send(buf, new InetSocketAddress("localhost", 1234));

    }
}
