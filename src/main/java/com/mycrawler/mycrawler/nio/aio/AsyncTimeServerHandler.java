package com.mycrawler.mycrawler.nio.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-10-29 09:37
 **/

public class AsyncTimeServerHandler implements Runnable {
    private int port;

    private CountDownLatch  countDownLatch;

    private AsynchronousServerSocketChannel serverSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            serverSocketChannel=AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("server start on port: "+port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch=new CountDownLatch(1);
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                try {
                    System.out.println("socket connect success"+result.getLocalAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

               // result.read(byteBuffer);//异步的不能用这个方法
                result.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer integer, ByteBuffer attachment) {
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        //
                        try {
                            String body=new String(bytes,"UTF-8");
                            System.out.println("revice client :"+body);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        doWrite(result);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                        try {
                            serverSocketChannel.close();
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
                    serverSocketChannel.close();
                    countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void doWrite(AsynchronousSocketChannel socketChannel) {
        String date=new Date().toString();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put(date.getBytes());
        allocate.flip();
        socketChannel.write(allocate, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("发送完成");
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
                try {
                    serverSocketChannel.close();
                    countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
