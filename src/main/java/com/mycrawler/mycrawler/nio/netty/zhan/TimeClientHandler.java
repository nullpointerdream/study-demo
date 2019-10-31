package com.mycrawler.mycrawler.nio.netty.zhan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 10:04
 **/

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] req = "QUERY TIME ORDER@@".getBytes();
        ByteBuf firstMessage=null;
       for (int i = 0; i < 100; i++) {
           firstMessage = Unpooled.buffer(req.length);
           firstMessage.writeBytes(req);
           ctx.writeAndFlush(firstMessage);
       }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

      /*  ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");*/
      String body= (String) msg;
        System.out.println("receive server : " + body);

    }

}
