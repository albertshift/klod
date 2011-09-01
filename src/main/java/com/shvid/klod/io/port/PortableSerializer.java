package com.shvid.klod.io.port;

import java.io.IOException;

public class PortableSerializer implements KoSerializer {

  @Override
  public Object readKo(KoReader reader) throws IOException {
    KoContext context = reader.getContext();
    int opcode = reader.getOpcode();
    if (opcode < KoOpcode.USER_OPCODE) {
      
    }
    return null;
  }

  @Override
  public void writeKo(KoWriter writer, Object value) throws IOException {
    // TODO Auto-generated method stub
    
  }

}
