package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgb
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgb>
{
  public String zzafx = null;
  public Long zzawe = null;
  private Integer zzawf = null;
  public zzgc[] zzawg = zzgc.zzmn();
  public zzga[] zzawh = zzga.zzmm();
  public zzfu[] zzawi = zzfu.zzmi();
  private String zzawj = null;
  
  public zzgb()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgb)) {
      return false;
    }
    paramObject = (zzgb)paramObject;
    if (zzawe == null)
    {
      if (zzawe != null) {
        return false;
      }
    }
    else if (!zzawe.equals(zzawe)) {
      return false;
    }
    if (zzafx == null)
    {
      if (zzafx != null) {
        return false;
      }
    }
    else if (!zzafx.equals(zzafx)) {
      return false;
    }
    if (zzawf == null)
    {
      if (zzawf != null) {
        return false;
      }
    }
    else if (!zzawf.equals(zzawf)) {
      return false;
    }
    if (!zzze.equals(zzawg, zzawg)) {
      return false;
    }
    if (!zzze.equals(zzawh, zzawh)) {
      return false;
    }
    if (!zzze.equals(zzawi, zzawi)) {
      return false;
    }
    if (zzawj == null)
    {
      if (zzawj != null) {
        return false;
      }
    }
    else if (!zzawj.equals(zzawj)) {
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
    Long localLong = zzawe;
    int i1 = 0;
    int i;
    if (localLong == null) {
      i = 0;
    } else {
      i = zzawe.hashCode();
    }
    int j;
    if (zzafx == null) {
      j = 0;
    } else {
      j = zzafx.hashCode();
    }
    int k;
    if (zzawf == null) {
      k = 0;
    } else {
      k = zzawf.hashCode();
    }
    int i3 = zzze.hashCode(zzawg);
    int i4 = zzze.hashCode(zzawh);
    int i5 = zzze.hashCode(zzawi);
    int m;
    if (zzawj == null) {
      m = 0;
    } else {
      m = zzawj.hashCode();
    }
    int n = i1;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        n = i1;
      } else {
        n = zzcfc.hashCode();
      }
    }
    return ((((((((i2 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + m) * 31 + n;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (zzawe != null) {
      i = j + zzyy.write(1, zzawe.longValue());
    }
    int k = i;
    if (zzafx != null) {
      k = i + zzyy.setProperty(2, zzafx);
    }
    j = k;
    if (zzawf != null) {
      j = k + zzyy.sendCommand(3, zzawf.intValue());
    }
    Object localObject = zzawg;
    int m = 0;
    i = j;
    if (localObject != null)
    {
      i = j;
      if (zzawg.length > 0)
      {
        k = 0;
        for (;;)
        {
          i = j;
          if (k >= zzawg.length) {
            break;
          }
          localObject = zzawg[k];
          i = j;
          if (localObject != null) {
            i = j + zzyy.addMenuItem(4, (zzzg)localObject);
          }
          k += 1;
          j = i;
        }
      }
    }
    j = i;
    if (zzawh != null)
    {
      j = i;
      if (zzawh.length > 0)
      {
        k = 0;
        for (;;)
        {
          j = i;
          if (k >= zzawh.length) {
            break;
          }
          localObject = zzawh[k];
          j = i;
          if (localObject != null) {
            j = i + zzyy.addMenuItem(5, (zzzg)localObject);
          }
          k += 1;
          i = j;
        }
      }
    }
    k = j;
    if (zzawi != null)
    {
      k = j;
      if (zzawi.length > 0)
      {
        i = m;
        for (;;)
        {
          k = j;
          if (i >= zzawi.length) {
            break;
          }
          localObject = zzawi[i];
          k = j;
          if (localObject != null) {
            k = j + zzyy.addMenuItem(6, (zzzg)localObject);
          }
          i += 1;
          j = k;
        }
      }
    }
    if (zzawj != null) {
      return k + zzyy.setProperty(7, zzawj);
    }
    return k;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzawe != null) {
      paramZzyy.add(1, zzawe.longValue());
    }
    if (zzafx != null) {
      paramZzyy.add(2, zzafx);
    }
    if (zzawf != null) {
      paramZzyy.addItem(3, zzawf.intValue());
    }
    Object localObject = zzawg;
    int j = 0;
    int i;
    if ((localObject != null) && (zzawg.length > 0))
    {
      i = 0;
      while (i < zzawg.length)
      {
        localObject = zzawg[i];
        if (localObject != null) {
          paramZzyy.writeHeader(4, (zzzg)localObject);
        }
        i += 1;
      }
    }
    if ((zzawh != null) && (zzawh.length > 0))
    {
      i = 0;
      while (i < zzawh.length)
      {
        localObject = zzawh[i];
        if (localObject != null) {
          paramZzyy.writeHeader(5, (zzzg)localObject);
        }
        i += 1;
      }
    }
    if ((zzawi != null) && (zzawi.length > 0))
    {
      i = j;
      while (i < zzawi.length)
      {
        localObject = zzawi[i];
        if (localObject != null) {
          paramZzyy.writeHeader(6, (zzzg)localObject);
        }
        i += 1;
      }
    }
    if (zzawj != null) {
      paramZzyy.add(7, zzawj);
    }
    super.multiply(paramZzyy);
  }
}
