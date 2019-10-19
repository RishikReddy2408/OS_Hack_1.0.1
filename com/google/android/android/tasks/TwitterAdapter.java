package com.google.android.android.tasks;

final class TwitterAdapter
  implements OnTokenCanceledListener
{
  TwitterAdapter(TaskCompletionSource paramTaskCompletionSource) {}
  
  public final void onCanceled()
  {
    TaskCompletionSource.getTask(messages).close();
  }
}
