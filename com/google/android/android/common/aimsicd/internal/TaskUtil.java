package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.aimsicd.ApiException;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class TaskUtil
{
  public TaskUtil() {}
  
  public static void setResultOrApiException(Status paramStatus, TaskCompletionSource paramTaskCompletionSource)
  {
    setResultOrApiException(paramStatus, null, paramTaskCompletionSource);
  }
  
  public static void setResultOrApiException(Status paramStatus, Object paramObject, TaskCompletionSource paramTaskCompletionSource)
  {
    if (paramStatus.isSuccess())
    {
      paramTaskCompletionSource.setResult(paramObject);
      return;
    }
    paramTaskCompletionSource.setException(new ApiException(paramStatus));
  }
  
  public static Task toVoidTaskThatFailsOnFalse(Task paramTask)
  {
    return paramTask.continueWith(new zacl());
  }
}
