package com.shvid.klod.io.port;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/*
 * Klod object serialization writer
 */
public interface KosWriter {

  public void writeString(int id, String value) throws IOException;

  public void writeBoolean(int id, boolean value) throws IOException;
  
  public void writeByte(int id, byte value) throws IOException;

  public void writeByteArray(int id, byte[] value) throws IOException;

  public void writeChar(int id, char value) throws IOException;
  
  public void writeShort(int id, short value) throws IOException;

  public void writeInt(int id, int value) throws IOException;

  public void writeLong(int id, long value) throws IOException;

  public void writeFloat(int id, float value) throws IOException;
  
  public void writeDouble(int id, double value) throws IOException;

  public void writeBigDecimal(int id, BigDecimal value) throws IOException;

  public void writeDate(int id, Date date) throws IOException;
  
  public void writeObject(int id, Object object) throws IOException;
  
  public void writeObjectArray(int id, Object[] value) throws IOException;
 
  public KosContext getContext();
  
}
