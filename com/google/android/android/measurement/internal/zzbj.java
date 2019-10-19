package com.google.android.android.measurement.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.android.common.internal.Preconditions;

public final class zzbj
{
  private final zzbm zzaoi;
  
  public zzbj(zzbm paramZzbm)
  {
    Preconditions.checkNotNull(paramZzbm);
    zzaoi = paramZzbm;
  }
  
  public static boolean isSystemApp(Context paramContext)
  {
    Preconditions.checkNotNull(paramContext);
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null) {
        return false;
      }
      paramContext = localPackageManager.getReceiverInfo(new ComponentName(paramContext, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);
      if (paramContext != null)
      {
        if (enabled) {
          return true;
        }
      }
      else {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    zzbt localZzbt = zzbt.register(paramContext, null);
    zzap localZzap = localZzbt.zzgo();
    if (paramIntent == null)
    {
      localZzap.zzjg().zzbx("Receiver called with null intent");
      return;
    }
    localZzbt.zzgr();
    Object localObject1 = paramIntent.getAction();
    localZzap.zzjl().append("Local receiver got", localObject1);
    if ("com.google.android.gms.measurement.UPLOAD".equals(localObject1))
    {
      paramIntent = new Intent().setClassName(paramContext, "com.google.android.gms.measurement.AppMeasurementService");
      paramIntent.setAction("com.google.android.gms.measurement.UPLOAD");
      localZzap.zzjl().zzbx("Starting wakeful intent.");
      zzaoi.doStartService(paramContext, paramIntent);
      return;
    }
    if ("com.android.vending.INSTALL_REFERRER".equals(localObject1))
    {
      try
      {
        localObject1 = localZzbt.zzgn();
        ((zzbo)localObject1).get(new zzbk(this, localZzbt, localZzap));
      }
      catch (Exception localException)
      {
        localZzap.zzjg().append("Install Referrer Reporter encountered a problem", localException);
      }
      BroadcastReceiver.PendingResult localPendingResult = zzaoi.doGoAsync();
      String str = paramIntent.getStringExtra("referrer");
      Object localObject2 = str;
      if (str == null)
      {
        localZzap.zzjl().zzbx("Install referrer extras are null");
        if (localPendingResult != null) {
          localPendingResult.finish();
        }
      }
      else
      {
        localZzap.zzjj().append("Install referrer extras are", str);
        if (!str.contains("?"))
        {
          localObject2 = String.valueOf(str);
          if (((String)localObject2).length() != 0) {
            localObject2 = "?".concat((String)localObject2);
          } else {
            localObject2 = new String("?");
          }
        }
        localObject2 = Uri.parse((String)localObject2);
        localObject2 = localZzbt.zzgm().parse((Uri)localObject2);
        if (localObject2 == null)
        {
          localZzap.zzjl().zzbx("No campaign defined in install referrer broadcast");
          if (localPendingResult != null) {
            localPendingResult.finish();
          }
        }
        else
        {
          long l = paramIntent.getLongExtra("referrer_timestamp_seconds", 0L) * 1000L;
          if (l == 0L) {
            localZzap.zzjg().zzbx("Install referrer is missing timestamp");
          }
          localZzbt.zzgn().get(new zzbl(this, localZzbt, l, (Bundle)localObject2, paramContext, localZzap, localPendingResult));
        }
      }
    }
  }
}
