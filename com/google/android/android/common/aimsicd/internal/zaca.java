package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.common.Feature;
import com.google.android.android.common.aimsicd.Api.AnyClient;
import com.google.android.android.tasks.TaskCompletionSource;

final class zaca
  extends com.google.android.gms.common.api.internal.RegisterListenerMethod<A, L>
{
  zaca(RegistrationMethods.Builder paramBuilder, ListenerHolder paramListenerHolder, Feature[] paramArrayOfFeature, boolean paramBoolean)
  {
    super(paramListenerHolder, paramArrayOfFeature, paramBoolean);
  }
  
  protected final void registerListener(Api.AnyClient paramAnyClient, TaskCompletionSource paramTaskCompletionSource)
    throws RemoteException
  {
    RegistrationMethods.Builder.elementAt(zakg).accept(paramAnyClient, paramTaskCompletionSource);
  }
}
