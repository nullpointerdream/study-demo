package com.mycrawler.mycrawler.nio.netty.transObject;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectTranferServer {

    private final int port;

    public ObjectTranferServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
          ServerBootstrap bootstrap =new ServerBootstrap();
          bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                  .childHandler(new ChannelInitializer<SocketChannel>() {
                      @Override
                      protected void initChannel(SocketChannel socketChannel) throws Exception {
                          socketChannel.pipeline().addLast(new ObjectEncoder(),new ObjectDecoder(Integer.MAX_VALUE,ClassResolvers.cacheDisabled(null)),new ObjectTransferServerHandler());
                      }
                  });
            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 11000;
        }
        new ObjectTranferServer(port).run();
    }
}