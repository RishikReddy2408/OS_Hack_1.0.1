package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.package_7.FragmentActivity;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.GoogleApiClient.Builder;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.common.aimsicd.PendingResult;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.GmsClientEventManager;
import com.google.android.android.common.internal.GmsClientEventManager.GmsClientEventState;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.service.Common;
import com.google.android.android.common.internal.service.Location;
import com.google.android.android.common.util.ClientLibraryUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.zaq;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zaaw
  extends GoogleApiClient
  implements zabt
{
  private final Context mContext;
  private final Looper zabj;
  private final int zaca;
  private final GoogleApiAvailability zacc;
  private final com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
  private boolean zacg;
  private final Lock zaen;
  private final ClientSettings zaes;
  private final Map<Api<?>, Boolean> zaev;
  @VisibleForTesting
  final Queue<com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl<?, ?>> zafb = new LinkedList();
  private final GmsClientEventManager zagr;
  private zabs zags = null;
  private volatile boolean zagt;
  private long zagu;
  private long zagv;
  private final zabb zagw;
  @VisibleForTesting
  private zabq zagx;
  final Map<com.google.android.gms.common.api.Api.AnyClientKey<?>, com.google.android.gms.common.api.Api.Client> zagy;
  Set<Scope> zagz;
  private final ListenerHolders zaha;
  private final ArrayList<zaq> zahb;
  private Integer zahc;
  Set<com.google.android.gms.common.api.internal.zacm> zahd;
  final zacp zahe;
  private final GmsClientEventManager.GmsClientEventState zahf;
  
  public zaaw(Context paramContext, Lock paramLock, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiAvailability paramGoogleApiAvailability, com.google.android.android.common.aimsicd.Api.AbstractClientBuilder paramAbstractClientBuilder, Map paramMap1, List paramList1, List paramList2, Map paramMap2, int paramInt1, int paramInt2, ArrayList paramArrayList, boolean paramBoolean)
  {
    long l;
    if (ClientLibraryUtils.isPackageSide()) {
      l = 10000L;
    } else {
      l = 120000L;
    }
    zagu = l;
    zagv = 5000L;
    zagz = new HashSet();
    zaha = new ListenerHolders();
    zahc = null;
    zahd = null;
    zahf = new zaax(this);
    mContext = paramContext;
    zaen = paramLock;
    zacg = false;
    zagr = new GmsClientEventManager(paramLooper, zahf);
    zabj = paramLooper;
    zagw = new zabb(this, paramLooper);
    zacc = paramGoogleApiAvailability;
    zaca = paramInt1;
    if (zaca >= 0) {
      zahc = Integer.valueOf(paramInt2);
    }
    zaev = paramMap1;
    zagy = paramMap2;
    zahb = paramArrayList;
    zahe = new zacp(zagy);
    paramContext = paramList1.iterator();
    while (paramContext.hasNext())
    {
      paramLock = (GoogleApiClient.ConnectionCallbacks)paramContext.next();
      zagr.registerConnectionCallbacks(paramLock);
    }
    paramContext = paramList2.iterator();
    while (paramContext.hasNext())
    {
      paramLock = (GoogleApiClient.OnConnectionFailedListener)paramContext.next();
      zagr.registerConnectionFailedListener(paramLock);
    }
    zaes = paramClientSettings;
    zacd = paramAbstractClientBuilder;
  }
  
  private final void onMessage(int paramInt)
  {
    if (zahc == null) {
      zahc = Integer.valueOf(paramInt);
    } else {
      if (zahc.intValue() != paramInt) {
        break label406;
      }
    }
    if (zags != null) {
      return;
    }
    Object localObject1 = zagy.values().iterator();
    int i = 0;
    paramInt = 0;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (com.google.android.android.common.aimsicd.Api.Client)((Iterator)localObject1).next();
      int j = i;
      if (((com.google.android.android.common.aimsicd.Api.Client)localObject2).requiresSignIn()) {
        j = 1;
      }
      i = j;
      if (((com.google.android.android.common.aimsicd.Api.Client)localObject2).providesSignIn())
      {
        paramInt = 1;
        i = j;
      }
    }
    switch (zahc.intValue())
    {
    default: 
      break;
    case 2: 
      if (i != 0)
      {
        if (zacg)
        {
          zags = new TaskManager(mContext, zaen, zabj, zacc, zagy, zaes, zaev, zacd, zahb, this, true);
          return;
        }
        zags = SocketIOClient.validate(mContext, this, zaen, zabj, zacc, zagy, zaes, zaev, zacd, zahb);
        return;
      }
      break;
    case 1: 
      if (i != 0)
      {
        if (paramInt != 0) {
          throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
        }
      }
      else {
        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
      }
      break;
    }
    if ((zacg) && (paramInt == 0))
    {
      zags = new TaskManager(mContext, zaen, zabj, zacc, zagy, zaes, zaev, zacd, zahb, this, false);
      return;
    }
    zags = new zabe(mContext, this, zaen, zabj, zacc, zagy, zaes, zaev, zacd, zahb, this);
    return;
    label406:
    localObject1 = toString(paramInt);
    Object localObject2 = toString(zahc.intValue());
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject1).length() + 51 + String.valueOf(localObject2).length());
    localStringBuilder.append("Cannot use sign-in mode: ");
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append(". Mode was already set to ");
    localStringBuilder.append((String)localObject2);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private final void persist(GoogleApiClient paramGoogleApiClient, StatusPendingResult paramStatusPendingResult, boolean paramBoolean)
  {
    Common.zaph.getName(paramGoogleApiClient).setResultCallback(new zaba(this, paramStatusPendingResult, paramBoolean, paramGoogleApiClient));
  }
  
  private final void resume()
  {
    zaen.lock();
    try
    {
      boolean bool = zagt;
      if (bool) {
        zaau();
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
  
  private static String toString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 3: 
      return "SIGN_IN_MODE_NONE";
    case 2: 
      return "SIGN_IN_MODE_OPTIONAL";
    }
    return "SIGN_IN_MODE_REQUIRED";
  }
  
  public static int transform(Iterable paramIterable, boolean paramBoolean)
  {
    paramIterable = paramIterable.iterator();
    int j = 0;
    int i = 0;
    while (paramIterable.hasNext())
    {
      com.google.android.android.common.aimsicd.Api.Client localClient = (com.google.android.android.common.aimsicd.Api.Client)paramIterable.next();
      int k = j;
      if (localClient.requiresSignIn()) {
        k = 1;
      }
      j = k;
      if (localClient.providesSignIn())
      {
        i = 1;
        j = k;
      }
    }
    if (j != 0)
    {
      if (i != 0)
      {
        if (paramBoolean) {
          return 2;
        }
      }
      else {
        return 1;
      }
    }
    else {
      return 3;
    }
    return 1;
  }
  
  private final void zaau()
  {
    zagr.enableCallbacks();
    zags.connect();
  }
  
  private final void zaav()
  {
    zaen.lock();
    try
    {
      boolean bool = zaaw();
      if (bool) {
        zaau();
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
  
  public final ConnectionResult blockingConnect()
  {
    Object localObject = Looper.myLooper();
    Looper localLooper = Looper.getMainLooper();
    boolean bool2 = true;
    boolean bool1;
    if (localObject != localLooper) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    Preconditions.checkState(bool1, "blockingConnect must not be called on the UI thread");
    zaen.lock();
    try
    {
      int i = zaca;
      if (i >= 0)
      {
        localObject = zahc;
        if (localObject != null) {
          bool1 = bool2;
        } else {
          bool1 = false;
        }
        Preconditions.checkState(bool1, "Sign-in mode should have been set explicitly by auto-manage.");
      }
      else
      {
        localObject = zahc;
        if (localObject == null)
        {
          zahc = Integer.valueOf(transform(zagy.values(), false));
        }
        else
        {
          i = zahc.intValue();
          if (i == 2) {
            break label167;
          }
        }
      }
      onMessage(zahc.intValue());
      zagr.enableCallbacks();
      localObject = zags.blockingConnect();
      zaen.unlock();
      return localObject;
      label167:
      throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool, "blockingConnect must not be called on the UI thread");
    Preconditions.checkNotNull(paramTimeUnit, "TimeUnit must not be null");
    zaen.lock();
    try
    {
      Integer localInteger = zahc;
      if (localInteger == null)
      {
        zahc = Integer.valueOf(transform(zagy.values(), false));
      }
      else
      {
        int i = zahc.intValue();
        if (i == 2) {
          break label133;
        }
      }
      onMessage(zahc.intValue());
      zagr.enableCallbacks();
      paramTimeUnit = zags.blockingConnect(paramLong, paramTimeUnit);
      zaen.unlock();
      return paramTimeUnit;
      label133:
      throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    catch (Throwable paramTimeUnit)
    {
      zaen.unlock();
      throw paramTimeUnit;
    }
  }
  
  public final PendingResult clearDefaultAccountAndReconnect()
  {
    Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
    boolean bool;
    if (zahc.intValue() != 2) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
    StatusPendingResult localStatusPendingResult = new StatusPendingResult(this);
    if (zagy.containsKey(Common.CLIENT_KEY))
    {
      persist(this, localStatusPendingResult, false);
      return localStatusPendingResult;
    }
    AtomicReference localAtomicReference = new AtomicReference();
    Object localObject = new zaay(this, localAtomicReference, localStatusPendingResult);
    zaaz localZaaz = new zaaz(this, localStatusPendingResult);
    localObject = new GoogleApiClient.Builder(mContext).addApi(Common.packageName).addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)localObject).addOnConnectionFailedListener(localZaaz).setHandler(zagw).build();
    localAtomicReference.set(localObject);
    ((GoogleApiClient)localObject).connect();
    return localStatusPendingResult;
  }
  
  public final void connect()
  {
    zaen.lock();
    try
    {
      int i = zaca;
      boolean bool = false;
      Integer localInteger;
      if (i >= 0)
      {
        localInteger = zahc;
        if (localInteger != null) {
          bool = true;
        }
        Preconditions.checkState(bool, "Sign-in mode should have been set explicitly by auto-manage.");
      }
      else
      {
        localInteger = zahc;
        if (localInteger == null)
        {
          zahc = Integer.valueOf(transform(zagy.values(), false));
        }
        else
        {
          i = zahc.intValue();
          if (i == 2) {
            break label107;
          }
        }
      }
      connect(zahc.intValue());
      zaen.unlock();
      return;
      label107:
      throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final void connect(int paramInt)
  {
    zaen.lock();
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramInt != 3)
    {
      bool1 = bool2;
      if (paramInt != 1) {
        if (paramInt == 2) {
          bool1 = bool2;
        } else {
          bool1 = false;
        }
      }
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder(33);
      localStringBuilder.append("Illegal sign-in mode: ");
      localStringBuilder.append(paramInt);
      Preconditions.checkArgument(bool1, localStringBuilder.toString());
      onMessage(paramInt);
      zaau();
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
      zahe.release();
      Object localObject = zags;
      if (localObject != null) {
        zags.disconnect();
      }
      zaha.release();
      localObject = zafb.iterator();
      for (;;)
      {
        boolean bool = ((Iterator)localObject).hasNext();
        if (!bool) {
          break;
        }
        BaseImplementation.ApiMethodImpl localApiMethodImpl = (BaseImplementation.ApiMethodImpl)((Iterator)localObject).next();
        localApiMethodImpl.remove(null);
        localApiMethodImpl.cancel();
      }
      zafb.clear();
      localObject = zags;
      if (localObject == null)
      {
        zaen.unlock();
        return;
      }
      zaaw();
      zagr.disableCallbacks();
      zaen.unlock();
      return;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.append(paramString).append("mContext=").println(mContext);
    paramPrintWriter.append(paramString).append("mResuming=").print(zagt);
    paramPrintWriter.append(" mWorkQueue.size()=").print(zafb.size());
    zacp localZacp = zahe;
    paramPrintWriter.append(" mUnconsumedApiCalls.size()=").println(zaky.size());
    if (zags != null) {
      zags.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    if (paramApiMethodImpl.getClientKey() != null) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "This task can not be enqueued (it's probably a Batch or malformed)");
    boolean bool = zagy.containsKey(paramApiMethodImpl.getClientKey());
    Object localObject;
    if (paramApiMethodImpl.getApi() != null) {
      localObject = paramApiMethodImpl.getApi().getName();
    } else {
      localObject = "the API";
    }
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 65);
    localStringBuilder.append("GoogleApiClient is not configured to use ");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(" required for this call.");
    Preconditions.checkArgument(bool, localStringBuilder.toString());
    zaen.lock();
    try
    {
      localObject = zags;
      if (localObject == null)
      {
        zafb.add(paramApiMethodImpl);
        zaen.unlock();
        return paramApiMethodImpl;
      }
      paramApiMethodImpl = zags.enqueue(paramApiMethodImpl);
      zaen.unlock();
      return paramApiMethodImpl;
    }
    catch (Throwable paramApiMethodImpl)
    {
      zaen.unlock();
      throw paramApiMethodImpl;
    }
  }
  
  public final void ensureInitialized(zacm paramZacm)
  {
    zaen.lock();
    try
    {
      Set localSet = zahd;
      if (localSet == null) {
        zahd = new HashSet();
      }
      zahd.add(paramZacm);
      zaen.unlock();
      return;
    }
    catch (Throwable paramZacm)
    {
      zaen.unlock();
      throw paramZacm;
    }
  }
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    if (paramApiMethodImpl.getClientKey() != null) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "This task can not be executed (it's probably a Batch or malformed)");
    boolean bool = zagy.containsKey(paramApiMethodImpl.getClientKey());
    Object localObject;
    if (paramApiMethodImpl.getApi() != null) {
      localObject = paramApiMethodImpl.getApi().getName();
    } else {
      localObject = "the API";
    }
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 65);
    localStringBuilder.append("GoogleApiClient is not configured to use ");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(" required for this call.");
    Preconditions.checkArgument(bool, localStringBuilder.toString());
    zaen.lock();
    try
    {
      localObject = zags;
      if (localObject != null)
      {
        bool = zagt;
        if (bool)
        {
          zafb.add(paramApiMethodImpl);
          for (;;)
          {
            bool = zafb.isEmpty();
            if (bool) {
              break;
            }
            localObject = (BaseImplementation.ApiMethodImpl)zafb.remove();
            zahe.close((BasePendingResult)localObject);
            ((BaseImplementation.ApiMethodImpl)localObject).setFailedResult(Status.RESULT_INTERNAL_ERROR);
          }
          zaen.unlock();
          return paramApiMethodImpl;
        }
        paramApiMethodImpl = zags.execute(paramApiMethodImpl);
        zaen.unlock();
        return paramApiMethodImpl;
      }
      throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
    catch (Throwable paramApiMethodImpl)
    {
      zaen.unlock();
      throw paramApiMethodImpl;
    }
  }
  
  public final void execute(Bundle paramBundle)
  {
    while (!zafb.isEmpty()) {
      execute((BaseImplementation.ApiMethodImpl)zafb.remove());
    }
    zagr.onConnectionSuccess(paramBundle);
  }
  
  public final com.google.android.android.common.aimsicd.Api.Client getClient(com.google.android.android.common.aimsicd.Api.AnyClientKey paramAnyClientKey)
  {
    paramAnyClientKey = (com.google.android.android.common.aimsicd.Api.Client)zagy.get(paramAnyClientKey);
    Preconditions.checkNotNull(paramAnyClientKey, "Appropriate Api was not requested.");
    return paramAnyClientKey;
  }
  
  public final ConnectionResult getConnectionResult(Sample paramSample)
  {
    zaen.lock();
    try
    {
      boolean bool = isConnected();
      if (!bool)
      {
        bool = zagt;
        if (!bool) {
          throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
        }
      }
      bool = zagy.containsKey(paramSample.getClientKey());
      if (bool)
      {
        ConnectionResult localConnectionResult = zags.getConnectionResult(paramSample);
        if (localConnectionResult == null)
        {
          bool = zagt;
          if (bool)
          {
            paramSample = ConnectionResult.RESULT_SUCCESS;
            zaen.unlock();
            return paramSample;
          }
          Log.w("GoogleApiClientImpl", zaay());
          Log.wtf("GoogleApiClientImpl", String.valueOf(paramSample.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
          paramSample = new ConnectionResult(8, null);
          zaen.unlock();
          return paramSample;
        }
        zaen.unlock();
        return localConnectionResult;
      }
      throw new IllegalArgumentException(String.valueOf(paramSample.getName()).concat(" was never registered with GoogleApiClient"));
    }
    catch (Throwable paramSample)
    {
      zaen.unlock();
      throw paramSample;
    }
  }
  
  public final Context getContext()
  {
    return mContext;
  }
  
  public final Looper getLooper()
  {
    return zabj;
  }
  
  public final boolean hasApi(Sample paramSample)
  {
    return zagy.containsKey(paramSample.getClientKey());
  }
  
  public final boolean hasConnectedApi(Sample paramSample)
  {
    if (!isConnected()) {
      return false;
    }
    paramSample = (com.google.android.android.common.aimsicd.Api.Client)zagy.get(paramSample.getClientKey());
    return (paramSample != null) && (paramSample.isConnected());
  }
  
  public final boolean isConnected()
  {
    return (zags != null) && (zags.isConnected());
  }
  
  public final boolean isConnecting()
  {
    return (zags != null) && (zags.isConnecting());
  }
  
  public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return zagr.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return zagr.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public final boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener)
  {
    return (zags != null) && (zags.maybeSignIn(paramSignInConnectionListener));
  }
  
  public final void maybeSignOut()
  {
    if (zags != null) {
      zags.maybeSignOut();
    }
  }
  
  public final void quit(int paramInt, boolean paramBoolean)
  {
    if ((paramInt == 1) && (!paramBoolean) && (!zagt))
    {
      zagt = true;
      if ((zagx == null) && (!ClientLibraryUtils.isPackageSide())) {
        zagx = zacc.start(mContext.getApplicationContext(), new zabc(this));
      }
      zagw.sendMessageDelayed(zagw.obtainMessage(1), zagu);
      zagw.sendMessageDelayed(zagw.obtainMessage(2), zagv);
    }
    zahe.zabx();
    zagr.onUnintentionalDisconnection(paramInt);
    zagr.disableCallbacks();
    if (paramInt == 2) {
      zaau();
    }
  }
  
  public final void reconnect()
  {
    disconnect();
    connect();
  }
  
  public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zagr.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zagr.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public final ListenerHolder registerListener(Object paramObject)
  {
    zaen.lock();
    try
    {
      paramObject = zaha.addOnChangeListener(paramObject, zabj, "NO_TYPE");
      zaen.unlock();
      return paramObject;
    }
    catch (Throwable paramObject)
    {
      zaen.unlock();
      throw paramObject;
    }
  }
  
  public final void removeAccount(ConnectionResult paramConnectionResult)
  {
    if (!zacc.isPlayServicesPossiblyUpdating(mContext, paramConnectionResult.getErrorCode())) {
      zaaw();
    }
    if (!zagt)
    {
      zagr.onConnectionFailure(paramConnectionResult);
      zagr.disableCallbacks();
    }
  }
  
  public final void removeAccount(zacm paramZacm)
  {
    zaen.lock();
    try
    {
      Set localSet = zahd;
      if (localSet == null)
      {
        Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
      }
      else
      {
        boolean bool = zahd.remove(paramZacm);
        if (!bool)
        {
          Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
        }
        else
        {
          bool = zaax();
          if (!bool) {
            zags.removeAccount();
          }
        }
      }
      zaen.unlock();
      return;
    }
    catch (Throwable paramZacm)
    {
      zaen.unlock();
      throw paramZacm;
    }
  }
  
  public final void stopAutoManage(FragmentActivity paramFragmentActivity)
  {
    paramFragmentActivity = new LifecycleActivity(paramFragmentActivity);
    if (zaca >= 0)
    {
      Elements.select(paramFragmentActivity).remove(zaca);
      return;
    }
    throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
  }
  
  public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zagr.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zagr.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  final boolean zaaw()
  {
    if (!zagt) {
      return false;
    }
    zagt = false;
    zagw.removeMessages(2);
    zagw.removeMessages(1);
    if (zagx != null)
    {
      zagx.unregister();
      zagx = null;
    }
    return true;
  }
  
  final boolean zaax()
  {
    zaen.lock();
    try
    {
      Set localSet = zahd;
      if (localSet == null)
      {
        zaen.unlock();
        return false;
      }
      boolean bool = zahd.isEmpty();
      zaen.unlock();
      return bool ^ true;
    }
    catch (Throwable localThrowable)
    {
      zaen.unlock();
      throw localThrowable;
    }
  }
  
  final String zaay()
  {
    StringWriter localStringWriter = new StringWriter();
    dump("", null, new PrintWriter(localStringWriter), null);
    return localStringWriter.toString();
  }
}
