package com.shvid.klod.io.port;

import java.io.IOException;

/*
 * Klod Object Instantiator 
 */

public interface KoInstantiator {

  /*
   * Instantiate Klod Object
   */
  public Object instantiateKo(Class<?> clazz) throws IOException;
  
}
