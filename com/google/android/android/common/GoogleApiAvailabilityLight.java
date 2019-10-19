package com.google.android.android.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.android.common.internal.PluginUtil;
import com.google.android.android.common.util.DeviceProperties;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
@ShowFirstParty
public class GoogleApiAvailabilityLight
{
  @KeepForSdk
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  @KeepForSdk
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  @KeepForSdk
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  @KeepForSdk
  static final String TRACKING_SOURCE_DIALOG = "d";
  @KeepForSdk
  static final String TRACKING_SOURCE_NOTIFICATION = "n";
  private static final GoogleApiAvailabilityLight sApp = new GoogleApiAvailabilityLight();
  
  GoogleApiAvailabilityLight() {}
  
  private static String create(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gcore_");
    localStringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
    localStringBuilder.append("-");
    if (!TextUtils.isEmpty(paramString)) {
      localStringBuilder.append(paramString);
    }
    localStringBuilder.append("-");
    if (paramContext != null) {
      localStringBuilder.append(paramContext.getPackageName());
    }
    localStringBuilder.append("-");
    if (paramContext != null) {}
    try
    {
      paramContext = Wrappers.packageManager(paramContext).getPackageInfo(paramContext.getPackageName(), 0);
      int i = versionCode;
      localStringBuilder.append(i);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return localStringBuilder.toString();
  }
  
  public static GoogleApiAvailabilityLight getInstance()
  {
    return sApp;
  }
  
  public void cancelAvailabilityErrorNotifications(Context paramContext)
  {
    GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(paramContext);
  }
  
  public int getApkVersion(Context paramContext)
  {
    return GooglePlayServicesUtilLight.getApkVersion(paramContext);
  }
  
  public int getClientVersion(Context paramContext)
  {
    return GooglePlayServicesUtilLight.getClientVersion(paramContext);
  }
  
  public Intent getErrorResolutionIntent(int paramInt)
  {
    return getErrorResolutionIntent(null, paramInt, null);
  }
  
  public Intent getErrorResolutionIntent(Context paramContext, int paramInt, String paramString)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 3: 
      return PluginUtil.showInstalledAppDetails("com.google.android.gms");
    }
    if ((paramContext != null) && (DeviceProperties.isWearableWithoutPlayStore(paramContext))) {
      return PluginUtil.createIntent();
    }
    return PluginUtil.execute("com.google.android.gms", create(paramContext, paramString));
  }
  
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2)
  {
    return getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2, null);
  }
  
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2, String paramString)
  {
    paramString = getErrorResolutionIntent(paramContext, paramInt1, paramString);
    if (paramString == null) {
      return null;
    }
    return PendingIntent.getActivity(paramContext, paramInt2, paramString, 134217728);
  }
  
  public String getErrorString(int paramInt)
  {
    return GooglePlayServicesUtilLight.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext)
  {
    return isGooglePlayServicesAvailable(paramContext, GOOGLE_PLAY_SERVICES_VERSION_CODE);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext, int paramInt)
  {
    paramInt = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(paramContext, paramInt);
    if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(paramContext, paramInt)) {
      return 18;
    }
    return paramInt;
  }
  
  public boolean isPlayServicesPossiblyUpdating(Context paramContext, int paramInt)
  {
    return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(paramContext, paramInt);
  }
  
  public boolean isPlayStorePossiblyUpdating(Context paramContext, int paramInt)
  {
    return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(paramContext, paramInt);
  }
  
  public boolean isUninstalledAppPossiblyUpdating(Context paramContext, String paramString)
  {
    return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(paramContext, paramString);
  }
  
  public boolean isUserResolvableError(int paramInt)
  {
    return GooglePlayServicesUtilLight.isUserRecoverableError(paramInt);
  }
  
  public void verifyGooglePlayServicesIsAvailable(Context paramContext, int paramInt)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    GooglePlayServicesUtilLight.ensurePlayServicesAvailable(paramContext, paramInt);
  }
}
