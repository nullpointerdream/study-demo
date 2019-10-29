package com.mycrawler.mycrawler.nio.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 
public class CustomServerHandler extends SimpleChannelInboundHandler<Object> {
 
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof CustomMsg) {
            CustomMsg customMsg = (CustomMsg)msg;
            System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+customMsg.getBody());
        }
        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        System.out.println("yic");
        cause.printStackTrace();                //5
        ctx.close();                            //6
    }
 
}

