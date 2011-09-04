package com.shvid.klod.net;

import org.apache.log4j.Logger;

public class Locator extends ManagedServer {
  
  private final static Logger log = Logger.getLogger(Locator.class);
  
  protected void startServer(String bindHost, int bindPort, String serverDir) {

    int threads = Integer.getInteger("klod.locator.threads", 2);
    
    
    
  }

  public static void main(String[] args) {

    System.out.println("Locator");
    try {
      new Locator().start("locator", args);
    }
    catch(Exception e) {
      log.error("server fail", e);
      System.exit(1);
    }

  }

}
