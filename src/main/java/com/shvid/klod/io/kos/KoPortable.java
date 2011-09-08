package com.shvid.klod.io.kos;

import java.io.IOException;

/*
 * Klod object portable 
 */
public interface KoPortable {

  public void readFrom(KosReader reader) throws IOException;
  
  public void writeTo(KosWriter writer) throws IOException;
  
}
