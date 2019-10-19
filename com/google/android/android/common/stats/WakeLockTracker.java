package com.google.android.android.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.common.util.Settings;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public class WakeLockTracker
{
  @VisibleForTesting
  private static boolean zzfb = false;
  private static WakeLockTracker zzgb = new WakeLockTracker();
  private static Boolean zzgc;
  
  public WakeLockTracker() {}
  
  public static WakeLockTracker getInstance()
  {
    return zzgb;
  }
  
  public void registerAcquireEvent(Context paramContext, Intent paramIntent, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    paramString4 = Arrays.asList(new String[] { paramString4 });
    registerEvent(paramContext, paramIntent.getStringExtra("WAKE_LOCK_KEY"), 7, paramString1, paramString2, paramString3, paramInt, paramString4);
  }
  
  public void registerEvent(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, List paramList)
  {
    registerEvent(paramContext, paramString1, paramInt1, paramString2, paramString3, paramString4, paramInt2, paramList, 0L);
  }
  
  public void registerEvent(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, List paramList, long paramLong)
  {
    Object localObject2 = paramList;
    if (zzgc == null) {
      zzgc = Boolean.valueOf(false);
    }
    if (!zzgc.booleanValue()) {
      return;
    }
    if (TextUtils.isEmpty(paramString1))
    {
      paramContext = String.valueOf(paramString1);
      if (paramContext.length() != 0) {
        paramContext = "missing wakeLock key. ".concat(paramContext);
      } else {
        paramContext = new String("missing wakeLock key. ");
      }
      Log.e("WakeLockTracker", paramContext);
      return;
    }
    long l1 = System.currentTimeMillis();
    if ((7 == paramInt1) || (8 == paramInt1) || (10 == paramInt1) || (11 == paramInt1))
    {
      Object localObject1 = localObject2;
      if (paramList != null)
      {
        localObject1 = localObject2;
        if (paramList.size() == 1)
        {
          localObject1 = localObject2;
          if ("com.google.android.gms".equals(paramList.get(0))) {
            localObject1 = null;
          }
        }
      }
      long l2 = SystemClock.elapsedRealtime();
      int i = Settings.init(paramContext);
      localObject2 = paramContext.getPackageName();
      paramList = (List)localObject2;
      if ("com.google.android.gms".equals(localObject2)) {
        paramList = null;
      }
      paramString1 = new WakeLockEvent(l1, paramInt1, paramString2, paramInt2, localObject1, paramString1, l2, i, paramString3, paramList, Settings.load(paramContext), paramLong, paramString4);
      try
      {
        paramString2 = new Intent();
        paramString3 = LoggingConstants.zzfg;
        paramContext.startService(paramString2.setComponent(paramString3).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", paramString1));
        return;
      }
      catch (Exception paramContext)
      {
        Log.wtf("WakeLockTracker", paramContext);
      }
    }
  }
  
  public void registerReleaseEvent(Context paramContext, Intent paramIntent)
  {
    registerEvent(paramContext, paramIntent.getStringExtra("WAKE_LOCK_KEY"), 8, null, null, null, 0, null);
  }
}
