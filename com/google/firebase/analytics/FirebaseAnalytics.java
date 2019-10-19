package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.measurement.AppMeasurement;
import com.google.android.android.measurement.internal.MultiMap;
import com.google.android.android.measurement.internal.zzap;
import com.google.android.android.measurement.internal.zzar;
import com.google.android.android.measurement.internal.zzbo;
import com.google.android.android.measurement.internal.zzbt;
import com.google.android.android.measurement.internal.zzcs;
import com.google.android.android.measurement.internal.zzdo;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.Tasks;
import com.google.firebase.package_8.FirebaseInstanceId;
import java.util.concurrent.Executor;

public final class FirebaseAnalytics
{
  private static volatile FirebaseAnalytics zzbsa;
  private final zzbt zzadj;
  private String zzbsb;
  private long zzbsc;
  private final Object zzbsd;
  
  private FirebaseAnalytics(zzbt paramZzbt)
  {
    Preconditions.checkNotNull(paramZzbt);
    zzadj = paramZzbt;
    zzbsd = new Object();
  }
  
  public static FirebaseAnalytics getInstance(Context paramContext)
  {
    if (zzbsa == null) {
      try
      {
        if (zzbsa == null) {
          zzbsa = new FirebaseAnalytics(zzbt.register(paramContext, null));
        }
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
    }
    return zzbsa;
  }
  
  private final void zzcm(String paramString)
  {
    Object localObject = zzbsd;
    try
    {
      zzbsb = paramString;
      zzbsc = zzadj.zzbx().elapsedRealtime();
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  private final String zzfx()
  {
    Object localObject = zzbsd;
    try
    {
      if (Math.abs(zzadj.zzbx().elapsedRealtime() - zzbsc) < 1000L)
      {
        String str = zzbsb;
        return str;
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Task getAppInstanceId()
  {
    try
    {
      Object localObject = zzfx();
      if (localObject != null)
      {
        localObject = Tasks.forResult(localObject);
        return localObject;
      }
      localObject = zzadj;
      localObject = ((zzbt)localObject).zzgn().zzkc();
      localObject = Tasks.call((Executor)localObject, new DatabaseHelper.1(this));
      return localObject;
    }
    catch (Exception localException)
    {
      zzadj.zzgo().zzjg().zzbx("Failed to schedule task for getAppInstanceId");
      return Tasks.forException(localException);
    }
  }
  
  public final String getFirebaseInstanceId()
  {
    return FirebaseInstanceId.getInstance().getId();
  }
  
  public final void logEvent(String paramString, Bundle paramBundle)
  {
    zzadj.zzki().logEvent(paramString, paramBundle);
  }
  
  public final void resetAnalyticsData()
  {
    zzcm(null);
    zzadj.zzge().resetAnalyticsData(zzadj.zzbx().currentTimeMillis());
  }
  
  public final void setAnalyticsCollectionEnabled(boolean paramBoolean)
  {
    zzadj.zzki().setMeasurementEnabled(paramBoolean);
  }
  
  public final void setCurrentScreen(Activity paramActivity, String paramString1, String paramString2)
  {
    if (!MultiMap.isMainThread())
    {
      zzadj.zzgo().zzjg().zzbx("setCurrentScreen must be called from the main thread");
      return;
    }
    zzadj.zzgh().setCurrentScreen(paramActivity, paramString1, paramString2);
  }
  
  public final void setMinimumSessionDuration(long paramLong)
  {
    zzadj.zzki().setMinimumSessionDuration(paramLong);
  }
  
  public final void setSessionTimeoutDuration(long paramLong)
  {
    zzadj.zzki().setSessionTimeoutDuration(paramLong);
  }
  
  public final void setUserId(String paramString)
  {
    zzadj.zzki().setUserPropertyInternal("app", "_id", paramString);
  }
  
  public final void setUserProperty(String paramString1, String paramString2)
  {
    zzadj.zzki().setUserProperty(paramString1, paramString2);
  }
  
  public static class Event
  {
    public static final String ADD_PAYMENT_INFO = "add_payment_info";
    public static final String ADD_TO_CART = "add_to_cart";
    public static final String ADD_TO_WISHLIST = "add_to_wishlist";
    public static final String APP_OPEN = "app_open";
    public static final String BEGIN_CHECKOUT = "begin_checkout";
    public static final String CAMPAIGN_DETAILS = "campaign_details";
    public static final String CHECKOUT_PROGRESS = "checkout_progress";
    public static final String EARN_VIRTUAL_CURRENCY = "earn_virtual_currency";
    public static final String ECOMMERCE_PURCHASE = "ecommerce_purchase";
    public static final String GENERATE_LEAD = "generate_lead";
    public static final String JOIN_GROUP = "join_group";
    public static final String LEVEL_END = "level_end";
    public static final String LEVEL_START = "level_start";
    public static final String LEVEL_UP = "level_up";
    public static final String LOGIN = "login";
    public static final String POST_SCORE = "post_score";
    public static final String PRESENT_OFFER = "present_offer";
    public static final String PURCHASE_REFUND = "purchase_refund";
    public static final String REMOVE_FROM_CART = "remove_from_cart";
    public static final String SEARCH = "search";
    public static final String SELECT_CONTENT = "select_content";
    public static final String SET_CHECKOUT_OPTION = "set_checkout_option";
    public static final String SHARE = "share";
    public static final String SIGN_UP = "sign_up";
    public static final String SPEND_VIRTUAL_CURRENCY = "spend_virtual_currency";
    public static final String TUTORIAL_BEGIN = "tutorial_begin";
    public static final String TUTORIAL_COMPLETE = "tutorial_complete";
    public static final String UNLOCK_ACHIEVEMENT = "unlock_achievement";
    public static final String VIEW_ITEM = "view_item";
    public static final String VIEW_ITEM_LIST = "view_item_list";
    public static final String VIEW_SEARCH_RESULTS = "view_search_results";
    
    protected Event() {}
  }
  
  public static class Param
  {
    public static final String ACHIEVEMENT_ID = "achievement_id";
    public static final String ACLID = "aclid";
    public static final String ACTION_UPDATE_ALL = "tax";
    public static final String AFFILIATION = "affiliation";
    public static final String CAMPAIGN = "campaign";
    public static final String CHARACTER = "character";
    public static final String CHECKOUT_OPTION = "checkout_option";
    public static final String CHECKOUT_STEP = "checkout_step";
    public static final String CONTENT = "content";
    public static final String CONTENT_TYPE = "content_type";
    public static final String COUPON = "coupon";
    public static final String CREATIVE_NAME = "creative_name";
    public static final String CREATIVE_SLOT = "creative_slot";
    public static final String CURRENCY = "currency";
    public static final String DESTINATION = "destination";
    public static final String END_DATE = "end_date";
    public static final String FLIGHT_NUMBER = "flight_number";
    public static final String GROUP_ID = "group_id";
    public static final String INDEX = "index";
    public static final String ITEM_BRAND = "item_brand";
    public static final String ITEM_CATEGORY = "item_category";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_LIST = "item_list";
    public static final String ITEM_LOCATION_ID = "item_location_id";
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_VARIANT = "item_variant";
    public static final String LEVEL = "level";
    public static final String LEVEL_NAME = "level_name";
    public static final String LOCATION = "location";
    public static final String MEDIUM = "medium";
    public static final String METHOD = "method";
    public static final String NUMBER_OF_NIGHTS = "number_of_nights";
    public static final String NUMBER_OF_PASSENGERS = "number_of_passengers";
    public static final String NUMBER_OF_ROOMS = "number_of_rooms";
    public static final String ORIGIN = "origin";
    public static final String PAGE_KEY = "cp1";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";
    public static final String SCORE = "score";
    public static final String SEARCH_TERM = "search_term";
    public static final String SHIPPING = "shipping";
    @Deprecated
    public static final String SIGN_UP_METHOD = "sign_up_method";
    public static final String SOURCE = "source";
    public static final String START_DATE = "start_date";
    public static final String SUCCESS = "success";
    public static final String TERM = "term";
    public static final String TRANSACTION_ID = "transaction_id";
    public static final String TRAVEL_CLASS = "travel_class";
    public static final String VALUE = "value";
    public static final String VIRTUAL_CURRENCY_NAME = "virtual_currency_name";
    
    protected Param() {}
  }
  
  public static class UserProperty
  {
    public static final String SIGN_UP_METHOD = "sign_up_method";
    
    protected UserProperty() {}
  }
}
