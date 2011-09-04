package com.shvid.klod.net;

import org.apache.log4j.Logger;

public class Agent extends ManagedServer {

  private final static Logger log = Logger.getLogger(Agent.class);
  
  protected void startServer(String bindHost, int bindPort, String serverDir) {
    
  }
  
  public static void main(String[] args) {
    
    System.out.println("Agent");
    try {
      new Agent().start("agent", args);
    }
    catch(Exception e) {
      log.error("server fail", e);
      System.exit(1);
    }
    
  }
  
}
