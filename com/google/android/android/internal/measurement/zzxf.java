package com.google.android.android.internal.measurement;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentMap;

final class zzxf
{
  private static final zzxf zzcbs = new zzxf();
  private final zzxk zzcbt;
  private final ConcurrentMap<Class<?>, com.google.android.gms.internal.measurement.zzxj<?>> zzcbu;
  
  private zzxf() {}
  
  private static zzxk zzgb(String paramString)
  {
    try
    {
      paramString = (zzxk)Class.forName(paramString).getConstructor(new Class[0]).newInstance(new Object[0]);
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static zzxf zzxn()
  {
    return zzcbs;
  }
  
  public final zzxj getAttributeValue(Class paramClass)
  {
    zzvo.attribute(paramClass, "messageType");
    zzxj localZzxj2 = (zzxj)zzcbu.get(paramClass);
    zzxj localZzxj1 = localZzxj2;
    if (localZzxj2 == null)
    {
      localZzxj1 = zzcbt.getAttributeValue(paramClass);
      zzvo.attribute(paramClass, "messageType");
      zzvo.attribute(localZzxj1, "schema");
      paramClass = (zzxj)zzcbu.putIfAbsent(paramClass, localZzxj1);
      if (paramClass != null) {
        return paramClass;
      }
    }
    return localZzxj1;
  }
  
  public final zzxj zzag(Object paramObject)
  {
    return getAttributeValue(paramObject.getClass());
  }
}
