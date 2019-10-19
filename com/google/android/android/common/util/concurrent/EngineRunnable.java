package com.google.android.android.common.util.concurrent;

import android.os.Process;

final class EngineRunnable
  implements Runnable
{
  private final int priority;
  private final Runnable zzhs;
  
  public EngineRunnable(Runnable paramRunnable, int paramInt)
  {
    zzhs = paramRunnable;
    priority = paramInt;
  }
  
  public final void run()
  {
    Process.setThreadPriority(priority);
    zzhs.run();
  }
}
