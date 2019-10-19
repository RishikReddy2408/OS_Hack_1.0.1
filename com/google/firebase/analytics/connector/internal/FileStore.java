package com.google.firebase.analytics.connector.internal;

import com.google.android.android.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.Set;

public final class FileStore
  implements EventHandler
{
  private AppMeasurement zzbsg;
  private AnalyticsConnector.AnalyticsConnectorListener zzbst;
  private PushService zzbsw;
  
  public FileStore(AppMeasurement paramAppMeasurement, AnalyticsConnector.AnalyticsConnectorListener paramAnalyticsConnectorListener)
  {
    zzbst = paramAnalyticsConnectorListener;
    zzbsg = paramAppMeasurement;
    zzbsw = new PushService(this);
    zzbsg.registerOnMeasurementEventListener(zzbsw);
  }
  
  public final void registerEventNames(Set paramSet) {}
  
  public final void unregisterEventNames() {}
  
  public final AnalyticsConnector.AnalyticsConnectorListener zztl()
  {
    return zzbst;
  }
}
