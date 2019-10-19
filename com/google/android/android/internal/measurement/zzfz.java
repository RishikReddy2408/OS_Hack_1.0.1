package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzfz
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzfz>
{
  public Integer zzavw = null;
  public String zzavx = null;
  public Boolean zzavy = null;
  public String[] zzavz = zzzj.zzcfv;
  
  public zzfz()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  private final zzfz parse(zzyx paramZzyx)
    throws IOException
  {
    for (;;)
    {
      int i = paramZzyx.zzug();
      if (i == 0) {
        break;
      }
      int j;
      Object localObject;
      if (i != 8)
      {
        if (i != 18)
        {
          if (i != 24)
          {
            if (i != 34)
            {
              if (!super.read(paramZzyx, i)) {
                return this;
              }
            }
            else
            {
              j = zzzj.add(paramZzyx, 34);
              if (zzavz == null) {
                i = 0;
              } else {
                i = zzavz.length;
              }
              localObject = new String[j + i];
              j = i;
              if (i != 0)
              {
                System.arraycopy(zzavz, 0, localObject, 0, i);
                j = i;
              }
              while (j < localObject.length - 1)
              {
                localObject[j] = paramZzyx.readString();
                paramZzyx.zzug();
                j += 1;
              }
              localObject[j] = paramZzyx.readString();
              zzavz = ((String[])localObject);
            }
          }
          else {
            zzavy = Boolean.valueOf(paramZzyx.zzum());
          }
        }
        else {
          zzavx = paramZzyx.readString();
        }
      }
      else {
        j = paramZzyx.getPosition();
      }
      try
      {
        int k = paramZzyx.zzuy();
        if ((k >= 0) && (k <= 6))
        {
          zzavw = Integer.valueOf(k);
          continue;
        }
        localObject = new StringBuilder(41);
        ((StringBuilder)localObject).append(k);
        ((StringBuilder)localObject).append(" is not a valid enum MatchType");
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
      paramZzyx.zzby(j);
      read(paramZzyx, i);
    }
    return this;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfz)) {
      return false;
    }
    paramObject = (zzfz)paramObject;
    if (zzavw == null)
    {
      if (zzavw != null) {
        return false;
      }
    }
    else if (!zzavw.equals(zzavw)) {
      return false;
    }
    if (zzavx == null)
    {
      if (zzavx != null) {
        return false;
      }
    }
    else if (!zzavx.equals(zzavx)) {
      return false;
    }
    if (zzavy == null)
    {
      if (zzavy != null) {
        return false;
      }
    }
    else if (!zzavy.equals(zzavy)) {
      return false;
    }
    if (!zzze.equals(zzavz, zzavz)) {
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
    Integer localInteger = zzavw;
    int n = 0;
    int i;
    if (localInteger == null) {
      i = 0;
    } else {
      i = zzavw.intValue();
    }
    int j;
    if (zzavx == null) {
      j = 0;
    } else {
      j = zzavx.hashCode();
    }
    int k;
    if (zzavy == null) {
      k = 0;
    } else {
      k = zzavy.hashCode();
    }
    int i2 = zzze.hashCode(zzavz);
    int m = n;
    if (zzcfc != null) {
      if (zzcfc.isEmpty()) {
        m = n;
      } else {
        m = zzcfc.hashCode();
      }
    }
    return (((((i1 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + i2) * 31 + m;
  }
  
  protected final int multiply()
  {
    int j = super.multiply();
    int i = j;
    if (zzavw != null) {
      i = j + zzyy.sendCommand(1, zzavw.intValue());
    }
    j = i;
    if (zzavx != null) {
      j = i + zzyy.setProperty(2, zzavx);
    }
    i = j;
    if (zzavy != null)
    {
      zzavy.booleanValue();
      i = j + (zzyy.zzbb(3) + 1);
    }
    j = i;
    if (zzavz != null)
    {
      j = i;
      if (zzavz.length > 0)
      {
        j = 0;
        int k = 0;
        int n = 0;
        while (j < zzavz.length)
        {
          String str = zzavz[j];
          int i1 = n;
          int m = k;
          if (str != null)
          {
            i1 = n + 1;
            m = k + zzyy.zzfx(str);
          }
          j += 1;
          n = i1;
          k = m;
        }
        j = i + k + n * 1;
      }
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (zzavw != null) {
      paramZzyy.addItem(1, zzavw.intValue());
    }
    if (zzavx != null) {
      paramZzyy.add(2, zzavx);
    }
    if (zzavy != null) {
      paramZzyy.add(3, zzavy.booleanValue());
    }
    if ((zzavz != null) && (zzavz.length > 0))
    {
      int i = 0;
      while (i < zzavz.length)
      {
        String str = zzavz[i];
        if (str != null) {
          paramZzyy.add(4, str);
        }
        i += 1;
      }
    }
    super.multiply(paramZzyy);
  }
}
