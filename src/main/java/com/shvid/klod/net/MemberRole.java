package com.shvid.klod.net;

public enum MemberRole {

  LOCATOR("locator"),
  AGENT("agent"),
  CACHE_SERVER("cacheserver"),
  LOAD_BALANCER("loadbalancer"),
  CLIENT("client");
  
  private String role;
  
  MemberRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

}
