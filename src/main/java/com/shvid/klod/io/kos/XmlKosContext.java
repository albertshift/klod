package com.shvid.klod.io.kos;

import java.util.HashMap;
import java.util.Map;

public class XmlKosContext implements KosContext {

  private Map<Integer, KoInstantiator> instantiators;
  private Map<Integer, KoSerializer> serializers;
  private Map<Integer, Class<?>> classes;
  
  public void load(String xmlFile) {
    instantiators = new HashMap<Integer, KoInstantiator>();
    serializers = new HashMap<Integer, KoSerializer>();
    classes = new HashMap<Integer, Class<?>>();
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
