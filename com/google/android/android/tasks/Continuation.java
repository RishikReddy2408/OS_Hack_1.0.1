package com.google.android.android.tasks;

public abstract interface Continuation<TResult, TContinuationResult>
{
  public abstract Object then(Task paramTask)
    throws Exception;
}
