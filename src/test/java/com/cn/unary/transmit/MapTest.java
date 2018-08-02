package com.cn.unary.transmit;

import java.net.SocketAddress;

import com.cn.unary.transmit.server.UnaryConnect;
import com.cn.unary.transmit.server.UnaryConnetMap;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;

public class MapTest {
	
    public static void main(String[] args) throws Exception {
    	String ip1 = "10.10.1.51";
    	String mac1 = "1-1-1-1-11-1";
    	
    	ChannelHandlerContext ctx = new ChannelHandlerContext() {
    		@Override
    		public ChannelFuture bind(SocketAddress localAddress) {
    			return null;
    		}

    		@Override
    		public ChannelFuture connect(SocketAddress remoteAddress) {
    			return null;
    		}

    		@Override
    		public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
    			return null;
    		}

    		@Override
    		public ChannelFuture disconnect() {
    			return null;
    		}

    		@Override
    		public ChannelFuture close() {
    			return null;
    		}

    		@Override
    		public ChannelFuture deregister() {
    			return null;
    		}

    		@Override
    		public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
    			return null;
    		}

    		@Override
    		public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
    			return null;
    		}

    		@Override
    		public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress,
    				ChannelPromise promise) {
    			return null;
    		}

    		@Override
    		public ChannelFuture disconnect(ChannelPromise promise) {
    			return null;
    		}

    		@Override
    		public ChannelFuture close(ChannelPromise promise) {
    			return null;
    		}

    		@Override
    		public ChannelFuture deregister(ChannelPromise promise) {
    			return null;
    		}

    		@Override
    		public ChannelFuture write(Object msg) {
    			return null;
    		}

    		@Override
    		public ChannelFuture write(Object msg, ChannelPromise promise) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelFuture writeAndFlush(Object msg) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelPromise newPromise() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelProgressivePromise newProgressivePromise() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelFuture newSucceededFuture() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelFuture newFailedFuture(Throwable cause) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelPromise voidPromise() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public Channel channel() {
    			// TODO Auto-generated method stub
    			NioServerSocketChannel channel = new NioServerSocketChannel();
    			return channel;
    		}

    		@Override
    		public EventExecutor executor() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public String name() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandler handler() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public boolean isRemoved() {
    			// TODO Auto-generated method stub
    			return false;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelRegistered() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelUnregistered() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelActive() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelInactive() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireExceptionCaught(Throwable cause) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireUserEventTriggered(Object evt) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelRead(Object msg) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelReadComplete() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext fireChannelWritabilityChanged() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext read() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelHandlerContext flush() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ChannelPipeline pipeline() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public ByteBufAllocator alloc() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public <T> Attribute<T> attr(AttributeKey<T> key) {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public <T> boolean hasAttr(AttributeKey<T> key) {
    			// TODO Auto-generated method stub
    			return false;
    		}};
			
		UnaryConnetMap.addClientConnection(ctx, ip1, mac1);
		
		UnaryConnect info = UnaryConnetMap.getClientConnection(ip1);
		System.out.println("ConnetInfo: " + info);
		
    	String ip2 = "10.10.1.51";
    	String mac2 = "2-2-2-2-22-2";
		info = UnaryConnetMap.getClientConnection(ip2);
		System.out.println("ConnetInfo: " + info);
		UnaryConnetMap.addClientConnection(ctx, ip2, mac2);
		info = UnaryConnetMap.getClientConnection(ip2);
		System.out.println("ConnetInfo: " + info);
		
    	String ip3 = "1.1.1.1";
    	String mac3 = "3-3-3-3-3-3";
		UnaryConnetMap.addClientConnection(ctx, ip2, mac3);
		info = UnaryConnetMap.getClientConnection(ip2);
		System.out.println("ConnetInfo: " + info);
		
		int id1 = UnaryConnetMap.agentip2id(ip1);
		int id2 = UnaryConnetMap.agentip2id(ip2);
		System.out.println("id1: " + id1 +"  id2:"+id2);
		UnaryConnetMap.addClientConnection(ctx, ip3, mac3);
		UnaryConnetMap.addClientConnection(ctx, ip3, mac1);
		UnaryConnetMap.addClientConnection(ctx, ip3, mac2);
		info = UnaryConnetMap.getClientConnection(ip3);
		System.out.println("ConnetInfo: " + info);
    }
}
