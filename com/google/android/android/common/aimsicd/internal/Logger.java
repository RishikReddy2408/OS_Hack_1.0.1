package com.google.android.android.common.aimsicd.internal;

import android.os.Bundle;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.api.Api;

public final class Logger
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  public final Api<?> mApi;
  private final boolean zaeb;
  private BlockingQueue zaec;
  
  public Logger(Sample paramSample, boolean paramBoolean)
  {
    mApi = paramSample;
    zaeb = paramBoolean;
  }
  
  private final void clear()
  {
    Preconditions.checkNotNull(zaec, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
  }
  
  public final void onConnected(Bundle paramBundle)
  {
    clear();
    zaec.onConnected(paramBundle);
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    clear();
    zaec.post(paramConnectionResult, mApi, zaeb);
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    clear();
    zaec.onConnectionSuspended(paramInt);
  }
  
  public final void v(BlockingQueue paramBlockingQueue)
  {
    zaec = paramBlockingQueue;
  }
}
