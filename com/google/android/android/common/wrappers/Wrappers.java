package com.google.android.android.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class Wrappers
{
  private static Wrappers zzhx = new Wrappers();
  private PackageManagerWrapper zzhw = null;
  
  public Wrappers() {}
  
  private final PackageManagerWrapper getAppVersion(Context paramContext)
  {
    try
    {
      if (zzhw == null)
      {
        if (paramContext.getApplicationContext() != null) {
          paramContext = paramContext.getApplicationContext();
        }
        zzhw = new PackageManagerWrapper(paramContext);
      }
      paramContext = zzhw;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static PackageManagerWrapper packageManager(Context paramContext)
  {
    return zzhx.getAppVersion(paramContext);
  }
}
