package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzfv
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzfv>
{
  private static volatile zzfv[] zzavd;
  public Boolean zzavb = null;
  public Boolean zzavc = null;
  public Integer zzave = null;
  public String zzavf = null;
  public zzfw[] zzavg = zzfw.zzmk();
  private Boolean zzavh = null;
  public zzfx zzavi = null;
  
  public zzfv()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzfv[] zzmj()
  {
    if (zzavd == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzavd == null) {
          zzavd = new zzfv[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzavd;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfv)) {
      return false;
    }
    paramObject = (zzfv)paramObject;
    if (zzave == null)
    {
      if (zzave != null) {
        return false;
      }
    }
    else if (!zzave.equals(zzave)) {
      return false;
    }
    if (zzavf == null)
    {
      if (zzavf != null) {
        return false;
      }
    }
    else if (!zzavf.equals(zzavf)) {
      return false;
    }
    if (!zzze.equals(zzavg, zzavg)) {
      return false;
    }
    if (zzavh == null)
    {
      if (zzavh != null) {
        return false;
      }
    }
    else if (!zzavh.equals(zzavh)) {
      return false;
    }
    if (zzavi == null)
    {
      if (zzavi != null) {
        return false;
      }
    }
    else if (!zzavi.equals(zzavi)) {
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
    int i4 = getClass().getName().hashCode();
    Object localObject = zzave;
    int i3 = 0;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = zzave.hashCode();
    }
    int j;
    if (zzavf == null) {
      j = 0;
    } else {
      j = zzavf.hashCode();
    }
    int i5 = zzze.hashCode(zzavg);
    int k;
    if (zzavh == null) {
      k = 0;
    } else {
      k = zzavh.hashCode();
    }
    localObject = zzavi;
    int m;
    if (localObject == null) {
      m = 0;
    } else {
      m = ((zzfx)localObject).hashCode();
    }
    int n;
    if (zzavb == null) {
      n = 0;
    } else {
      n = zzavb.hashCode();
    }
    int i1;
    if (zzavc == null) {
      i1 = 0;
    } else {
      i1 = zzavc.hashCode();
    }
    int i2 = i3;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        i2 = i3;
      } else {
        i2 = zzcfc.hashCode();
      }
    }
    return ((((((((i4 + 527) * 31 + i) * 31 + j) * 31 + i5) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2;
  }
  
  protected final int multiply()
  {
    int i = super.multiply();
    int j = i;
    if (zzave != null) {
      j = i + zzyy.sendCommand(1, zzave.intValue());
    }
    i = j;
    if (zzavf != null) {
      i = j + zzyy.setProperty(2, zzavf);
    }
    j = i;
    if (zzavg != null)
    {
      j = i;
      if (zzavg.length > 0)
      {
        int k = 0;
        for (;;)
        {
          j = i;
          if (k >= zzavg.length) {
            break;
          }
          zzfw localZzfw = zzavg[k];
          j = i;
          if (localZzfw != null) {
            j = i + zzyy.addMenuItem(3, localZzfw);
          }
          k += 1;
          i = j;
        }
      }
    }
    i = j;
    if (zzavh != null)
    {
      zzavh.booleanValue();
      i = j + (zzyy.zzbb(4) + 1);
    }
    j = i;
    if (zzavi != null) {
      j = i + zzyy.addMenuItem(5, zzavi);
    }
    i = j;
    if (zzavb != null)
    {
      zzavb.booleanValue();
      i = j + (zzyy.zzbb(6) + 1);
    }
    j = i;
    if (zzavc != null)
    {
      zzavc.booleanValue();
      j = i + (zzyy.zzbb(7) + 1);
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzave != null) {
      paramZzyy.addItem(1, zzave.intValue());
    }
    if (zzavf != null) {
      paramZzyy.add(2, zzavf);
    }
    if ((zzavg != null) && (zzavg.length > 0))
    {
      int i = 0;
      while (i < zzavg.length)
      {
        zzfw localZzfw = zzavg[i];
        if (localZzfw != null) {
          paramZzyy.writeHeader(3, localZzfw);
        }
        i += 1;
      }
    }
    if (zzavh != null) {
      paramZzyy.add(4, zzavh.booleanValue());
    }
    if (zzavi != null) {
      paramZzyy.writeHeader(5, zzavi);
    }
    if (zzavb != null) {
      paramZzyy.add(6, zzavb.booleanValue());
    }
    if (zzavc != null) {
      paramZzyy.add(7, zzavc.booleanValue());
    }
    super.multiply(paramZzyy);
  }
}
