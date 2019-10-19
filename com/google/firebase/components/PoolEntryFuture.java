package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

final class PoolEntryFuture
  implements zze<Context>
{
  private PoolEntryFuture() {}
  
  private static Bundle get(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null)
      {
        Log.w("ComponentDiscovery", "Context has no PackageManager.");
        return null;
      }
      paramContext = localPackageManager.getServiceInfo(new ComponentName(paramContext, ComponentDiscoveryService.class), 128);
      if (paramContext == null)
      {
        Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
        return null;
      }
      return metaData;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    Log.w("ComponentDiscovery", "Application info not found.");
    return null;
  }
}
