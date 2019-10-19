package com.google.android.android.measurement.internal;

import android.content.Context;
import android.os.RemoteException;

final class zzdw
  implements Runnable
{
  zzdw(zzdr paramZzdr, zzdn paramZzdn) {}
  
  public final void run()
  {
    Object localObject1 = zzdr.method_1(zzasg);
    if (localObject1 == null)
    {
      zzasg.zzgo().zzjd().zzbx("Failed to send current screen to service");
      return;
    }
    Object localObject2;
    if (zzary == null) {
      localObject2 = zzasg;
    }
    try
    {
      ((zzag)localObject1).setPresence(0L, null, null, ((zzco)localObject2).getContext().getPackageName());
      break label112;
      long l = zzary.zzarm;
      localObject2 = zzary.zzuw;
      String str = zzary.zzarl;
      zzdr localZzdr = zzasg;
      ((zzag)localObject1).setPresence(l, (String)localObject2, str, localZzdr.getContext().getPackageName());
      label112:
      localObject1 = zzasg;
      zzdr.Refresh((zzdr)localObject1);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzasg.zzgo().zzjd().append("Failed to send current screen to the service", localRemoteException);
    }
  }
}
