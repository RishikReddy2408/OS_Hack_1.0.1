package com.google.android.android.measurement.internal;

final class zzcg
  implements Runnable
{
  zzcg(zzbv paramZzbv, zzad paramZzad, ApplicationInfo paramApplicationInfo) {}
  
  public final void run()
  {
    zzad localZzad = zzaqo.toJSONObject(zzaqr, zzaqn);
    zzbv.get0(zzaqo).zzly();
    zzbv.get0(zzaqo).getInstalledApps(localZzad, zzaqn);
  }
}
