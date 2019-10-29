package com.mycrawler.mycrawler.nio.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
 
public class CustomClientHandler extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CustomMsg customMsg = new CustomMsg((byte)0xAB, (byte)0xCD, 1, "Hello,Netty");
        ctx.writeAndFlush(customMsg);
    }
 
}

