package com.shvid.klod.net;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;

public class CacheServer extends ManagedServer {

  private final static Logger log = Logger.getLogger(CacheServer.class);
  
  @Override
  protected String getName() {
    return "cacheserver";
  }
  
  @Override
  protected ChannelPipelineFactory createPipelineFactory(ThreadPoolExecutor pipelineExecutor, ClientSocketChannelFactory clientFactory) {
    return null;
  }

  public static void main(String[] args) {
    launch(new CacheServer(), args);
  }
  
}
