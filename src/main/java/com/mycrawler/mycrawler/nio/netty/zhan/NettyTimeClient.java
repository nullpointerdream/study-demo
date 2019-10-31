package com.mycrawler.mycrawler.nio.netty.zhan;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 10:00
 **/

public class NettyTimeClient {
    private int port;
    private String host;

    public NettyTimeClient(int port,String host) {
        this.port = port;
        this.host=host;
    }

    public static void main(String[] args) {
        new NettyTimeClient(9999,"127.0.0.1").start();
    }

    private void start() {
        NioEventLoopGroup boss= new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(boss)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)//开启了Nagle算法。Nagle算法通过减少需要传输的数据包，来优化网络
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        //ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("@@".getBytes())));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new TimeClientHandler());
                       //ch.pipeline().addLast(new TimeClientSimpleHandler());
                    }
                });
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("优雅关闭");
            boss.shutdownGracefully();
        }

    }

}
