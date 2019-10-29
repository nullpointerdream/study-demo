package com.mycrawler.mycrawler.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebServer {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(3333));
        System.out.println("服务器启动。。。");
        SocketChannel accept = serverSocketChannel.accept();
        System.out.println("阻塞完成");
        ByteBuffer readBuffer=ByteBuffer.allocate(128);
        accept.read(readBuffer);
//String 字符串常量，不可变；StringBuffer 字符串变量（线程安全），可变；StringBuilder 字符串变量（非线程安全），可变
        StringBuilder sb =new StringBuilder();
//4.将Buffer从写模式变为可读模式
        readBuffer.flip();
        while(readBuffer.hasRemaining()) {
            sb.append((char) readBuffer.get());
        }
        System.out.println("从服务端接收到的数据：" + sb.toString());

        // 3.创建写数据缓存区对象
        ByteBuffer writeBuffer =ByteBuffer.allocate(128);
        writeBuffer.put(sb.toString().toUpperCase().getBytes());
        writeBuffer.flip();
        accept.write(writeBuffer);
        accept.close();


    }


}