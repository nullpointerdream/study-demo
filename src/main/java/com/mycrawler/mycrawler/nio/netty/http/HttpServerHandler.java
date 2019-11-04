package com.mycrawler.mycrawler.nio.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @program: mycrawler
 * @description: http请求类
 * @author: 陈家乐
 * @create: 2019-03-21 10:37
 **/

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        if(fullHttpRequest.decoderResult().isFailure()){
            sendError(channelHandlerContext, BAD_REQUEST);
            return;
        }

        String uri = sanitizeUri(fullHttpRequest.uri());
        if(uri.contains("favicon.ico")){
            return;
        }
        System.out.println(uri);
        File file=new File(uri);
        if(file.isHidden() || !file.exists()){
            sendError(channelHandlerContext, BAD_REQUEST);
            return;
        }

        if (file.isDirectory()) {
            sendListing(channelHandlerContext, file);
            return;
        }

        if (!file.isFile()) {
            sendError(channelHandlerContext, FORBIDDEN);
            return;
        }

        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");// 以只读的方式打开文件
        } catch (FileNotFoundException fnfe) {

        }
        long fileLength = randomAccessFile.length();
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);


        HttpHeaders headers = response.headers();
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        headers.add(HttpHeaderNames.CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
        headers.add(HttpHeaderNames.CONTENT_LENGTH, fileLength);
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        channelHandlerContext.write(response);

        channelHandlerContext.write(new ChunkedFile(randomAccessFile, 0,
                fileLength, 8192), channelHandlerContext.newProgressivePromise());

        ChannelFuture lastContentFuture = channelHandlerContext
                .writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

        }

    private static void sendListing(ChannelHandlerContext ctx, File dir) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        StringBuilder buf = new StringBuilder();
        String dirPath = dir.getPath();
        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append(dirPath);
        buf.append(" 目录：");
        buf.append("</title></head><body>\r\n");
        buf.append("<h3>");
        buf.append(dirPath).append(" 目录：");
        buf.append("</h3>\r\n");
        buf.append("<ul>");
        buf.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
        for (File f : dir.listFiles()) {
            if (f.isHidden() || !f.canRead()) {
                continue;
            }
            String name = f.getName();
            /*if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                continue;
            }*/
            buf.append("<li>链接：<a href=\"");
            buf.append(name);
            buf.append("\">");
            buf.append(name);
            buf.append("</a></li>\r\n");
        }
        buf.append("</ul></body></html>\r\n");
        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();
        ctx.writeAndFlush(response);
    }

    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
                throw new Error();
            }
        }


        uri = uri.replace('/', File.separatorChar);

        return System.getProperty("user.dir") + uri;
    }




    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("Failure: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response);
    }


}
