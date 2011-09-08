/*
 * Copyright (c) Alex Shvid. All Rights Reserved. 2011
 */

package com.shvid.klod.net;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;

public class LocatorServerHandler extends SimpleChannelHandler {

  private final static Logger log = Logger.getLogger(LocatorServerHandler.class);
  
  private final ClientSocketChannelFactory clientFactory;
  
  private static AtomicInteger connectionIdFactory = new AtomicInteger(10000);
  
  private Channel outboundChannel = null;
  private int connectionId = 0;
  private AtomicInteger num = new AtomicInteger(0);
  
  public LocatorServerHandler(ClientSocketChannelFactory clientFactory) {
    super();
    this.clientFactory = clientFactory;
  }

  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    ChannelBuffer msg = (ChannelBuffer) e.getMessage();
  }
  
  @Override
  public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    closeOnFlush(e.getChannel());
  }
  
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
    log.error("Unexpected exception from downstream.", e.getCause());
    Channels.close(e.getChannel());
    if (outboundChannel != null && outboundChannel.isConnected()) {
      Channels.close(outboundChannel);
    }
  }
  
  private static void closeOnFlush(Channel ch) {
    if (ch.isConnected()) {
      ch.write(ChannelBuffers.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
  }
  
}
