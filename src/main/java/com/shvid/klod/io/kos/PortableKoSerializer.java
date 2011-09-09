package com.shvid.klod.io.kos;

import java.io.IOException;

public class PortableKoSerializer implements KoSerializer {

  @Override
  public Object readKo(KosReader reader) throws IOException {
    KosContext context = reader.getKosContext();
    int opcode = reader.getOpcode();
    KoInstantiator instantiator = context.getInstantiator(opcode);
    if (instantiator != null) {
      Object obj = instantiator.instantiateKo(context.getClass(opcode));
      if (obj instanceof KoPortable) {
        KoPortable portableObject = (KoPortable) obj;
        portableObject.readFrom(reader);
        return portableObject;
      }
    }
    KoSerializer serializer = context.getSerializer(opcode);
    if (serializer != null) {
      return serializer.readKo(reader);
    }
    return null;
  }

  @Override
  public void writeKo(KosWriter writer, Object value) throws IOException {
    if (value == null) {
      //writer.writeNull();
    }
    
  }

}
