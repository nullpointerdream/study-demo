package com.mycrawler.mycrawler.nio.netty.serialization.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-10-31 09:46
 **/

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final int length = msg.readableBytes();
        byte[] b = new byte[length];
        msg.readBytes(b);
        MessagePack msgpack = new MessagePack();
        out.add(msgpack.read(b));
    }
}
