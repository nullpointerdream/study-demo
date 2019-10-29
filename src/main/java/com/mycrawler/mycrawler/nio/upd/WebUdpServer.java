package com.mycrawler.mycrawler.nio.upd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-05-27 15:21
 **/

public class WebUdpServer {

    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel =DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(1234));
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        datagramChannel.receive(buf);
        while (buf.hasRemaining()){
            System.out.print((char) buf.get());
        }
    }
}
