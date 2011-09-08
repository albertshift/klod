package com.shvid.klod.io.port;

import java.io.IOException;

public interface KosPortable {

  public void readFrom(KosReader reader) throws IOException;
  
  public void writeTo(KosWriter writer) throws IOException;
  
}
