package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.android.common.internal.GoogleApiAvailabilityCache;
import com.google.android.android.signin.Client;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zaan
  extends zaau
{
  private final Map<com.google.android.gms.common.api.Api.Client, com.google.android.gms.common.api.internal.zaam> zagk;
  
  public zaan(zaak paramZaak, Map paramMap)
  {
    super(paramZaak, null);
    zagk = paramMap;
  }
  
  public final void zaan()
  {
    Object localObject1 = new GoogleApiAvailabilityCache(zaak.getBasePath(zagi));
    Object localObject2 = new ArrayList();
    Object localObject3 = new ArrayList();
    Object localObject4 = zagk.keySet().iterator();
    while (((Iterator)localObject4).hasNext())
    {
      com.google.android.android.common.aimsicd.Api.Client localClient = (com.google.android.android.common.aimsicd.Api.Client)((Iterator)localObject4).next();
      if ((localClient.requiresGooglePlayServices()) && (!zaam.isInheritable((zaam)zagk.get(localClient)))) {
        ((List)localObject2).add(localClient);
      } else {
        ((List)localObject3).add(localClient);
      }
    }
    int i = -1;
    boolean bool = ((List)localObject2).isEmpty();
    int j = 0;
    int k = 0;
    int n;
    int m;
    if (bool)
    {
      localObject2 = (ArrayList)localObject3;
      n = ((ArrayList)localObject2).size();
      do
      {
        if (k >= n) {
          break;
        }
        localObject3 = ((ArrayList)localObject2).get(k);
        k += 1;
        localObject3 = (com.google.android.android.common.aimsicd.Api.Client)localObject3;
        m = ((GoogleApiAvailabilityCache)localObject1).getClientAvailability(zaak.unwrap(zagi), (com.google.android.android.common.aimsicd.Api.Client)localObject3);
        j = m;
        i = j;
      } while (m != 0);
      i = j;
    }
    else
    {
      localObject2 = (ArrayList)localObject2;
      n = ((ArrayList)localObject2).size();
      k = j;
      while (k < n)
      {
        localObject3 = ((ArrayList)localObject2).get(k);
        k += 1;
        localObject3 = (com.google.android.android.common.aimsicd.Api.Client)localObject3;
        m = ((GoogleApiAvailabilityCache)localObject1).getClientAvailability(zaak.unwrap(zagi), (com.google.android.android.common.aimsicd.Api.Client)localObject3);
        j = m;
        i = j;
        if (m != 0) {
          i = j;
        }
      }
    }
    if (i != 0)
    {
      localObject1 = new ConnectionResult(i, null);
      zaak.setContentTitle(zagi).notify(new zaao(this, zagi, (ConnectionResult)localObject1));
      return;
    }
    if (zaak.get0(zagi)) {
      zaak.fromId(zagi).connect();
    }
    localObject2 = zagk.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (com.google.android.android.common.aimsicd.Api.Client)((Iterator)localObject2).next();
      localObject4 = (BaseGmsClient.ConnectionProgressReportCallbacks)zagk.get(localObject3);
      if ((((com.google.android.android.common.aimsicd.Api.Client)localObject3).requiresGooglePlayServices()) && (((GoogleApiAvailabilityCache)localObject1).getClientAvailability(zaak.unwrap(zagi), (com.google.android.android.common.aimsicd.Api.Client)localObject3) != 0)) {
        zaak.setContentTitle(zagi).notify(new zaap(this, zagi, (BaseGmsClient.ConnectionProgressReportCallbacks)localObject4));
      } else {
        ((com.google.android.android.common.aimsicd.Api.Client)localObject3).connect((BaseGmsClient.ConnectionProgressReportCallbacks)localObject4);
      }
    }
  }
}
