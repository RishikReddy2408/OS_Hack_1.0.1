package com.google.android.android.internal.measurement;

final class zzua
{
  private static final Class<?> zzbtv = zzfu("libcore.io.Memory");
  private static final boolean zzbtw;
  
  static
  {
    boolean bool;
    if (zzfu("org.robolectric.Robolectric") != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbtw = bool;
  }
  
  private static Class zzfu(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  static boolean zzty()
  {
    return (zzbtv != null) && (!zzbtw);
  }
  
  static Class zztz()
  {
    return zzbtv;
  }
}
