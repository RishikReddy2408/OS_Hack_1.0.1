package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzfw
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzfw>
{
  private static volatile zzfw[] zzavj;
  public zzfz zzavk = null;
  public zzfx zzavl = null;
  public Boolean zzavm = null;
  public String zzavn = null;
  
  public zzfw()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzfw[] zzmk()
  {
    if (zzavj == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzavj == null) {
          zzavj = new zzfw[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzavj;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfw)) {
      return false;
    }
    paramObject = (zzfw)paramObject;
    if (zzavk == null)
    {
      if (zzavk != null) {
        return false;
      }
    }
    else if (!zzavk.equals(zzavk)) {
      return false;
    }
    if (zzavl == null)
    {
      if (zzavl != null) {
        return false;
      }
    }
    else if (!zzavl.equals(zzavl)) {
      return false;
    }
    if (zzavm == null)
    {
      if (zzavm != null) {
        return false;
      }
    }
    else if (!zzavm.equals(zzavm)) {
      return false;
    }
    if (zzavn == null)
    {
      if (zzavn != null) {
        return false;
      }
    }
    else if (!zzavn.equals(zzavn)) {
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
    Object localObject = zzavk;
    int i1 = 0;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = ((zzfz)localObject).hashCode();
    }
    localObject = zzavl;
    int j;
    if (localObject == null) {
      j = 0;
    } else {
      j = ((zzfx)localObject).hashCode();
    }
    int k;
    if (zzavm == null) {
      k = 0;
    } else {
      k = zzavm.hashCode();
    }
    int m;
    if (zzavn == null) {
      m = 0;
    } else {
      m = zzavn.hashCode();
    }
    int n = i1;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        n = i1;
      } else {
        n = zzcfc.hashCode();
      }
    }
    return (((((i2 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (zzavk != null) {
      i = j + zzyy.addMenuItem(1, zzavk);
    }
    j = i;
    if (zzavl != null) {
      j = i + zzyy.addMenuItem(2, zzavl);
    }
    i = j;
    if (zzavm != null)
    {
      zzavm.booleanValue();
      i = j + (zzyy.zzbb(3) + 1);
    }
    j = i;
    if (zzavn != null) {
      j = i + zzyy.setProperty(4, zzavn);
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzavk != null) {
      paramZzyy.writeHeader(1, zzavk);
    }
    if (zzavl != null) {
      paramZzyy.writeHeader(2, zzavl);
    }
    if (zzavm != null) {
      paramZzyy.add(3, zzavm.booleanValue());
    }
    if (zzavn != null) {
      paramZzyy.add(4, zzavn);
    }
    super.multiply(paramZzyy);
  }
}
