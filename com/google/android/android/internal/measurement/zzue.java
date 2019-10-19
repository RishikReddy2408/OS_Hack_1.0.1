package com.google.android.android.internal.measurement;

import java.util.NoSuchElementException;

final class zzue
  implements zzuj
{
  private final int limit = zzbuc.size();
  private int position = 0;
  
  zzue(zzud paramZzud) {}
  
  public final boolean hasNext()
  {
    return position < limit;
  }
  
  public final byte nextByte()
  {
    zzud localZzud = zzbuc;
    int i = position;
    position = (i + 1);
    try
    {
      byte b = localZzud.zzal(i);
      return b;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      throw new NoSuchElementException(localIndexOutOfBoundsException.getMessage());
    }
  }
  
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}
