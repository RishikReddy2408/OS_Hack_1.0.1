package com.google.android.android.tasks;

abstract interface Object<TResult>
{
  public abstract void cancel();
  
  public abstract void onComplete(Task paramTask);
}
