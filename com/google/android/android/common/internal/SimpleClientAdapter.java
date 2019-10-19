package com.google.android.android.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;

public class SimpleClientAdapter<T extends IInterface>
  extends com.google.android.gms.common.internal.GmsClient<T>
{
  private final com.google.android.gms.common.api.Api.SimpleClient<T> zapf;
  
  public SimpleClientAdapter(Context paramContext, Looper paramLooper, int paramInt, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, ClientSettings paramClientSettings, com.google.android.android.common.aimsicd.Api.SimpleClient paramSimpleClient)
  {
    super(paramContext, paramLooper, paramInt, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
    zapf = paramSimpleClient;
  }
  
  protected IInterface createServiceInterface(IBinder paramIBinder)
  {
    return zapf.createServiceInterface(paramIBinder);
  }
  
  public com.google.android.android.common.aimsicd.Api.SimpleClient getClient()
  {
    return zapf;
  }
  
  public int getMinApkVersion()
  {
    return super.getMinApkVersion();
  }
  
  protected String getServiceDescriptor()
  {
    return zapf.getServiceDescriptor();
  }
  
  protected String getStartServiceAction()
  {
    return zapf.getStartServiceAction();
  }
  
  protected void onSetConnectState(int paramInt, IInterface paramIInterface)
  {
    zapf.setState(paramInt, paramIInterface);
  }
}
