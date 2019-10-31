package com.mycrawler.mycrawler.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @program: mycrawler
 * @description: 服务端
 * @author: 陈家乐
 * @create: 2019-03-11 14:19
 **/

public class EchoServer {

    private final int port;

    public static final byte PKG_DELIMITER = 0x0D;
    public static final byte PKG_DELIMITER2 = 0x0A;

    public EchoServer(int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
        //new EchoServer(8888).start();
    }

    public void start() throws Exception {

        EventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)//指定nio传输的channel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                            //如果有入站信息，这个处理器将被通知
                           socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
                           socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,Unpooled.copiedBuffer(new byte[]{PKG_DELIMITER,PKG_DELIMITER2})));
                           //socketChannel.pipeline().addLast("decoder", new MyDecoder());
                           socketChannel.pipeline().addLast(new EchoServerHandler());
                           socketChannel.pipeline().addLast(new EchoServerHandler2());

                        }
                    });
            ChannelFuture f = b.bind(8888).sync();//绑定的服务器;sync 等待服务器关闭调用 sync() 的原因是当前线程阻塞
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
            System.out.println("关闭");//关闭 channel 和 块，直到它被关闭
        } finally {
            group.shutdownGracefully().sync();            //关闭的 EventLoopGroup，释放所有资源。
        }
    }

}
