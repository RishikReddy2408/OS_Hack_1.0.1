package com.google.android.android.tasks;

public abstract interface SuccessContinuation<TResult, TContinuationResult>
{
  public abstract Task then(Object paramObject)
    throws Exception;
}
