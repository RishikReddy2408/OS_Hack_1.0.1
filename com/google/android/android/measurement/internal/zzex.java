package com.google.android.android.measurement.internal;

final class zzex
  extends Alarm
{
  zzex(zzew paramZzew, zzcq paramZzcq, zzfa paramZzfa)
  {
    super(paramZzcq);
  }
  
  public final void readOne()
  {
    zzatb.cancel();
    zzatb.zzgo().zzjl().zzbx("Starting upload from DelayedRunnable");
    zzasv.zzlt();
  }
}
