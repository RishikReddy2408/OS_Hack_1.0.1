package com.google.android.android.measurement.internal;

import android.os.RemoteException;

final class zzdv
  implements Runnable
{
  zzdv(zzdr paramZzdr, ApplicationInfo paramApplicationInfo) {}
  
  public final void run()
  {
    Object localObject1 = zzdr.method_1(zzasg);
    if (localObject1 == null)
    {
      zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send app launch");
      return;
    }
    Object localObject2 = zzaqn;
    try
    {
      ((zzag)localObject1).chmod((ApplicationInfo)localObject2);
      localObject2 = zzasg;
      ApplicationInfo localApplicationInfo = zzaqn;
      ((zzdr)localObject2).sendRequest((zzag)localObject1, null, localApplicationInfo);
      localObject1 = zzasg;
      zzdr.Refresh((zzdr)localObject1);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzasg.zzgo().zzjd().append("Failed to send app launch to the service", localRemoteException);
    }
  }
}
