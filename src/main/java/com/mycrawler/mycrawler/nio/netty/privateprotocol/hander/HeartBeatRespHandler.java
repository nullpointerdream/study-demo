package com.mycrawler.mycrawler.nio.netty.privateprotocol.hander;

import com.mycrawler.mycrawler.nio.netty.privateprotocol.MessageType;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.Header;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatRespHandler extends SimpleChannelInboundHandler<NettyMessage> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, NettyMessage message)
			throws Exception {
			// 返回心跳应答消息
		System.out.println("客户端心跳请求");
			if (message.getHeader() != null
				&& message.getHeader().getType() == MessageType.HEARTBEAT_REQ
					.value()) {
			    NettyMessage heartBeat = buildHeatBeat();
			    ctx.writeAndFlush(heartBeat);
			} else
			    ctx.fireChannelRead(message);
	  }
	
	private NettyMessage buildHeatBeat() {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.HEARTBEAT_RESP.value());
		message.setHeader(header);
		return message;
	 }
}