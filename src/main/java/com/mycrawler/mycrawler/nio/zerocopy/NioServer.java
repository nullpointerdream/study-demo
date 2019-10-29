package com.mycrawler.mycrawler.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-24 10:47
 **/

public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8899));
        while (true){
            SocketChannel accept = serverSocketChannel.accept();
            long start=System.currentTimeMillis();
            accept.configureBlocking(true);
            int size=0;
            int totle=0;
            ByteBuffer byteBuffer=ByteBuffer.allocate(4096);
            while ((size=accept.read(byteBuffer))>=0){
                totle+=size;
                byteBuffer.clear();

            }
            System.out.println("总字节："+totle+"   耗时"+(System.currentTimeMillis()-start)+"");

        }


    }
}
