package com.google.android.android.common;

import java.lang.ref.WeakReference;

abstract class ParseItem
  extends Type
{
  private static final WeakReference<byte[]> description = new WeakReference(null);
  private WeakReference<byte[]> mChildren = description;
  
  ParseItem(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  protected abstract byte[] encode();
  
  final byte[] getBytes()
  {
    try
    {
      byte[] arrayOfByte2 = (byte[])mChildren.get();
      byte[] arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte2 == null)
      {
        arrayOfByte2 = encode();
        arrayOfByte1 = arrayOfByte2;
        mChildren = new WeakReference(arrayOfByte2);
      }
      return arrayOfByte1;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
