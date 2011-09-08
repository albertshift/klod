package com.shvid.klod.io.port;

import java.io.IOException;

public class ReflectionInstantiator implements KoInstantiator {

  @Override
  public Object instantiateKo(Class<?> clazz) throws IOException {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      throw new IOException("Class '" + clazz.getCanonicalName() + "' could not be instantiated because it is not a concrete class.", e);
    } catch (IllegalAccessException e) {
      throw new IOException("Class '" + clazz.getCanonicalName() + "' could not be instantiated because it is not public.", e);
    }
  }

}
