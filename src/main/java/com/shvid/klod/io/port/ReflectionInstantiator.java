package com.shvid.klod.io.port;

import java.io.IOException;

public class ReflectionInstantiator implements KoInstantiator {

  @Override
  public Object instantiateKo(Class<?> clazz) throws IOException {
    try {
      return clazz.newInstance();
    }
    catch(Exception clause) {
      throw new IOException("fail to instantiate class " + clazz, clause);
    }
  }

}
