package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzgj
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzgj>
{
  public long[] zzaye = zzzj.zzcfr;
  public long[] zzayf = zzzj.zzcfr;
  public zzge[] zzayg = zzge.zzmp();
  public zzgk[] zzayh = zzgk.zzmt();
  
  public zzgj()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzgj)) {
      return false;
    }
    paramObject = (zzgj)paramObject;
    if (!zzze.equals(zzaye, zzaye)) {
      return false;
    }
    if (!zzze.equals(zzayf, zzayf)) {
      return false;
    }
    if (!zzze.equals(zzayg, zzayg)) {
      return false;
    }
    if (!zzze.equals(zzayh, zzayh)) {
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
    int j = getClass().getName().hashCode();
    int k = zzze.hashCode(zzaye);
    int m = zzze.hashCode(zzayf);
    int n = zzze.hashCode(zzayg);
    int i1 = zzze.hashCode(zzayh);
    int i;
    if ((zzcfc != null) && (!zzcfc.isEmpty())) {
      i = zzcfc.hashCode();
    } else {
      i = 0;
    }
    return (((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i;
  }
  
  protected final int multiply()
  {
    int k = super.multiply();
    int j = k;
    Object localObject = zzaye;
    int m = 0;
    int i = j;
    if (localObject != null)
    {
      i = j;
      if (zzaye.length > 0)
      {
        i = 0;
        j = 0;
        while (i < zzaye.length)
        {
          j += zzyy.zzbi(zzaye[i]);
          i += 1;
        }
        i = k + j + zzaye.length * 1;
      }
    }
    j = i;
    if (zzayf != null)
    {
      j = i;
      if (zzayf.length > 0)
      {
        j = 0;
        k = 0;
        while (j < zzayf.length)
        {
          k += zzyy.zzbi(zzayf[j]);
          j += 1;
        }
        j = i + k + zzayf.length * 1;
      }
    }
    i = j;
    if (zzayg != null)
    {
      i = j;
      if (zzayg.length > 0)
      {
        k = 0;
        for (;;)
        {
          i = j;
          if (k >= zzayg.length) {
            break;
          }
          localObject = zzayg[k];
          i = j;
          if (localObject != null) {
            i = j + zzyy.addMenuItem(3, (zzzg)localObject);
          }
          k += 1;
          j = i;
        }
      }
    }
    k = i;
    if (zzayh != null)
    {
      k = i;
      if (zzayh.length > 0)
      {
        j = m;
        for (;;)
        {
          k = i;
          if (j >= zzayh.length) {
            break;
          }
          localObject = zzayh[j];
          k = i;
          if (localObject != null) {
            k = i + zzyy.addMenuItem(4, (zzzg)localObject);
          }
          j += 1;
          i = k;
        }
      }
    }
    return k;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    Object localObject = zzaye;
    int j = 0;
    int i;
    if ((localObject != null) && (zzaye.length > 0))
    {
      i = 0;
      while (i < zzaye.length)
      {
        paramZzyy.multiply(1, zzaye[i]);
        i += 1;
      }
    }
    if ((zzayf != null) && (zzayf.length > 0))
    {
      i = 0;
      while (i < zzayf.length)
      {
        paramZzyy.multiply(2, zzayf[i]);
        i += 1;
      }
    }
    if ((zzayg != null) && (zzayg.length > 0))
    {
      i = 0;
      while (i < zzayg.length)
      {
        localObject = zzayg[i];
        if (localObject != null) {
          paramZzyy.writeHeader(3, (zzzg)localObject);
        }
        i += 1;
      }
    }
    if ((zzayh != null) && (zzayh.length > 0))
    {
      i = j;
      while (i < zzayh.length)
      {
        localObject = zzayh[i];
        if (localObject != null) {
          paramZzyy.writeHeader(4, (zzzg)localObject);
        }
        i += 1;
      }
    }
    super.multiply(paramZzyy);
  }
}
