package com.google.android.android.measurement.internal;

final class zzdj
  implements Runnable
{
  zzdj(zzcs paramZzcs, boolean paramBoolean) {}
  
  public final void run()
  {
    boolean bool1 = zzarc.zzadj.isEnabled();
    boolean bool2 = zzarc.zzadj.zzko();
    zzarc.zzadj.checkState(zzaes);
    if (bool2 == zzaes) {
      zzarc.zzadj.zzgo().zzjl().append("Default data collection state already set to", Boolean.valueOf(zzaes));
    }
    if ((zzarc.zzadj.isEnabled() == bool1) || (zzarc.zzadj.isEnabled() != zzarc.zzadj.zzko())) {
      zzarc.zzadj.zzgo().zzji().append("Default data collection is different than actual status", Boolean.valueOf(zzaes), Boolean.valueOf(bool1));
    }
    zzcs.Zip(zzarc);
  }
}
