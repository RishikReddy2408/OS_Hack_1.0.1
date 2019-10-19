package com.google.android.android.internal.measurement;

import java.util.Arrays;

final class zzzi
{
  final int columns;
  final byte[] zzbug;
  
  zzzi(int paramInt, byte[] paramArrayOfByte)
  {
    columns = paramInt;
    zzbug = paramArrayOfByte;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzzi)) {
      return false;
    }
    paramObject = (zzzi)paramObject;
    return (columns == columns) && (Arrays.equals(zzbug, zzbug));
  }
  
  public final int hashCode()
  {
    return (columns + 527) * 31 + Arrays.hashCode(zzbug);
  }
}
