package com.google.android.android.common.aimsicd.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Api.AnyClient;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.SimpleClientAdapter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zaah
  implements zabd
{
  private final zabe zafs;
  private boolean zaft = false;
  
  public zaah(zabe paramZabe)
  {
    zafs = paramZabe;
  }
  
  public final void addHeaders(ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean) {}
  
  public final void begin() {}
  
  public final void connect()
  {
    if (zaft)
    {
      zaft = false;
      zafs.notify(new zaaj(this, this));
    }
  }
  
  public final boolean disconnect()
  {
    if (zaft) {
      return false;
    }
    if (zafs.zaed.zaax())
    {
      zaft = true;
      Iterator localIterator = zafs.zaed.zahd.iterator();
      while (localIterator.hasNext()) {
        ((zacm)localIterator.next()).zabv();
      }
      return false;
    }
    zafs.wakeup(null);
    return true;
  }
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    return execute(paramApiMethodImpl);
  }
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    Object localObject1 = zafs.zaed.zahe;
    try
    {
      ((zacp)localObject1).close(paramApiMethodImpl);
      Object localObject2 = zafs.zaed;
      localObject1 = paramApiMethodImpl.getClientKey();
      localObject2 = zagy;
      localObject1 = ((Map)localObject2).get(localObject1);
      localObject2 = (Api.Client)localObject1;
      Preconditions.checkNotNull(localObject2, "Appropriate Api was not requested.");
      localObject1 = (Api.Client)localObject2;
      boolean bool = ((Api.Client)localObject1).isConnected();
      if (!bool)
      {
        localObject1 = zafs.zaho;
        bool = ((Map)localObject1).containsKey(paramApiMethodImpl.getClientKey());
        if (bool)
        {
          paramApiMethodImpl.setFailedResult(new Status(17));
          return paramApiMethodImpl;
        }
      }
      localObject1 = localObject2;
      if ((localObject2 instanceof SimpleClientAdapter))
      {
        localObject1 = (SimpleClientAdapter)localObject2;
        localObject1 = ((SimpleClientAdapter)localObject1).getClient();
      }
      paramApiMethodImpl.mkdir((Api.AnyClient)localObject1);
      return paramApiMethodImpl;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      for (;;) {}
    }
    zafs.notify(new zaai(this, this));
    return paramApiMethodImpl;
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt)
  {
    zafs.wakeup(null);
    zafs.zahs.quit(paramInt, zaft);
  }
  
  final void zaam()
  {
    if (zaft)
    {
      zaft = false;
      zafs.zaed.zahe.release();
      disconnect();
    }
  }
}
