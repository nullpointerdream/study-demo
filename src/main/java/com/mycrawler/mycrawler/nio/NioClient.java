package com.mycrawler.mycrawler.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

public class NioClient {
    //  nio异步非阻塞IO
    public static void main(String[] args) throws IOException {
        System.out.println("客户端已经启动...");
        //1、创建通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
        //2、切换异步非阻塞
        sChannel.configureBlocking(false);
        //3、指定缓冲区大小
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容：");
        while (scanner.hasNext()){
            System.out.println("请输入内容：");
            String str = scanner.next();
            allocate.put((new Date().toString()+"\n"+str).getBytes());
            //4、切换到写模式
            allocate.flip();
            sChannel.write(allocate);
            allocate.clear();

            ByteBuffer a = ByteBuffer.allocate(1024);
            StringBuilder sb =new StringBuilder();
//4.将Buffer从写模式变为可读模式
            sChannel.read(a);
            a.flip();
            while(a.hasRemaining()) {
                sb.append((char) a.get());
            }
            System.out.println("从服务端接收到的数据：" + sb.toString());

            a.clear();

        }
        sChannel.close();
    }
}
