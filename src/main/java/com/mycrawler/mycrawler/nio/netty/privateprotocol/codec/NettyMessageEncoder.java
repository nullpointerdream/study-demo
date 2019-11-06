package com.mycrawler.mycrawler.nio.netty.privateprotocol.codec;

import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-06 09:38
 **/

public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    private MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        marshallingEncoder = new MarshallingEncoder();
    }
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {
        if(msg==null||msg.getHeader()==null){
            throw new Exception("The encode message is null");
        }

        out.writeInt(msg.getHeader().getCrcCode());
        out.writeInt(msg.getHeader().getLength());
        out.writeLong(msg.getHeader().getSessionID());
        out.writeByte(msg.getHeader().getType());
        out.writeByte(msg.getHeader().getPriority());
        out.writeInt(msg.getHeader().getAttachment().size());
        String key=null;
        byte[] keyBytes=null;
        Object value= null;
        for(Map.Entry<String, Object> param:msg.getHeader().getAttachment().entrySet()){
            key= param.getKey();
            keyBytes = key.getBytes("UTF-8");
            out.writeInt(keyBytes.length);
            out.writeBytes(keyBytes);
            value = param.getValue();
            marshallingEncoder.encode(value,out);
        }
        key=null;
        keyBytes=null;
        value= null;
        if(msg.getBody()!=null) {
            marshallingEncoder.encode(msg.getBody(), out);
        }else {
            out.writeInt(0);
        }
        out.setInt(4, out.readableBytes() - 8);



    }
}
