package com.google.android.android.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzdu
  implements Runnable
{
  zzdu(zzdr paramZzdr, AtomicReference paramAtomicReference, ApplicationInfo paramApplicationInfo) {}
  
  public final void run()
  {
    localAtomicReference = zzash;
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      ApplicationInfo localApplicationInfo;
      try
      {
        localObject1 = zzasg;
      }
      catch (Throwable localThrowable1) {}
      try
      {
        localObject1 = zzdr.method_1((zzdr)localObject1);
        if (localObject1 == null)
        {
          localObject1 = zzasg;
          ((zzco)localObject1).zzgo().zzjd().zzbx("Failed to get app instance id");
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzasg.zzgo().zzjd().append("Failed to get app instance id", localRemoteException);
        zzash.notify();
        return;
      }
    }
    try
    {
      zzash.notify();
      return;
    }
    catch (Throwable localThrowable2)
    {
      throw localThrowable2;
    }
    localObject2 = zzash;
    localApplicationInfo = zzaqn;
    ((AtomicReference)localObject2).set(((zzag)localObject1).getAbsoluteUrl(localApplicationInfo));
    localObject1 = zzash;
    localObject1 = ((AtomicReference)localObject1).get();
    localObject1 = (String)localObject1;
    if (localObject1 != null)
    {
      localObject2 = zzasg;
      ((class_2)localObject2).zzge().zzcm((String)localObject1);
      localObject2 = zzasg;
      localObject2 = ((zzco)localObject2).zzgp();
      localObject2 = zzanl;
      ((zzbf)localObject2).zzcc((String)localObject1);
    }
    localObject1 = zzasg;
    zzdr.Refresh((zzdr)localObject1);
    zzash.notify();
    zzash.notify();
    throw localRemoteException;
  }
}
