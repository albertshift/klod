package com.shvid.klod.net;

public class LoadBalancer extends ManagedServer {

  public static void main(String[] args) {
    
    System.out.println("LoadBalancer");
    new LoadBalancer().start(args);
    
  }
  
}
