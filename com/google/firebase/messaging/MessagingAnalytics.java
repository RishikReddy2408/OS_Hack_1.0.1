package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;

@KeepForSdk
public class MessagingAnalytics
{
  public MessagingAnalytics() {}
  
  private static void execute(String paramString, Intent paramIntent)
  {
    Bundle localBundle = new Bundle();
    Object localObject = paramIntent.getStringExtra("google.c.a.c_id");
    if (localObject != null) {
      localBundle.putString("_nmid", (String)localObject);
    }
    localObject = paramIntent.getStringExtra("google.c.a.c_l");
    if (localObject != null) {
      localBundle.putString("_nmn", (String)localObject);
    }
    localObject = paramIntent.getStringExtra("google.c.a.m_l");
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localBundle.putString("label", (String)localObject);
    }
    localObject = paramIntent.getStringExtra("google.c.a.m_c");
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localBundle.putString("message_channel", (String)localObject);
    }
    String str = paramIntent.getStringExtra("from");
    localObject = str;
    if ((str == null) || (!str.startsWith("/topics/"))) {
      localObject = null;
    }
    if (localObject != null) {
      localBundle.putString("_nt", (String)localObject);
    }
    if (paramIntent.hasExtra("google.c.a.ts")) {
      try
      {
        localBundle.putInt("_nmt", Integer.parseInt(paramIntent.getStringExtra("google.c.a.ts")));
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", localNumberFormatException);
      }
    }
    if (paramIntent.hasExtra("google.c.a.udt")) {
      try
      {
        localBundle.putInt("_ndt", Integer.parseInt(paramIntent.getStringExtra("google.c.a.udt")));
      }
      catch (NumberFormatException paramIntent)
      {
        Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", paramIntent);
      }
    }
    if (Log.isLoggable("FirebaseMessaging", 3))
    {
      paramIntent = String.valueOf(localBundle);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 22 + String.valueOf(paramIntent).length());
      localStringBuilder.append("Sending event=");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" params=");
      localStringBuilder.append(paramIntent);
      Log.d("FirebaseMessaging", localStringBuilder.toString());
    }
    paramIntent = (AnalyticsConnector)FirebaseApp.getInstance().get(AnalyticsConnector.class);
    if (paramIntent != null)
    {
      paramIntent.logEvent("fcm", paramString, localBundle);
      return;
    }
    Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
  }
  
  public static void logNotificationDismiss(Intent paramIntent)
  {
    execute("_nd", paramIntent);
  }
  
  public static void logNotificationForeground(Intent paramIntent)
  {
    execute("_nf", paramIntent);
  }
  
  public static void logNotificationOpen(Intent paramIntent)
  {
    if (paramIntent != null) {
      if ("1".equals(paramIntent.getStringExtra("google.c.a.tc")))
      {
        AnalyticsConnector localAnalyticsConnector = (AnalyticsConnector)FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (Log.isLoggable("FirebaseMessaging", 3)) {
          Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
        }
        if (localAnalyticsConnector != null)
        {
          String str = paramIntent.getStringExtra("google.c.a.c_id");
          localAnalyticsConnector.setUserProperty("fcm", "_ln", str);
          Bundle localBundle = new Bundle();
          localBundle.putString("source", "Firebase");
          localBundle.putString("medium", "notification");
          localBundle.putString("campaign", str);
          localAnalyticsConnector.logEvent("fcm", "_cmp", localBundle);
        }
        else
        {
          Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
        }
      }
      else if (Log.isLoggable("FirebaseMessaging", 3))
      {
        Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
      }
    }
    execute("_no", paramIntent);
  }
  
  public static void logNotificationReceived(Intent paramIntent)
  {
    execute("_nr", paramIntent);
  }
  
  public static boolean shouldUploadMetrics(Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    if ("com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(paramIntent.getAction())) {
      return false;
    }
    return "1".equals(paramIntent.getStringExtra("google.c.a.e"));
  }
}
