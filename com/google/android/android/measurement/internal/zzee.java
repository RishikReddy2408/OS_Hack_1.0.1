package com.google.android.android.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzee
  implements Runnable
{
  zzee(zzdr paramZzdr, AtomicReference paramAtomicReference, ApplicationInfo paramApplicationInfo, boolean paramBoolean) {}
  
  public final void run()
  {
    localAtomicReference1 = zzash;
    for (;;)
    {
      Object localObject;
      AtomicReference localAtomicReference2;
      ApplicationInfo localApplicationInfo;
      boolean bool;
      try
      {
        localObject = zzasg;
      }
      catch (Throwable localThrowable1) {}
      try
      {
        localObject = zzdr.method_1((zzdr)localObject);
        if (localObject == null)
        {
          localObject = zzasg;
          ((zzco)localObject).zzgo().zzjd().zzbx("Failed to get user properties");
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzasg.zzgo().zzjd().append("Failed to get user properties", localRemoteException);
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
    localAtomicReference2 = zzash;
    localApplicationInfo = zzaqn;
    bool = zzaev;
    localAtomicReference2.set(((zzag)localObject).getFromLocationName(localApplicationInfo, bool));
    localObject = zzasg;
    zzdr.Refresh((zzdr)localObject);
    zzash.notify();
    zzash.notify();
    throw localRemoteException;
  }
}
