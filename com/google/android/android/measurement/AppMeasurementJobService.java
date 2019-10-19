package com.google.android.android.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import com.google.android.android.measurement.internal.zzep;

@TargetApi(24)
public final class AppMeasurementJobService
  extends JobService
  implements zzep
{
  private com.google.android.gms.measurement.internal.zzel<com.google.android.gms.measurement.AppMeasurementJobService> zzadr;
  
  public AppMeasurementJobService() {}
  
  private final com.google.android.android.measurement.internal.zzel zzfu()
  {
    if (zzadr == null) {
      zzadr = new com.google.android.android.measurement.internal.zzel((Context)this);
    }
    return zzadr;
  }
  
  public final boolean callServiceStopSelfResult(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public final void cleanup(Intent paramIntent) {}
  
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
  
  public final boolean onStartJob(JobParameters paramJobParameters)
  {
    return zzfu().onStartJob(paramJobParameters);
  }
  
  public final boolean onStopJob(JobParameters paramJobParameters)
  {
    return false;
  }
  
  public final boolean onUnbind(Intent paramIntent)
  {
    return zzfu().onUnbind(paramIntent);
  }
  
  public final void sort(JobParameters paramJobParameters, boolean paramBoolean)
  {
    jobFinished(paramJobParameters, false);
  }
}
