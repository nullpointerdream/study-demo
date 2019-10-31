package com.mycrawler.mycrawler.nio.netty.zhan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 10:04
 **/

public class TimeClientSimpleHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuf firstMessage= Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
        ctx.writeAndFlush("QUERY TIME ORDER");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }
}
