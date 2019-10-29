package com.mycrawler.mycrawler.nio.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

/**
 * @program: mycrawler
 * @description: http请求类
 * @author: 陈家乐
 * @create: 2019-03-21 10:37
 **/

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        String method = fullHttpRequest.method().name();
        String uri = fullHttpRequest.uri();
        System.out.println(method+" "+uri);
        ByteBuf byteBuf = fullHttpRequest.content();
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String requestStr = new String(bytes, "UTF-8");
        System.out.println(requestStr);

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
        ,Unpooled.wrappedBuffer("你才是猪！".getBytes()));

        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        channelHandlerContext.write(response);


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ctx...");
        super.channelReadComplete(ctx);
        ctx.flush();
    }


}
