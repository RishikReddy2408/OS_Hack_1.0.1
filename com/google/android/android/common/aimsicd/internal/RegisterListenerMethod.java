package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.common.Feature;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class RegisterListenerMethod<A extends com.google.android.gms.common.api.Api.AnyClient, L>
{
  private final com.google.android.gms.common.api.internal.ListenerHolder<L> zajt;
  private final Feature[] zaju;
  private final boolean zajv;
  
  protected RegisterListenerMethod(ListenerHolder paramListenerHolder)
  {
    zajt = paramListenerHolder;
    zaju = null;
    zajv = false;
  }
  
  protected RegisterListenerMethod(ListenerHolder paramListenerHolder, Feature[] paramArrayOfFeature, boolean paramBoolean)
  {
    zajt = paramListenerHolder;
    zaju = paramArrayOfFeature;
    zajv = paramBoolean;
  }
  
  public void clearListener()
  {
    zajt.clear();
  }
  
  public ListenerHolder.ListenerKey getListenerKey()
  {
    return zajt.getListenerKey();
  }
  
  public Feature[] getRequiredFeatures()
  {
    return zaju;
  }
  
  protected abstract void registerListener(com.google.android.android.common.aimsicd.Api.AnyClient paramAnyClient, TaskCompletionSource paramTaskCompletionSource)
    throws RemoteException;
  
  public final boolean shouldAutoResolveMissingFeatures()
  {
    return zajv;
  }
}
