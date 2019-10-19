package com.google.android.android.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class zzec
  implements Runnable
{
  zzec(zzdr paramZzdr, AtomicReference paramAtomicReference, String paramString1, String paramString2, String paramString3, boolean paramBoolean, ApplicationInfo paramApplicationInfo) {}
  
  public final void run()
  {
    localAtomicReference = zzash;
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      String str1;
      String str2;
      boolean bool;
      Object localObject3;
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
          localObject1 = ((zzco)localObject1).zzgo().zzjd();
          localObject2 = zzaqq;
          localObject2 = zzap.zzbv((String)localObject2);
          str1 = zzaeh;
          str2 = zzaeo;
          ((zzar)localObject1).append("Failed to get user properties", localObject2, str1, str2);
          localObject1 = zzash;
          ((AtomicReference)localObject1).set(Collections.emptyList());
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzasg.zzgo().zzjd().append("Failed to get user properties", zzap.zzbv(zzaqq), zzaeh, localRemoteException);
        zzash.set(Collections.emptyList());
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
    localObject2 = zzaqq;
    bool = TextUtils.isEmpty((CharSequence)localObject2);
    if (bool)
    {
      localObject2 = zzash;
      str1 = zzaeh;
      str2 = zzaeo;
      bool = zzaev;
      localObject3 = zzaqn;
      ((AtomicReference)localObject2).set(((zzag)localObject1).getFromLocationName(str1, str2, bool, (ApplicationInfo)localObject3));
    }
    else
    {
      localObject2 = zzash;
      str1 = zzaqq;
      str2 = zzaeh;
      localObject3 = zzaeo;
      bool = zzaev;
      ((AtomicReference)localObject2).set(((zzag)localObject1).getFromLocationName(str1, str2, (String)localObject3, bool));
    }
    localObject1 = zzasg;
    zzdr.Refresh((zzdr)localObject1);
    zzash.notify();
    zzash.notify();
    throw localRemoteException;
  }
}
