package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.common.aimsicd.Api.AnyClient;
import com.google.android.android.tasks.TaskCompletionSource;

final class zacb
  extends com.google.android.gms.common.api.internal.UnregisterListenerMethod<A, L>
{
  zacb(RegistrationMethods.Builder paramBuilder, ListenerHolder.ListenerKey paramListenerKey)
  {
    super(paramListenerKey);
  }
  
  protected final void unregisterListener(Api.AnyClient paramAnyClient, TaskCompletionSource paramTaskCompletionSource)
    throws RemoteException
  {
    RegistrationMethods.Builder.removeOnChangeListener(zakg).accept(paramAnyClient, paramTaskCompletionSource);
  }
}
