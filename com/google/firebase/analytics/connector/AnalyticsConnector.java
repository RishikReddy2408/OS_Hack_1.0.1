package com.google.firebase.analytics.connector;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface AnalyticsConnector
{
  public abstract void clearConditionalUserProperty(String paramString1, String paramString2, Bundle paramBundle);
  
  public abstract List getConditionalUserProperties(String paramString1, String paramString2);
  
  public abstract int getMaxUserProperties(String paramString);
  
  public abstract Map getUserProperties(boolean paramBoolean);
  
  public abstract void logEvent(String paramString1, String paramString2, Bundle paramBundle);
  
  public abstract AnalyticsConnectorHandle registerAnalyticsConnectorListener(String paramString, AnalyticsConnectorListener paramAnalyticsConnectorListener);
  
  public abstract void setConditionalUserProperty(ConditionalUserProperty paramConditionalUserProperty);
  
  public abstract void setUserProperty(String paramString1, String paramString2, Object paramObject);
  
  @KeepForSdk
  public static abstract interface AnalyticsConnectorHandle
  {
    public abstract void registerEventNames(Set paramSet);
    
    public abstract void unregister();
    
    public abstract void unregisterEventNames();
  }
  
  @KeepForSdk
  public static abstract interface AnalyticsConnectorListener
  {
    public abstract void onMessageTriggered(int paramInt, Bundle paramBundle);
  }
  
  @KeepForSdk
  public static class ConditionalUserProperty
  {
    @KeepForSdk
    public boolean active;
    @KeepForSdk
    public long creationTimestamp;
    @KeepForSdk
    public String expiredEventName;
    @KeepForSdk
    public Bundle expiredEventParams;
    @KeepForSdk
    public String name;
    @KeepForSdk
    public String origin;
    @KeepForSdk
    public long timeToLive;
    @KeepForSdk
    public String timedOutEventName;
    @KeepForSdk
    public Bundle timedOutEventParams;
    @KeepForSdk
    public String triggerEventName;
    @KeepForSdk
    public long triggerTimeout;
    @KeepForSdk
    public String triggeredEventName;
    @KeepForSdk
    public Bundle triggeredEventParams;
    @KeepForSdk
    public long triggeredTimestamp;
    @KeepForSdk
    public Object value;
    
    public ConditionalUserProperty() {}
  }
}
