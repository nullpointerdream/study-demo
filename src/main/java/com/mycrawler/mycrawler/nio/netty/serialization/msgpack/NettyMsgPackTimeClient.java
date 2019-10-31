package com.mycrawler.mycrawler.nio.netty.serialization.msgpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 10:00
 **/

public class NettyMsgPackTimeClient {
    private int port;
    private String host;

    public NettyMsgPackTimeClient(int port,String host) {
        this.port = port;
        this.host=host;
    }

    public static void main(String[] args) {
        new NettyMsgPackTimeClient(9999,"127.0.0.1").start();
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

                        ch.pipeline().addLast(new TimeMsgPackClientHandler());
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
