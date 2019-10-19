package com.google.android.android.common.aimsicd.internal;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.android.common.aimsicd.AvailabilityException;
import com.google.android.android.common.aimsicd.GoogleApi;
import com.google.android.android.tasks.Task;
import com.google.android.gms.common.api.internal.zai;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class LogItem
{
  private final ArrayMap<zai<?>, com.google.android.gms.common.ConnectionResult> zaay = new ArrayMap();
  private final ArrayMap<zai<?>, String> zada = new ArrayMap();
  private final com.google.android.gms.tasks.TaskCompletionSource<Map<zai<?>, String>> zadb = new com.google.android.android.tasks.TaskCompletionSource();
  private int zadc;
  private boolean zadd = false;
  
  public LogItem(Iterable paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      GoogleApi localGoogleApi = (GoogleApi)paramIterable.next();
      zaay.put(localGoogleApi.get(), null);
    }
    zadc = zaay.keySet().size();
  }
  
  public final Task getTask()
  {
    return zadb.getTask();
  }
  
  public final Set getTypes()
  {
    return zaay.keySet();
  }
  
  public final void setTimestamp(Msg paramMsg, com.google.android.android.common.ConnectionResult paramConnectionResult, String paramString)
  {
    zaay.put(paramMsg, paramConnectionResult);
    zada.put(paramMsg, paramString);
    zadc -= 1;
    if (!paramConnectionResult.isSuccess()) {
      zadd = true;
    }
    if (zadc == 0)
    {
      if (zadd)
      {
        paramMsg = new AvailabilityException(zaay);
        zadb.setException(paramMsg);
        return;
      }
      zadb.setResult(zada);
    }
  }
}
