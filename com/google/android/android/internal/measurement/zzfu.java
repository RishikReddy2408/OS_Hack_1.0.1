package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzfu
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzfu>
{
  private static volatile zzfu[] zzaux;
  public Integer zzauy = null;
  public zzfy[] zzauz = zzfy.zzml();
  public zzfv[] zzava = zzfv.zzmj();
  private Boolean zzavb = null;
  private Boolean zzavc = null;
  
  public zzfu()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzfu[] zzmi()
  {
    if (zzaux == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzaux == null) {
          zzaux = new zzfu[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzaux;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfu)) {
      return false;
    }
    paramObject = (zzfu)paramObject;
    if (zzauy == null)
    {
      if (zzauy != null) {
        return false;
      }
    }
    else if (!zzauy.equals(zzauy)) {
      return false;
    }
    if (!zzze.equals(zzauz, zzauz)) {
      return false;
    }
    if (!zzze.equals(zzava, zzava)) {
      return false;
    }
    if (zzavb == null)
    {
      if (zzavb != null) {
        return false;
      }
    }
    else if (!zzavb.equals(zzavb)) {
      return false;
    }
    if (zzavc == null)
    {
      if (zzavc != null) {
        return false;
      }
    }
    else if (!zzavc.equals(zzavc)) {
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
    int i1 = getClass().getName().hashCode();
    Integer localInteger = zzauy;
    int n = 0;
    int i;
    if (localInteger == null) {
      i = 0;
    } else {
      i = zzauy.hashCode();
    }
    int i2 = zzze.hashCode(zzauz);
    int i3 = zzze.hashCode(zzava);
    int j;
    if (zzavb == null) {
      j = 0;
    } else {
      j = zzavb.hashCode();
    }
    int k;
    if (zzavc == null) {
      k = 0;
    } else {
      k = zzavc.hashCode();
    }
    int m = n;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        m = n;
      } else {
        m = zzcfc.hashCode();
      }
    }
    return ((((((i1 + 527) * 31 + i) * 31 + i2) * 31 + i3) * 31 + j) * 31 + k) * 31 + m;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (zzauy != null) {
      i = j + zzyy.sendCommand(1, zzauy.intValue());
    }
    Object localObject = zzauz;
    int m = 0;
    j = i;
    int k;
    if (localObject != null)
    {
      j = i;
      if (zzauz.length > 0)
      {
        k = 0;
        for (;;)
        {
          j = i;
          if (k >= zzauz.length) {
            break;
          }
          localObject = zzauz[k];
          j = i;
          if (localObject != null) {
            j = i + zzyy.addMenuItem(2, (zzzg)localObject);
          }
          k += 1;
          i = j;
        }
      }
    }
    i = j;
    if (zzava != null)
    {
      i = j;
      if (zzava.length > 0)
      {
        k = m;
        for (;;)
        {
          i = j;
          if (k >= zzava.length) {
            break;
          }
          localObject = zzava[k];
          i = j;
          if (localObject != null) {
            i = j + zzyy.addMenuItem(3, (zzzg)localObject);
          }
          k += 1;
          j = i;
        }
      }
    }
    j = i;
    if (zzavb != null)
    {
      zzavb.booleanValue();
      j = i + (zzyy.zzbb(4) + 1);
    }
    i = j;
    if (zzavc != null)
    {
      zzavc.booleanValue();
      i = j + (zzyy.zzbb(5) + 1);
    }
    return i;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzauy != null) {
      paramZzyy.addItem(1, zzauy.intValue());
    }
    Object localObject = zzauz;
    int j = 0;
    int i;
    if ((localObject != null) && (zzauz.length > 0))
    {
      i = 0;
      while (i < zzauz.length)
      {
        localObject = zzauz[i];
        if (localObject != null) {
          paramZzyy.writeHeader(2, (zzzg)localObject);
        }
        i += 1;
      }
    }
    if ((zzava != null) && (zzava.length > 0))
    {
      i = j;
      while (i < zzava.length)
      {
        localObject = zzava[i];
        if (localObject != null) {
          paramZzyy.writeHeader(3, (zzzg)localObject);
        }
        i += 1;
      }
    }
    if (zzavb != null) {
      paramZzyy.add(4, zzavb.booleanValue());
    }
    if (zzavc != null) {
      paramZzyy.add(5, zzavc.booleanValue());
    }
    super.multiply(paramZzyy);
  }
}
