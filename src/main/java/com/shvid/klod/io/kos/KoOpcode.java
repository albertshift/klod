package com.shvid.klod.io.kos;

/*
 * Klod Object Opcode
 */
public enum KoOpcode {
  
  NULL(0),
  BOOLEAN(1),
  BYTE(2),
  CHAR(3),
  SHORT(4),
  INT(5),
  LONG(6),
  FLOAT(7),
  DOUBLE(8),
  BIGDECIMAL(9),
  DATE(10),
  KO_OBJECT(11),
  OBJECT(12);
  
  private int opcode;
  
  public static final int USER_OPCODE = 1000;
  
  KoOpcode(int opcode) {
    this.opcode = opcode;
  }

  public int getOpcode() {
    return opcode;
  }

}
