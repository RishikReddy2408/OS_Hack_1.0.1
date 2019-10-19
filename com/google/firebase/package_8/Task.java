package com.google.firebase.package_8;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class Task
{
  private static final Executor zzad = MoreExecutors.DirectExecutor.zzaf;
  
  static Executor getExecutor()
  {
    return new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.1.zzae);
  }
  
  static Executor isCompleted()
  {
    return zzad;
  }
}
