package com.mycrawler.mycrawler.nio.netty.privateprotocol.hander;

import com.mycrawler.mycrawler.nio.netty.privateprotocol.MessageType;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.Header;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-06 10:17
 **/

public class LoginAuthReqHandler extends SimpleChannelInboundHandler<NettyMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage message) throws Exception {

        System.out.println("登录回复请求");
        //如果是握手应答消息，需要判断是否认证成功
        if(message.getHeader()!=null&&message.getHeader().getType()==MessageType.LOGIN_RESP.value()){
            byte loginResult = (Byte) message.getBody();
            if(loginResult!=(byte)0){
                ctx.close();
            }else{
                ctx.fireChannelRead(message);
            }
        }else{
            ctx.fireChannelRead(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
        System.out.println("--------------------------");
        ctx.fireExceptionCaught(cause);
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
}
