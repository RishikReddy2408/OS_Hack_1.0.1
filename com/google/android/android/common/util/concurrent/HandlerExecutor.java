package com.google.android.android.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.android.android.internal.common.Timer;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executor;

@KeepForSdk
public class HandlerExecutor
  implements Executor
{
  private final Handler handler;
  
  public HandlerExecutor(Looper paramLooper)
  {
    handler = new Timer(paramLooper);
  }
  
  public void execute(Runnable paramRunnable)
  {
    handler.post(paramRunnable);
  }
}
