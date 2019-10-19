package com.google.android.android.common;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.android.common.internal.AppUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.ClientLibraryUtils;
import com.google.android.android.common.util.DeviceProperties;
import com.google.android.android.common.util.PlatformVersion;
import com.google.android.android.common.util.Speex;
import com.google.android.android.common.util.UidVerifier;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
@ShowFirstParty
public class GooglePlayServicesUtilLight
{
  @KeepForSdk
  static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
  @KeepForSdk
  static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 39789;
  @KeepForSdk
  public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
  @Deprecated
  @KeepForSdk
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  @Deprecated
  @KeepForSdk
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
  @KeepForSdk
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  @KeepForSdk
  @VisibleForTesting
  static final AtomicBoolean sCanceledAvailabilityNotification = new AtomicBoolean();
  @VisibleForTesting
  private static boolean zzag;
  @VisibleForTesting
  private static boolean zzah;
  private static boolean zzai;
  @VisibleForTesting
  private static boolean zzaj;
  private static final AtomicBoolean zzak = new AtomicBoolean();
  
  GooglePlayServicesUtilLight() {}
  
  public static void cancelAvailabilityErrorNotifications(Context paramContext)
  {
    if (sCanceledAvailabilityNotification.getAndSet(true)) {
      return;
    }
    try
    {
      paramContext = paramContext.getSystemService("notification");
      paramContext = (NotificationManager)paramContext;
      if (paramContext != null)
      {
        paramContext.cancel(10436);
        return;
      }
    }
    catch (SecurityException paramContext) {}
  }
  
  public static void enableUsingApkIndependentContext()
  {
    zzak.set(true);
  }
  
