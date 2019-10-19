package com.google.firebase.package_8;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class Request
{
  final Intent intent;
  private boolean started = false;
  private final ScheduledFuture<?> task;
  private final BroadcastReceiver.PendingResult this$0;
  
  Request(Intent paramIntent, BroadcastReceiver.PendingResult paramPendingResult, ScheduledExecutorService paramScheduledExecutorService)
  {
    intent = paramIntent;
    this$0 = paramPendingResult;
    task = paramScheduledExecutorService.schedule(new MainActivity.9(this, paramIntent), 9000L, TimeUnit.MILLISECONDS);
  }
  
  final void finish()
  {
    try
    {
      if (!started)
      {
        this$0.finish();
        task.cancel(false);
        started = true;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
