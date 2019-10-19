package com.google.android.android.common.aimsicd.internal;

import android.os.Bundle;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.Sample;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

public final class zaav
  implements zabd
{
  private final zabe zafs;
  
  public zaav(zabe paramZabe)
  {
    zafs = paramZabe;
  }
  
  public final void addHeaders(ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean) {}
  
  public final void begin()
  {
    Iterator localIterator = zafs.zagy.values().iterator();
    while (localIterator.hasNext()) {
      ((Api.Client)localIterator.next()).disconnect();
    }
    zafs.zaed.zagz = Collections.emptySet();
  }
  
  public final void connect()
  {
    zafs.zaaz();
  }
  
  public final boolean disconnect()
  {
    return true;
  }
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    zafs.zaed.zafb.add(paramApiMethodImpl);
    return paramApiMethodImpl;
  }
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt) {}
}
