package com.mycrawler.mycrawler.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {

    //存储SelectionKey的队列
    private static List<SelectionKey> writeQueen = new ArrayList<SelectionKey>();
    private static Selector selector = null;

    //添加SelectionKey到队列
    public static void addWriteQueen(SelectionKey key){
        synchronized (writeQueen) {
            writeQueen.add(key);
            //唤醒主线程
            selector.wakeup();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("服务端已经启动...");
        //1、创建通道
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        //2、切换异步非阻塞
        sChannel.configureBlocking(false);
        //3、绑定连接
        sChannel.bind(new InetSocketAddress(8888));
        //4、获取选择器
        selector =Selector.open();
        //5、将通道注册到选择器，并且"指定监听接受事件"。
        sChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6、轮训式 获取选择"已经准备就绪"的事件
        while (true){
            int n = selector.select();
            if(n>0) {
                // 7.获取当前选择器所有注册的"选择键(已经就绪的监听事件)"
                System.out.println("监听到事件");
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    //8.获取准备就绪的事件
                    SelectionKey sk = iterator.next();
                    iterator.remove();
                    //9.判断具体是什么事件准备就绪
                    if (sk.isAcceptable()) {
                        // 10.若"接受就绪",获取客户端连接
                        System.out.println("接受就绪");
                        SocketChannel socketChannel = sChannel.accept();
                        //11.设置阻塞模式
                        socketChannel.configureBlocking(false);
                        //12.将该通道注册到服务器上
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (sk.isReadable()) {
                        //取消读事件的监控
                        sk.cancel();
                        RequestProcessor.ProcessorRequest(sk, selector);
                    } else if (sk.isWritable()) {
                        System.out.println("du");
                        sk.cancel();
                        ResponeProcessor.ProcessorRespone(sk);
                    }
                }
            }else {

                synchronized (writeQueen) {
                    while (writeQueen.size() > 0) {
                        SelectionKey key = writeQueen.remove(0);
                        //注册写事件
                        SocketChannel channel = (SocketChannel) key.channel();
                        Object attachment = key.attachment();
                        channel.register(selector, SelectionKey.OP_WRITE, attachment);
                    }
                }
            }


        }


    }
}