  public static void ensurePlayServicesAvailable(Context paramContext, int paramInt)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    paramInt = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(paramContext, paramInt);
    if (paramInt != 0)
    {
      paramContext = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(paramContext, paramInt, "e");
      StringBuilder localStringBuilder = new StringBuilder(57);
      localStringBuilder.append("GooglePlayServices not available due to error ");
      localStringBuilder.append(paramInt);
      Log.e("GooglePlayServicesUtil", localStringBuilder.toString());
      if (paramContext == null) {
        throw new GooglePlayServicesNotAvailableException(paramInt);
      }
      throw new GooglePlayServicesRepairableException(paramInt, "Google Play Services not available", paramContext);
    }
  }
  
  public static int getApkVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      return versionCode;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
    return 0;
  }
  
  public static int getClientVersion(Context paramContext)
  {
    Preconditions.checkState(true);
    return ClientLibraryUtils.getClientVersion(paramContext, paramContext.getPackageName());
  }
  
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  public static String getErrorString(int paramInt)
  {
    return ConnectionResult.toString(paramInt);
  }
  
  public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int paramInt)
  {
    return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, paramInt, null);
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      paramContext = paramContext.createPackageContext("com.google.android.gms", 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static boolean honorsDebugCertificates(Context paramContext)
  {
    if (!zzaj)
    {
      try
      {
        PackageInfo localPackageInfo = Wrappers.packageManager(paramContext).getPackageInfo("com.google.android.gms", 64);
        GoogleSignatureVerifier.getInstance(paramContext);
        if (localPackageInfo != null)
        {
          boolean bool = GoogleSignatureVerifier.backup(localPackageInfo, false);
          if (!bool)
          {
            bool = GoogleSignatureVerifier.backup(localPackageInfo, true);
            if (bool)
            {
              zzai = true;
              break label58;
            }
          }
        }
        zzai = false;
        label58:
        zzaj = true;
      }
      catch (Throwable paramContext) {}catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", paramContext);
        zzaj = true;
      }
      zzaj = true;
      throw paramContext;
    }
    if (!zzai) {
      return !DeviceProperties.isUserBuild();
    }
    return true;
  }
  
  private static int init(Context paramContext, boolean paramBoolean, int paramInt)
  {
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = null;
    if (paramBoolean) {}
    try
    {
      localObject = localPackageManager.getPackageInfo("com.android.vending", 8256);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
    return 9;
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo("com.google.android.gms", 64);
      GoogleSignatureVerifier.getInstance(paramContext);
      if (!GoogleSignatureVerifier.backup(localPackageInfo, true))
      {
        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
        return 9;
      }
      if ((paramBoolean) && ((!GoogleSignatureVerifier.backup((PackageInfo)localObject, true)) || (!signatures[0].equals(signatures[0]))))
      {
        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
        return 9;
      }
      if (Speex.open(versionCode) < Speex.open(paramInt))
      {
        int i = versionCode;
        paramContext = new StringBuilder(77);
        paramContext.append("Google Play services out of date.  Requires ");
        paramContext.append(paramInt);
        paramContext.append(" but found ");
        paramContext.append(i);
        Log.w("GooglePlayServicesUtil", paramContext.toString());
        return 2;
      }
      localObject = applicationInfo;
      paramContext = (Context)localObject;
      if (localObject == null) {
        try
        {
          paramContext = localPackageManager.getApplicationInfo("com.google.android.gms", 0);
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", paramContext);
          return 1;
        }
      }
      if (!enabled) {
        return 3;
      }
      return 0;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
    return 1;
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    return isGooglePlayServicesAvailable(paramContext, GOOGLE_PLAY_SERVICES_VERSION_CODE);
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext, int paramInt)
  {
    try
    {
      paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
    }
    catch (Throwable localThrowable)
    {
      int i;
      boolean bool;
      for (;;) {}
    }
    Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
    if ((!"com.google.android.gms".equals(paramContext.getPackageName())) && (!zzak.get()))
    {
      i = AppUtils.getVersionCode(paramContext);
      if (i != 0)
      {
        if (i != GOOGLE_PLAY_SERVICES_VERSION_CODE)
        {
          paramInt = GOOGLE_PLAY_SERVICES_VERSION_CODE;
          paramContext = new StringBuilder(320);
          paramContext.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
          paramContext.append(paramInt);
          paramContext.append(" but found ");
          paramContext.append(i);
          paramContext.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
          throw new IllegalStateException(paramContext.toString());
        }
      }
      else {
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
      }
    }
    if ((!DeviceProperties.isWearableWithoutPlayStore(paramContext)) && (!DeviceProperties.checkVersion(paramContext))) {
      bool = true;
    } else {
      bool = false;
    }
    return init(paramContext, bool, paramInt);
  }
  
  public static boolean isGooglePlayServicesUid(Context paramContext, int paramInt)
  {
    return UidVerifier.isGooglePlayServicesUid(paramContext, paramInt);
  }
  
  public static boolean isPlayServicesPossiblyUpdating(Context paramContext, int paramInt)
  {
    if (paramInt == 18) {
      return true;
    }
    if (paramInt == 1) {
      return isUninstalledAppPossiblyUpdating(paramContext, "com.google.android.gms");
    }
    return false;
  }
  
  public static boolean isPlayStorePossiblyUpdating(Context paramContext, int paramInt)
  {
    if (paramInt == 9) {
      return isUninstalledAppPossiblyUpdating(paramContext, "com.android.vending");
    }
    return false;
  }
  
  public static boolean isRestrictedUserProfile(Context paramContext)
  {
    if (PlatformVersion.isAtLeastJellyBeanMR2())
    {
      paramContext = ((UserManager)paramContext.getSystemService("user")).getApplicationRestrictions(paramContext.getPackageName());
      if ((paramContext != null) && ("true".equals(paramContext.getString("restricted_profile")))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isSidewinderDevice(Context paramContext)
  {
    return DeviceProperties.isSidewinder(paramContext);
  }
  
  /* Error */
  static boolean isUninstalledAppPossiblyUpdating(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 18
    //   3: invokevirtual 298	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   6: istore_2
    //   7: invokestatic 369	com/google/android/android/common/util/PlatformVersion:isAtLeastLollipop	()Z
    //   10: ifeq +51 -> 61
    //   13: aload_0
    //   14: invokevirtual 134	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: invokevirtual 373	android/content/pm/PackageManager:getPackageInstaller	()Landroid/content/pm/PackageInstaller;
    //   20: invokevirtual 379	android/content/pm/PackageInstaller:getAllSessions	()Ljava/util/List;
    //   23: astore_3
    //   24: aload_3
    //   25: invokeinterface 385 1 0
    //   30: astore_3
    //   31: aload_3
    //   32: invokeinterface 390 1 0
    //   37: ifeq +24 -> 61
    //   40: aload_1
    //   41: aload_3
    //   42: invokeinterface 394 1 0
    //   47: checkcast 396	android/content/pm/PackageInstaller$SessionInfo
    //   50: invokevirtual 399	android/content/pm/PackageInstaller$SessionInfo:getAppPackageName	()Ljava/lang/String;
    //   53: invokevirtual 298	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq -25 -> 31
    //   59: iconst_1
    //   60: ireturn
    //   61: aload_0
    //   62: invokevirtual 134	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   65: astore_3
    //   66: aload_3
    //   67: aload_1
    //   68: sipush 8192
    //   71: invokevirtual 266	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   74: astore_1
    //   75: iload_2
    //   76: ifeq +8 -> 84
    //   79: aload_1
    //   80: getfield 276	android/content/pm/ApplicationInfo:enabled	Z
    //   83: ireturn
    //   84: aload_1
    //   85: getfield 276	android/content/pm/ApplicationInfo:enabled	Z
    //   88: ifeq +14 -> 102
    //   91: aload_0
    //   92: invokestatic 401	com/google/android/android/common/GooglePlayServicesUtilLight:isRestrictedUserProfile	(Landroid/content/Context;)Z
    //   95: istore_2
    //   96: iload_2
    //   97: ifne +11 -> 108
    //   100: iconst_1
    //   101: ireturn
    //   102: iconst_0
    //   103: ireturn
    //   104: astore_0
    //   105: iconst_0
    //   106: ireturn
    //   107: astore_0
    //   108: iconst_0
    //   109: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	110	0	paramContext	Context
    //   0	110	1	paramString	String
    //   6	91	2	bool	boolean
    //   23	44	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   13	24	104	java/lang/Exception
    //   66	75	107	android/content/pm/PackageManager$NameNotFoundException
    //   91	96	107	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static boolean isUserRecoverableError(int paramInt)
  {
    if (paramInt != 9) {
      switch (paramInt)
      {
      default: 
        return false;
      }
    }
    return true;
  }
  
  public static boolean uidHasPackageName(Context paramContext, int paramInt, String paramString)
  {
    return UidVerifier.uidHasPackageName(paramContext, paramInt, paramString);
  }
}
