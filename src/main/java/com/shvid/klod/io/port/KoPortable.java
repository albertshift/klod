package com.shvid.klod.io.port;

import java.io.IOException;

public interface KoPortable {

  public void readFrom(KoReader reader) throws IOException;
  
  public void writeTo(KoWriter writer) throws IOException;
  
}
