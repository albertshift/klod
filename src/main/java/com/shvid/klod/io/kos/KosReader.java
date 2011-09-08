package com.shvid.klod.io.kos;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/*
 * Klod object serialization reader
 */
public interface KosReader {

  public String readString(int id) throws IOException;

  public boolean readBoolean(int id) throws IOException;
  
  public byte readByte(int id) throws IOException;

  public byte[] readByteArray(int id) throws IOException;

  public char readChar(int id) throws IOException;
  
  public short readShort(int id) throws IOException;

  public int readInt(int id) throws IOException;

  public long readLong(int id) throws IOException;

  public float readFloat(int id) throws IOException;
  
  public double readDouble(int id) throws IOException;

  public BigDecimal readBigDecimal(int id) throws IOException;

  public Date readDate(int id) throws IOException;
  
  public Object readObject(int id) throws IOException;

  public Object[] readObjectArray(int id) throws IOException;
  
  public KosContext getContext();
  
  public int getOpcode();
  
}
