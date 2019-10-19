package com.google.android.android.common.aimsicd.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zacp
{
  public static final Status zakw = new Status(8, "The connection to Google Play services was lost");
  private static final com.google.android.gms.common.api.internal.BasePendingResult<?>[] zakx = new BasePendingResult[0];
  private final Map<Api.AnyClientKey<?>, com.google.android.gms.common.api.Api.Client> zagy;
  @VisibleForTesting
  final Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> zaky = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
  private final zacs zakz = new zacq(this);
  
  public zacp(Map paramMap)
  {
    zagy = paramMap;
  }
  
  final void close(BasePendingResult paramBasePendingResult)
  {
    zaky.add(paramBasePendingResult);
    paramBasePendingResult.remove(zakz);
  }
  
  public final void release()
  {
    BasePendingResult[] arrayOfBasePendingResult = (BasePendingResult[])zaky.toArray(zakx);
    int j = arrayOfBasePendingResult.length;
    int i = 0;
    while (i < j)
    {
      BasePendingResult localBasePendingResult = arrayOfBasePendingResult[i];
      localBasePendingResult.remove(null);
      IBinder localIBinder;
      zacr localZacr;
      if (localBasePendingResult.getValue() == null)
      {
        if (localBasePendingResult.compareAndSet()) {
          zaky.remove(localBasePendingResult);
        }
      }
      else
      {
        localBasePendingResult.setResultCallback(null);
        localIBinder = ((com.google.android.android.common.aimsicd.Api.Client)zagy.get(((BaseImplementation.ApiMethodImpl)localBasePendingResult).getClientKey())).getServiceBrokerBinder();
        if (localBasePendingResult.isReady())
        {
          localBasePendingResult.remove(new zacr(localBasePendingResult, null, localIBinder, null));
        }
        else if ((localIBinder != null) && (localIBinder.isBinderAlive()))
        {
          localZacr = new zacr(localBasePendingResult, null, localIBinder, null);
          localBasePendingResult.remove(localZacr);
        }
      }
      try
      {
        localIBinder.linkToDeath(localZacr, 0);
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      localBasePendingResult.cancel();
      localBasePendingResult.getValue().intValue();
      throw new NullPointerException("This statement would have triggered an Exception: virtualinvoke $u5#8.<com.google.android.gms.common.api.zac: void remove(int)>($u-1#32)");
      localBasePendingResult.remove(null);
      localBasePendingResult.cancel();
      localBasePendingResult.getValue().intValue();
      throw new NullPointerException("This statement would have triggered an Exception: virtualinvoke $u5#8.<com.google.android.gms.common.api.zac: void remove(int)>($u-1#36)");
      zaky.remove(localBasePendingResult);
      i += 1;
    }
  }
  
  public final void zabx()
  {
    BasePendingResult[] arrayOfBasePendingResult = (BasePendingResult[])zaky.toArray(zakx);
    int j = arrayOfBasePendingResult.length;
    int i = 0;
    while (i < j)
    {
      arrayOfBasePendingResult[i].await(zakw);
      i += 1;
    }
  }
}
