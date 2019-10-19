package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

final class zzbr<V>
  extends FutureTask<V>
  implements Comparable<com.google.android.gms.measurement.internal.zzbr>
{
  private final String zzapf;
  private final long zzaph;
  final boolean zzapi;
  
  zzbr(zzbo paramZzbo, Runnable paramRunnable, boolean paramBoolean, String paramString)
  {
    super(paramRunnable, null);
    Preconditions.checkNotNull(paramString);
    zzaph = zzbo.zzkd().getAndIncrement();
    zzapf = paramString;
    zzapi = false;
    if (zzaph == Long.MAX_VALUE) {
      paramZzbo.zzgo().zzjd().zzbx("Tasks index overflow");
    }
  }
  
  zzbr(zzbo paramZzbo, Callable paramCallable, boolean paramBoolean, String paramString)
  {
    super(paramCallable);
    Preconditions.checkNotNull(paramString);
    zzaph = zzbo.zzkd().getAndIncrement();
    zzapf = paramString;
    zzapi = paramBoolean;
    if (zzaph == Long.MAX_VALUE) {
      paramZzbo.zzgo().zzjd().zzbx("Tasks index overflow");
    }
  }
  
  protected final void setException(Throwable paramThrowable)
  {
    zzapg.zzgo().zzjd().append(zzapf, paramThrowable);
    if ((paramThrowable instanceof zzbp)) {
      Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), paramThrowable);
    }
    super.setException(paramThrowable);
  }
}
