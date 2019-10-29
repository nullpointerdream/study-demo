package com.mycrawler.mycrawler.nio.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: mycrawler
 * @description: 服务器
 * @author: 陈家乐
 * @create: 2019-06-05 09:48
 **/

public class ChatServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap =new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(1111).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            System.out.println(11);
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }

}
