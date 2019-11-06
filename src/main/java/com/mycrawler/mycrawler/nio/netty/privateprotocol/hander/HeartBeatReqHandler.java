package com.mycrawler.mycrawler.nio.netty.privateprotocol.hander;

import com.mycrawler.mycrawler.nio.netty.privateprotocol.MessageType;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.Header;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends SimpleChannelInboundHandler<NettyMessage> {
	private volatile ScheduledFuture<?> heartBeat;
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, NettyMessage message)
			throws Exception {
		System.out.println("心跳回复请求");
		//如果握手成功，主动发送心跳消息
		if(message.getHeader()!=null&&message.getHeader().getType()== MessageType.LOGIN_RESP.value()){
			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 
					0, 5000, TimeUnit.MILLISECONDS);
		}else if(message.getHeader()!=null&&message.getHeader().getType()==MessageType.HEARTBEAT_RESP.value()){
		}else{
			super.channelRead(ctx, message);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		if(heartBeat!=null){
			heartBeat.cancel(true);
			heartBeat=null;
		}
		ctx.fireExceptionCaught(cause);
	}

	private class HeartBeatTask implements Runnable{
		private final ChannelHandlerContext ctx;
		public HeartBeatTask(final ChannelHandlerContext ctx){
			this.ctx =ctx;
		}
		public void run() {
			NettyMessage heatBeat  = buildHeatBeat();
			ctx.writeAndFlush(heatBeat);
		}
		
		private NettyMessage buildHeatBeat() {
			NettyMessage message  = new NettyMessage();
			Header header = new Header();
			header.setType(MessageType.HEARTBEAT_REQ.value());
			message.setHeader(header);
			return message;
		}
		
	}
}