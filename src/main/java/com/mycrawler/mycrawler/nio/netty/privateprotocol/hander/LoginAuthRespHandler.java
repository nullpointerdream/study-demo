package com.mycrawler.mycrawler.nio.netty.privateprotocol.hander;

import com.mycrawler.mycrawler.nio.netty.privateprotocol.MessageType;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.Header;
import com.mycrawler.mycrawler.nio.netty.privateprotocol.model.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAuthRespHandler extends SimpleChannelInboundHandler<NettyMessage> {

	private Map<String,Boolean> nodeCheck = new ConcurrentHashMap<String,Boolean>();
	
	private String[] whiteList = {"127.0.0.1","10.1.2.95"};
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, NettyMessage message) throws Exception {
		System.out.println("客户端登录");
		if(message.getHeader()!=null&&message.getHeader().getType()== MessageType.LOGIN_REQ.value()){
			String nodeIndex = ctx.channel().remoteAddress().toString();
			NettyMessage loginResp = null;
			//重复登录，拒绝
			if(nodeCheck.containsKey(nodeIndex)){
				loginResp = buildResponse((byte)-1);
			}else{
				InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
				String ip = address.getAddress().getHostAddress();
				boolean isOK = false;
				for(String WIP:whiteList){
					if(WIP.equals(ip)){
						isOK = true;
					}
				}
				loginResp = isOK?buildResponse((byte)0):buildResponse((byte)-1);
				if(isOK)nodeCheck.put(ip, true);
				
				ctx.writeAndFlush(loginResp);
			}
		}else{
			ctx.fireChannelRead(message);
		}
	}

	private NettyMessage buildResponse(byte b) {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_RESP.value());
		message.setHeader(header);
		message.setBody(b);
		return message;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
			nodeCheck.remove(ctx.channel().remoteAddress().toString());
			ctx.close();
			ctx.fireExceptionCaught(cause);
	}
	
	
	
	
}