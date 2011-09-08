package com.shvid.klod.io.kos;

import java.io.IOException;

/*
 * Klod Object Serializer
 */
public interface KoSerializer {

  /*
   * read Klod Object
   */
  public Object readKo(KosReader reader) throws IOException;
  
  
  /*
   * write Klod Object 
   */
  public void writeKo(KosWriter writer, Object value) throws IOException;
  
}
