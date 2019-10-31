package com.mycrawler.mycrawler.nio.netty.serialization.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:15
 **/

public class TimeProtobufServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
       UserInfo.UserMsg userMsg= (UserInfo.UserMsg) msg;
        System.out.println(userMsg.getName()+"--"+userMsg.getAge());
        ctx.writeAndFlush(resp(userMsg.getId()));
    }

    private UserInfo.UserMsg resp(int id) {
        UserInfo.UserMsg.Builder builder = UserInfo.UserMsg
                .newBuilder();
        builder.setAge(id);
        builder.setId(id);
        builder.setName("Netty"+id);
        return builder.build();
    }
}
