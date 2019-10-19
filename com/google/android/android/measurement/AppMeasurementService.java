package com.google.android.android.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.android.measurement.internal.zzep;

public final class AppMeasurementService
  extends Service
  implements zzep
{
  private com.google.android.gms.measurement.internal.zzel<com.google.android.gms.measurement.AppMeasurementService> zzadr;
  
  public AppMeasurementService() {}
  
  private final com.google.android.android.measurement.internal.zzel zzfu()
  {
    if (zzadr == null) {
      zzadr = new com.google.android.android.measurement.internal.zzel(this);
    }
    return zzadr;
  }
  
  public final boolean callServiceStopSelfResult(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
  
  public final void cleanup(Intent paramIntent)
  {
    WakefulBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    return zzfu().onBind(paramIntent);
  }
  
  public final void onCreate()
  {
    super.onCreate();
    zzfu().onCreate();
  }
  
  public final void onDestroy()
  {
    zzfu().onDestroy();
    super.onDestroy();
  }
  
  public final void onRebind(Intent paramIntent)
  {
    zzfu().onRebind(paramIntent);
  }
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return zzfu().onStartCommand(paramIntent, paramInt1, paramInt2);
  }
  
  public final boolean onUnbind(Intent paramIntent)
  {
    return zzfu().onUnbind(paramIntent);
  }
  
  public final void sort(JobParameters paramJobParameters, boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
}
