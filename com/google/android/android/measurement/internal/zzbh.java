package com.google.android.android.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.android.internal.measurement.IInAppBillingService.Stub;

public final class zzbh
  implements ServiceConnection
{
  private final String packageName;
  
  zzbh(zzbg paramZzbg, String paramString)
  {
    packageName = paramString;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    if (paramIBinder == null)
    {
      zzaoe.zzadj.zzgo().zzjg().zzbx("Install Referrer connection returned with null binder");
      return;
    }
    try
    {
      paramComponentName = IInAppBillingService.Stub.asInterface(paramIBinder);
      if (paramComponentName == null)
      {
        paramComponentName = zzaoe.zzadj;
        paramComponentName.zzgo().zzjg().zzbx("Install Referrer Service implementation was not found");
        return;
      }
      paramIBinder = zzaoe.zzadj;
      paramIBinder.zzgo().zzjj().zzbx("Install Referrer Service connected");
      paramIBinder = zzaoe.zzadj;
      paramIBinder = paramIBinder.zzgn();
      paramIBinder.get(new zzbi(this, paramComponentName, this));
      return;
    }
    catch (Exception paramComponentName)
    {
      zzaoe.zzadj.zzgo().zzjg().append("Exception occurred while calling Install Referrer API", paramComponentName);
    }
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    zzaoe.zzadj.zzgo().zzjj().zzbx("Install Referrer Service disconnected");
  }
}
