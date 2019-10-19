package com.google.android.android.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.util.ArrayMap;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.measurement.internal.FileTransfer;
import com.google.android.android.measurement.internal.zzbt;
import com.google.android.android.measurement.internal.zzcs;
import com.google.android.android.measurement.internal.zzfh;
import com.google.android.android.measurement.internal.zzfk;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement
{
  @KeepForSdk
  public static final String CRASH_ORIGIN = "crash";
  @KeepForSdk
  public static final String FCM_ORIGIN = "fcm";
  @KeepForSdk
  public static final String FIAM_ORIGIN = "fiam";
  private final zzbt zzadj;
  
  public AppMeasurement(zzbt paramZzbt)
  {
    Preconditions.checkNotNull(paramZzbt);
    zzadj = paramZzbt;
  }
  
  public static AppMeasurement getInstance(Context paramContext)
  {
    return zzbt.register(paramContext, null).zzki();
  }
  
  public void beginAdUnitExposure(String paramString)
  {
    zzadj.zzgd().beginAdUnitExposure(paramString, zzadj.zzbx().elapsedRealtime());
  }
  
  public void clearConditionalUserProperty(String paramString1, String paramString2, Bundle paramBundle)
  {
    zzadj.zzge().clearConditionalUserProperty(paramString1, paramString2, paramBundle);
  }
  
  protected void clearConditionalUserPropertyAs(String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    zzadj.zzge().clearConditionalUserPropertyAs(paramString1, paramString2, paramString3, paramBundle);
  }
  
  public final void createThumbnail(boolean paramBoolean)
  {
    zzadj.zzge().e(paramBoolean);
  }
  
  public void endAdUnitExposure(String paramString)
  {
    zzadj.zzgd().endAdUnitExposure(paramString, zzadj.zzbx().elapsedRealtime());
  }
  
  public long generateEventId()
  {
    return zzadj.zzgm().zzmc();
  }
  
  public String getAppInstanceId()
  {
    return zzadj.zzge().zzfx();
  }
  
  public Boolean getBoolean()
  {
    return zzadj.zzge().zzkt();
  }
  
  public List getConditionalUserProperties(String paramString1, String paramString2)
  {
    return zzadj.zzge().getConditionalUserProperties(paramString1, paramString2);
  }
  
  protected List getConditionalUserPropertiesAs(String paramString1, String paramString2, String paramString3)
  {
    return zzadj.zzge().getConditionalUserPropertiesAs(paramString1, paramString2, paramString3);
  }
  
  public String getCurrentScreenClass()
  {
    return zzadj.zzge().getCurrentScreenClass();
  }
  
  public String getCurrentScreenName()
  {
    return zzadj.zzge().getCurrentScreenName();
  }
  
  public Double getDouble()
  {
    return zzadj.zzge().zzkx();
  }
  
  public String getGmpAppId()
  {
    return zzadj.zzge().getGmpAppId();
  }
  
  public Integer getInteger()
  {
    return zzadj.zzge().zzkw();
  }
  
  public Long getLong()
  {
    return zzadj.zzge().zzkv();
  }
  
  public int getMaxUserProperties(String paramString)
  {
    zzadj.zzge();
    Preconditions.checkNotEmpty(paramString);
    return 25;
  }
  
  public String getString()
  {
    return zzadj.zzge().zzku();
  }
  
  protected Map getUserProperties(String paramString1, String paramString2, boolean paramBoolean)
  {
    return zzadj.zzge().getUserProperties(paramString1, paramString2, paramBoolean);
  }
  
  public Map getUserProperties(boolean paramBoolean)
  {
    Object localObject = zzadj.zzge().getHeaders(paramBoolean);
    ArrayMap localArrayMap = new ArrayMap(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      zzfh localZzfh = (zzfh)((Iterator)localObject).next();
      localArrayMap.put(name, localZzfh.getValue());
    }
    return localArrayMap;
  }
  
  protected Map getUserPropertiesAs(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    return zzadj.zzge().getUserPropertiesAs(paramString1, paramString2, paramString3, paramBoolean);
  }
  
  public final void logEvent(String paramString, Bundle paramBundle)
  {
    zzadj.zzge().e("app", paramString, paramBundle, true);
  }
  
  public void logEventInternal(String paramString1, String paramString2, Bundle paramBundle)
  {
    zzadj.zzge().logEvent(paramString1, paramString2, paramBundle);
  }
  
  public void logEventInternalNoInterceptor(String paramString1, String paramString2, Bundle paramBundle, long paramLong)
  {
    zzadj.zzge().logEvent(paramString1, paramString2, paramBundle, true, false, paramLong);
  }
  
  public void registerOnMeasurementEventListener(OnEventListener paramOnEventListener)
  {
    zzadj.zzge().registerOnMeasurementEventListener(paramOnEventListener);
  }
  
  public void setConditionalUserProperty(ConditionalUserProperty paramConditionalUserProperty)
  {
    zzadj.zzge().setConditionalUserProperty(paramConditionalUserProperty);
  }
  
  protected void setConditionalUserPropertyAs(ConditionalUserProperty paramConditionalUserProperty)
  {
    zzadj.zzge().setConditionalUserPropertyAs(paramConditionalUserProperty);
  }
  
  public void setEventInterceptor(EventInterceptor paramEventInterceptor)
  {
    zzadj.zzge().setEventInterceptor(paramEventInterceptor);
  }
  
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    zzadj.zzge().setMeasurementEnabled(paramBoolean);
  }
  
  public final void setMinimumSessionDuration(long paramLong)
  {
    zzadj.zzge().setMinimumSessionDuration(paramLong);
  }
  
  public final void setSessionTimeoutDuration(long paramLong)
  {
    zzadj.zzge().setSessionTimeoutDuration(paramLong);
  }
  
  public final void setUserProperty(String paramString1, String paramString2)
  {
    zzadj.zzge().open("app", paramString1, paramString2, false);
  }
  
  public void setUserPropertyInternal(String paramString1, String paramString2, Object paramObject)
  {
    Preconditions.checkNotEmpty(paramString1);
    zzadj.zzge().open(paramString1, paramString2, paramObject, true);
  }
  
  public void unregisterOnMeasurementEventListener(OnEventListener paramOnEventListener)
  {
    zzadj.zzge().unregisterOnMeasurementEventListener(paramOnEventListener);
  }
  
  @KeepForSdk
  public class ConditionalUserProperty
  {
    @Keep
    @KeepForSdk
    public boolean mActive;
    @Keep
    @KeepForSdk
    public String mAppId;
    @Keep
    @KeepForSdk
    public long mCreationTimestamp;
    @Keep
    public String mExpiredEventName;
    @Keep
    public Bundle mExpiredEventParams;
    @Keep
    @KeepForSdk
    public String mName;
    @Keep
    @KeepForSdk
    public String mOrigin;
    @Keep
    @KeepForSdk
    public long mTimeToLive;
    @Keep
    public String mTimedOutEventName;
    @Keep
    public Bundle mTimedOutEventParams;
    @Keep
    @KeepForSdk
    public String mTriggerEventName;
    @Keep
    @KeepForSdk
    public long mTriggerTimeout;
    @Keep
    public String mTriggeredEventName;
    @Keep
    public Bundle mTriggeredEventParams;
    @Keep
    @KeepForSdk
    public long mTriggeredTimestamp;
    @Keep
    @KeepForSdk
    public Object mValue;
    
    public ConditionalUserProperty() {}
    
    public ConditionalUserProperty()
    {
      Preconditions.checkNotNull(this$1);
      mAppId = mAppId;
      mOrigin = mOrigin;
      mCreationTimestamp = mCreationTimestamp;
      mName = mName;
      if (mValue != null)
      {
        mValue = zzfk.put(mValue);
        if (mValue == null) {
          mValue = mValue;
        }
      }
      mActive = mActive;
      mTriggerEventName = mTriggerEventName;
      mTriggerTimeout = mTriggerTimeout;
      mTimedOutEventName = mTimedOutEventName;
      if (mTimedOutEventParams != null) {
        mTimedOutEventParams = new Bundle(mTimedOutEventParams);
      }
      mTriggeredEventName = mTriggeredEventName;
      if (mTriggeredEventParams != null) {
        mTriggeredEventParams = new Bundle(mTriggeredEventParams);
      }
      mTriggeredTimestamp = mTriggeredTimestamp;
      mTimeToLive = mTimeToLive;
      mExpiredEventName = mExpiredEventName;
      if (mExpiredEventParams != null) {
        mExpiredEventParams = new Bundle(mExpiredEventParams);
      }
    }
  }
  
  @KeepForSdk
  public final class Event
  {
    @KeepForSdk
    public static final String AD_REWARD = "_ar";
    @KeepForSdk
    public static final String APP_EXCEPTION = "_ae";
    public static final String[] zzadk = { "app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "ad_reward", "screen_view", "ga_extra_parameter" };
    public static final String[] zzadl = { "_cd", "_ae", "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "_ar", "_vs", "_ep" };
    
    private Event() {}
    
    public static String zzak(String paramString)
    {
      return zzfk.verify(paramString, zzadl, zzadk);
    }
    
    public static String zzal(String paramString)
    {
      return zzfk.verify(paramString, zzadk, zzadl);
    }
  }
  
  @KeepForSdk
  public abstract interface EventInterceptor
  {
    public abstract void interceptEvent(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  @KeepForSdk
  public abstract interface OnEventListener
  {
    public abstract void onEvent(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  @KeepForSdk
  public final class Param
  {
    @KeepForSdk
    public static final String FATAL = "fatal";
    @KeepForSdk
    public static final String TIMESTAMP = "timestamp";
    @KeepForSdk
    public static final String TYPE = "type";
    public static final String[] zzadm = { "firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "ga_event_id", "ga_extra_params_ct", "ga_group_name", "ga_list_length", "ga_index", "ga_event_name", "campaign_info_source", "deferred_analytics_collection", "session_number", "session_id" };
    public static final String[] zzadn = { "_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en", "_cis", "_dac", "_sno", "_sid" };
    
    private Param() {}
    
    public static String zzal(String paramString)
    {
      return zzfk.verify(paramString, zzadm, zzadn);
    }
  }
  
  @KeepForSdk
  public final class UserProperty
  {
    @KeepForSdk
    public static final String FIREBASE_LAST_NOTIFICATION = "_ln";
    public static final String[] zzado = { "firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement", "google_allow_ad_personalization_signals", "session_number", "session_id" };
    public static final String[] zzadp = { "_ln", "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte", "_ap", "_sno", "_sid" };
    
    private UserProperty() {}
    
    public static String zzal(String paramString)
    {
      return zzfk.verify(paramString, zzado, zzadp);
    }
  }
}
