package com.google.android.android.internal.measurement;

import java.lang.reflect.Constructor;

final class zzvc
{
  private static final com.google.android.gms.internal.measurement.zzva<?> zzbvo = new zzvb();
  private static final com.google.android.gms.internal.measurement.zzva<?> zzbvp = zzvq();
  
  private static zzva zzvq()
  {
    try
    {
      Object localObject = Class.forName("com.google.protobuf.ExtensionSchemaFull");
      localObject = ((Class)localObject).getDeclaredConstructor(new Class[0]);
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return (zzva)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  static zzva zzvr()
  {
    return zzbvo;
  }
  
  static zzva zzvs()
  {
    if (zzbvp != null) {
      return zzbvp;
    }
    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
  }
}
