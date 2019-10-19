package com.google.android.android.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;

final class zzdz
  implements Runnable
{
  zzdz(zzdr paramZzdr, boolean paramBoolean1, boolean paramBoolean2, zzad paramZzad, ApplicationInfo paramApplicationInfo, String paramString) {}
  
  public final void run()
  {
    zzag localZzag = zzdr.method_1(zzasg);
    if (localZzag == null)
    {
      zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send event to service");
      return;
    }
    Object localObject2;
    Object localObject1;
    if (zzasi)
    {
      localObject2 = zzasg;
      if (zzasj) {
        localObject1 = null;
      } else {
        localObject1 = zzaqr;
      }
      ((zzdr)localObject2).sendRequest(localZzag, (AbstractSafeParcelable)localObject1, zzaqn);
    }
    else
    {
      localObject1 = zzaqq;
      try
      {
        boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
        if (bool)
        {
          localObject1 = zzaqr;
          localObject2 = zzaqn;
          localZzag.handleResult((zzad)localObject1, (ApplicationInfo)localObject2);
        }
        else
        {
          localObject1 = zzaqr;
          localObject2 = zzaqq;
          zzdr localZzdr = zzasg;
          localZzag.handleResult((zzad)localObject1, (String)localObject2, localZzdr.zzgo().zzjn());
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzasg.zzgo().zzjd().append("Failed to send event to the service", localRemoteException);
      }
    }
    zzdr.Refresh(zzasg);
  }
}
