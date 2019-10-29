package com.mycrawler.mycrawler.nio.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable                                //1
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for(int i=0;i<2;i++) {
            //tcp粘包demo
            byte[] req = null;
            ByteBuf buffer = null;
            // req=ByteBufUtil.decodeHexDump("0200002d010000000000007b000000070000000600000001000000020003000400051904061915541206000000000000110100e3040000000bfe7e");
          //  req=ByteBufUtil.decodeHexDump("7e0002000001983002708401520e7e");
          //  req=ByteBufUtil.decodeHexDump("7e0102000301983002708400013132336e7e");
            // req=ByteBufUtil.decodeHexDump("78781101086812020900631820043202000cb57a0D0A");

            //req = ByteBufUtil.decodeHexDump("79790020940a08681202090063180460043545517409898604151518916974090005c36e0D0A");
            // req=ByteBufUtil.decodeHexDump("787822220F0C1D023305C9027AC8180C46586000140001CC00287D001F71000001000820860D0A");
              if(i==0) {
                  req = ByteBufUtil.decodeHexDump("7878110108681202090063182004320200070ba90D0A");
              }
                  //req=ByteBufUtil.decodeHexDump("7e0002000001983002708401520e7e0D0A");
              else {
                  //  req=ByteBufUtil.decodeHexDump("787822220F0C1D023305C9027AC8180C46586000140001CC00287D001F71000001000820860D0A");

                  req = ByteBufUtil.decodeHexDump("787822221306110c101ecf04487cf00c7e90a02ad45901cc0010b600073d01000000b4a6a80D0A");
              }
                buffer = Unpooled.buffer(req.length);
            buffer.writeBytes(req);

            ctx.writeAndFlush(buffer);
        }


        //拆包demo
       /* byte[] req = null;
        ByteBuf buffer = null;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<100;i++){
            sb.append("abcdefghijklmnopqrstuvwxyz");
        }
        req = sb.toString().getBytes();
        buffer = Unpooled.buffer(req.length);
        buffer.writeBytes(req);
        ctx.writeAndFlush(buffer);*/


    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Client received: " +  ByteBufUtil.hexDump(in));    //3
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println(111);//4
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args) {
        System.out.println(Integer.toHexString(29185));

    }
}
