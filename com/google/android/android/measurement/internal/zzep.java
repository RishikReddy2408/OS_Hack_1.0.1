package com.google.android.android.measurement.internal;

import android.app.job.JobParameters;
import android.content.Intent;

public abstract interface zzep
{
  public abstract boolean callServiceStopSelfResult(int paramInt);
  
  public abstract void cleanup(Intent paramIntent);
  
  public abstract void sort(JobParameters paramJobParameters, boolean paramBoolean);
}
