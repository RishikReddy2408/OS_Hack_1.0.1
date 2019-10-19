package com.google.android.android.measurement;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.android.measurement.internal.zzbj;
import com.google.android.android.measurement.internal.zzbm;

public final class AppMeasurementReceiver
  extends WakefulBroadcastReceiver
  implements zzbm
{
  private zzbj zzadq;
  
  public AppMeasurementReceiver() {}
  
  public final BroadcastReceiver.PendingResult doGoAsync()
  {
    return goAsync();
  }
  
  public final void doStartService(Context paramContext, Intent paramIntent)
  {
    WakefulBroadcastReceiver.startWakefulService(paramContext, paramIntent);
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if (zzadq == null) {
      zzadq = new zzbj(this);
    }
    zzadq.onReceive(paramContext, paramIntent);
  }
}
