package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.PendingResult.StatusListener;
import com.google.android.android.common.aimsicd.ResultCallback;
import com.google.android.android.common.aimsicd.ResultTransform;
import com.google.android.android.common.aimsicd.TransformedResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.TimeUnit;

@KeepForSdk
public final class OptionalPendingResultImpl<R extends com.google.android.gms.common.api.Result>
  extends com.google.android.gms.common.api.OptionalPendingResult<R>
{
  private final com.google.android.gms.common.api.internal.BasePendingResult<R> zajp;
  
  public OptionalPendingResultImpl(PendingResult paramPendingResult)
  {
    zajp = ((BasePendingResult)paramPendingResult);
  }
  
  public final void addStatusListener(PendingResult.StatusListener paramStatusListener)
  {
    zajp.addStatusListener(paramStatusListener);
  }
  
  public final com.google.android.android.common.aimsicd.Result await()
  {
    return zajp.await();
  }
  
  public final com.google.android.android.common.aimsicd.Result await(long paramLong, TimeUnit paramTimeUnit)
  {
    return zajp.await(paramLong, paramTimeUnit);
  }
  
  public final void cancel()
  {
    zajp.cancel();
  }
  
  public final com.google.android.android.common.aimsicd.Result getResponses()
  {
    if (isDone()) {
      return await(0L, TimeUnit.MILLISECONDS);
    }
    throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
  }
  
  public final Integer getValue()
  {
    return zajp.getValue();
  }
  
  public final boolean isCanceled()
  {
    return zajp.isCanceled();
  }
  
  public final boolean isDone()
  {
    return zajp.isReady();
  }
  
  public final void setResultCallback(ResultCallback paramResultCallback)
  {
    zajp.setResultCallback(paramResultCallback);
  }
  
  public final void setResultCallback(ResultCallback paramResultCallback, long paramLong, TimeUnit paramTimeUnit)
  {
    zajp.setResultCallback(paramResultCallback, paramLong, paramTimeUnit);
  }
  
  public final TransformedResult then(ResultTransform paramResultTransform)
  {
    return zajp.then(paramResultTransform);
  }
}
