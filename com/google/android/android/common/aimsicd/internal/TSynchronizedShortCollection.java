package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.common.Feature;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zad;
import java.util.Map;

public final class TSynchronizedShortCollection
  extends zad<Void>
{
  private final com.google.android.gms.common.api.internal.RegisterListenerMethod<Api.AnyClient, ?> zaco;
  private final UnregisterListenerMethod<Api.AnyClient, ?> zacp;
  
  public TSynchronizedShortCollection(zabw paramZabw, TaskCompletionSource paramTaskCompletionSource)
  {
    super(3, paramTaskCompletionSource);
    zaco = zajw;
    zacp = zajx;
  }
  
  public final void forEach(GoogleApiManager.zaa paramZaa)
    throws RemoteException
  {
    zaco.registerListener(paramZaa.zaab(), zacm);
    if (zaco.getListenerKey() != null) {
      paramZaa.zabk().put(zaco.getListenerKey(), new zabw(zaco, zacp));
    }
  }
  
  public final boolean isEmpty(GoogleApiManager.zaa paramZaa)
  {
    return zaco.shouldAutoResolveMissingFeatures();
  }
  
  public final Feature[] toString(GoogleApiManager.zaa paramZaa)
  {
    return zaco.getRequiredFeatures();
  }
}
