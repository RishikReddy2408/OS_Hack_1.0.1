package com.google.firebase.package_8;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.android.common.util.concurrent.NamedThreadFactory;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.TaskCompletionSource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class zzab
{
  @GuardedBy("MessengerIpcClient.class")
  private static zzab zzbt;
  private final Context sTemp;
  private final ScheduledExecutorService zzbu;
  @GuardedBy("this")
  private zzad zzbv = new zzad(this, null);
  @GuardedBy("this")
  private int zzbw = 1;
  
  private zzab(Context paramContext, ScheduledExecutorService paramScheduledExecutorService)
  {
    zzbu = paramScheduledExecutorService;
    sTemp = paramContext.getApplicationContext();
  }
  
  private final Task call(zzak paramZzak)
  {
    try
    {
      if (Log.isLoggable("MessengerIpcClient", 3))
      {
        String str = String.valueOf(paramZzak);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 9);
        localStringBuilder.append("Queueing ");
        localStringBuilder.append(str);
        Log.d("MessengerIpcClient", localStringBuilder.toString());
      }
      if (!zzbv.start(paramZzak))
      {
        zzbv = new zzad(this, null);
        zzbv.start(paramZzak);
      }
      paramZzak = zzcg.getTask();
      return paramZzak;
    }
    catch (Throwable paramZzak)
    {
      throw paramZzak;
    }
  }
  
  public static zzab get(Context paramContext)
  {
    try
    {
      if (zzbt == null) {
        zzbt = new zzab(paramContext, Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("MessengerIpcClient")));
      }
      paramContext = zzbt;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private final int notifyChange()
  {
    try
    {
      int i = zzbw;
      zzbw = (i + 1);
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Task readMessage(int paramInt, Bundle paramBundle)
  {
    return call(new zzam(notifyChange(), 1, paramBundle));
  }
  
  public final Task save(int paramInt, Bundle paramBundle)
  {
    return call(new zzaj(notifyChange(), 2, paramBundle));
  }
}
