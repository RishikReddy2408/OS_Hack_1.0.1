package com.google.android.android.measurement.internal;

final class zzei
  implements Runnable
{
  zzei(zzef paramZzef, zzag paramZzag) {}
  
  public final void run()
  {
    zzef localZzef = zzasp;
    try
    {
      zzef.updateButton(zzasp, false);
      if (!zzasp.zzasg.isConnected())
      {
        zzasp.zzasg.zzgo().zzjk().zzbx("Connected to remote service");
        zzasp.zzasg.removeEventListener(zzasq);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
