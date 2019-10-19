package com.google.android.android.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;

final class zzea
  implements Runnable
{
  zzea(zzdr paramZzdr, boolean paramBoolean1, boolean paramBoolean2, ComponentInfo paramComponentInfo1, ApplicationInfo paramApplicationInfo, ComponentInfo paramComponentInfo2) {}
  
  public final void run()
  {
    zzag localZzag = zzdr.method_1(zzasg);
    if (localZzag == null)
    {
      zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send conditional user property to service");
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
        localObject1 = zzask;
      }
      ((zzdr)localObject2).sendRequest(localZzag, (AbstractSafeParcelable)localObject1, zzaqn);
    }
    else
    {
      localObject1 = zzasl.packageName;
      try
      {
        boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
        if (bool)
        {
          localObject1 = zzask;
          localObject2 = zzaqn;
          localZzag.getPackageInfo((ComponentInfo)localObject1, (ApplicationInfo)localObject2);
        }
        else
        {
          localObject1 = zzask;
          localZzag.toggleState((ComponentInfo)localObject1);
        }
      }
      catch (RemoteException localRemoteException)
      {
        zzasg.zzgo().zzjd().append("Failed to send conditional user property to the service", localRemoteException);
      }
    }
    zzdr.Refresh(zzasg);
  }
}
