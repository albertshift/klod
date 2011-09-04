package com.shvid.klod.net;

import org.apache.log4j.Logger;

public class LoadBalancer extends ManagedServer {

  private final static Logger log = Logger.getLogger(LoadBalancer.class);
  
  protected void startServer(String bindHost, int bindPort, String serverDir) {
    
  }

  
  public static void main(String[] args) {
    
    System.out.println("LoadBalancer");
    try {
      new LoadBalancer().start("loadbalancer", args);
    }
    catch(Exception e) {
      log.error("server fail", e);
      System.exit(1);
    }
    
  }
  
}
