package com.google.android.android.common.aimsicd;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.TimeUnit;

@KeepForSdk
public abstract class PendingResult<R extends com.google.android.gms.common.api.Result>
{
  public PendingResult() {}
  
  public void addStatusListener(StatusListener paramStatusListener)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract Result await();
  
  public abstract Result await(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract void cancel();
  
  public Integer getValue()
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean isCanceled();
  
  public abstract void setResultCallback(ResultCallback paramResultCallback);
  
  public abstract void setResultCallback(ResultCallback paramResultCallback, long paramLong, TimeUnit paramTimeUnit);
  
  public TransformedResult then(ResultTransform paramResultTransform)
  {
    throw new UnsupportedOperationException();
  }
  
  @KeepForSdk
  public abstract interface StatusListener
  {
    public abstract void onComplete(Status paramStatus);
  }
}
