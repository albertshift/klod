/*
 * Copyright (c) Alex Shvid. All Rights Reserved. 2011
 */

package com.shvid.klod.net;

import java.util.concurrent.ThreadPoolExecutor;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;

public class LocatorPipelineFactory implements ChannelPipelineFactory {

  private final ThreadPoolExecutor pipelineExecutor;
  private final ClientSocketChannelFactory clientFactory;
  
  public LocatorPipelineFactory(ThreadPoolExecutor pipelineExecutor, ClientSocketChannelFactory clientFactory) {
    super();
    this.pipelineExecutor = pipelineExecutor;
    this.clientFactory = clientFactory;
  }
  
  public ChannelPipeline getPipeline() throws Exception {
    ChannelPipeline pipeline = Channels.pipeline();
    pipeline.addLast("pipelineExecutor", new ExecutionHandler(pipelineExecutor));
    pipeline.addLast("handler", new LocatorServerHandler(clientFactory));
    return pipeline;
  }

}
