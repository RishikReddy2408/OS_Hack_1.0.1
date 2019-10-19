package com.google.android.android.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ClientLibraryUtils
{
  private ClientLibraryUtils() {}
  
  public static boolean get(Context paramContext, String paramString)
  {
    "com.google.android.gms".equals(paramString);
    try
    {
      paramContext = Wrappers.packageManager(paramContext).getApplicationInfo(paramString, 0);
      return (flags & 0x200000) != 0;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static int getClientVersion(Context paramContext, String paramString)
  {
    paramContext = getPackageInfo(paramContext, paramString);
    if (paramContext != null)
    {
      if (applicationInfo == null) {
        return -1;
      }
      paramContext = applicationInfo.metaData;
      if (paramContext == null) {
        return -1;
      }
      return paramContext.getInt("com.google.android.gms.version", -1);
    }
    return -1;
  }
  
  private static PackageInfo getPackageInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Wrappers.packageManager(paramContext).getPackageInfo(paramString, 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static boolean isPackageSide()
  {
    return false;
  }
}
