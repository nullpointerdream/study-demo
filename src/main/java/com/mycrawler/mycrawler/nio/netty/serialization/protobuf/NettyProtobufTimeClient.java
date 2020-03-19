package com.mycrawler.mycrawler.nio.netty.serialization.protobuf;

import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.MsgpackDecoder;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.MsgpackEncoder;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.NettyMsgPackTimeClient;
import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.TimeMsgPackClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
 * @create: 2019-10-30 10:00
 **/

public class NettyProtobufTimeClient {
    private int port;
    private String host;

    public NettyProtobufTimeClient(int port,String host) {
        this.port = port;
        this.host=host;
    }

    public static void main(String[] args) {
        new NettyProtobufTimeClient(9999,"127.0.0.1").start();
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

                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                       // ch.pipeline().addLast(new ProtobufDecoder(UserInfo.UserMsg.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new TimeProtobufClientHandler());
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
