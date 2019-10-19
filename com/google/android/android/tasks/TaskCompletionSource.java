package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzu;

public class TaskCompletionSource<TResult>
{
  private final zzu<TResult> this$0 = new AbstractDataSource();
  
  public TaskCompletionSource() {}
  
  public TaskCompletionSource(CancellationToken paramCancellationToken)
  {
    paramCancellationToken.onCanceledRequested(new TwitterAdapter(this));
  }
  
  public Task getTask()
  {
    return this$0;
  }
  
  public void setException(Exception paramException)
  {
    this$0.setException(paramException);
  }
  
  public void setResult(Object paramObject)
  {
    this$0.setResult(paramObject);
  }
  
  public boolean trySetException(Exception paramException)
  {
    return this$0.trySetException(paramException);
  }
  
  public boolean trySetResult(Object paramObject)
  {
    return this$0.trySetResult(paramObject);
  }
}
