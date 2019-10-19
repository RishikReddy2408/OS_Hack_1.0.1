package com.google.android.android.stats;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.android.common.stats.WakeLockTracker;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
@ShowFirstParty
public abstract class GCoreWakefulBroadcastReceiver
  extends WakefulBroadcastReceiver
{
  private static String mLogTag;
  
  public GCoreWakefulBroadcastReceiver() {}
  
  public static boolean completeWakefulIntent(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {
      return false;
    }
    if (paramContext != null)
    {
      WakeLockTracker.getInstance().registerReleaseEvent(paramContext, paramIntent);
    }
    else
    {
      String str = mLogTag;
      paramContext = String.valueOf(paramIntent.toUri(0));
      if (paramContext.length() != 0) {
        paramContext = "context shouldn't be null. intent: ".concat(paramContext);
      } else {
        paramContext = new String("context shouldn't be null. intent: ");
      }
      Log.w(str, paramContext);
    }
    return WakefulBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
}
