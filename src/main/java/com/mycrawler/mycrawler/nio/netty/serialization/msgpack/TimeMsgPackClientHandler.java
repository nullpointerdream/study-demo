package com.mycrawler.mycrawler.nio.netty.serialization.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-30 10:04
 **/

public class TimeMsgPackClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // TODO Auto-generated method stub
        //发送50个UserInfo给服务器，由于启用了粘包/拆包支持，所以这里连续发送多个也不会出现粘包的现象。
        for (int i = 0; i < 50; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i + "year");
            userInfo.setUsername(i+"senninha");
            ctx.write(userInfo);
        }
        ctx.flush();
        System.out.println("-----------------send over-----------------");


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        System.out.println("receive server : " + msg);

    }

}
