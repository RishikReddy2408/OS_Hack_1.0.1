package com.google.android.android.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.support.v4.package_7.Fragment;
import com.google.android.android.common.internal.DialogRedirect;

public final class GooglePlayServicesUtil
  extends GooglePlayServicesUtilLight
{
  public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  
  private GooglePlayServicesUtil() {}
  
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return getErrorDialog(paramInt1, paramActivity, paramInt2, null);
  }
  
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    int i = paramInt1;
    if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(paramActivity, paramInt1)) {
      i = 18;
    }
    return GoogleApiAvailability.getInstance().getErrorDialog(paramActivity, i, paramInt2, paramOnCancelListener);
  }
  
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    return GooglePlayServicesUtilLight.getErrorPendingIntent(paramInt1, paramContext, paramInt2);
  }
  
  public static String getErrorString(int paramInt)
  {
    return GooglePlayServicesUtilLight.getErrorString(paramInt);
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    return GooglePlayServicesUtilLight.getRemoteContext(paramContext);
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    return GooglePlayServicesUtilLight.getRemoteResource(paramContext);
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(paramContext);
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext, int paramInt)
  {
    return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(paramContext, paramInt);
  }
  
  public static boolean isUserRecoverableError(int paramInt)
  {
    return GooglePlayServicesUtilLight.isUserRecoverableError(paramInt);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, paramInt2, null);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, null, paramInt2, paramOnCancelListener);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, Fragment paramFragment, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    int i = paramInt1;
    if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(paramActivity, paramInt1)) {
      i = 18;
    }
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
    if (paramFragment == null) {
      return localGoogleApiAvailability.showErrorDialogFragment(paramActivity, i, paramInt2, paramOnCancelListener);
    }
    paramFragment = GoogleApiAvailability.create(paramActivity, i, DialogRedirect.getInstance(paramFragment, GoogleApiAvailability.getInstance().getErrorResolutionIntent(paramActivity, i, "d"), paramInt2), paramOnCancelListener);
    if (paramFragment == null) {
      return false;
    }
    GoogleApiAvailability.showDialog(paramActivity, paramFragment, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public static void showErrorNotification(int paramInt, Context paramContext)
  {
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
    if ((!GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(paramContext, paramInt)) && (!GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(paramContext, paramInt)))
    {
      localGoogleApiAvailability.showErrorNotification(paramContext, paramInt);
      return;
    }
    localGoogleApiAvailability.notify(paramContext);
  }
}
