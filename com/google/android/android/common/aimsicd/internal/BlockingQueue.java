package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.Sample;

public abstract interface BlockingQueue
  extends GoogleApiClient.ConnectionCallbacks
{
  public abstract void post(ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean);
}
