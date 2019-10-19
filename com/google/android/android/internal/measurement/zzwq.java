package com.google.android.android.internal.measurement;

import java.lang.reflect.Constructor;

final class zzwq
{
  private static final zzwo zzcav = ;
  private static final zzwo zzcaw = new zzwp();
  
  static zzwo zzxd()
  {
    return zzcav;
  }
  
  static zzwo zzxe()
  {
    return zzcaw;
  }
  
  private static zzwo zzxf()
  {
    try
    {
      Object localObject = Class.forName("com.google.protobuf.MapFieldSchemaFull");
      localObject = ((Class)localObject).getDeclaredConstructor(new Class[0]);
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return (zzwo)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
}
