package com.google.android.android.measurement.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.internal.measurement.zzdx;

abstract class Alarm
{
  private static volatile Handler handler;
  private final zzcq zzahw;
  private final Runnable zzyo;
  private volatile long zzyp;
  
  Alarm(zzcq paramZzcq)
  {
    Preconditions.checkNotNull(paramZzcq);
    zzahw = paramZzcq;
    zzyo = new XMPPService.4(this, paramZzcq);
  }
  
  private final Handler getHandler()
  {
    if (handler != null) {
      return handler;
    }
    try
    {
      if (handler == null) {
        handler = new zzdx(zzahw.getContext().getMainLooper());
      }
      Handler localHandler = handler;
      return localHandler;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  final void cancel()
  {
    zzyp = 0L;
    getHandler().removeCallbacks(zzyo);
  }
  
  public abstract void readOne();
  
  public final void setAlarm(long paramLong)
  {
    cancel();
    if (paramLong >= 0L)
    {
      zzyp = zzahw.zzbx().currentTimeMillis();
      if (!getHandler().postDelayed(zzyo, paramLong)) {
        zzahw.zzgo().zzjd().append("Failed to schedule delayed post. time", Long.valueOf(paramLong));
      }
    }
  }
  
  public final boolean zzej()
  {
    return zzyp != 0L;
  }
}
