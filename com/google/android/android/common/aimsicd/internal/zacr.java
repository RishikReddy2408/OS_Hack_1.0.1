package com.google.android.android.common.aimsicd.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.Realm;
import com.google.android.gms.common.api.zac;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zacr
  implements IBinder.DeathRecipient, zacs
{
  private final WeakReference<com.google.android.gms.common.api.internal.BasePendingResult<?>> zalb;
  private final WeakReference<zac> zalc;
  private final WeakReference<IBinder> zald;
  
  private zacr(BasePendingResult paramBasePendingResult, Realm paramRealm, IBinder paramIBinder)
  {
    zalc = new WeakReference(paramRealm);
    zalb = new WeakReference(paramBasePendingResult);
    zald = new WeakReference(paramIBinder);
  }
  
  private final void zaby()
  {
    Object localObject = (BasePendingResult)zalb.get();
    Realm localRealm = (Realm)zalc.get();
    if ((localRealm != null) && (localObject != null)) {
      localRealm.remove(((PendingResult)localObject).getValue().intValue());
    }
    localObject = (IBinder)zald.get();
    if (localObject != null) {}
    try
    {
      ((IBinder)localObject).unlinkToDeath(this, 0);
      return;
    }
    catch (NoSuchElementException localNoSuchElementException) {}
  }
  
  public final void andNot(BasePendingResult paramBasePendingResult)
  {
    zaby();
  }
  
  public final void binderDied()
  {
    zaby();
  }
}
