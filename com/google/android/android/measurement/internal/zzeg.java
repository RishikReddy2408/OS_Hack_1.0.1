package com.google.android.android.measurement.internal;

final class zzeg
  implements Runnable
{
  zzeg(zzef paramZzef, zzag paramZzag) {}
  
  public final void run()
  {
    zzef localZzef = zzasp;
    try
    {
      zzef.updateButton(zzasp, false);
      if (!zzasp.zzasg.isConnected())
      {
        zzasp.zzasg.zzgo().zzjl().zzbx("Connected to service");
        zzasp.zzasg.removeEventListener(zzaso);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
