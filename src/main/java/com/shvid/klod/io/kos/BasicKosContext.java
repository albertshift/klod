package com.shvid.klod.io.kos;

import java.util.HashMap;
import java.util.Map;

public class BasicKosContext implements KosContext {

  private final static KoInstantiator REFLECTION_INSTANTIATOR = new ReflectionKoInstantiator();
  
  private Map<Integer, KoInstantiator> instantiators = new HashMap<Integer, KoInstantiator>();
  private Map<Integer, KoSerializer> serializers = new HashMap<Integer, KoSerializer>();
  private Map<Integer, Class<?>> classes = new HashMap<Integer, Class<?>>();
  
  public void registerPortable(int opcode, Class<?> clazz) {
    registerPortable(opcode, clazz, REFLECTION_INSTANTIATOR);
  }
  
  public void registerPortable(int opcode, Class<?> clazz, KoInstantiator instantiator) {
    if (!KosPortable.class.isAssignableFrom(clazz)) {
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
    return instantiators.get(opcode);
  }

  @Override
  public Class<?> getClass(int opcode) {
    return classes.get(opcode);
  }
  
  @Override
  public KoSerializer getSerializer(int opcode) {
    return serializers.get(opcode);
  }
  
}
