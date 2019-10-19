package com.google.android.android.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzuz
{
  private static volatile boolean zzbvj;
  private static final Class<?> zzbvk = ;
  private static volatile zzuz zzbvl;
  static final zzuz zzbvm = new zzuz(true);
  private final Map<com.google.android.gms.internal.measurement.zzuz.zza, com.google.android.gms.internal.measurement.zzvm.zzd<?, ?>> zzbvn;
  
  zzuz()
  {
    zzbvn = new HashMap();
  }
  
  private zzuz(boolean paramBoolean)
  {
    zzbvn = Collections.emptyMap();
  }
  
  static zzuz zzvm()
  {
    return zzvk.newInstance(com.google.android.gms.internal.measurement.zzuz.class);
  }
  
  private static Class zzvn()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.Extension");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static zzuz zzvo()
  {
    return zzuy.zzvl();
  }
  
  public static zzuz zzvp()
  {
    Object localObject = zzbvl;
    if (localObject == null) {
      try
      {
        zzuz localZzuz = zzbvl;
        localObject = localZzuz;
        if (localZzuz == null)
        {
          localZzuz = zzuy.zzvm();
          localObject = localZzuz;
          zzbvl = localZzuz;
        }
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return localThrowable;
  }
  
  public final zzvm.zzd readInteger(zzwt paramZzwt, int paramInt)
  {
    return (zzvm.zzd)zzbvn.get(new zza(paramZzwt, paramInt));
  }
  
  final class zza
  {
    private final int number;
    
    zza(int paramInt)
    {
      number = paramInt;
    }
    
    public final boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {
        return false;
      }
      paramObject = (zza)paramObject;
      return (zzuz.this == object) && (number == number);
    }
    
    public final int hashCode()
    {
      return System.identityHashCode(zzuz.this) * 65535 + number;
    }
  }
}
