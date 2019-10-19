package com.google.android.android.internal.measurement;

import java.lang.reflect.Constructor;

final class zzxc
{
  private static final zzxa zzcbq = ;
  private static final zzxa zzcbr = new zzxb();
  
  static zzxa zzxk()
  {
    return zzcbq;
  }
  
  static zzxa zzxl()
  {
    return zzcbr;
  }
  
  private static zzxa zzxm()
  {
    try
    {
      Object localObject = Class.forName("com.google.protobuf.NewInstanceSchemaFull");
      localObject = ((Class)localObject).getDeclaredConstructor(new Class[0]);
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return (zzxa)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
}
