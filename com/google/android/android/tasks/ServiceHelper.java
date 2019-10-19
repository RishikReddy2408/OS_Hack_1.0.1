package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzq;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class ServiceHelper<TResult>
  implements zzq<TResult>
{
  private final Executor mContext;
  private final Object mLock = new Object();
  @GuardedBy("mLock")
  private OnFailureListener mView;
  
  public ServiceHelper(Executor paramExecutor, OnFailureListener paramOnFailureListener)
  {
    mContext = paramExecutor;
    mView = paramOnFailureListener;
  }
  
  public final void cancel()
  {
    Object localObject = mLock;
    try
    {
      mView = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void onComplete(Task paramTask)
  {
    if ((!paramTask.isSuccessful()) && (!paramTask.isCanceled()))
    {
      Object localObject = mLock;
      try
      {
        if (mView == null) {
          return;
        }
        mContext.execute(new DayFragment.1(this, paramTask));
        return;
      }
      catch (Throwable paramTask)
      {
        throw paramTask;
      }
    }
  }
}
