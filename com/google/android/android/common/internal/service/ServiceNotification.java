package com.google.android.android.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.gms.common.internal.service.zal;

public final class ServiceNotification
  extends com.google.android.gms.common.internal.GmsClient<zal>
{
  public ServiceNotification(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 39, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected final String getServiceDescriptor()
  {
    return "com.google.android.gms.common.internal.service.ICommonService";
  }
  
  public final String getStartServiceAction()
  {
    return "com.google.android.gms.common.service.START";
  }
}
