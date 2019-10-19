package com.google.android.android.measurement.internal;

import android.os.Bundle;

final class zzdp
  implements Runnable
{
  zzdp(zzdo paramZzdo, boolean paramBoolean, zzdn paramZzdn1, zzdn paramZzdn2) {}
  
  public final void run()
  {
    if ((zzaru) && (zzarx.zzaro != null)) {
      zzdo.access$getSetAlarm(zzarx, zzarx.zzaro);
    }
    int i;
    if ((zzarv != null) && (zzarv.zzarm == zzarw.zzarm) && (zzfk.verifySignature(zzarv.zzarl, zzarw.zzarl)) && (zzfk.verifySignature(zzarv.zzuw, zzarw.zzuw))) {
      i = 0;
    } else {
      i = 1;
    }
    if (i != 0)
    {
      Bundle localBundle = new Bundle();
      zzdo.set(zzarw, localBundle, true);
      if (zzarv != null)
      {
        if (zzarv.zzuw != null) {
          localBundle.putString("_pn", zzarv.zzuw);
        }
        localBundle.putString("_pc", zzarv.zzarl);
        localBundle.putLong("_pi", zzarv.zzarm);
      }
      zzarx.zzge().saveToFile("auto", "_vs", localBundle);
    }
    zzarx.zzaro = zzarw;
    zzarx.zzgg().Open(zzarw);
  }
}
