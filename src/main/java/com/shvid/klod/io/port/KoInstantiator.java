package com.shvid.klod.io.port;

import java.io.IOException;

public interface KoInstantiator {

  public Object instantiateKo(Class<?> clazz) throws IOException;
  
}
