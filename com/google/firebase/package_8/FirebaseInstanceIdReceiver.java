package com.google.firebase.package_8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.google.android.android.common.util.PlatformVersion;
import javax.annotation.concurrent.GuardedBy;

public final class FirebaseInstanceIdReceiver
  extends WakefulBroadcastReceiver
{
  private static boolean zzbf;
  @GuardedBy("FirebaseInstanceIdReceiver.class")
  private static Session zzbg;
  @GuardedBy("FirebaseInstanceIdReceiver.class")
  private static Session zzbh;
  
  public FirebaseInstanceIdReceiver() {}
  
  private static int execute(BroadcastReceiver paramBroadcastReceiver, Context paramContext, String paramString, Intent paramIntent)
  {
    if (Log.isLoggable("FirebaseInstanceId", 3))
    {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Binding to service: ".concat(str);
      } else {
        str = new String("Binding to service: ");
      }
      Log.d("FirebaseInstanceId", str);
    }
    if (paramBroadcastReceiver.isOrderedBroadcast()) {
      paramBroadcastReceiver.setResultCode(-1);
    }
    getSession(paramContext, paramString).connect(paramIntent, paramBroadcastReceiver.goAsync());
    return -1;
  }
  
  private static Session getSession(Context paramContext, String paramString)
  {
    try
    {
      if ("com.google.firebase.MESSAGING_EVENT".equals(paramString))
      {
        if (zzbh == null) {
          zzbh = new Session(paramContext, paramString);
        }
        paramContext = zzbh;
        return paramContext;
      }
      if (zzbg == null) {
        zzbg = new Session(paramContext, paramString);
      }
      paramContext = zzbg;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static int init(BroadcastReceiver paramBroadcastReceiver, Context paramContext, String paramString, Intent paramIntent)
  {
    boolean bool = PlatformVersion.isAtLeastO();
    int j = 1;
    if ((bool) && (getApplicationInfotargetSdkVersion >= 26)) {
      i = 1;
    } else {
      i = 0;
    }
    if ((paramIntent.getFlags() & 0x10000000) == 0) {
      j = 0;
    }
    if ((i != 0) && (j == 0)) {
      return execute(paramBroadcastReceiver, paramContext, paramString, paramIntent);
    }
    int i = zzav.zzai().execute(paramContext, paramString, paramIntent);
    if ((PlatformVersion.isAtLeastO()) && (i == 402))
    {
      execute(paramBroadcastReceiver, paramContext, paramString, paramIntent);
      return 403;
    }
    return i;
  }
  
  private final void init(Context paramContext, Intent paramIntent, String paramString)
  {
    Object localObject = null;
    paramIntent.setComponent(null);
    paramIntent.setPackage(paramContext.getPackageName());
    if (Build.VERSION.SDK_INT <= 18) {
      paramIntent.removeCategory(paramContext.getPackageName());
    }
    String str = paramIntent.getStringExtra("gcm.rawData64");
    if (str != null)
    {
      paramIntent.putExtra("rawData", Base64.decode(str, 0));
      paramIntent.removeExtra("gcm.rawData64");
    }
    if ((!"google.com/iid".equals(paramIntent.getStringExtra("from"))) && (!"com.google.firebase.INSTANCE_ID_EVENT".equals(paramString)))
    {
      if ((!"com.google.android.c2dm.intent.RECEIVE".equals(paramString)) && (!"com.google.firebase.MESSAGING_EVENT".equals(paramString)))
      {
        Log.d("FirebaseInstanceId", "Unexpected intent");
        paramString = localObject;
      }
      else
      {
        paramString = "com.google.firebase.MESSAGING_EVENT";
      }
    }
    else {
      paramString = "com.google.firebase.INSTANCE_ID_EVENT";
    }
    int i = -1;
    if (paramString != null) {
      i = init(this, paramContext, paramString, paramIntent);
    }
    if (isOrderedBroadcast()) {
      setResultCode(i);
    }
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {
      return;
    }
    Object localObject = paramIntent.getParcelableExtra("wrapped_intent");
    if ((localObject instanceof Intent)) {
      localObject = (Intent)localObject;
    } else {
      localObject = null;
    }
    if (localObject != null)
    {
      init(paramContext, (Intent)localObject, paramIntent.getAction());
      return;
    }
    init(paramContext, paramIntent, paramIntent.getAction());
  }
}
