package com.google.android.android.common.internal;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.common.GooglePlayServicesUtil;
import com.google.android.android.common.util.DeviceProperties;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import javax.annotation.concurrent.GuardedBy;

public final class ConnectionErrorMessages
{
  @GuardedBy("sCache")
  private static final SimpleArrayMap<String, String> zaof = new SimpleArrayMap();
  
  private ConnectionErrorMessages() {}
  
  private static String format(Context paramContext, String paramString1, String paramString2)
  {
    Resources localResources = paramContext.getResources();
    paramString1 = loadData(paramContext, paramString1);
    paramContext = paramString1;
    if (paramString1 == null) {
      paramContext = localResources.getString(com.google.android.android.common.R.string.common_google_play_services_unknown_issue);
    }
    return String.format(getConfigurationlocale, paramContext, new Object[] { paramString2 });
  }
  
  public static String getAppName(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    try
    {
      String str2 = Wrappers.packageManager(paramContext).getApplicationLabel(str1).toString();
      return str2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;) {}
    }
    paramContext = getApplicationInfoname;
    if (TextUtils.isEmpty(paramContext)) {
      return str1;
    }
    return paramContext;
  }
  
  public static String getDefaultNotificationChannelName(Context paramContext)
  {
    return paramContext.getResources().getString(com.google.android.android.base.R.string.common_google_play_services_notification_channel_name);
  }
  
  public static String getErrorDialogButtonMessage(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      return paramContext.getString(17039370);
    case 3: 
      return paramContext.getString(com.google.android.android.base.R.string.common_google_play_services_enable_button);
    case 2: 
      return paramContext.getString(com.google.android.android.base.R.string.common_google_play_services_update_button);
    }
    return paramContext.getString(com.google.android.android.base.R.string.common_google_play_services_install_button);
  }
  
  public static String getErrorMessage(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    String str = getAppName(paramContext);
    if (paramInt != 5)
    {
      if (paramInt != 7)
      {
        if (paramInt != 9)
        {
          if (paramInt != 20)
          {
            switch (paramInt)
            {
            default: 
              switch (paramInt)
              {
              default: 
                return localResources.getString(com.google.android.android.common.R.string.common_google_play_services_unknown_issue, new Object[] { str });
              case 18: 
                return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_updating_text, new Object[] { str });
              case 17: 
                return format(paramContext, "common_google_play_services_sign_in_failed_text", str);
              }
              return format(paramContext, "common_google_play_services_api_unavailable_text", str);
            case 3: 
              return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_enable_text, new Object[] { str });
            case 2: 
              if (DeviceProperties.isWearableWithoutPlayStore(paramContext)) {
                return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_wear_update_text);
              }
              return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_update_text, new Object[] { str });
            }
            return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_install_text, new Object[] { str });
          }
          return format(paramContext, "common_google_play_services_restricted_profile_text", str);
        }
        return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_unsupported_text, new Object[] { str });
      }
      return format(paramContext, "common_google_play_services_network_error_text", str);
    }
    return format(paramContext, "common_google_play_services_invalid_account_text", str);
  }
  
  public static String getErrorNotificationMessage(Context paramContext, int paramInt)
  {
    if (paramInt == 6) {
      return format(paramContext, "common_google_play_services_resolution_required_text", getAppName(paramContext));
    }
    return getErrorMessage(paramContext, paramInt);
  }
  
  public static String getErrorNotificationTitle(Context paramContext, int paramInt)
  {
    String str1;
    if (paramInt == 6) {
      str1 = loadData(paramContext, "common_google_play_services_resolution_required_title");
    } else {
      str1 = getErrorTitle(paramContext, paramInt);
    }
    String str2 = str1;
    if (str1 == null) {
      str2 = paramContext.getResources().getString(com.google.android.android.base.R.string.common_google_play_services_notification_ticker);
    }
    return str2;
  }
  
  public static String getErrorTitle(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    if (paramInt != 20)
    {
      switch (paramInt)
      {
      default: 
        switch (paramInt)
        {
        default: 
          paramContext = new StringBuilder(33);
          paramContext.append("Unexpected error code ");
          paramContext.append(paramInt);
          Log.e("GoogleApiAvailability", paramContext.toString());
          return null;
        case 17: 
          Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
          return loadData(paramContext, "common_google_play_services_sign_in_failed_title");
        case 16: 
          Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
          return null;
        }
      case 11: 
        Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
        return null;
      case 10: 
        Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
        return null;
      case 9: 
        Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
        return null;
      case 8: 
        Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
        return null;
      case 7: 
        Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
        return loadData(paramContext, "common_google_play_services_network_error_title");
      case 5: 
        Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
        return loadData(paramContext, "common_google_play_services_invalid_account_title");
      case 4: 
      case 6: 
        return null;
      case 3: 
        return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_enable_title);
      case 2: 
        return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_update_title);
      }
      return localResources.getString(com.google.android.android.base.R.string.common_google_play_services_install_title);
    }
    Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
    return loadData(paramContext, "common_google_play_services_restricted_profile_title");
  }
  
  private static String loadData(Context paramContext, String paramString)
  {
    SimpleArrayMap localSimpleArrayMap = zaof;
    try
    {
      String str = (String)zaof.get(paramString);
      if (str != null) {
        return str;
      }
      paramContext = GooglePlayServicesUtil.getRemoteResource(paramContext);
      if (paramContext == null) {
        return null;
      }
      int i = paramContext.getIdentifier(paramString, "string", "com.google.android.gms");
      if (i == 0)
      {
        paramContext = String.valueOf(paramString);
        if (paramContext.length() != 0) {
          paramContext = "Missing resource: ".concat(paramContext);
        } else {
          paramContext = new String("Missing resource: ");
        }
        Log.w("GoogleApiAvailability", paramContext);
        return null;
      }
      paramContext = paramContext.getString(i);
      if (TextUtils.isEmpty(paramContext))
      {
        paramContext = String.valueOf(paramString);
        if (paramContext.length() != 0) {
          paramContext = "Got empty resource: ".concat(paramContext);
        } else {
          paramContext = new String("Got empty resource: ");
        }
        Log.w("GoogleApiAvailability", paramContext);
        return null;
      }
      zaof.put(paramString, paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
}
