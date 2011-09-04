package com.shvid.klod.net;

import org.apache.log4j.Logger;

public class CacheServer extends ManagedServer {

  private final static Logger log = Logger.getLogger(CacheServer.class);
  
  protected void startServer(String bindHost, int bindPort, String serverDir) {
    
  }

  
  public static void main(String[] args) {
    
    System.out.println("CacheServer");
    try {
      new CacheServer().start("cacheserver", args);
    }
    catch(Exception e) {
      log.error("server fail", e);
      System.exit(1);
    }
    
  }
  
}
