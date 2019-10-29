package com.mycrawler.mycrawler.nio.netty.transObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectTransferClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(ObjectTransferClientHandler.class.getName());

    private final List<User> message;

    /**
     * Creates a client-side handler.
     */
    public ObjectTransferClientHandler(int messageSize) {
        if (messageSize <= 0) {
            throw new IllegalArgumentException("firstMessageSize: "
                    + messageSize);
        }
        message = new ArrayList<User>(messageSize);
        for (int i = 0; i < messageSize; i++) {
            User user = new User();
            user.setId(i);
            user.setCardNo("420000" + i);
            user.setName("hu" + i);
            user.setDescription("你觉得这样好吗？？真的好吗" + i);
            message.add(user);
         
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the message to Server
        super.channelActive(ctx);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // you can use the Object from Server here
        System.out.println(msg);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }
}