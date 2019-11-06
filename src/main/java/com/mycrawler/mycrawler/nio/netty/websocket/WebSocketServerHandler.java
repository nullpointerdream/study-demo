package com.mycrawler.mycrawler.nio.netty.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.BAD_REQUEST;

/**
 * @program: mycrawler
 * @description:
 * @author: chenjiale
 * @create: 2019-11-05 09:37
 **/
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }// WebSocket接入
        else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        Thread.sleep(3000);
        ctx.channel().writeAndFlush(
                new TextWebSocketFrame("你好"));

    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是Ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 判断是否是关闭链路的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),
                    (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println(String.format("%s received %s", ctx.channel(), request));
        ctx.channel().write(
                new TextWebSocketFrame(request
                        + " , 欢迎使用Netty WebSocket服务，现在时刻："
                        + new java.util.Date().toString()));
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg) {
            if(msg.decoderResult().isFailure()||!"websocket".equals(msg.headers().get("Upgrade"))){
                sendHttpResponse(ctx, msg, new DefaultFullHttpResponse(HTTP_1_1,BAD_REQUEST));
                             return;
            }
        WebSocketServerHandshakerFactory wsFactory=new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        handshaker = wsFactory.newHandshaker(msg);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), msg);
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
