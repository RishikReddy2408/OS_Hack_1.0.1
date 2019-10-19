package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgc
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgc>
{
  private static volatile zzgc[] zzawk;
  public String value = null;
  public String zzoj = null;
  
  public zzgc()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzgc[] zzmn()
  {
    if (zzawk == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzawk == null) {
          zzawk = new zzgc[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzawk;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgc)) {
      return false;
    }
    paramObject = (zzgc)paramObject;
    if (zzoj == null)
    {
      if (zzoj != null) {
        return false;
      }
    }
    else if (!zzoj.equals(zzoj)) {
      return false;
    }
    if (value == null)
    {
      if (value != null) {
        return false;
      }
    }
    else if (!value.equals(value)) {
      return false;
    }
    if ((zzcfc != null) && (!zzcfc.isEmpty())) {
      return zzcfc.equals(zzcfc);
    }
    if (zzcfc != null) {
      return zzcfc.isEmpty();
    }
    return true;
  }
  
  public final int hashCode()
  {
    int n = getClass().getName().hashCode();
    String str = zzoj;
    int m = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = zzoj.hashCode();
    }
    int j;
    if (value == null) {
      j = 0;
    } else {
      j = value.hashCode();
    }
    int k = m;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        k = m;
      } else {
        k = zzcfc.hashCode();
      }
    }
    return (((n + 527) * 31 + i) * 31 + j) * 31 + k;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (zzoj != null) {
      i = j + zzyy.setProperty(1, zzoj);
    }
    if (value != null) {
      return i + zzyy.setProperty(2, value);
    }
    return i;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzoj != null) {
      paramZzyy.add(1, zzoj);
    }
    if (value != null) {
      paramZzyy.add(2, value);
    }
    super.multiply(paramZzyy);
  }
}
