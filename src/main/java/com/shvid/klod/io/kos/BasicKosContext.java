package com.shvid.klod.io.kos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BasicKosContext extends SystemKosContext {

  private Map<Integer, KoInstantiator> instantiators = new ConcurrentHashMap<Integer, KoInstantiator>();
  private Map<Integer, KoSerializer> serializers = new ConcurrentHashMap<Integer, KoSerializer>();
  private Map<Integer, Class<?>> classes = new ConcurrentHashMap<Integer, Class<?>>();
  
  public void registerPortable(int opcode, Class<?> clazz) {
    registerPortable(opcode, clazz, REFLECTION_INSTANTIATOR);
  }
  
  public void registerPortable(int opcode, Class<?> clazz, KoInstantiator instantiator) {
    if (!KoPortable.class.isAssignableFrom(clazz)) {
      throw new IllegalArgumentException("class does not implements KoPortable interface");
    }
    instantiators.put(opcode, instantiator);
    classes.put(opcode, clazz);
  }

  public void registerSerializer(int opcode, Class<?> clazz, KoSerializer serializer) {
    serializers.put(opcode, serializer);
    classes.put(opcode, clazz);
  }
  
  public void unregisterOpcode(int opcode) {
    instantiators.remove(opcode);
    serializers.remove(opcode);
    classes.remove(opcode);
  }

  @Override
  public KoInstantiator getInstantiator(int opcode) {
    KoInstantiator instantiator = super.getInstantiator(opcode);
    if (instantiator != null) {
      return instantiator;
    }
    if (opcode >= KoOpcode.USER_OPCODE) {
      return instantiators.get(opcode);
    }
    return null;
  }

  @Override
  public Class<?> getClass(int opcode) {
    Class<?> clazz = super.getClass(opcode);
    if (clazz != null) {
      return clazz;
    }
    if (opcode >= KoOpcode.USER_OPCODE) {
      return classes.get(opcode);
    }
    return null;
  }
  
  @Override
  public KoSerializer getSerializer(int opcode) {
    KoSerializer serializer = super.getSerializer(opcode);
    if (serializer != null) {
      return serializer;
    }
    if (opcode >= KoOpcode.USER_OPCODE) {
      return serializers.get(opcode);
    }
    return null;
  }
  
}
