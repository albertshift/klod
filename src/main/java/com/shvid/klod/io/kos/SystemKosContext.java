package com.shvid.klod.io.kos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.shvid.klod.net.messages.DistributionSystemMessage;
import com.shvid.klod.net.messages.JoinClusterRequestMessage;

public class SystemKosContext implements KosContext {

  public final static KoInstantiator REFLECTION_INSTANTIATOR = new ReflectionKoInstantiator();

  private final static Map<Integer, KoInstantiator> instantiators = new HashMap<Integer, KoInstantiator>();
  private final static Map<Integer, Class<?>> classes = new HashMap<Integer, Class<?>>();
  
  static {
    int opcode = KoOpcode.SYSTEM_OPCODE;
    register(opcode++, DistributionSystemMessage.class, new KoInstantiator() {
      @Override
      public Object instantiateKo(Class<?> clazz) throws IOException {
        return new DistributionSystemMessage();
      }
    });
    register(opcode++, JoinClusterRequestMessage.class, new KoInstantiator() {
      @Override
      public Object instantiateKo(Class<?> clazz) throws IOException {
        return new JoinClusterRequestMessage();
      }
    });    
  }
  
  private static void register(int opcode, Class<?> clazz, KoInstantiator instantiator) {
    instantiators.put(opcode, instantiator);
    classes.put(opcode, clazz);
  }

  @Override
  public KoInstantiator getInstantiator(int opcode) {
    if (opcode >= KoOpcode.SYSTEM_OPCODE && opcode < KoOpcode.USER_OPCODE) {
      return instantiators.get(opcode);
    }
    return null;
  }

  @Override
  public Class<?> getClass(int opcode) {
    if (opcode >= KoOpcode.SYSTEM_OPCODE && opcode < KoOpcode.USER_OPCODE) {
      return classes.get(opcode);
    }
    return null;
  }
  
  @Override
  public KoSerializer getSerializer(int opcode) {
    return null;
  }

}
