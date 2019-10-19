package com.google.android.android.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class TaskExecutors
{
  public static final Executor MAIN_THREAD = new zza();
  static final Executor executor = new Threading.2();
  
  private TaskExecutors() {}
  
  final class zza
    implements Executor
  {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    
    public zza() {}
    
    public final void execute(Runnable paramRunnable)
    {
      mHandler.post(paramRunnable);
    }
  }
}
