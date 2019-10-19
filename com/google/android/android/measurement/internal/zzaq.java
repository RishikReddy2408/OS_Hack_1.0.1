package com.google.android.android.measurement.internal;

final class zzaq
  implements Runnable
{
  zzaq(zzap paramZzap, int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {}
  
  public final void run()
  {
    zzba localZzba = zzamm.zzadj.zzgp();
    if (!localZzba.isInitialized())
    {
      zzamm.put(6, "Persisted config not initialized. Not logging error/warn");
      return;
    }
    if (zzap.getLinkTarget(zzamm) == 0) {
      if (zzamm.zzgq().zzdw())
      {
        localObject1 = zzamm;
        zzamm.zzgr();
        zzap.key((zzap)localObject1, 'C');
      }
      else
      {
        localObject1 = zzamm;
        zzamm.zzgr();
        zzap.key((zzap)localObject1, 'c');
      }
    }
    if (zzap.getDialogId(zzamm) < 0L) {
      zzap.writeLong(zzamm, zzamm.zzgq().zzhc());
    }
    char c1 = "01VDIWEA?".charAt(zzamh);
    char c2 = zzap.getLinkTarget(zzamm);
    long l = zzap.getDialogId(zzamm);
    Object localObject1 = zzap.getDisplayTitle(true, zzami, zzamj, zzamk, zzaml);
    Object localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 24);
    ((StringBuilder)localObject2).append("2");
    ((StringBuilder)localObject2).append(c1);
    ((StringBuilder)localObject2).append(c2);
    ((StringBuilder)localObject2).append(l);
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append((String)localObject1);
    localObject2 = ((StringBuilder)localObject2).toString();
    localObject1 = localObject2;
    if (((String)localObject2).length() > 1024) {
      localObject1 = zzami.substring(0, 1024);
    }
    zzand.store((String)localObject1, 1L);
  }
}
