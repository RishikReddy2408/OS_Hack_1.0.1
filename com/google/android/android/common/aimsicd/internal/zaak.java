package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.Api.BaseClientBuilder;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.android.common.internal.IAccountAccessor;
import com.google.android.android.common.internal.ResolveAccountResponse;
import com.google.android.android.signin.Client;
import com.google.android.android.signin.internal.Server;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zaak
  implements zabd
{
  private final Context mContext;
  private final com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
  private final Lock zaen;
  private final ClientSettings zaes;
  private final Map<Api<?>, Boolean> zaev;
  private final GoogleApiAvailabilityLight zaex;
  private ConnectionResult zafg;
  private final zabe zafs;
  private int zafv;
  private int zafw = 0;
  private int zafx;
  private final Bundle zafy = new Bundle();
  private final Set<com.google.android.gms.common.api.Api.AnyClientKey> zafz = new HashSet();
  private Client zaga;
  private boolean zagb;
  private boolean zagc;
  private boolean zagd;
  private IAccountAccessor zage;
  private boolean zagf;
  private boolean zagg;
  private ArrayList<Future<?>> zagh = new ArrayList();
  
  public zaak(zabe paramZabe, ClientSettings paramClientSettings, Map paramMap, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, com.google.android.android.common.aimsicd.Api.AbstractClientBuilder paramAbstractClientBuilder, Lock paramLock, Context paramContext)
  {
    zafs = paramZabe;
    zaes = paramClientSettings;
    zaev = paramMap;
    zaex = paramGoogleApiAvailabilityLight;
    zacd = paramAbstractClientBuilder;
    zaen = paramLock;
    mContext = paramContext;
  }
  
  private final boolean changed(ConnectionResult paramConnectionResult)
  {
    return (zagb) && (!paramConnectionResult.hasResolution());
  }
  
  private final void f(ConnectionResult paramConnectionResult)
  {
    zaas();
    putChannel(paramConnectionResult.hasResolution() ^ true);
    zafs.wakeup(paramConnectionResult);
    zafs.zahs.removeAccount(paramConnectionResult);
  }
  
  private static String getType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 1: 
      return "STEP_GETTING_REMOTE_SERVICE";
    }
    return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
  }
  
  private final boolean next(int paramInt)
  {
    if (zafw != paramInt)
    {
      Log.w("GoogleApiClientConnecting", zafs.zaed.zaay());
      Object localObject1 = String.valueOf(this);
      Object localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 23);
      ((StringBuilder)localObject2).append("Unexpected callback in ");
      ((StringBuilder)localObject2).append((String)localObject1);
      Log.w("GoogleApiClientConnecting", ((StringBuilder)localObject2).toString());
      int i = zafx;
      localObject1 = new StringBuilder(33);
      ((StringBuilder)localObject1).append("mRemainingConnections=");
      ((StringBuilder)localObject1).append(i);
      Log.w("GoogleApiClientConnecting", ((StringBuilder)localObject1).toString());
      localObject1 = getType(zafw);
      localObject2 = getType(paramInt);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject1).length() + 70 + String.valueOf(localObject2).length());
      localStringBuilder.append("GoogleApiClient connecting is in step ");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" but received callback for step ");
      localStringBuilder.append((String)localObject2);
      Log.wtf("GoogleApiClientConnecting", localStringBuilder.toString(), new Exception());
      f(new ConnectionResult(8, null));
      return false;
    }
    return true;
  }
  
  private final void putChannel(boolean paramBoolean)
  {
    if (zaga != null)
    {
      if ((zaga.isConnected()) && (paramBoolean)) {
        zaga.zacv();
      }
      zaga.disconnect();
      zage = null;
    }
  }
  
  private final void setHeader(ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean)
  {
    int m = paramSample.getValue().getPriority();
    int k = 0;
    int j;
    if (paramBoolean)
    {
      if (paramConnectionResult.hasResolution()) {}
      while (zaex.getErrorResolutionIntent(paramConnectionResult.getErrorCode()) != null)
      {
        i = 1;
        break;
      }
      int i = 0;
      j = k;
      if (i == 0) {}
    }
    else if (zafg != null)
    {
      j = k;
      if (m >= zafv) {}
    }
    else
    {
      j = 1;
    }
    if (j != 0)
    {
      zafg = paramConnectionResult;
      zafv = m;
    }
    zafs.zaho.put(paramSample.getClientKey(), paramConnectionResult);
  }
  
  private final void setServer(Server paramServer)
  {
    if (!next(0)) {
      return;
    }
    Object localObject = paramServer.getConnectionResult();
    if (((ConnectionResult)localObject).isSuccess())
    {
      localObject = paramServer.zacw();
      paramServer = ((ResolveAccountResponse)localObject).getConnectionResult();
      if (!paramServer.isSuccess())
      {
        localObject = String.valueOf(paramServer);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 48);
        localStringBuilder.append("Sign-in succeeded with resolve account failure: ");
        localStringBuilder.append((String)localObject);
        Log.wtf("GoogleApiClientConnecting", localStringBuilder.toString(), new Exception());
        f(paramServer);
        return;
      }
      zagd = true;
      zage = ((ResolveAccountResponse)localObject).getAccountAccessor();
      zagf = ((ResolveAccountResponse)localObject).getSaveDefaultAccount();
      zagg = ((ResolveAccountResponse)localObject).isFromCrossClientAuth();
      zaap();
      return;
    }
    if (changed((ConnectionResult)localObject))
    {
      zaar();
      zaap();
      return;
    }
    f((ConnectionResult)localObject);
  }
  
  private final boolean zaao()
  {
    zafx -= 1;
    if (zafx > 0) {
      return false;
    }
    if (zafx < 0)
    {
      Log.w("GoogleApiClientConnecting", zafs.zaed.zaay());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
      f(new ConnectionResult(8, null));
      return false;
    }
    if (zafg != null)
    {
      zafs.zahr = zafv;
      f(zafg);
      return false;
    }
    return true;
  }
  
  private final void zaap()
  {
    if (zafx != 0) {
      return;
    }
    if ((!zagc) || (zagd))
    {
      ArrayList localArrayList = new ArrayList();
      zafw = 1;
      zafx = zafs.zagy.size();
      Iterator localIterator = zafs.zagy.keySet().iterator();
      while (localIterator.hasNext())
      {
        com.google.android.android.common.aimsicd.Api.AnyClientKey localAnyClientKey = (com.google.android.android.common.aimsicd.Api.AnyClientKey)localIterator.next();
        if (zafs.zaho.containsKey(localAnyClientKey))
        {
          if (zaao()) {
            zaaq();
          }
        }
        else {
          localArrayList.add((Api.Client)zafs.zagy.get(localAnyClientKey));
        }
      }
      if (!localArrayList.isEmpty()) {
        zagh.add(zabh.zabb().submit(new zaaq(this, localArrayList)));
      }
    }
  }
  
  private final void zaaq()
  {
    zafs.zaba();
    zabh.zabb().execute(new zaal(this));
    if (zaga != null)
    {
      if (zagf) {
        zaga.disconnect(zage, zagg);
      }
      putChannel(false);
    }
    Object localObject = zafs.zaho.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.google.android.android.common.aimsicd.Api.AnyClientKey localAnyClientKey = (com.google.android.android.common.aimsicd.Api.AnyClientKey)((Iterator)localObject).next();
      ((Api.Client)zafs.zagy.get(localAnyClientKey)).disconnect();
    }
    if (zafy.isEmpty()) {
      localObject = null;
    } else {
      localObject = zafy;
    }
    zafs.zahs.execute((Bundle)localObject);
  }
  
  private final void zaar()
  {
    zagc = false;
    zafs.zaed.zagz = Collections.emptySet();
    Iterator localIterator = zafz.iterator();
    while (localIterator.hasNext())
    {
      com.google.android.android.common.aimsicd.Api.AnyClientKey localAnyClientKey = (com.google.android.android.common.aimsicd.Api.AnyClientKey)localIterator.next();
      if (!zafs.zaho.containsKey(localAnyClientKey)) {
        zafs.zaho.put(localAnyClientKey, new ConnectionResult(17, null));
      }
    }
  }
  
  private final void zaas()
  {
    ArrayList localArrayList = (ArrayList)zagh;
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = localArrayList.get(i);
      i += 1;
      ((Future)localObject).cancel(true);
    }
    zagh.clear();
  }
  
  private final Set zaat()
  {
    if (zaes == null) {
      return Collections.emptySet();
    }
    HashSet localHashSet = new HashSet(zaes.getRequiredScopes());
    Map localMap = zaes.getOptionalApiSettings();
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Sample localSample = (Sample)localIterator.next();
      if (!zafs.zaho.containsKey(localSample.getClientKey())) {
        localHashSet.addAll(getmScopes);
      }
    }
    return localHashSet;
  }
  
  public final void addHeaders(ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean)
  {
    if (!next(1)) {
      return;
    }
    setHeader(paramConnectionResult, paramSample, paramBoolean);
    if (zaao()) {
      zaaq();
    }
  }
  
  public final void begin()
  {
    zafs.zaho.clear();
    zagc = false;
    zafg = null;
    zafw = 0;
    zagb = true;
    zagd = false;
    zagf = false;
    HashMap localHashMap = new HashMap();
    Object localObject = zaev.keySet().iterator();
    int i = 0;
    while (((Iterator)localObject).hasNext())
    {
      Sample localSample = (Sample)((Iterator)localObject).next();
      Api.Client localClient = (Api.Client)zafs.zagy.get(localSample.getClientKey());
      int j;
      if (localSample.getValue().getPriority() == 1) {
        j = 1;
      } else {
        j = 0;
      }
      i |= j;
      boolean bool = ((Boolean)zaev.get(localSample)).booleanValue();
      if (localClient.requiresSignIn())
      {
        zagc = true;
        if (bool) {
          zafz.add(localSample.getClientKey());
        } else {
          zagb = false;
        }
      }
      localHashMap.put(localClient, new zaam(this, localSample, bool));
    }
    if (i != 0) {
      zagc = false;
    }
    if (zagc)
    {
      zaes.setClientSessionId(Integer.valueOf(System.identityHashCode(zafs.zaed)));
      localObject = new zaat(this, null);
      zaga = ((Client)zacd.buildClient(mContext, zafs.zaed.getLooper(), zaes, zaes.getSignInOptions(), (GoogleApiClient.ConnectionCallbacks)localObject, (GoogleApiClient.OnConnectionFailedListener)localObject));
    }
    zafx = zafs.zagy.size();
    zagh.add(zabh.zabb().submit(new zaan(this, localHashMap)));
  }
  
  public final void connect() {}
  
  public final boolean disconnect()
  {
    zaas();
    putChannel(true);
    zafs.wakeup(null);
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
  
  public final void onConnected(Bundle paramBundle)
  {
    if (!next(1)) {
      return;
    }
    if (paramBundle != null) {
      zafy.putAll(paramBundle);
    }
    if (zaao()) {
      zaaq();
    }
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    f(new ConnectionResult(8, null));
  }
}
