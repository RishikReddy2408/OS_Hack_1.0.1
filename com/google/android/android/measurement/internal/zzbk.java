package com.google.android.android.measurement.internal;

import android.content.Context;

final class zzbk
  implements Runnable
{
  zzbk(zzbj paramZzbj, zzbt paramZzbt, zzap paramZzap) {}
  
  public final void run()
  {
    if (zzaoj.zzkg() == null)
    {
      zzaok.zzjd().zzbx("Install Referrer Reporter is null");
      return;
    }
    zzbg localZzbg = zzaoj.zzkg();
    zzadj.zzgb();
    localZzbg.zzcd(zzadj.getContext().getPackageName());
  }
}
