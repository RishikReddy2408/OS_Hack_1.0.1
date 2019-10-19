package com.google.android.android.measurement.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.android.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.android.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.measurement.internal.zzag;

public final class zzao
  extends com.google.android.gms.common.internal.BaseGmsClient<zzag>
{
  public zzao(Context paramContext, Looper paramLooper, BaseGmsClient.BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 93, paramBaseConnectionCallbacks, paramBaseOnConnectionFailedListener, null);
  }
  
  public final int getMinApkVersion()
  {
    return 12451000;
  }
  
  protected final String getServiceDescriptor()
  {
    return "com.google.android.gms.measurement.internal.IMeasurementService";
  }
  
  protected final String getStartServiceAction()
  {
    return "com.google.android.gms.measurement.START";
  }
}
