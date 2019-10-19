package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzfy
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzfy>
{
  private static volatile zzfy[] zzavt;
  public Boolean zzavb = null;
  public Boolean zzavc = null;
  public Integer zzave = null;
  public String zzavu = null;
  public zzfw zzavv = null;
  
  public zzfy()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzfy[] zzml()
  {
    if (zzavt == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzavt == null) {
          zzavt = new zzfy[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzavt;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfy)) {
      return false;
    }
    paramObject = (zzfy)paramObject;
    if (zzave == null)
    {
      if (zzave != null) {
        return false;
      }
    }
    else if (!zzave.equals(zzave)) {
      return false;
    }
    if (zzavu == null)
    {
      if (zzavu != null) {
        return false;
      }
    }
    else if (!zzavu.equals(zzavu)) {
      return false;
    }
    if (zzavv == null)
    {
      if (zzavv != null) {
        return false;
      }
    }
    else if (!zzavv.equals(zzavv)) {
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
    int i3 = getClass().getName().hashCode();
    Object localObject = zzave;
    int i2 = 0;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = zzave.hashCode();
    }
    int j;
    if (zzavu == null) {
      j = 0;
    } else {
      j = zzavu.hashCode();
    }
    localObject = zzavv;
    int k;
    if (localObject == null) {
      k = 0;
    } else {
      k = ((zzfw)localObject).hashCode();
    }
    int m;
    if (zzavb == null) {
      m = 0;
    } else {
      m = zzavb.hashCode();
    }
    int n;
    if (zzavc == null) {
      n = 0;
    } else {
      n = zzavc.hashCode();
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
    if (zzave != null) {
      i = j + zzyy.sendCommand(1, zzave.intValue());
    }
    j = i;
    if (zzavu != null) {
      j = i + zzyy.setProperty(2, zzavu);
    }
    i = j;
    if (zzavv != null) {
      i = j + zzyy.addMenuItem(3, zzavv);
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
    if (zzave != null) {
      paramZzyy.addItem(1, zzave.intValue());
    }
    if (zzavu != null) {
      paramZzyy.add(2, zzavu);
    }
    if (zzavv != null) {
      paramZzyy.writeHeader(3, zzavv);
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
