package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgg
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgg>
{
  private static volatile zzgg[] zzaww;
  public String name = null;
  public String zzamp = null;
  private Float zzaug = null;
  public Double zzauh = null;
  public Long zzawx = null;
  
  public zzgg()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzgg[] zzmr()
  {
    if (zzaww == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzaww == null) {
          zzaww = new zzgg[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzaww;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgg)) {
      return false;
    }
    paramObject = (zzgg)paramObject;
    if (name == null)
    {
      if (name != null) {
        return false;
      }
    }
    else if (!name.equals(name)) {
      return false;
    }
    if (zzamp == null)
    {
      if (zzamp != null) {
        return false;
      }
    }
    else if (!zzamp.equals(zzamp)) {
      return false;
    }
    if (zzawx == null)
    {
      if (zzawx != null) {
        return false;
      }
    }
    else if (!zzawx.equals(zzawx)) {
      return false;
    }
    if (zzaug == null)
    {
      if (zzaug != null) {
        return false;
      }
    }
    else if (!zzaug.equals(zzaug)) {
      return false;
    }
    if (zzauh == null)
    {
      if (zzauh != null) {
        return false;
      }
    }
    else if (!zzauh.equals(zzauh)) {
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
    int i3 = getClass().getName().hashCode();
    String str = name;
    int i2 = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = name.hashCode();
    }
    int j;
    if (zzamp == null) {
      j = 0;
    } else {
      j = zzamp.hashCode();
    }
    int k;
    if (zzawx == null) {
      k = 0;
    } else {
      k = zzawx.hashCode();
    }
    int m;
    if (zzaug == null) {
      m = 0;
    } else {
      m = zzaug.hashCode();
    }
    int n;
    if (zzauh == null) {
      n = 0;
    } else {
      n = zzauh.hashCode();
    }
    int i1 = i2;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        i1 = i2;
      } else {
        i1 = zzcfc.hashCode();
      }
    }
    return ((((((i3 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (name != null) {
      i = j + zzyy.setProperty(1, name);
    }
    j = i;
    if (zzamp != null) {
      j = i + zzyy.setProperty(2, zzamp);
    }
    i = j;
    if (zzawx != null) {
      i = j + zzyy.write(3, zzawx.longValue());
    }
    j = i;
    if (zzaug != null)
    {
      zzaug.floatValue();
      j = i + (zzyy.zzbb(4) + 4);
    }
    i = j;
    if (zzauh != null)
    {
      zzauh.doubleValue();
      i = j + (zzyy.zzbb(5) + 8);
    }
    return i;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (name != null) {
      paramZzyy.add(1, name);
    }
    if (zzamp != null) {
      paramZzyy.add(2, zzamp);
    }
    if (zzawx != null) {
      paramZzyy.add(3, zzawx.longValue());
    }
    if (zzaug != null) {
      paramZzyy.write(4, zzaug.floatValue());
    }
    if (zzauh != null) {
      paramZzyy.add(5, zzauh.doubleValue());
    }
    super.multiply(paramZzyy);
  }
}
