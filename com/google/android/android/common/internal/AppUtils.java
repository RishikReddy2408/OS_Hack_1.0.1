package com.google.android.android.common.internal;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import javax.annotation.concurrent.GuardedBy;

public final class AppUtils
{
  private static Object sLock = new Object();
  @GuardedBy("sLock")
  private static boolean zzeo;
  private static String zzep;
  private static int zzeq;
  
  private static void getAppVersion(Context paramContext)
  {
    Object localObject = sLock;
    try
    {
      if (zzeo) {
        return;
      }
      zzeo = true;
      String str = paramContext.getPackageName();
      paramContext = Wrappers.packageManager(paramContext);
      try
      {
        paramContext = paramContext.getApplicationInfo(str, 128);
        paramContext = metaData;
        if (paramContext == null) {
          return;
        }
        str = paramContext.getString("com.google.app.id");
        zzep = str;
        int i = paramContext.getInt("com.google.android.gms.version");
        zzeq = i;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.wtf("MetadataValueReader", "This should never happen.", paramContext);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static int getVersionCode(Context paramContext)
  {
    getAppVersion(paramContext);
    return zzeq;
  }
  
  public static String getVersionString(Context paramContext)
  {
    getAppVersion(paramContext);
    return zzep;
  }
}
