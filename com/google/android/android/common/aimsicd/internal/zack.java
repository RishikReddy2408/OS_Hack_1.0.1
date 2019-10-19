package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.common.Feature;
import com.google.android.android.common.aimsicd.Api.AnyClient;
import com.google.android.android.tasks.TaskCompletionSource;

final class zack
  extends com.google.android.gms.common.api.internal.TaskApiCall<A, ResultT>
{
  zack(TaskApiCall.Builder paramBuilder, Feature[] paramArrayOfFeature, boolean paramBoolean)
  {
    super(paramArrayOfFeature, paramBoolean, null);
  }
  
  protected final void doExecute(Api.AnyClient paramAnyClient, TaskCompletionSource paramTaskCompletionSource)
    throws RemoteException
  {
    TaskApiCall.Builder.getExecutor(zakm).accept(paramAnyClient, paramTaskCompletionSource);
  }
}
