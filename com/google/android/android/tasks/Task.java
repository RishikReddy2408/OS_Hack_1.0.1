package com.google.android.android.tasks;

import android.app.Activity;
import java.util.concurrent.Executor;

public abstract class Task<TResult>
{
  public Task() {}
  
  public Task addOnCanceledListener(Activity paramActivity, OnCanceledListener paramOnCanceledListener)
  {
    throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
  }
  
  public Task addOnCanceledListener(OnCanceledListener paramOnCanceledListener)
  {
    throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
  }
  
  public Task addOnCanceledListener(Executor paramExecutor, OnCanceledListener paramOnCanceledListener)
  {
    throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
  }
  
  public Task addOnCompleteListener(Activity paramActivity, OnCompleteListener paramOnCompleteListener)
  {
    throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
  }
  
  public Task addOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
  }
  
  public Task addOnCompleteListener(Executor paramExecutor, OnCompleteListener paramOnCompleteListener)
  {
    throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
  }
  
  public abstract Task addOnFailureListener(Activity paramActivity, OnFailureListener paramOnFailureListener);
  
  public abstract Task addOnFailureListener(OnFailureListener paramOnFailureListener);
  
  public abstract Task addOnFailureListener(Executor paramExecutor, OnFailureListener paramOnFailureListener);
  
  public abstract Task addOnSuccessListener(Activity paramActivity, OnSuccessListener paramOnSuccessListener);
  
  public abstract Task addOnSuccessListener(OnSuccessListener paramOnSuccessListener);
  
  public abstract Task addOnSuccessListener(Executor paramExecutor, OnSuccessListener paramOnSuccessListener);
  
  public Task continueWith(Continuation paramContinuation)
  {
    throw new UnsupportedOperationException("continueWith is not implemented");
  }
  
  public Task continueWith(Executor paramExecutor, Continuation paramContinuation)
  {
    throw new UnsupportedOperationException("continueWith is not implemented");
  }
  
  public Task continueWithTask(Continuation paramContinuation)
  {
    throw new UnsupportedOperationException("continueWithTask is not implemented");
  }
  
  public Task continueWithTask(Executor paramExecutor, Continuation paramContinuation)
  {
    throw new UnsupportedOperationException("continueWithTask is not implemented");
  }
  
  public abstract Exception getException();
  
  public abstract Object getResult();
  
  public abstract Object getResult(Class paramClass)
    throws Throwable;
  
  public abstract boolean isCanceled();
  
  public abstract boolean isComplete();
  
  public abstract boolean isSuccessful();
  
  public Task onSuccessTask(SuccessContinuation paramSuccessContinuation)
  {
    throw new UnsupportedOperationException("onSuccessTask is not implemented");
  }
  
  public Task onSuccessTask(Executor paramExecutor, SuccessContinuation paramSuccessContinuation)
  {
    throw new UnsupportedOperationException("onSuccessTask is not implemented");
  }
}
