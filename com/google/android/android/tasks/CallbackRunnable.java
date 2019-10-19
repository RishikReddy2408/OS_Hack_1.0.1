package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzq;
import com.google.android.gms.tasks.zzu;
import java.util.concurrent.Executor;

final class CallbackRunnable<TResult, TContinuationResult>
  implements zzq<TResult>
{
  private final com.google.android.gms.tasks.Continuation<TResult, TContinuationResult> callback;
  private final Executor callbackExecutor;
  private final zzu<TContinuationResult> onComplete;
  
  public CallbackRunnable(Executor paramExecutor, Continuation paramContinuation, AbstractDataSource paramAbstractDataSource)
  {
    callbackExecutor = paramExecutor;
    callback = paramContinuation;
    onComplete = paramAbstractDataSource;
  }
  
  public final void cancel()
  {
    throw new UnsupportedOperationException();
  }
  
  public final void onComplete(Task paramTask)
  {
    callbackExecutor.execute(new Callables.3(this, paramTask));
  }
}
