package com.mycrawler.mycrawler.nio.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-06-05 09:58
 **/

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        channelGroup.forEach(bean->{
                    if(!bean.equals(ctx.channel())){
                        bean.writeAndFlush( bean.remoteAddress()+"说.."+s+"\n");
                    }else {
                        bean.writeAndFlush( "本人说.."+s+"\n");
                    }
                });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush(ctx.channel().remoteAddress()+"---上线了\n");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush(ctx.channel().remoteAddress()+"---下线了\n");
        channelGroup.remove(ctx.channel());
    }
}
