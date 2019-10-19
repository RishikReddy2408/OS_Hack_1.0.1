package com.google.android.android.common.providers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class UnsignedInteger
  implements PooledExecutorsProvider.PooledExecutorFactory
{
  UnsignedInteger() {}
  
  public final ScheduledExecutorService newSingleThreadScheduledExecutor()
  {
    return Executors.newSingleThreadScheduledExecutor();
  }
}
