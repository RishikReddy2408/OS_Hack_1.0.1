package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgd
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgd>
{
  private static volatile zzgd[] zzawl;
  public Integer zzauy = null;
  public zzgj zzawm = null;
  public zzgj zzawn = null;
  public Boolean zzawo = null;
  
  public zzgd()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzgd[] zzmo()
  {
    if (zzawl == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzawl == null) {
          zzawl = new zzgd[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzawl;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgd)) {
      return false;
    }
    paramObject = (zzgd)paramObject;
    if (zzauy == null)
    {
      if (zzauy != null) {
        return false;
      }
    }
    else if (!zzauy.equals(zzauy)) {
      return false;
    }
    if (zzawm == null)
    {
      if (zzawm != null) {
        return false;
      }
    }
    else if (!zzawm.equals(zzawm)) {
      return false;
    }
    if (zzawn == null)
    {
      if (zzawn != null) {
        return false;
      }
    }
    else if (!zzawn.equals(zzawn)) {
      return false;
    }
    if (zzawo == null)
    {
      if (zzawo != null) {
        return false;
      }
    }
    else if (!zzawo.equals(zzawo)) {
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
    Object localObject = zzauy;
    int i1 = 0;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = zzauy.hashCode();
    }
    localObject = zzawm;
    int j;
    if (localObject == null) {
      j = 0;
    } else {
      j = ((zzgj)localObject).hashCode();
    }
    localObject = zzawn;
    int k;
    if (localObject == null) {
      k = 0;
    } else {
      k = ((zzgj)localObject).hashCode();
    }
    int m;
    if (zzawo == null) {
      m = 0;
    } else {
      m = zzawo.hashCode();
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
    if (zzauy != null) {
      i = j + zzyy.sendCommand(1, zzauy.intValue());
    }
    j = i;
    if (zzawm != null) {
      j = i + zzyy.addMenuItem(2, zzawm);
    }
    i = j;
    if (zzawn != null) {
      i = j + zzyy.addMenuItem(3, zzawn);
    }
    j = i;
    if (zzawo != null)
    {
      zzawo.booleanValue();
      j = i + (zzyy.zzbb(4) + 1);
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzauy != null) {
      paramZzyy.addItem(1, zzauy.intValue());
    }
    if (zzawm != null) {
      paramZzyy.writeHeader(2, zzawm);
    }
    if (zzawn != null) {
      paramZzyy.writeHeader(3, zzawn);
    }
    if (zzawo != null) {
      paramZzyy.add(4, zzawo.booleanValue());
    }
    super.multiply(paramZzyy);
  }
}
