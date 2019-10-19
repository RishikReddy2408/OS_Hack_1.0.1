package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class UnregisterListenerMethod<A extends com.google.android.gms.common.api.Api.AnyClient, L>
{
  private final com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey<L> zajk;
  
  protected UnregisterListenerMethod(ListenerHolder.ListenerKey paramListenerKey)
  {
    zajk = paramListenerKey;
  }
  
  public ListenerHolder.ListenerKey getListenerKey()
  {
    return zajk;
  }
  
  protected abstract void unregisterListener(com.google.android.android.common.aimsicd.Api.AnyClient paramAnyClient, TaskCompletionSource paramTaskCompletionSource)
    throws RemoteException;
}
