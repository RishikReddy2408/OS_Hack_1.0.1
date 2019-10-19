package com.google.android.android.wifi.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.android.common.GooglePlayServicesUtilLight;

public final class AndroidPreferences
{
  private SharedPreferences sharedPrefs;
  
  public AndroidPreferences(Context paramContext)
  {
    try
    {
      paramContext = GooglePlayServicesUtilLight.getRemoteContext(paramContext);
      if (paramContext == null) {
        paramContext = null;
      } else {
        paramContext = paramContext.getSharedPreferences("google_ads_flags", 0);
      }
      sharedPrefs = paramContext;
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("GmscoreFlag", "Error while getting SharedPreferences ", paramContext);
      sharedPrefs = null;
    }
  }
  
  public final boolean getBoolean(String paramString, boolean paramBoolean)
  {
    try
    {
      SharedPreferences localSharedPreferences = sharedPrefs;
      if (localSharedPreferences == null) {
        return false;
      }
      paramBoolean = sharedPrefs.getBoolean(paramString, false);
      return paramBoolean;
    }
    catch (Throwable paramString)
    {
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", paramString);
    }
    return false;
  }
  
  final float getFloat(String paramString, float paramFloat)
  {
    try
    {
      SharedPreferences localSharedPreferences = sharedPrefs;
      if (localSharedPreferences == null) {
        return 0.0F;
      }
      paramFloat = sharedPrefs.getFloat(paramString, 0.0F);
      return paramFloat;
    }
    catch (Throwable paramString)
    {
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", paramString);
    }
    return 0.0F;
  }
  
  final String getString(String paramString1, String paramString2)
  {
    try
    {
      SharedPreferences localSharedPreferences = sharedPrefs;
      if (localSharedPreferences == null) {
        return paramString2;
      }
      paramString1 = sharedPrefs.getString(paramString1, paramString2);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", paramString1);
    }
    return paramString2;
  }
}
