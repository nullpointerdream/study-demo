package com.mycrawler.mycrawler.nio.nioChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-07-22 10:24
 **/

public class NioClient {
    public static void main(String[] args) throws  Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",1122));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true){
            selector.select();
            for (SelectionKey selectedKey : selector.selectedKeys()) {
                SocketChannel channel=null;
                if(selectedKey.isConnectable()){
                    channel = (SocketChannel) selectedKey.channel();
                    if (channel.isConnectionPending())
                        channel.finishConnect();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                    ByteBuffer byteBuffer =ByteBuffer.allocate(512);
                    byteBuffer.put((LocalDateTime.now().toString()+" 连接了"+channel.getRemoteAddress().toString()).getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    SocketChannel finalChannel = channel;
                    executorService.submit(()->{
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            byteBuffer.clear();
                            String s = br.readLine();
                            byteBuffer.put(s.getBytes());
                            byteBuffer.flip();
                            finalChannel.write(byteBuffer);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }else if(selectedKey.isReadable()){
                    channel= (SocketChannel) selectedKey.channel();
                    ByteBuffer byteBuffer =ByteBuffer.allocate(512);
                    int read = channel.read(byteBuffer);
                    if(read>0){
                        byteBuffer.flip();
                        StringBuilder sb =new StringBuilder();
                        while(byteBuffer.hasRemaining()) {
                            sb.append((char) byteBuffer.get());
                        }
                        System.out.println(sb.toString());


                    }
                }
            }

            ;

        }
    }
}
