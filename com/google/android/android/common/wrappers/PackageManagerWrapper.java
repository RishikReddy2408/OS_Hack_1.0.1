package com.google.android.android.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Process;
import com.google.android.android.common.util.PlatformVersion;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PackageManagerWrapper
{
  private final Context zzhv;
  
  public PackageManagerWrapper(Context paramContext)
  {
    zzhv = paramContext;
  }
  
  public int checkCallingOrSelfPermission(String paramString)
  {
    return zzhv.checkCallingOrSelfPermission(paramString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return zzhv.getPackageManager().checkPermission(paramString1, paramString2);
  }
  
  public final boolean checkPermission(int paramInt, String paramString)
  {
    if (PlatformVersion.isAtLeastKitKat()) {
      localObject = zzhv;
    }
    try
    {
      localObject = ((Context)localObject).getSystemService("appops");
      localObject = (AppOpsManager)localObject;
      ((AppOpsManager)localObject).checkPackage(paramInt, paramString);
      return true;
    }
    catch (SecurityException paramString) {}
    Object localObject = zzhv.getPackageManager().getPackagesForUid(paramInt);
    if (paramString != null)
    {
      if (localObject != null)
      {
        paramInt = 0;
        while (paramInt < localObject.length)
        {
          if (paramString.equals(localObject[paramInt])) {
            return true;
          }
          paramInt += 1;
        }
      }
    }
    else {
      return false;
    }
    return false;
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return zzhv.getPackageManager().getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return zzhv.getPackageManager().getApplicationLabel(zzhv.getPackageManager().getApplicationInfo(paramString, 0));
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return zzhv.getPackageManager().getPackageInfo(paramString, paramInt);
  }
  
  public final PackageInfo getPackageInfo(String paramString, int paramInt1, int paramInt2)
    throws PackageManager.NameNotFoundException
  {
    return zzhv.getPackageManager().getPackageInfo(paramString, 64);
  }
  
  public final String[] getPackagesForUid(int paramInt)
  {
    return zzhv.getPackageManager().getPackagesForUid(paramInt);
  }
  
  public boolean isCallerInstantApp()
  {
    if (Binder.getCallingUid() == Process.myUid()) {
      return InstantApps.isInstantApp(zzhv);
    }
    if (PlatformVersion.isAtLeastO())
    {
      String str = zzhv.getPackageManager().getNameForUid(Binder.getCallingUid());
      if (str != null) {
        return zzhv.getPackageManager().isInstantApp(str);
      }
    }
    return false;
  }
}
