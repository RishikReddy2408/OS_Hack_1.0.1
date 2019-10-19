package com.google.android.android.stats;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.providers.PooledExecutorsProvider;
import com.google.android.android.common.providers.PooledExecutorsProvider.PooledExecutorFactory;
import com.google.android.android.common.stats.StatsUtils;
import com.google.android.android.common.stats.WakeLockTracker;
import com.google.android.android.common.util.Strings;
import com.google.android.android.common.util.WorkSourceUtil;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.ThreadSafe;

@KeepForSdk
@ShowFirstParty
@ThreadSafe
public class WakeLock
{
  private static ScheduledExecutorService scheduler;
  private static volatile zza wakeLocks = new Transport();
  private final Context context;
  private final Set<Future<?>> done = Collections.synchronizedSet(new HashSet());
  private AtomicInteger id = new AtomicInteger(0);
  private final Object lock = this;
  private final int log;
  private final Map<String, Integer[]> pending = new HashMap();
  private int startTime;
  private WorkSource state;
  private final String tag;
  private final String text;
  private boolean this$0 = true;
  private final String timeout;
  private final PowerManager.WakeLock wakeLock;
  
  public WakeLock(Context paramContext, int paramInt, String paramString)
  {
    this(paramContext, paramInt, paramString, null, str);
  }
  
  private WakeLock(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this(paramContext, paramInt, paramString1, null, paramString3, null);
  }
  
  private WakeLock(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Preconditions.checkNotNull(paramContext, "WakeLock: context must not be null");
    Preconditions.checkNotEmpty(paramString1, "WakeLock: wakeLockName must not be empty");
    log = paramInt;
    tag = null;
    text = null;
    context = paramContext.getApplicationContext();
    if (!"com.google.android.gms".equals(paramContext.getPackageName()))
    {
      paramString2 = String.valueOf("*gcore*:");
      paramString4 = String.valueOf(paramString1);
      if (paramString4.length() != 0) {
        paramString2 = paramString2.concat(paramString4);
      } else {
        paramString2 = new String(paramString2);
      }
      timeout = paramString2;
    }
    else
    {
      timeout = paramString1;
    }
    wakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(paramInt, paramString1);
    if (WorkSourceUtil.hasWorkSourcePermission(paramContext))
    {
      paramString1 = paramString3;
      if (Strings.isEmptyOrWhitespace(paramString3)) {
        paramString1 = paramContext.getPackageName();
      }
      state = WorkSourceUtil.fromPackage(paramContext, paramString1);
      paramContext = state;
      if ((paramContext != null) && (WorkSourceUtil.hasWorkSourcePermission(context)))
      {
        if (state != null) {
          state.add(paramContext);
        } else {
          state = paramContext;
        }
        paramContext = state;
        paramString1 = wakeLock;
        try
        {
          paramString1.setWorkSource(paramContext);
        }
        catch (IllegalArgumentException|ArrayIndexOutOfBoundsException paramContext)
        {
          Log.wtf("WakeLock", paramContext.toString());
        }
      }
    }
    if (scheduler == null) {
      scheduler = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
    }
  }
  
  private final List getIds()
  {
    return WorkSourceUtil.getNames(state);
  }
  
  private final String getTag(String paramString)
  {
    if (this$0)
    {
      if (!TextUtils.isEmpty(paramString)) {
        return paramString;
      }
      return tag;
    }
    return tag;
  }
  
  private final void release(int paramInt)
  {
    if (wakeLock.isHeld())
    {
      try
      {
        wakeLock.release();
      }
      catch (RuntimeException localRuntimeException)
      {
        if (!localRuntimeException.getClass().equals(RuntimeException.class)) {
          break label61;
        }
      }
      Log.e("WakeLock", String.valueOf(timeout).concat(" was already released!"), localRuntimeException);
      wakeLock.isHeld();
      return;
      label61:
      throw localRuntimeException;
    }
  }
  
  public void acquire(long paramLong)
  {
    id.incrementAndGet();
    String str = getTag(null);
    Object localObject = lock;
    for (;;)
    {
      int i;
      try
      {
        boolean bool = pending.isEmpty();
        i = 0;
        if (((!bool) || (startTime > 0)) && (!wakeLock.isHeld()))
        {
          pending.clear();
          startTime = 0;
        }
        if (this$0)
        {
          Integer[] arrayOfInteger = (Integer[])pending.get(str);
          if (arrayOfInteger == null)
          {
            pending.put(str, new Integer[] { Integer.valueOf(1) });
            i = 1;
            break label256;
          }
          arrayOfInteger[0] = Integer.valueOf(arrayOfInteger[0].intValue() + 1);
          break label256;
        }
        if ((!this$0) && (startTime == 0))
        {
          WakeLockTracker.getInstance().registerEvent(context, StatsUtils.getEventKey(wakeLock, str), 7, timeout, str, null, log, getIds(), paramLong);
          startTime += 1;
        }
        wakeLock.acquire();
        if (paramLong > 0L)
        {
          scheduler.schedule(new MonthByWeekFragment.2(this), paramLong, TimeUnit.MILLISECONDS);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      return;
      label256:
      if (i != 0) {}
    }
  }
  
  public boolean isHeld()
  {
    return wakeLock.isHeld();
  }
  
  public void release()
  {
    if (id.decrementAndGet() < 0) {
      Log.e("WakeLock", String.valueOf(timeout).concat(" release without a matched acquire!"));
    }
    String str = getTag(null);
    Object localObject = lock;
    for (;;)
    {
      try
      {
        if (this$0)
        {
          Integer[] arrayOfInteger = (Integer[])pending.get(str);
          if (arrayOfInteger != null)
          {
            if (arrayOfInteger[0].intValue() == 1)
            {
              pending.remove(str);
              i = 1;
              break label192;
            }
            arrayOfInteger[0] = Integer.valueOf(arrayOfInteger[0].intValue() - 1);
          }
        }
        else
        {
          if ((!this$0) && (startTime == 1))
          {
            WakeLockTracker.getInstance().registerEvent(context, StatsUtils.getEventKey(wakeLock, str), 8, timeout, str, null, log, getIds());
            startTime -= 1;
          }
          release(0);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      int i = 0;
      label192:
      if (i != 0) {}
    }
  }
  
  public void setReferenceCounted(boolean paramBoolean)
  {
    wakeLock.setReferenceCounted(paramBoolean);
    this$0 = paramBoolean;
  }
  
  public abstract interface zza {}
}
