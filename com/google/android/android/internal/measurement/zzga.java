package com.google.android.android.internal.measurement;

import java.io.IOException;

public final class zzga
  extends com.google.android.gms.internal.measurement.zzza<com.google.android.gms.internal.measurement.zzga>
{
  private static volatile zzga[] zzawa;
  public String name = null;
  public Boolean zzawb = null;
  public Boolean zzawc = null;
  public Integer zzawd = null;
  
  public zzga()
  {
    zzcfc = null;
    zzcfm = -1;
  }
  
  public static zzga[] zzmm()
  {
    if (zzawa == null)
    {
      Object localObject = zzze.zzcfl;
      try
      {
        if (zzawa == null) {
          zzawa = new zzga[0];
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzawa;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzga)) {
      return false;
    }
    paramObject = (zzga)paramObject;
    if (name == null)
    {
      if (name != null) {
        return false;
      }
    }
    else if (!name.equals(name)) {
      return false;
    }
    if (zzawb == null)
    {
      if (zzawb != null) {
        return false;
      }
    }
    else if (!zzawb.equals(zzawb)) {
      return false;
    }
    if (zzawc == null)
    {
      if (zzawc != null) {
        return false;
      }
    }
    else if (!zzawc.equals(zzawc)) {
      return false;
    }
    if (zzawd == null)
    {
      if (zzawd != null) {
        return false;
      }
    }
    else if (!zzawd.equals(zzawd)) {
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
    String str = name;
    int i1 = 0;
    int i;
    if (str == null) {
      i = 0;
    } else {
      i = name.hashCode();
    }
    int j;
    if (zzawb == null) {
      j = 0;
    } else {
      j = zzawb.hashCode();
    }
    int k;
    if (zzawc == null) {
      k = 0;
    } else {
      k = zzawc.hashCode();
    }
    int m;
    if (zzawd == null) {
      m = 0;
    } else {
      m = zzawd.hashCode();
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
    if (name != null) {
      i = j + zzyy.setProperty(1, name);
    }
    j = i;
    if (zzawb != null)
    {
      zzawb.booleanValue();
      j = i + (zzyy.zzbb(2) + 1);
    }
    i = j;
    if (zzawc != null)
    {
      zzawc.booleanValue();
      i = j + (zzyy.zzbb(3) + 1);
    }
    j = i;
    if (zzawd != null) {
      j = i + zzyy.sendCommand(4, zzawd.intValue());
    }
    return j;
  }
  
  public final void multiply(zzyy paramZzyy)
    throws IOException
  {
    if (name != null) {
      paramZzyy.add(1, name);
    }
    if (zzawb != null) {
      paramZzyy.add(2, zzawb.booleanValue());
    }
    if (zzawc != null) {
      paramZzyy.add(3, zzawc.booleanValue());
    }
    if (zzawd != null) {
      paramZzyy.addItem(4, zzawd.intValue());
    }
    super.multiply(paramZzyy);
  }
}
