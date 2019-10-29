package com.mycrawler.mycrawler.nio.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @program: mycrawler
 * @description: http服务器
 * @author: 陈家乐
 * @create: 2019-03-21 10:51
 **/

public class HttpServer {
    private final int port;

    public HttpServer(int port){
        this.port = port;
    }
    public static void main(String[] args) throws InterruptedException {
         new HttpServer(9999).start();
         System.out.println("Start http server success!");
    }
    public void start() throws InterruptedException{
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        serverBootstrap.group(group).channel(NioServerSocketChannel.class).
                childHandler(new ChannelInitializer<SocketChannel>(){

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("initChannel ch:" + ch);
                ch.pipeline().addLast("decoder", new HttpRequestDecoder())
                             .addLast("encoder", new HttpResponseEncoder())
                              /* aggregator，消息聚合器（重要）。
54                         Netty4中为什么能有FullHttpRequest这个东西，
55                         就是因为有他，HttpObjectAggregator，如果没有他，
56                         就不会有那个消息是FullHttpRequest的那段Channel，
57                         同样也不会有FullHttpResponse，HttpObjectAggregator(512 * 1024)的参数含义是消息合并的数据大小，
58                         如此代表聚合的消息内容长度不超过512kb。*/
                        .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                        .addLast("handler", new HttpServerHandler()); // 请求的业务类
            }
        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);;
        serverBootstrap.bind(port).sync();
    }
}
