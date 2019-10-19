package com.google.android.android.measurement.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.android.common.util.Clock;
import com.google.android.android.internal.measurement.zzdx;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzeq
  extends Log
{
  private Handler handler;
  @VisibleForTesting
  private long zzasw = zzbx().elapsedRealtime();
  private final Alarm zzasx = new zzer(this, zzadj);
  private final Alarm zzasy = new zzes(this, zzadj);
  
  zzeq(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final void zzal(long paramLong)
  {
    zzaf();
    zzli();
    zzgo().zzjl().append("Activity resumed, time", Long.valueOf(paramLong));
    zzasw = paramLong;
    if (zzgq().zzbj(zzgf().zzal()))
    {
      zzam(zzbx().currentTimeMillis());
      return;
    }
    zzasx.cancel();
    zzasy.cancel();
    if (zzbx().currentTimeMillis() - zzgpzzanq.readLong() > zzgpzzant.readLong())
    {
      zzgpzzanr.store(true);
      zzgpzzanu.getFolder(0L);
    }
    if (zzgpzzanr.getBool())
    {
      zzasx.setAlarm(Math.max(0L, zzgpzzanp.readLong() - zzgpzzanu.readLong()));
      return;
    }
    zzasy.setAlarm(Math.max(0L, 3600000L - zzgpzzanu.readLong()));
  }
  
  private final void zzan(long paramLong)
  {
    zzaf();
    zzli();
    zzasx.cancel();
    zzasy.cancel();
    zzgo().zzjl().append("Activity paused, time", Long.valueOf(paramLong));
    if (zzasw != 0L) {
      zzgpzzanu.getFolder(zzgpzzanu.readLong() + (paramLong - zzasw));
    }
  }
  
  private final void zzao(long paramLong)
  {
    zzaf();
    long l = zzbx().elapsedRealtime();
    zzgo().zzjl().append("Session started, time", Long.valueOf(l));
    if (zzgq().zzbi(zzgf().zzal()))
    {
      l = paramLong / 1000L;
      zzge().attribute("auto", "_sid", Long.valueOf(l), paramLong);
    }
    else
    {
      zzge().attribute("auto", "_sid", null, paramLong);
    }
    zzgpzzanr.store(false);
    Bundle localBundle = new Bundle();
    if (zzgq().zzbi(zzgf().zzal())) {
      localBundle.putLong("_sid", paramLong / 1000L);
    }
    zzge().attribute("auto", "_s", paramLong, localBundle);
    zzgpzzant.getFolder(paramLong);
  }
  
  private final void zzli()
  {
    try
    {
      if (handler == null) {
        handler = new zzdx(Looper.getMainLooper());
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private final void zzll()
  {
    zzaf();
    setAlarm(false);
    zzgd().upload(zzbx().elapsedRealtime());
  }
  
  public final boolean setAlarm(boolean paramBoolean)
  {
    zzaf();
    zzcl();
    long l1 = zzbx().elapsedRealtime();
    zzgpzzant.getFolder(zzbx().currentTimeMillis());
    long l2 = l1 - zzasw;
    if ((!paramBoolean) && (l2 < 1000L))
    {
      zzgo().zzjl().append("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(l2));
      return false;
    }
    zzgpzzanu.getFolder(l2);
    zzgo().zzjl().append("Recording user engagement, ms", Long.valueOf(l2));
    Bundle localBundle = new Bundle();
    localBundle.putLong("_et", l2);
    zzdo.set(zzgh().zzla(), localBundle, true);
    zzge().logEvent("auto", "_e", localBundle);
    zzasw = l1;
    zzasy.cancel();
    zzasy.setAlarm(Math.max(0L, 3600000L - zzgpzzanu.readLong()));
    return true;
  }
  
  final void zzam(long paramLong)
  {
    zzaf();
    zzli();
    zzasx.cancel();
    zzasy.cancel();
    if (paramLong - zzgpzzanq.readLong() > zzgpzzant.readLong())
    {
      zzgpzzanr.store(true);
      zzgpzzanu.getFolder(0L);
    }
    if (zzgpzzanr.getBool())
    {
      zzao(paramLong);
      return;
    }
    zzasy.setAlarm(Math.max(0L, 3600000L - zzgpzzanu.readLong()));
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  final void zzlj()
  {
    zzasx.cancel();
    zzasy.cancel();
    zzasw = 0L;
  }
  
  protected final void zzlk()
  {
    zzaf();
    zzao(zzbx().currentTimeMillis());
  }
}
