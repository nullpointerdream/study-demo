package com.mycrawler.mycrawler.nio.netty.transObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectTransferServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(ObjectTransferServerHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if(msg instanceof List)
        System.out.println(msg);
        ctx.writeAndFlush(msg);

    }

    // @Override
    // public void channelReadComplete(ChannelHandlerContext ctx) throws
    // Exception {
    // ctx.flush();
    // ctx.close();
    // }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }

}
