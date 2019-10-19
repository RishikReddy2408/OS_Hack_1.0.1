package com.google.android.android.measurement;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import com.google.android.android.measurement.internal.zzbj;
import com.google.android.android.measurement.internal.zzbm;

public final class AppMeasurementInstallReferrerReceiver
  extends BroadcastReceiver
  implements zzbm
{
  private zzbj zzadq;
  
  public AppMeasurementInstallReferrerReceiver() {}
  
  public final BroadcastReceiver.PendingResult doGoAsync()
  {
    return goAsync();
  }
  
  public final void doStartService(Context paramContext, Intent paramIntent) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if (zzadq == null) {
      zzadq = new zzbj(this);
    }
    zzadq.onReceive(paramContext, paramIntent);
  }
}
