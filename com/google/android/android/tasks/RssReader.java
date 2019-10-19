package com.google.android.android.tasks;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.zzq;
import com.google.android.gms.tasks.zzu;
import java.util.concurrent.Executor;

final class RssReader<TResult, TContinuationResult>
  implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult>
{
  private final Executor mContext;
  private final zzu<TContinuationResult> this$0;
  private final com.google.android.gms.tasks.SuccessContinuation<TResult, TContinuationResult> url;
  
  public RssReader(Executor paramExecutor, SuccessContinuation paramSuccessContinuation, AbstractDataSource paramAbstractDataSource)
  {
    mContext = paramExecutor;
    url = paramSuccessContinuation;
    this$0 = paramAbstractDataSource;
  }
  
  public final void cancel()
  {
    throw new UnsupportedOperationException();
  }
  
  public final void onCanceled()
  {
    this$0.close();
  }
  
  public final void onComplete(Task paramTask)
  {
    mContext.execute(new Parallel.TaskRunnable(this, paramTask));
  }
  
  public final void onFailure(Exception paramException)
  {
    this$0.setException(paramException);
  }
  
  public final void onSuccess(Object paramObject)
  {
    this$0.setResult(paramObject);
  }
}
