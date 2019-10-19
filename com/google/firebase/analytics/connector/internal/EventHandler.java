package com.google.firebase.analytics.connector.internal;

import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.Set;

public abstract interface EventHandler
{
  public abstract void registerEventNames(Set paramSet);
  
  public abstract void unregisterEventNames();
  
  public abstract AnalyticsConnector.AnalyticsConnectorListener zztl();
}
