package com.google.android.android.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.google.android.android.common.stats.ConnectionTracker;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.android.internal.measurement.IInAppBillingService;
import java.util.List;

public final class zzbg
{
  final zzbt zzadj;
  
  zzbg(zzbt paramZzbt)
  {
    zzadj = paramZzbt;
  }
  
  private final boolean zzka()
  {
    Object localObject = zzadj;
    try
    {
      localObject = Wrappers.packageManager(((zzbt)localObject).getContext());
      if (localObject == null)
      {
        localObject = zzadj;
        ((zzbt)localObject).zzgo().zzjj().zzbx("Failed to retrieve Package Manager to check Play Store compatibility");
        return false;
      }
      localObject = ((PackageManagerWrapper)localObject).getPackageInfo("com.android.vending", 128);
      if (versionCode >= 80837300) {
        return true;
      }
    }
    catch (Exception localException)
    {
      zzadj.zzgo().zzjj().append("Failed to retrieve Play Store version", localException);
    }
    return false;
  }
  
  final Bundle editComment(String paramString, IInAppBillingService paramIInAppBillingService)
  {
    zzadj.zzgn().zzaf();
    if (paramIInAppBillingService == null)
    {
      zzadj.zzgo().zzjg().zzbx("Attempting to use Install Referrer Service while it is not initialized");
      return null;
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("package_name", paramString);
    try
    {
      paramString = paramIInAppBillingService.getSkuDetails(localBundle);
      if (paramString == null)
      {
        paramString = zzadj;
        paramString.zzgo().zzjd().zzbx("Install Referrer Service returned a null response");
        return null;
      }
      return paramString;
    }
    catch (Exception paramString)
    {
      zzadj.zzgo().zzjd().append("Exception occurred while retrieving the Install Referrer", paramString.getMessage());
    }
    return null;
  }
  
  protected final void zzcd(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      zzadj.zzgn().zzaf();
      if (!zzka())
      {
        zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter is not available");
        return;
      }
      zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter is initializing");
      paramString = new zzbh(this, paramString);
      zzadj.zzgn().zzaf();
      Object localObject1 = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
      ((Intent)localObject1).setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
      Object localObject2 = zzadj.getContext().getPackageManager();
      if (localObject2 == null)
      {
        zzadj.zzgo().zzjg().zzbx("Failed to obtain Package Manager to verify binding conditions");
        return;
      }
      localObject2 = ((PackageManager)localObject2).queryIntentServices((Intent)localObject1, 0);
      if ((localObject2 != null) && (!((List)localObject2).isEmpty()))
      {
        localObject2 = (ResolveInfo)((List)localObject2).get(0);
        if (serviceInfo != null)
        {
          Object localObject3 = serviceInfo.packageName;
          if ((serviceInfo.name != null) && ("com.android.vending".equals(localObject3)) && (zzka()))
          {
            localObject1 = new Intent((Intent)localObject1);
            try
            {
              localObject2 = ConnectionTracker.getInstance();
              localObject3 = zzadj;
              boolean bool = ((ConnectionTracker)localObject2).bindService(((zzbt)localObject3).getContext(), (Intent)localObject1, paramString, 1);
              paramString = zzadj;
              localObject1 = paramString.zzgo().zzjj();
              if (bool) {
                paramString = "available";
              } else {
                paramString = "not available";
              }
              ((zzar)localObject1).append("Install Referrer Service is", paramString);
              return;
            }
            catch (Exception paramString)
            {
              zzadj.zzgo().zzjd().append("Exception occurred while binding to Install Referrer Service", paramString.getMessage());
              return;
            }
          }
          zzadj.zzgo().zzjj().zzbx("Play Store missing or incompatible. Version 8.3.73 or later required");
        }
      }
      else
      {
        zzadj.zzgo().zzjj().zzbx("Play Service for fetching Install Referrer is unavailable on device");
      }
    }
    else
    {
      zzadj.zzgo().zzjj().zzbx("Install Referrer Reporter was called with invalid app package name");
    }
  }
}
