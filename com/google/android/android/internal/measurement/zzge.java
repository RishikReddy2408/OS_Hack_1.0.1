package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzge
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzge>
{
  private static volatile zzge[] zzawp;
  public Integer zzawq = null;
  public Long zzawr = null;
  
  public zzge()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzge[] zzmp()
  {
    if (zzawp == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzawp == null) {
          zzawp = new zzge[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzawp;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzge)) {
      return false;
    }
    paramObject = (zzge)paramObject;
    if (zzawq == null)
    {
      if (zzawq != null) {
        return false;
      }
    }
    else if (!zzawq.equals(zzawq)) {
      return false;
    }
    if (zzawr == null)
    {
      if (zzawr != null) {
        return false;
      }
    }
    else if (!zzawr.equals(zzawr)) {
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
    Integer localInteger = zzawq;
    int m = 0;
    int i;
    if (localInteger == null) {
      i = 0;
    } else {
      i = zzawq.hashCode();
    }
    int j;
    if (zzawr == null) {
      j = 0;
    } else {
      j = zzawr.hashCode();
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
    if (zzawq != null) {
      i = j + zzyy.sendCommand(1, zzawq.intValue());
    }
    if (zzawr != null) {
      return i + zzyy.write(2, zzawr.longValue());
    }
    return i;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzawq != null) {
      paramZzyy.addItem(1, zzawq.intValue());
    }
    if (zzawr != null) {
      paramZzyy.add(2, zzawr.longValue());
    }
    super.multiply(paramZzyy);
  }
}
