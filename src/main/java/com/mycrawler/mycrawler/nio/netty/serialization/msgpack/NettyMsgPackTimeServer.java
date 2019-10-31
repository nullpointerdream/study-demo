package com.mycrawler.mycrawler.nio.netty.serialization.msgpack;

import com.mycrawler.mycrawler.nio.netty.EchoServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 09:31
 **/

public class NettyMsgPackTimeServer {
    private int port;

    public NettyMsgPackTimeServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new NettyMsgPackTimeServer(9999).start();
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
                        /**
                         *
                         * @param maxFrameLength 解码时，处理每个帧数据的最大长度
                         * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的数据的起始位置
                         * @param lengthFieldLength 记录该帧数据长度的字段本身的长度
                         * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数
                         * @param initialBytesToStrip 解析的时候需要跳过的字节数
                         * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常
                         */
                        ch.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(1024, 0, 2,0,2));
                        ch.pipeline().addLast(new MsgpackDecoder());
                        ch.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));
                        ch.pipeline().addLast(new MsgpackEncoder());



                        ch.pipeline().addLast(new TimeMsgPackServerHandler());
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
