package com.shvid.klod.net;

public class CacheServer extends ManagedServer {

  public static void main(String[] args) {
    
    System.out.println("CacheServer");
    new CacheServer().start(args);
    
  }
  
}
