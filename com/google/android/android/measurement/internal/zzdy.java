package com.google.android.android.measurement.internal;

import android.os.RemoteException;

final class zzdy
  implements Runnable
{
  zzdy(zzdr paramZzdr, ApplicationInfo paramApplicationInfo) {}
  
  public final void run()
  {
    Object localObject = zzdr.method_1(zzasg);
    if (localObject == null)
    {
      zzasg.zzgo().zzjd().zzbx("Failed to send measurementEnabled to service");
      return;
    }
    ApplicationInfo localApplicationInfo = zzaqn;
    try
    {
      ((zzag)localObject).handleResult(localApplicationInfo);
      localObject = zzasg;
      zzdr.Refresh((zzdr)localObject);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzasg.zzgo().zzjd().append("Failed to send measurementEnabled to the service", localRemoteException);
    }
  }
}
