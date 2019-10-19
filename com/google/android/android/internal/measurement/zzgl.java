package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgl
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgl>
{
  private static volatile zzgl[] zzayk;
  public String name = null;
  public String zzamp = null;
  private Float zzaug = null;
  public Double zzauh = null;
  public Long zzawx = null;
  public Long zzayl = null;
  
  public zzgl()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzgl[] zzmu()
  {
    if (zzayk == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzayk == null) {
          zzayk = new zzgl[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzayk;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgl)) {
      return false;
    }
    paramObject = (zzgl)paramObject;
    if (zzayl == null)
    {
      if (zzayl != null) {
        return false;
      }
    }
    else if (!zzayl.equals(zzayl)) {
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
    int i4 = getClass().getName().hashCode();
    Long localLong = zzayl;
    int i3 = 0;
    int i;
    if (localLong == null) {
      i = 0;
    } else {
      i = zzayl.hashCode();
    }
    int j;
    if (name == null) {
      j = 0;
    } else {
      j = name.hashCode();
    }
    int k;
    if (zzamp == null) {
      k = 0;
    } else {
      k = zzamp.hashCode();
    }
    int m;
    if (zzawx == null) {
      m = 0;
    } else {
      m = zzawx.hashCode();
    }
    int n;
    if (zzaug == null) {
      n = 0;
    } else {
      n = zzaug.hashCode();
    }
    int i1;
    if (zzauh == null) {
      i1 = 0;
    } else {
      i1 = zzauh.hashCode();
    }
    int i2 = i3;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        i2 = i3;
      } else {
        i2 = zzcfc.hashCode();
      }
    }
    return (((((((i4 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (zzayl != null) {
      i = j + zzyy.write(1, zzayl.longValue());
    }
    j = i;
    if (name != null) {
      j = i + zzyy.setProperty(2, name);
    }
    i = j;
    if (zzamp != null) {
      i = j + zzyy.setProperty(3, zzamp);
    }
    j = i;
    if (zzawx != null) {
      j = i + zzyy.write(4, zzawx.longValue());
    }
    i = j;
    if (zzaug != null)
    {
      zzaug.floatValue();
      i = j + (zzyy.zzbb(5) + 4);
    }
    j = i;
    if (zzauh != null)
    {
      zzauh.doubleValue();
      j = i + (zzyy.zzbb(6) + 8);
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzayl != null) {
      paramZzyy.add(1, zzayl.longValue());
    }
    if (name != null) {
      paramZzyy.add(2, name);
    }
    if (zzamp != null) {
      paramZzyy.add(3, zzamp);
    }
    if (zzawx != null) {
      paramZzyy.add(4, zzawx.longValue());
    }
    if (zzaug != null) {
      paramZzyy.write(5, zzaug.floatValue());
    }
    if (zzauh != null) {
      paramZzyy.add(6, zzauh.doubleValue());
    }
    super.multiply(paramZzyy);
  }
}
