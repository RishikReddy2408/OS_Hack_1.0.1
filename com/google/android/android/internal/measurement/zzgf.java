package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgf
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgf>
{
  private static volatile zzgf[] zzaws;
  public Integer count = null;
  public String name = null;
  public zzgg[] zzawt = zzgg.zzmr();
  public Long zzawu = null;
  public Long zzawv = null;
  
  public zzgf()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzgf[] zzmq()
  {
    if (zzaws == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzaws == null) {
          zzaws = new zzgf[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzaws;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgf)) {
      return false;
    }
    paramObject = (zzgf)paramObject;
    if (!zzze.equals(zzawt, zzawt)) {
      return false;
    }
    if (name == null)
    {
      if (name != null) {
        return false;
      }
    }
    else if (!name.equals(name)) {
      return false;
    }
    if (zzawu == null)
    {
      if (zzawu != null) {
        return false;
      }
    }
    else if (!zzawu.equals(zzawu)) {
      return false;
    }
    if (zzawv == null)
    {
      if (zzawv != null) {
        return false;
      }
    }
    else if (!zzawv.equals(zzawv)) {
      return false;
    }
    if (count == null)
    {
      if (count != null) {
        return false;
      }
    }
    else if (!count.equals(count)) {
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
    int i2 = getClass().getName().hashCode();
    int i3 = zzze.hashCode(zzawt);
    String str = name;
    int i1 = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = name.hashCode();
    }
    int j;
    if (zzawu == null) {
      j = 0;
    } else {
      j = zzawu.hashCode();
    }
    int k;
    if (zzawv == null) {
      k = 0;
    } else {
      k = zzawv.hashCode();
    }
    int m;
    if (count == null) {
      m = 0;
    } else {
      m = count.hashCode();
    }
    int n = i1;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        n = i1;
      } else {
        n = zzcfc.hashCode();
      }
    }
    return ((((((i2 + 527) * 31 + i3) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  protected final int multiply()
  {
    int i = super.multiply();
    int j = i;
    if (zzawt != null)
    {
      j = i;
      if (zzawt.length > 0)
      {
        int k = 0;
        for (;;)
        {
          j = i;
          if (k >= zzawt.length) {
            break;
          }
          zzgg localZzgg = zzawt[k];
          j = i;
          if (localZzgg != null) {
            j = i + zzyy.addMenuItem(1, localZzgg);
          }
          k += 1;
          i = j;
        }
      }
    }
    i = j;
    if (name != null) {
      i = j + zzyy.setProperty(2, name);
    }
    j = i;
    if (zzawu != null) {
      j = i + zzyy.write(3, zzawu.longValue());
    }
    i = j;
    if (zzawv != null) {
      i = j + zzyy.write(4, zzawv.longValue());
    }
    j = i;
    if (count != null) {
      j = i + zzyy.sendCommand(5, count.intValue());
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if ((zzawt != null) && (zzawt.length > 0))
    {
      int i = 0;
      while (i < zzawt.length)
      {
        zzgg localZzgg = zzawt[i];
        if (localZzgg != null) {
          paramZzyy.writeHeader(1, localZzgg);
        }
        i += 1;
      }
    }
    if (name != null) {
      paramZzyy.add(2, name);
    }
    if (zzawu != null) {
      paramZzyy.add(3, zzawu.longValue());
    }
    if (zzawv != null) {
      paramZzyy.add(4, zzawv.longValue());
    }
    if (count != null) {
      paramZzyy.addItem(5, count.intValue());
    }
    super.multiply(paramZzyy);
  }
}
