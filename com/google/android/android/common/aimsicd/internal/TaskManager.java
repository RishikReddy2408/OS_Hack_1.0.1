package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.Api.AbstractClientBuilder;
import com.google.android.android.common.aimsicd.Api.BaseClientBuilder;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.GoogleApi;
import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.android.common.util.concurrent.HandlerExecutor;
import com.google.android.android.tasks.Task;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.api.internal.zaw;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class TaskManager
  implements zabs
{
  private final Looper zabj;
  private final GoogleApiManager zabm;
  private final Lock zaen;
  private final ClientSettings zaes;
  private final Map<com.google.android.gms.common.api.Api.AnyClientKey<?>, zaw<?>> zaet = new HashMap();
  private final Map<com.google.android.gms.common.api.Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
  private final Map<Api<?>, Boolean> zaev;
  private final zaaw zaew;
  private final GoogleApiAvailabilityLight zaex;
  private final Condition zaey;
  private final boolean zaez;
  private final boolean zafa;
  private final Queue<com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl<?, ?>> zafb = new LinkedList();
  @GuardedBy("mLock")
  private boolean zafc;
  @GuardedBy("mLock")
  private Map<zai<?>, com.google.android.gms.common.ConnectionResult> zafd;
  @GuardedBy("mLock")
  private Map<zai<?>, com.google.android.gms.common.ConnectionResult> zafe;
  @GuardedBy("mLock")
  private zaaa zaff;
  @GuardedBy("mLock")
  private com.google.android.android.common.ConnectionResult zafg;
  
  public TaskManager(Context paramContext, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map paramMap1, ClientSettings paramClientSettings, Map paramMap2, Api.AbstractClientBuilder paramAbstractClientBuilder, ArrayList paramArrayList, zaaw paramZaaw, boolean paramBoolean)
  {
    zaen = paramLock;
    zabj = paramLooper;
    zaey = paramLock.newCondition();
    zaex = paramGoogleApiAvailabilityLight;
    zaew = paramZaaw;
    zaev = paramMap2;
    zaes = paramClientSettings;
    zaez = paramBoolean;
    paramLock = new HashMap();
    paramGoogleApiAvailabilityLight = paramMap2.keySet().iterator();
    while (paramGoogleApiAvailabilityLight.hasNext())
    {
      paramMap2 = (Sample)paramGoogleApiAvailabilityLight.next();
      paramLock.put(paramMap2.getClientKey(), paramMap2);
    }
    paramGoogleApiAvailabilityLight = new HashMap();
    paramMap2 = (ArrayList)paramArrayList;
    int j = paramMap2.size();
    int i = 0;
    while (i < j)
    {
      paramArrayList = paramMap2.get(i);
      i += 1;
      paramArrayList = (Logger)paramArrayList;
      paramGoogleApiAvailabilityLight.put(mApi, paramArrayList);
    }
    paramMap1 = paramMap1.entrySet().iterator();
    paramBoolean = true;
    int k = 0;
    j = 1;
    i = 0;
    while (paramMap1.hasNext())
    {
      paramMap2 = (Map.Entry)paramMap1.next();
      paramZaaw = (Sample)paramLock.get(paramMap2.getKey());
      paramArrayList = (Api.Client)paramMap2.getValue();
      if (paramArrayList.requiresGooglePlayServices())
      {
        if (!((Boolean)zaev.get(paramZaaw)).booleanValue()) {
          i = 1;
        }
        k = 1;
      }
      else
      {
        j = 0;
      }
      paramZaaw = new Errors(paramContext, paramZaaw, paramLooper, paramArrayList, (Logger)paramGoogleApiAvailabilityLight.get(paramZaaw), paramClientSettings, paramAbstractClientBuilder);
      zaet.put((com.google.android.android.common.aimsicd.Api.AnyClientKey)paramMap2.getKey(), paramZaaw);
      if (paramArrayList.requiresSignIn()) {
        zaeu.put((com.google.android.android.common.aimsicd.Api.AnyClientKey)paramMap2.getKey(), paramZaaw);
      }
    }
    if ((k == 0) || (j != 0) || (i != 0)) {
      paramBoolean = false;
    }
    zafa = paramBoolean;
    zabm = GoogleApiManager.zabc();
  }
  
  private final boolean addTask(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    com.google.android.android.common.aimsicd.Api.AnyClientKey localAnyClientKey = paramApiMethodImpl.getClientKey();
    com.google.android.android.common.ConnectionResult localConnectionResult = getTask(localAnyClientKey);
    if ((localConnectionResult != null) && (localConnectionResult.getErrorCode() == 4))
    {
      paramApiMethodImpl.setFailedResult(new Status(4, null, zabm.getIntent(((Errors)zaet.get(localAnyClientKey)).get(), System.identityHashCode(zaew))));
      return true;
    }
    return false;
  }
  
  private final boolean count(Errors paramErrors, com.google.android.android.common.ConnectionResult paramConnectionResult)
  {
    return (!paramConnectionResult.isSuccess()) && (!paramConnectionResult.hasResolution()) && (((Boolean)zaev.get(paramErrors.getApi())).booleanValue()) && (paramErrors.zaab().requiresGooglePlayServices()) && (zaex.isUserResolvableError(paramConnectionResult.getErrorCode()));
  }
  
  private final com.google.android.android.common.ConnectionResult getTask(com.google.android.android.common.aimsicd.Api.AnyClientKey paramAnyClientKey)
  {
    zaen.lock();
    try
    {
      paramAnyClientKey = (Errors)zaet.get(paramAnyClientKey);
      Map localMap = zafd;
      if ((localMap != null) && (paramAnyClientKey != null))
      {
        paramAnyClientKey = (com.google.android.android.common.ConnectionResult)zafd.get(paramAnyClientKey.get());
        zaen.unlock();
        return paramAnyClientKey;
      }
      zaen.unlock();
      return null;
    }
    catch (Throwable paramAnyClientKey)
    {
      zaen.unlock();
      throw paramAnyClientKey;
    }
  }
  
  private final boolean zaac()
  {
    zaen.lock();
    try
    {
      boolean bool = zafc;
      if (bool)
      {
        bool = zaez;
        if (bool)
        {
          Iterator localIterator = zaeu.keySet().iterator();
          do
          {
            bool = localIterator.hasNext();
            if (!bool) {
              break label94;
            }
            com.google.android.android.common.ConnectionResult localConnectionResult = getTask((com.google.android.android.common.aimsicd.Api.AnyClientKey)localIterator.next());
            if (localConnectionResult == null) {
              break;
            }
            bool = localConnectionResult.isSuccess();
          } while (bool);
          zaen.unlock();
          return false;
          label94:
          zaen.unlock();
          return true;
        }
      }
      zaen.unlock();
      return false;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  private final void zaad()
  {
    if (zaes == null)
    {
      zaew.zagz = Collections.emptySet();
      return;
    }
    HashSet localHashSet = new HashSet(zaes.getRequiredScopes());
    Map localMap = zaes.getOptionalApiSettings();
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Sample localSample = (Sample)localIterator.next();
      com.google.android.android.common.ConnectionResult localConnectionResult = getConnectionResult(localSample);
      if ((localConnectionResult != null) && (localConnectionResult.isSuccess())) {
        localHashSet.addAll(getmScopes);
      }
    }
    zaew.zagz = localHashSet;
  }
  
  private final void zaae()
  {
    while (!zafb.isEmpty()) {
      execute((BaseImplementation.ApiMethodImpl)zafb.remove());
    }
    zaew.execute(null);
  }
  
  private final com.google.android.android.common.ConnectionResult zaaf()
  {
    Iterator localIterator = zaet.values().iterator();
    Object localObject2 = null;
    Object localObject1 = null;
    int j = 0;
    int i = 0;
    while (localIterator.hasNext())
    {
      Object localObject3 = (Errors)localIterator.next();
      Sample localSample = ((GoogleApi)localObject3).getApi();
      localObject3 = ((GoogleApi)localObject3).get();
      localObject3 = (com.google.android.android.common.ConnectionResult)zafd.get(localObject3);
      if ((!((com.google.android.android.common.ConnectionResult)localObject3).isSuccess()) && ((!((Boolean)zaev.get(localSample)).booleanValue()) || (((com.google.android.android.common.ConnectionResult)localObject3).hasResolution()) || (zaex.isUserResolvableError(((com.google.android.android.common.ConnectionResult)localObject3).getErrorCode()))))
      {
        int k;
        if ((((com.google.android.android.common.ConnectionResult)localObject3).getErrorCode() == 4) && (zaez))
        {
          k = localSample.getValue().getPriority();
          if ((localObject1 == null) || (i > k))
          {
            localObject1 = localObject3;
            i = k;
          }
        }
        else
        {
          k = localSample.getValue().getPriority();
          if ((localObject2 == null) || (j > k))
          {
            localObject2 = localObject3;
            j = k;
          }
        }
      }
    }
    if ((localObject2 != null) && (localObject1 != null) && (j > i)) {
      return localObject1;
    }
    return localObject2;
  }
  
  public final com.google.android.android.common.ConnectionResult blockingConnect()
  {
    connect();
    for (;;)
    {
      if (!isConnecting()) {
        break label42;
      }
      Condition localCondition = zaey;
      try
      {
        localCondition.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
    Thread.currentThread().interrupt();
    return new com.google.android.android.common.ConnectionResult(15, null);
    label42:
    if (isConnected()) {
      return com.google.android.android.common.ConnectionResult.RESULT_SUCCESS;
    }
    if (zafg != null) {
      return zafg;
    }
    return new com.google.android.android.common.ConnectionResult(13, null);
  }
  
  public final com.google.android.android.common.ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    connect();
    for (paramLong = paramTimeUnit.toNanos(paramLong);; paramLong = paramTimeUnit.awaitNanos(paramLong))
    {
      if ((!isConnecting()) || (paramLong <= 0L)) {}
      try
      {
        disconnect();
        paramTimeUnit = new com.google.android.android.common.ConnectionResult(14, null);
        return paramTimeUnit;
      }
      catch (InterruptedException paramTimeUnit)
      {
        for (;;) {}
      }
      paramTimeUnit = zaey;
    }
    Thread.currentThread().interrupt();
    return new com.google.android.android.common.ConnectionResult(15, null);
    if (isConnected()) {
      return com.google.android.android.common.ConnectionResult.RESULT_SUCCESS;
    }
    if (zafg != null) {
      return zafg;
    }
    return new com.google.android.android.common.ConnectionResult(13, null);
  }
  
  public final void connect()
  {
    zaen.lock();
    try
    {
      boolean bool = zafc;
      if (bool)
      {
        zaen.unlock();
        return;
      }
      zafc = true;
      zafd = null;
      zafe = null;
      zaff = null;
      zafg = null;
      zabm.close();
      zabm.call(zaet.values()).addOnCompleteListener(new HandlerExecutor(zabj), new LoginActivity.1(this, null));
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final void disconnect()
  {
    zaen.lock();
    try
    {
      zafc = false;
      zafd = null;
      zafe = null;
      Object localObject = zaff;
      if (localObject != null)
      {
        zaff.cancel();
        zaff = null;
      }
      zafg = null;
      for (;;)
      {
        boolean bool = zafb.isEmpty();
        if (bool) {
          break;
        }
        localObject = (BaseImplementation.ApiMethodImpl)zafb.remove();
        ((BasePendingResult)localObject).remove(null);
        ((PendingResult)localObject).cancel();
      }
      zaey.signalAll();
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    if ((zaez) && (addTask(paramApiMethodImpl))) {
      return paramApiMethodImpl;
    }
    if (!isConnected())
    {
      zafb.add(paramApiMethodImpl);
      return paramApiMethodImpl;
    }
    zaew.zahe.close(paramApiMethodImpl);
    return ((Errors)zaet.get(paramApiMethodImpl.getClientKey())).doRead(paramApiMethodImpl);
  }
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    com.google.android.android.common.aimsicd.Api.AnyClientKey localAnyClientKey = paramApiMethodImpl.getClientKey();
    if ((zaez) && (addTask(paramApiMethodImpl))) {
      return paramApiMethodImpl;
    }
    zaew.zahe.close(paramApiMethodImpl);
    return ((Errors)zaet.get(localAnyClientKey)).doWrite(paramApiMethodImpl);
  }
  
  public final com.google.android.android.common.ConnectionResult getConnectionResult(Sample paramSample)
  {
    return getTask(paramSample.getClientKey());
  }
  
  public final boolean isConnected()
  {
    zaen.lock();
    try
    {
      Object localObject = zafd;
      if (localObject != null)
      {
        localObject = zafg;
        if (localObject == null)
        {
          bool = true;
          break label34;
        }
      }
      boolean bool = false;
      label34:
      zaen.unlock();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final boolean isConnecting()
  {
    zaen.lock();
    try
    {
      Map localMap = zafd;
      if (localMap == null)
      {
        bool = zafc;
        if (bool)
        {
          bool = true;
          break label34;
        }
      }
      boolean bool = false;
      label34:
      zaen.unlock();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener)
  {
    zaen.lock();
    try
    {
      boolean bool = zafc;
      if (bool)
      {
        bool = zaac();
        if (!bool)
        {
          zabm.close();
          zaff = new zaaa(this, paramSignInConnectionListener);
          zabm.call(zaeu.values()).addOnCompleteListener(new HandlerExecutor(zabj), zaff);
          zaen.unlock();
          return true;
        }
      }
      zaen.unlock();
      return false;
    }
    catch (Throwable paramSignInConnectionListener)
    {
      zaen.unlock();
      throw paramSignInConnectionListener;
    }
  }
  
  public final void maybeSignOut()
  {
    zaen.lock();
    try
    {
      zabm.maybeSignOut();
      Object localObject = zaff;
      if (localObject != null)
      {
        zaff.cancel();
        zaff = null;
      }
      localObject = zafe;
      if (localObject == null) {
        zafe = new ArrayMap(zaeu.size());
      }
      localObject = new com.google.android.android.common.ConnectionResult(4);
      Iterator localIterator = zaeu.values().iterator();
      for (;;)
      {
        boolean bool = localIterator.hasNext();
        if (!bool) {
          break;
        }
        Errors localErrors = (Errors)localIterator.next();
        zafe.put(localErrors.get(), localObject);
      }
      localObject = zafd;
      if (localObject != null) {
        zafd.putAll(zafe);
      }
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final void removeAccount() {}
}
