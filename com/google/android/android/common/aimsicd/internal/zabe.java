package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zabe
  implements zabs, BlockingQueue
{
  private final Context mContext;
  private final com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
  final zaaw zaed;
  private final Lock zaen;
  private final ClientSettings zaes;
  private final Map<Api<?>, Boolean> zaev;
  private final GoogleApiAvailabilityLight zaex;
  final Map<Api.AnyClientKey<?>, com.google.android.gms.common.api.Api.Client> zagy;
  private final Condition zahm;
  private final zabg zahn;
  final Map<Api.AnyClientKey<?>, com.google.android.gms.common.ConnectionResult> zaho = new HashMap();
  private volatile zabd zahp;
  private com.google.android.android.common.ConnectionResult zahq = null;
  int zahr;
  final zabt zahs;
  
  public zabe(Context paramContext, zaaw paramZaaw, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map paramMap1, ClientSettings paramClientSettings, Map paramMap2, com.google.android.android.common.aimsicd.Api.AbstractClientBuilder paramAbstractClientBuilder, ArrayList paramArrayList, zabt paramZabt)
  {
    mContext = paramContext;
    zaen = paramLock;
    zaex = paramGoogleApiAvailabilityLight;
    zagy = paramMap1;
    zaes = paramClientSettings;
    zaev = paramMap2;
    zacd = paramAbstractClientBuilder;
    zaed = paramZaaw;
    zahs = paramZabt;
    paramContext = (ArrayList)paramArrayList;
    int j = paramContext.size();
    int i = 0;
    while (i < j)
    {
      paramZaaw = paramContext.get(i);
      i += 1;
      ((Logger)paramZaaw).v(this);
    }
    zahn = new zabg(this, paramLooper);
    zahm = paramLock.newCondition();
    zahp = new zaav(this);
  }
  
  public final com.google.android.android.common.ConnectionResult blockingConnect()
  {
    connect();
    for (;;)
    {
      if (!isConnecting()) {
        break label42;
      }
      Condition localCondition = zahm;
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
    if (zahq != null) {
      return zahq;
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
      paramTimeUnit = zahm;
    }
    Thread.currentThread().interrupt();
    return new com.google.android.android.common.ConnectionResult(15, null);
    if (isConnected()) {
      return com.google.android.android.common.ConnectionResult.RESULT_SUCCESS;
    }
    if (zahq != null) {
      return zahq;
    }
    return new com.google.android.android.common.ConnectionResult(13, null);
  }
  
  public final void connect()
  {
    zahp.connect();
  }
  
  public final void disconnect()
  {
    if (zahp.disconnect()) {
      zaho.clear();
    }
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    String str = String.valueOf(paramString).concat("  ");
    paramPrintWriter.append(paramString).append("mState=").println(zahp);
    Iterator localIterator = zaev.keySet().iterator();
    while (localIterator.hasNext())
    {
      Sample localSample = (Sample)localIterator.next();
      paramPrintWriter.append(paramString).append(localSample.getName()).println(":");
      ((com.google.android.android.common.aimsicd.Api.Client)zagy.get(localSample.getClientKey())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    paramApiMethodImpl.put();
    return zahp.enqueue(paramApiMethodImpl);
  }
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    paramApiMethodImpl.put();
    return zahp.execute(paramApiMethodImpl);
  }
  
  public final com.google.android.android.common.ConnectionResult getConnectionResult(Sample paramSample)
  {
    paramSample = paramSample.getClientKey();
    if (zagy.containsKey(paramSample))
    {
      if (((com.google.android.android.common.aimsicd.Api.Client)zagy.get(paramSample)).isConnected()) {
        return com.google.android.android.common.ConnectionResult.RESULT_SUCCESS;
      }
      if (zaho.containsKey(paramSample)) {
        return (com.google.android.android.common.ConnectionResult)zaho.get(paramSample);
      }
    }
    return null;
  }
  
  public final boolean isConnected()
  {
    return zahp instanceof zaah;
  }
  
  public final boolean isConnecting()
  {
    return zahp instanceof zaak;
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener)
  {
    return false;
  }
  
  public final void maybeSignOut() {}
  
  final void notify(zabf paramZabf)
  {
    paramZabf = zahn.obtainMessage(1, paramZabf);
    zahn.sendMessage(paramZabf);
  }
  
  public final void onConnected(Bundle paramBundle)
  {
    zaen.lock();
    try
    {
      zahp.onConnected(paramBundle);
      zaen.unlock();
      return;
    }
    catch (Throwable paramBundle)
    {
      zaen.unlock();
      throw paramBundle;
    }
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    zaen.lock();
    try
    {
      zahp.onConnectionSuspended(paramInt);
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  final void onFutureDone(RuntimeException paramRuntimeException)
  {
    paramRuntimeException = zahn.obtainMessage(2, paramRuntimeException);
    zahn.sendMessage(paramRuntimeException);
  }
  
  public final void post(com.google.android.android.common.ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean)
  {
    zaen.lock();
    try
    {
      zahp.addHeaders(paramConnectionResult, paramSample, paramBoolean);
      zaen.unlock();
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      zaen.unlock();
      throw paramConnectionResult;
    }
  }
  
  public final void removeAccount()
  {
    if (isConnected()) {
      ((zaah)zahp).zaam();
    }
  }
  
  final void wakeup(com.google.android.android.common.ConnectionResult paramConnectionResult)
  {
    zaen.lock();
    try
    {
      zahq = paramConnectionResult;
      zahp = new zaav(this);
      zahp.begin();
      zahm.signalAll();
      zaen.unlock();
      return;
    }
    catch (Throwable paramConnectionResult)
    {
      zaen.unlock();
      throw paramConnectionResult;
    }
  }
  
  final void zaaz()
  {
    zaen.lock();
    try
    {
      zahp = new zaak(this, zaes, zaev, zaex, zacd, zaen, mContext);
      zahp.begin();
      zahm.signalAll();
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  final void zaba()
  {
    zaen.lock();
    try
    {
      zaed.zaaw();
      zahp = new zaah(this);
      zahp.begin();
      zahm.signalAll();
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
}
