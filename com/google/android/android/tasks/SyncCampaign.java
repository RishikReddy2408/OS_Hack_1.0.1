package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzq;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class SyncCampaign<TResult>
  implements zzq<TResult>
{
  private final Executor httpExecutor;
  private final Object mLock = new Object();
  @GuardedBy("mLock")
  private com.google.android.gms.tasks.OnCompleteListener<TResult> this$0;
  
  public SyncCampaign(Executor paramExecutor, OnCompleteListener paramOnCompleteListener)
  {
    httpExecutor = paramExecutor;
    this$0 = paramOnCompleteListener;
  }
  
  public final void cancel()
  {
    Object localObject = mLock;
    try
    {
      this$0 = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void onComplete(Task paramTask)
  {
    Object localObject = mLock;
    try
    {
      if (this$0 == null) {
        return;
      }
      httpExecutor.execute(new Connection.1(this, paramTask));
      return;
    }
    catch (Throwable paramTask)
    {
      throw paramTask;
    }
  }
}
