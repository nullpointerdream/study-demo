package com.mycrawler.mycrawler.nio.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-29 09:47
 **/

public class AsyncTimeClientHandler implements Runnable{
    private String host;

    private int port;

    private AsynchronousSocketChannel socketChannel;

    private CountDownLatch countDownLatch;

    public AsyncTimeClientHandler(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socketChannel=AsynchronousSocketChannel.open();
    }

    @Override
    public void run() {
        countDownLatch=new CountDownLatch(1);
        doConnect();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doConnect()   {
        //conect也是异步的需要传回调调
        //socketChannel.connect(new InetSocketAddress(host, port));
        socketChannel.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {

                String str="ni hao";
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(str.getBytes());
                buffer.flip();
                socketChannel.write(buffer, null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        socketChannel.read(allocate, allocate, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                attachment.flip();
                                byte[] bytes=new byte[attachment.remaining()];
                                attachment.get(bytes);
                                try {
                                    String s=new String(bytes,"UTF-8");
                                    System.out.println(s);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                exc.printStackTrace();
                                try {
                                    socketChannel.close();
                                    countDownLatch.countDown();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        exc.printStackTrace();
                        try {
                            socketChannel.close();
                            countDownLatch.countDown();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
                try {
                    socketChannel.close();
                    countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
