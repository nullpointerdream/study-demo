package com.mycrawler.mycrawler.nio.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-05 09:33
 **/

public class WebSocketServer {
        private final int port;

        public WebSocketServer(int port){
            this.port = port;
        }

        public static void main(String[] args) throws InterruptedException {
            new WebSocketServer(8080).start();
            System.out.println("Start http server success!");
        }

        public void start() {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            NioEventLoopGroup group = new NioEventLoopGroup();
            serverBootstrap.group(group).channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder", new HttpRequestDecoder());
                            ch.pipeline().addLast("http-encoder",
                                    new HttpResponseEncoder());//响应解码器
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                            ch.pipeline().addLast("handler", new WebSocketServerHandler()); // 请求的业务类
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);;
            ChannelFuture sync = null;
            try {
                sync = serverBootstrap.bind(port).sync();
                sync.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
        }
    }

}
