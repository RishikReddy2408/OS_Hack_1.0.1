package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver
  extends BroadcastReceiver
{
  private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
  private static int mNextId = 1;
  private static final SparseArray<PowerManager.WakeLock> sActiveWakeLocks = new SparseArray();
  
  public WakefulBroadcastReceiver() {}
  
  public static boolean completeWakefulIntent(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("android.support.content.wakelockid", 0);
    if (i == 0) {
      return false;
    }
    paramIntent = sActiveWakeLocks;
    try
    {
      Object localObject = (PowerManager.WakeLock)sActiveWakeLocks.get(i);
      if (localObject != null)
      {
        ((PowerManager.WakeLock)localObject).release();
        sActiveWakeLocks.remove(i);
        return true;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No active wake lock id #");
      ((StringBuilder)localObject).append(i);
      Log.w("WakefulBroadcastReceiv.", ((StringBuilder)localObject).toString());
      return true;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static ComponentName startWakefulService(Context paramContext, Intent paramIntent)
  {
    SparseArray localSparseArray = sActiveWakeLocks;
    try
    {
      int i = mNextId;
      mNextId += 1;
      if (mNextId <= 0) {
        mNextId = 1;
      }
      paramIntent.putExtra("android.support.content.wakelockid", i);
      paramIntent = paramContext.startService(paramIntent);
      if (paramIntent == null) {
        return null;
      }
      paramContext = (PowerManager)paramContext.getSystemService("power");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("wake:");
      localStringBuilder.append(paramIntent.flattenToShortString());
      paramContext = paramContext.newWakeLock(1, localStringBuilder.toString());
      paramContext.setReferenceCounted(false);
      paramContext.acquire(60000L);
      sActiveWakeLocks.put(i, paramContext);
      return paramIntent;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
}
