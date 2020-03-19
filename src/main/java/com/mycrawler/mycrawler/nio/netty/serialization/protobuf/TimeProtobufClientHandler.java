package com.mycrawler.mycrawler.nio.netty.serialization.protobuf;

import com.mycrawler.mycrawler.nio.netty.serialization.msgpack.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 11:14
 **/

public class TimeProtobufClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*for (int i = 0; i < 100; i++) {
            ctx.write(res(i));
        }
        ctx.flush();
*/
    }


    /*private UserInfo.UserMsg res(int id) {
        UserInfo.UserMsg.Builder builder = UserInfo.UserMsg
                .newBuilder();
        builder.setAge(id);
        builder.setId(id+20);
        builder.setName("mysql"+id);
        return builder.build();
    }*/

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
       /* UserInfo.UserMsg userMsg= (UserInfo.UserMsg) msg;
        System.out.println("revice --"+userMsg.getName()+"--"+userMsg.getAge());*/
    }

}
