package com.google.android.android.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.android.common.util.ClientLibraryUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class ConnectionTracker
{
  private static final Object zzdp = new Object();
  private static volatile ConnectionTracker zzfa;
  @VisibleForTesting
  private static boolean zzfb = false;
  private final List<String> zzfc = Collections.EMPTY_LIST;
  private final List<String> zzfd = Collections.EMPTY_LIST;
  private final List<String> zzfe = Collections.EMPTY_LIST;
  private final List<String> zzff = Collections.EMPTY_LIST;
  
  private ConnectionTracker() {}
  
  public static ConnectionTracker getInstance()
  {
    if (zzfa == null)
    {
      Object localObject = zzdp;
      try
      {
        if (zzfa == null) {
          zzfa = new ConnectionTracker();
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return zzfa;
  }
  
  public boolean bindService(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    return update(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  public void unbindService(Context paramContext, ServiceConnection paramServiceConnection)
  {
    paramContext.unbindService(paramServiceConnection);
  }
  
  public final boolean update(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    paramString = paramIntent.getComponent();
    boolean bool;
    if (paramString == null) {
      bool = false;
    } else {
      bool = ClientLibraryUtils.get(paramContext, paramString.getPackageName());
    }
    if (bool)
    {
      Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
      return false;
    }
    return paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
  }
}
