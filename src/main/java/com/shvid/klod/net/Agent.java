package com.shvid.klod.net;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;

public class Agent extends ManagedServer {

  private final static Logger log = Logger.getLogger(Agent.class);
  
  @Override
  protected ChannelPipelineFactory createPipelineFactory(ThreadPoolExecutor pipelineExecutor, ClientSocketChannelFactory clientFactory) {
    return null;
  }

  public static void main(String[] args) {
    launch(new Agent(), "agent", args);
  }
  
}
