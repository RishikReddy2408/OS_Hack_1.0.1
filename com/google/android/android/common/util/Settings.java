package com.google.android.android.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class Settings
{
  private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
  private static long zzgt;
  private static float zzgu = NaN.0F;
  
  public static int init(Context paramContext)
  {
    if (paramContext != null)
    {
      if (paramContext.getApplicationContext() == null) {
        return -1;
      }
      Intent localIntent = paramContext.getApplicationContext().registerReceiver(null, filter);
      int j = 0;
      int i;
      if (localIntent == null) {
        i = 0;
      } else {
        i = localIntent.getIntExtra("plugged", 0);
      }
      if ((i & 0x7) != 0) {
        i = 1;
      } else {
        i = 0;
      }
      paramContext = (PowerManager)paramContext.getSystemService("power");
      if (paramContext == null) {
        return -1;
      }
      boolean bool;
      if (PlatformVersion.isAtLeastKitKatWatch()) {
        bool = paramContext.isInteractive();
      } else {
        bool = paramContext.isScreenOn();
      }
      if (bool) {
        j = 1;
      }
      return j << 1 | i;
    }
    return -1;
  }
  
  public static float load(Context paramContext)
  {
    try
    {
      if ((SystemClock.elapsedRealtime() - zzgt < 60000L) && (!Float.isNaN(zzgu)))
      {
        f = zzgu;
        return f;
      }
      paramContext = paramContext.getApplicationContext().registerReceiver(null, filter);
      if (paramContext != null)
      {
        int i = paramContext.getIntExtra("level", -1);
        int j = paramContext.getIntExtra("scale", -1);
        zzgu = i / j;
      }
      zzgt = SystemClock.elapsedRealtime();
      float f = zzgu;
      return f;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
}
