package com.google.android.android.common.internal;

import com.google.android.android.common.aimsicd.ApiException;
import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PendingResultUtil
{
  private static final zaa zaot = new Response();
  
  public PendingResultUtil() {}
  
  public static Task toResponseTask(PendingResult paramPendingResult, com.google.android.android.common.aimsicd.Response paramResponse)
  {
    return toTask(paramPendingResult, new Converter(paramResponse));
  }
  
  public static Task toTask(PendingResult paramPendingResult, ResultConverter paramResultConverter)
  {
    zaa localZaa = zaot;
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramPendingResult.addStatusListener(new LoginActivity.1(paramPendingResult, localTaskCompletionSource, paramResultConverter, localZaa));
    return localTaskCompletionSource.getTask();
  }
  
  public static Task toVoidTask(PendingResult paramPendingResult)
  {
    return toTask(paramPendingResult, new TypeConverter());
  }
  
  @KeepForSdk
  public abstract interface ResultConverter<R extends com.google.android.gms.common.api.Result, T>
  {
    public abstract Object convert(com.google.android.android.common.aimsicd.Result paramResult);
  }
  
  public abstract interface zaa
  {
    public abstract ApiException getException(Status paramStatus);
  }
}
