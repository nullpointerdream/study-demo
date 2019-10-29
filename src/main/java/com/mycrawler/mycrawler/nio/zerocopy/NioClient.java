package com.mycrawler.mycrawler.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-24 10:44
 **/

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel =SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));
        String fileName="/Users/chenjiale/java_error_in_idea.hprof";
        long start=System.currentTimeMillis();
        FileChannel channel = new FileInputStream(fileName).getChannel();
        long l = channel.transferTo(0, channel.size(), socketChannel);
        channel.close();
        System.out.println("总字节："+l+"   耗时"+(System.currentTimeMillis()-start)+"");
    }
}
