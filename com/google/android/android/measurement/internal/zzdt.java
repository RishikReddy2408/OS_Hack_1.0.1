package com.google.android.android.measurement.internal;

import android.os.RemoteException;

final class zzdt
  implements Runnable
{
  zzdt(zzdr paramZzdr, ApplicationInfo paramApplicationInfo) {}
  
  public final void run()
  {
    zzag localZzag = zzdr.method_1(zzasg);
    if (localZzag == null)
    {
      zzasg.zzgo().zzjd().zzbx("Failed to reset data on the service; null service");
      return;
    }
    ApplicationInfo localApplicationInfo = zzaqn;
    try
    {
      localZzag.createShortcut(localApplicationInfo);
    }
    catch (RemoteException localRemoteException)
    {
      zzasg.zzgo().zzjd().append("Failed to reset data on the service", localRemoteException);
    }
    zzdr.Refresh(zzasg);
  }
}
