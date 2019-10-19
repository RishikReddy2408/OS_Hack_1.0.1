package com.google.android.android.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class StatsUtils
{
  public StatsUtils() {}
  
  public static String getEventKey(Context paramContext, Intent paramIntent)
  {
    long l = System.identityHashCode(paramContext);
    return String.valueOf(System.identityHashCode(paramIntent) | l << 32);
  }
  
  public static String getEventKey(PowerManager.WakeLock paramWakeLock, String paramString)
  {
    String str = String.valueOf(String.valueOf(Process.myPid() << 32 | System.identityHashCode(paramWakeLock)));
    paramWakeLock = paramString;
    if (TextUtils.isEmpty(paramString)) {
      paramWakeLock = "";
    }
    paramWakeLock = String.valueOf(paramWakeLock);
    if (paramWakeLock.length() != 0) {
      return str.concat(paramWakeLock);
    }
    return new String(str);
  }
}
