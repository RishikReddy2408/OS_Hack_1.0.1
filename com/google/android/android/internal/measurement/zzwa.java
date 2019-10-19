package com.google.android.android.internal.measurement;

public class zzwa
{
  private static final zzuz zzbtt = ;
  private zzud zzcad;
  private volatile zzwt zzcae;
  private volatile zzud zzcaf;
  
  public zzwa() {}
  
  private final zzwt xor(zzwt paramZzwt)
  {
    if (zzcae == null) {
      try
      {
        if (zzcae != null) {
          break label46;
        }
        zzcae = paramZzwt;
        zzcaf = zzud.zzbtz;
      }
      catch (Throwable paramZzwt)
      {
        throw paramZzwt;
      }
    }
    label46:
    return zzcae;
  }
  
  public final zzwt addValue(zzwt paramZzwt)
  {
    zzwt localZzwt = zzcae;
    zzcad = null;
    zzcaf = null;
    zzcae = paramZzwt;
    return localZzwt;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzwa)) {
      return false;
    }
    paramObject = (zzwa)paramObject;
    zzwt localZzwt1 = zzcae;
    zzwt localZzwt2 = zzcae;
    if ((localZzwt1 == null) && (localZzwt2 == null)) {
      return zztt().equals(paramObject.zztt());
    }
    if ((localZzwt1 != null) && (localZzwt2 != null)) {
      return localZzwt1.equals(localZzwt2);
    }
    if (localZzwt1 != null) {
      return localZzwt1.equals(paramObject.xor(localZzwt1.zzwf()));
    }
    return xor(localZzwt2.zzwf()).equals(localZzwt2);
  }
  
  public int hashCode()
  {
    return 1;
  }
  
  public final zzud zztt()
  {
    if (zzcaf != null) {
      return zzcaf;
    }
    try
    {
      if (zzcaf != null)
      {
        localZzud = zzcaf;
        return localZzud;
      }
      if (zzcae == null) {
        zzcaf = zzud.zzbtz;
      } else {
        zzcaf = zzcae.zztt();
      }
      zzud localZzud = zzcaf;
      return localZzud;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final int zzvu()
  {
    if (zzcaf != null) {
      return zzcaf.size();
    }
    if (zzcae != null) {
      return zzcae.zzvu();
    }
    return 0;
  }
}
