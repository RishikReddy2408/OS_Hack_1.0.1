package com.google.android.android.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.Api.Client;

public class GoogleApiAvailabilityCache
{
  private final SparseIntArray zaor = new SparseIntArray();
  private GoogleApiAvailabilityLight zaos;
  
  public GoogleApiAvailabilityCache()
  {
    this(GoogleApiAvailability.getInstance());
  }
  
  public GoogleApiAvailabilityCache(GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight)
  {
    Preconditions.checkNotNull(paramGoogleApiAvailabilityLight);
    zaos = paramGoogleApiAvailabilityLight;
  }
  
  public void flush()
  {
    zaor.clear();
  }
  
  public int getClientAvailability(Context paramContext, Api.Client paramClient)
  {
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotNull(paramClient);
    if (!paramClient.requiresGooglePlayServices()) {
      return 0;
    }
    int m = paramClient.getMinApkVersion();
    int i = zaor.get(m, -1);
    int j = i;
    if (i != -1) {
      return i;
    }
    int k = 0;
    for (;;)
    {
      i = j;
      if (k >= zaor.size()) {
        break;
      }
      i = zaor.keyAt(k);
      if ((i > m) && (zaor.get(i) == 0))
      {
        i = 0;
        break;
      }
      k += 1;
    }
    j = i;
    if (i == -1) {
      j = zaos.isGooglePlayServicesAvailable(paramContext, m);
    }
    zaor.put(m, j);
    return j;
  }
}
