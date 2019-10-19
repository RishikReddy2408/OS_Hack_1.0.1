package com.google.android.android.internal.measurement;

import java.lang.reflect.Method;

final class zzuy
{
  private static final Class<?> zzbvi = ;
  
  private static final zzuz zzfz(String paramString)
    throws Exception
  {
    return (zzuz)zzbvi.getDeclaredMethod(paramString, new Class[0]).invoke(null, new Object[0]);
  }
  
  private static Class zzvk()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.ExtensionRegistry");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static zzuz zzvl()
  {
    if (zzbvi != null) {}
    try
    {
      zzuz localZzuz = zzfz("getEmptyRegistry");
      return localZzuz;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return zzuz.zzbvm;
  }
  
  static zzuz zzvm()
  {
    if (zzbvi != null) {}
    try
    {
      localZzuz1 = zzfz("loadGeneratedRegistry");
    }
    catch (Exception localException)
    {
      zzuz localZzuz1;
      for (;;) {}
    }
    localZzuz1 = null;
    zzuz localZzuz2 = localZzuz1;
    if (localZzuz1 == null) {
      localZzuz2 = zzuz.zzvm();
    }
    if (localZzuz2 == null) {
      return zzvl();
    }
    return localZzuz2;
  }
}
