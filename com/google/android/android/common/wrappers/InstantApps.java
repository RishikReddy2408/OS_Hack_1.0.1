package com.google.android.android.common.wrappers;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.android.common.util.PlatformVersion;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class InstantApps
{
  private static Context zzht;
  private static Boolean zzhu;
  
  public InstantApps() {}
  
  public static boolean isInstantApp(Context paramContext)
  {
    for (;;)
    {
      try
      {
        localContext = paramContext.getApplicationContext();
        if ((zzht != null) && (zzhu != null) && (zzht == localContext))
        {
          bool = zzhu.booleanValue();
          return bool;
        }
        zzhu = null;
        if (PlatformVersion.isAtLeastO()) {
          zzhu = Boolean.valueOf(localContext.getPackageManager().isInstantApp());
        }
      }
      catch (Throwable paramContext)
      {
        Context localContext;
        boolean bool;
        throw paramContext;
      }
      try
      {
        paramContext.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
        zzhu = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException paramContext) {}
    }
    zzhu = Boolean.valueOf(false);
    zzht = localContext;
    bool = zzhu.booleanValue();
    return bool;
  }
}
