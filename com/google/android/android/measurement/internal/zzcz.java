package com.google.android.android.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcz
  implements Runnable
{
  zzcz(zzcs paramZzcs, long paramLong) {}
  
  public final void run()
  {
    zzcs localZzcs = zzarc;
    long l = zzari;
    localZzcs.zzaf();
    localZzcs.zzgb();
    localZzcs.zzcl();
    localZzcs.zzgo().zzjk().zzbx("Resetting analytics data (FE)");
    localZzcs.zzgj().zzlj();
    if (localZzcs.zzgq().zzbe(localZzcs.zzgf().zzal())) {
      zzgpzzanj.getFolder(l);
    }
    boolean bool = zzadj.isEnabled();
    if (!localZzcs.zzgq().zzhu()) {
      localZzcs.zzgp().startService(bool ^ true);
    }
    localZzcs.zzgg().resetAnalyticsData();
    zzara = (bool ^ true);
    if (zzarc.zzgq().isTrue(zzaf.zzalk)) {
      zzarc.zzgg().terminate(new AtomicReference());
    }
  }
}
