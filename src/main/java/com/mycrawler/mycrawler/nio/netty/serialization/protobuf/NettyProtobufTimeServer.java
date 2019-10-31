package com.mycrawler.mycrawler.nio.netty.serialization.protobuf;

import com.mycrawler.mycrawler.nio.netty.EchoServer;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.MsgpackDecoder;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.MsgpackEncoder;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.NettyMsgPackTimeServer;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.TimeMsgPackServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 09:31
 **/

public class NettyProtobufTimeServer {
    private int port;

    public NettyProtobufTimeServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new NettyProtobufTimeServer(9999).start();
    }

    private void start() {
        NioEventLoopGroup boss =new NioEventLoopGroup();
        NioEventLoopGroup work =new NioEventLoopGroup();
        ServerBootstrap serverBootstra=new ServerBootstrap();
        serverBootstra.group(boss,work)////设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
                .channel(NioServerSocketChannel.class)
               .childOption(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {// //为accept channel的pipeline预添加的inboundhandler

                    //当新连接accept的时候，这个方法会调用
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //传输的协议 Protobuf
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast(new ProtobufDecoder(UserInfo.UserMsg.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new TimeProtobufServerHandler());
                    }
                });
        ChannelFuture future = null;
        try {
            future = serverBootstra.bind(port).sync(); //sync 等待服务器关闭调用 sync() 的原因是当前线程阻塞

            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
            System.out.println("关闭");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
