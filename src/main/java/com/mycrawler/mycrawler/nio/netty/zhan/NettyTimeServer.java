package com.mycrawler.mycrawler.nio.netty.zhan;

import com.mycrawler.mycrawler.nio.netty.EchoServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import javax.naming.ldap.SortControl;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 09:31
 **/

public class NettyTimeServer {
    private int port;

    public NettyTimeServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new NettyTimeServer(9999).start();
    }

    private void start() {
        NioEventLoopGroup boss =new NioEventLoopGroup();
        NioEventLoopGroup work =new NioEventLoopGroup();
        ServerBootstrap serverBootstra=new ServerBootstrap();
        serverBootstra.group(boss,work)////设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
                .channel(NioServerSocketChannel.class)
                // 第2次握手服务端向客户端发送请求确认，同时把此连接放入队列A中，
                // 然后客户端接受到服务端返回的请求后，再次向服务端发送请求，表示准备完毕，此时服务端收到请求，把这个连接从队列A移动到队列B中，
                // 此时A+B的总数，不能超过SOBackLOG的数值，满了之后无法建立新的TCP连接,2次握手后和3次握手后的总数
                // 当服务端从队列B中按照FIFO的原则获取到连接并且建立连接[ServerSocket.accept()]后，B中对应的连接会被移除，这样A+B的数值就会变小
                //此参数对于程序的连接数没影响，会影响正在准备建立连接的握手。
               .childOption(ChannelOption.SO_BACKLOG,1024)
                //启用心跳，双方TCP套接字建立连接后（即都进入ESTABLISHED状态），
                // 并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活，TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                //TCP协议中，TCP总是希望每次发送的数据足够大，避免网络中充满了小数据块。
                // Nagle算法就是为了尽可能的发送大数据快。
                // TCP_NODELAY就是控制是否启用Nagle算法。
                // 如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；
                // 如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false。
                .option(ChannelOption.SO_REUSEADDR,true)//是否允许重复绑定端口，重复启动，会把端口从上一个使用者上抢过来
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,30000)//连接超时30000毫秒
                .option(ChannelOption.SO_TIMEOUT,5000)//输入流的read方法被阻塞时，接受数据的等待超时时间5000毫秒，抛出SocketException
                //child是在客户端连接connect之后处理的handler，不带child的是在客户端初始化时需要进行处理的
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)//缓冲池
                .childHandler(new ChannelInitializer<SocketChannel>() {// //为accept channel的pipeline预添加的inboundhandler

                    //当新连接accept的时候，这个方法会调用
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //工作原理是它一次遍历ByteBuf中的可读字节，判断看是否有“\n”或者“\r\n”，
                        // 如果有，就以此位置为结束位置，从可读索引到结束位置区间的字节就组成了一行。
                        // 它是以换行符为结束标志的解码器，支持携带结束符或者不携带结束符两种解码方式，同时支持配置单行的最大长度。如果连续读取到最大长度后仍然没有出现换行符，就会抛出异常，同时忽略掉之前读到的异常码流。
                        //StringDecoder的功能非常简单，就是将接收到的对象转换成字符串，
                        // 然后继续调用后面的Handler。LineBasedFrameDecoder+StringDecoder组合就是按行切换的文本解码器，
                        // 它被设计用来支持TCP的粘包和拆包。
                         //   ch.pipeline().addLast(new LineBasedFrameDecoder(1024));//空格分割
                        //ch.pipeline().addLast(new FixedLengthFrameDecoder(5));//定长
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("@@".getBytes())));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new TimeServerHandler());
                            //ch.pipeline().addLast(new TimeServerSimpleHandler());
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
