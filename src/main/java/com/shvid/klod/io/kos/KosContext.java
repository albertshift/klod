package com.shvid.klod.io.kos;

/*
 * Klod object serialization (Kos) context
 */
public interface KosContext {

  public KoInstantiator getInstantiator(int opcode);

  public Class<?> getClass(int opcode);

  public KoSerializer getSerializer(int opcode);
  
}
