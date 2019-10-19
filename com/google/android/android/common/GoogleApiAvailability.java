package com.google.android.android.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageItemInfo;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.v4.package_7.FragmentActivity;
import android.support.v4.package_7.NotificationCompat.BigTextStyle;
import android.support.v4.package_7.NotificationCompat.Builder;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.android.base.R.drawable;
import com.google.android.android.base.R.string;
import com.google.android.android.common.aimsicd.GoogleApi;
import com.google.android.android.common.aimsicd.GoogleApiActivity;
import com.google.android.android.common.aimsicd.internal.AbstractGalleryActivity;
import com.google.android.android.common.aimsicd.internal.GoogleApiManager;
import com.google.android.android.common.aimsicd.internal.LifecycleFragment;
import com.google.android.android.common.aimsicd.internal.zabq;
import com.google.android.android.common.aimsicd.internal.zabr;
import com.google.android.android.common.aimsicd.internal.zabu;
import com.google.android.android.common.internal.ConnectionErrorMessages;
import com.google.android.android.common.internal.DialogRedirect;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.DeviceProperties;
import com.google.android.android.common.util.PlatformVersion;
import com.google.android.android.internal.base.Credentials;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GoogleApiAvailability
  extends GoogleApiAvailabilityLight
{
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  private static final Object mLock = new Object();
  private static final GoogleApiAvailability zaao = new GoogleApiAvailability();
  @GuardedBy("mLock")
  private String zaap;
  
  GoogleApiAvailability() {}
  
  static Dialog create(Context paramContext, int paramInt, DialogRedirect paramDialogRedirect, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    AlertDialog.Builder localBuilder = null;
    if (paramInt == 0) {
      return null;
    }
    Object localObject = new TypedValue();
    paramContext.getTheme().resolveAttribute(16843529, (TypedValue)localObject, true);
    if ("Theme.Dialog.Alert".equals(paramContext.getResources().getResourceEntryName(resourceId))) {
      localBuilder = new AlertDialog.Builder(paramContext, 5);
    }
    localObject = localBuilder;
    if (localBuilder == null) {
      localObject = new AlertDialog.Builder(paramContext);
    }
    ((AlertDialog.Builder)localObject).setMessage(ConnectionErrorMessages.getErrorMessage(paramContext, paramInt));
    if (paramOnCancelListener != null) {
      ((AlertDialog.Builder)localObject).setOnCancelListener(paramOnCancelListener);
    }
    paramOnCancelListener = ConnectionErrorMessages.getErrorDialogButtonMessage(paramContext, paramInt);
    if (paramOnCancelListener != null) {
      ((AlertDialog.Builder)localObject).setPositiveButton(paramOnCancelListener, paramDialogRedirect);
    }
    paramContext = ConnectionErrorMessages.getErrorTitle(paramContext, paramInt);
    if (paramContext != null) {
      ((AlertDialog.Builder)localObject).setTitle(paramContext);
    }
    return ((AlertDialog.Builder)localObject).create();
  }
  
  private final void createNotification(Context paramContext, int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    if (paramInt == 18)
    {
      notify(paramContext);
      return;
    }
    if (paramPendingIntent == null)
    {
      if (paramInt == 6) {
        Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
      }
    }
    else
    {
      Object localObject1 = ConnectionErrorMessages.getErrorNotificationTitle(paramContext, paramInt);
      paramString = ConnectionErrorMessages.getErrorNotificationMessage(paramContext, paramInt);
      Object localObject2 = paramContext.getResources();
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      localObject1 = new NotificationCompat.Builder(paramContext).setLocalOnly(true).setAutoCancel(true).setContentTitle((CharSequence)localObject1).setStyle(new NotificationCompat.BigTextStyle().bigText(paramString));
      if (DeviceProperties.isWearable(paramContext))
      {
        Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
        ((NotificationCompat.Builder)localObject1).setSmallIcon(getApplicationInfoicon).setPriority(2);
        if (DeviceProperties.isWearableWithoutPlayStore(paramContext)) {
          ((NotificationCompat.Builder)localObject1).addAction(R.drawable.common_full_open_on_phone, ((Resources)localObject2).getString(R.string.common_open_on_phone), paramPendingIntent);
        } else {
          ((NotificationCompat.Builder)localObject1).setContentIntent(paramPendingIntent);
        }
      }
      else
      {
        ((NotificationCompat.Builder)localObject1).setSmallIcon(17301642).setTicker(((Resources)localObject2).getString(R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setContentIntent(paramPendingIntent).setContentText(paramString);
      }
      if (PlatformVersion.isAtLeastO())
      {
        Preconditions.checkState(PlatformVersion.isAtLeastO());
        paramPendingIntent = isLink();
        paramString = paramPendingIntent;
        if (paramPendingIntent == null)
        {
          paramPendingIntent = "com.google.android.gms.availability";
          localObject2 = localNotificationManager.getNotificationChannel("com.google.android.gms.availability");
          paramContext = ConnectionErrorMessages.getDefaultNotificationChannelName(paramContext);
          if (localObject2 == null)
          {
            localNotificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", paramContext, 4));
            paramString = paramPendingIntent;
          }
          else
          {
            paramString = paramPendingIntent;
            if (!paramContext.equals(((NotificationChannel)localObject2).getName()))
            {
              ((NotificationChannel)localObject2).setName(paramContext);
              localNotificationManager.createNotificationChannel((NotificationChannel)localObject2);
              paramString = paramPendingIntent;
            }
          }
        }
        ((NotificationCompat.Builder)localObject1).setChannelId(paramString);
      }
      paramContext = ((NotificationCompat.Builder)localObject1).build();
      switch (paramInt)
      {
      default: 
        paramInt = 39789;
        break;
      case 1: 
      case 2: 
      case 3: 
        paramInt = 10436;
        GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
      }
      localNotificationManager.notify(paramInt, paramContext);
    }
  }
  
  public static GoogleApiAvailability getInstance()
  {
    return zaao;
  }
  
  private final String isLink()
  {
    Object localObject = mLock;
    try
    {
      String str = zaap;
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static Dialog show(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Object localObject = new ProgressBar(paramActivity, null, 16842874);
    ((ProgressBar)localObject).setIndeterminate(true);
    ((ProgressBar)localObject).setVisibility(0);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
    localBuilder.setView((View)localObject);
    localBuilder.setMessage(ConnectionErrorMessages.getErrorMessage(paramActivity, 18));
    localBuilder.setPositiveButton("", null);
    localObject = localBuilder.create();
    showDialog(paramActivity, (Dialog)localObject, "GooglePlayServicesUpdatingDialog", paramOnCancelListener);
    return localObject;
  }
  
  static void showDialog(Activity paramActivity, Dialog paramDialog, String paramString, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    if ((paramActivity instanceof FragmentActivity))
    {
      paramActivity = ((FragmentActivity)paramActivity).getSupportFragmentManager();
      SupportErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(paramActivity, paramString);
      return;
    }
    paramActivity = paramActivity.getFragmentManager();
    ErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(paramActivity, paramString);
  }
  
  public Task checkApiAvailability(GoogleApi paramGoogleApi, GoogleApi... paramVarArgs)
  {
    Preconditions.checkNotNull(paramGoogleApi, "Requested API must not be null.");
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      Preconditions.checkNotNull(paramVarArgs[i], "Requested API must not be null.");
      i += 1;
    }
    ArrayList localArrayList = new ArrayList(paramVarArgs.length + 1);
    localArrayList.add(paramGoogleApi);
    localArrayList.addAll(Arrays.asList(paramVarArgs));
    return GoogleApiManager.zabc().call(localArrayList).continueWith(new Task.8(this));
  }
  
  public final boolean create(Activity paramActivity, LifecycleFragment paramLifecycleFragment, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    paramLifecycleFragment = create(paramActivity, paramInt1, DialogRedirect.getInstance(paramLifecycleFragment, getErrorResolutionIntent(paramActivity, paramInt1, "d"), 2), paramOnCancelListener);
    if (paramLifecycleFragment == null) {
      return false;
    }
    showDialog(paramActivity, paramLifecycleFragment, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public int getClientVersion(Context paramContext)
  {
    return super.getClientVersion(paramContext);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2)
  {
    return getErrorDialog(paramActivity, paramInt1, paramInt2, null);
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return create(paramActivity, paramInt1, DialogRedirect.getInstance(paramActivity, getErrorResolutionIntent(paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
  }
  
  public Intent getErrorResolutionIntent(Context paramContext, int paramInt, String paramString)
  {
    return super.getErrorResolutionIntent(paramContext, paramInt, paramString);
  }
  
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2)
  {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.hasResolution()) {
      return paramConnectionResult.getResolution();
    }
    return getErrorResolutionPendingIntent(paramContext, paramConnectionResult.getErrorCode(), 0);
  }
  
  public final String getErrorString(int paramInt)
  {
    return super.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext)
  {
    return super.isGooglePlayServicesAvailable(paramContext);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext, int paramInt)
  {
    return super.isGooglePlayServicesAvailable(paramContext, paramInt);
  }
  
  public final boolean isUserResolvableError(int paramInt)
  {
    return super.isUserResolvableError(paramInt);
  }
  
  public Task makeGooglePlayServicesAvailable(Activity paramActivity)
  {
    int i = GOOGLE_PLAY_SERVICES_VERSION_CODE;
    Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
    i = isGooglePlayServicesAvailable(paramActivity, i);
    if (i == 0) {
      return Tasks.forResult(null);
    }
    paramActivity = zabu.findAll(paramActivity);
    paramActivity.next(new ConnectionResult(i, null), 0);
    return paramActivity.getTask();
  }
  
  final void notify(Context paramContext)
  {
    new zaa(paramContext).sendEmptyMessageDelayed(1, 120000L);
  }
  
  public final boolean setCurrentTheme(Context paramContext, ConnectionResult paramConnectionResult, int paramInt)
  {
    PendingIntent localPendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (localPendingIntent != null)
    {
      createNotification(paramContext, paramConnectionResult.getErrorCode(), null, GoogleApiActivity.createPendingIntent(paramContext, localPendingIntent, paramInt));
      return true;
    }
    return false;
  }
  
  public void setDefaultNotificationChannelId(Context paramContext, String paramString)
  {
    if (PlatformVersion.isAtLeastO()) {
      Preconditions.checkNotNull(((NotificationManager)paramContext.getSystemService("notification")).getNotificationChannel(paramString));
    }
    paramContext = mLock;
    try
    {
      zaap = paramString;
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2)
  {
    return showErrorDialogFragment(paramActivity, paramInt1, paramInt2, null);
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Dialog localDialog = getErrorDialog(paramActivity, paramInt1, paramInt2, paramOnCancelListener);
    if (localDialog == null) {
      return false;
    }
    showDialog(paramActivity, localDialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public void showErrorNotification(Context paramContext, int paramInt)
  {
    createNotification(paramContext, paramInt, null, getErrorResolutionPendingIntent(paramContext, paramInt, 0, "n"));
  }
  
  public void showErrorNotification(Context paramContext, ConnectionResult paramConnectionResult)
  {
    PendingIntent localPendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    createNotification(paramContext, paramConnectionResult.getErrorCode(), null, localPendingIntent);
  }
  
  public final zabq start(Context paramContext, zabr paramZabr)
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addDataScheme("package");
    zabq localZabq = new zabq(paramZabr);
    paramContext.registerReceiver(localZabq, localIntentFilter);
    localZabq.startListening(paramContext);
    if (!isUninstalledAppPossiblyUpdating(paramContext, "com.google.android.gms"))
    {
      paramZabr.cancel();
      localZabq.unregister();
      return null;
    }
    return localZabq;
  }
  
  @SuppressLint({"HandlerLeak"})
  final class zaa
    extends Credentials
  {
    private final Context zaaq;
    
    public zaa(Context paramContext)
    {
      super();
      zaaq = paramContext.getApplicationContext();
    }
    
    public final void handleMessage(Message paramMessage)
    {
      if (what != 1)
      {
        i = what;
        paramMessage = new StringBuilder(50);
        paramMessage.append("Don't know how to handle this message: ");
        paramMessage.append(i);
        Log.w("GoogleApiAvailability", paramMessage.toString());
        return;
      }
      int i = isGooglePlayServicesAvailable(zaaq);
      if (isUserResolvableError(i)) {
        showErrorNotification(zaaq, i);
      }
    }
  }
}
