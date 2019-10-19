package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;

final class zzbq
  implements Thread.UncaughtExceptionHandler
{
  private final String zzapf;
  
  public zzbq(zzbo paramZzbo, String paramString)
  {
    Preconditions.checkNotNull(paramString);
    zzapf = paramString;
  }
  
  public final void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    try
    {
      zzapg.zzgo().zzjd().append(zzapf, paramThrowable);
      return;
    }
    catch (Throwable paramThread)
    {
      throw paramThread;
    }
  }
}
