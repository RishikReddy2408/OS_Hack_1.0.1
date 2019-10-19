package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzq;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class AppAdapter<TResult>
  implements zzq<TResult>
{
  private final Executor background;
  @GuardedBy("mLock")
  private com.google.android.gms.tasks.OnSuccessListener<? super TResult> context;
  private final Object mLock = new Object();
  
  public AppAdapter(Executor paramExecutor, OnSuccessListener paramOnSuccessListener)
  {
    background = paramExecutor;
    context = paramOnSuccessListener;
  }
  
  public final void cancel()
  {
    Object localObject = mLock;
    try
    {
      context = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void onComplete(Task paramTask)
  {
    if (paramTask.isSuccessful())
    {
      Object localObject = mLock;
      try
      {
        if (context == null) {
          return;
        }
        background.execute(new EventInfoFragment.1(this, paramTask));
        return;
      }
      catch (Throwable paramTask)
      {
        throw paramTask;
      }
    }
  }
}
