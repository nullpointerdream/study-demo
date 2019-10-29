package com.mycrawler.mycrawler.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 写操作工具类
 * @author MOTUI
 *
 */
public class ResponeProcessor {
    //构造线程池
    private static ExecutorService  executorService  = Executors.newFixedThreadPool(10);

    public static void ProcessorRespone(final SelectionKey key) {
        //获得线程并执行
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("开始写");
                    // 写操作
                    SocketChannel writeChannel = (SocketChannel) key.channel();
                    //拿到客户端传递的数据
                    //ByteArrayOutputStream attachment = (ByteArrayOutputStream)key.attachment();

                    //System.out.println("客户端发送来的数据："+new String(attachment.toByteArray()));
                    //拿到客户端传递的数据
                    ByteArrayOutputStream attachment = (ByteArrayOutputStream)key.attachment();
                    System.out.println("客户端发送来的数据："+new String(attachment.toByteArray()));
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    String message = "你好，我好，大家好！！";
                    buffer.put(message.getBytes());
                    buffer.flip();
                    writeChannel.write(buffer);
                    writeChannel.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }       
            }
        });
    }
}
