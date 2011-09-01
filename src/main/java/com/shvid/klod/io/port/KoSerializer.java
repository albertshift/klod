package com.shvid.klod.io.port;

import java.io.IOException;

public interface KoSerializer {

  public Object readKo(KoReader reader) throws IOException;
  
  public void writeKo(KoWriter writer, Object value) throws IOException;
  
}
