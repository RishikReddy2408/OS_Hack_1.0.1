package com.comfortclick.bosclient.helpers;

public class Converter
{
  protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
  
  public Converter() {}
  
  public static String bytesToHex(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt > paramArrayOfByte.length) {
      return "";
    }
    char[] arrayOfChar = new char[(paramArrayOfByte.length - paramInt) * 2];
    int i = paramInt;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      int k = (i - paramInt) * 2;
      arrayOfChar[k] = hexArray[(j >>> 4)];
      arrayOfChar[(k + 1)] = hexArray[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
}
