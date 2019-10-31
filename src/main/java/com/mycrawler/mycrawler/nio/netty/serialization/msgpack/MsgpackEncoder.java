package com.mycrawler.mycrawler.nio.netty.serialization.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 09:44
 **/

public class MsgpackEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MessagePack messagePack =new MessagePack();
        byte[] write = messagePack.write(msg);
        out.writeBytes(write);
    }
}
