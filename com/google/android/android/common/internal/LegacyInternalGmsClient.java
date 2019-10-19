package com.google.android.android.common.internal;

import android.content.Context;
import android.os.IInterface;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;

@Deprecated
public abstract class LegacyInternalGmsClient<T extends IInterface>
  extends com.google.android.gms.common.internal.GmsClient<T>
{
  private final GmsClientEventManager zagr = new GmsClientEventManager(paramContext.getMainLooper(), this);
  
  public LegacyInternalGmsClient(Context paramContext, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramContext.getMainLooper(), paramInt, paramClientSettings);
    zagr.registerConnectionCallbacks(paramConnectionCallbacks);
    zagr.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void checkAvailabilityAndConnect()
  {
    zagr.enableCallbacks();
    super.checkAvailabilityAndConnect();
  }
  
  public void disconnect()
  {
    zagr.disableCallbacks();
    super.disconnect();
  }
  
  public int getMinApkVersion()
  {
    return super.getMinApkVersion();
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return zagr.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return zagr.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void onConnectedLocked(IInterface paramIInterface)
  {
    super.onConnectedLocked(paramIInterface);
    zagr.onConnectionSuccess(getConnectionHint());
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    super.onConnectionFailed(paramConnectionResult);
    zagr.onConnectionFailure(paramConnectionResult);
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    super.onConnectionSuspended(paramInt);
    zagr.onUnintentionalDisconnection(paramInt);
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zagr.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zagr.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zagr.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zagr.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
}
