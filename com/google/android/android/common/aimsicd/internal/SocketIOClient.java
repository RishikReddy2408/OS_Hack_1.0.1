package com.google.android.android.common.aimsicd.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.Api.AbstractClientBuilder;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.internal.base.Credentials;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

final class SocketIOClient
  implements zabs
{
  private final Context mContext;
  private final Looper zabj;
  private final zaaw zaed;
  private final zabe zaee;
  private final zabe zaef;
  private final Map<com.google.android.gms.common.api.Api.AnyClientKey<?>, com.google.android.gms.common.api.internal.zabe> zaeg;
  private final Set<com.google.android.gms.common.api.internal.SignInConnectionListener> zaeh = Collections.newSetFromMap(new WeakHashMap());
  private final Api.Client zaei;
  private Bundle zaej;
  private ConnectionResult zaek = null;
  private ConnectionResult zael = null;
  private boolean zaem = false;
  private final Lock zaen;
  @GuardedBy("mLock")
  private int zaeo = 0;
  
  private SocketIOClient(Context paramContext, zaaw paramZaaw, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map paramMap1, Map paramMap2, ClientSettings paramClientSettings, Api.AbstractClientBuilder paramAbstractClientBuilder, Api.Client paramClient, ArrayList paramArrayList1, ArrayList paramArrayList2, Map paramMap3, Map paramMap4)
  {
    mContext = paramContext;
    zaed = paramZaaw;
    zaen = paramLock;
    zabj = paramLooper;
    zaei = paramClient;
    zaee = new zabe(paramContext, zaed, paramLock, paramLooper, paramGoogleApiAvailabilityLight, paramMap2, null, paramMap4, null, paramArrayList2, new StateMachine(this, null));
    zaef = new zabe(paramContext, zaed, paramLock, paramLooper, paramGoogleApiAvailabilityLight, paramMap1, paramClientSettings, paramMap3, paramAbstractClientBuilder, paramArrayList1, new Exec(this, null));
    paramContext = new ArrayMap();
    paramZaaw = paramMap2.keySet().iterator();
    while (paramZaaw.hasNext()) {
      paramContext.put((com.google.android.android.common.aimsicd.Api.AnyClientKey)paramZaaw.next(), zaee);
    }
    paramZaaw = paramMap1.keySet().iterator();
    while (paramZaaw.hasNext()) {
      paramContext.put((com.google.android.android.common.aimsicd.Api.AnyClientKey)paramZaaw.next(), zaef);
    }
    zaeg = Collections.unmodifiableMap(paramContext);
  }
  
  private final void cleanup()
  {
    Iterator localIterator = zaeh.iterator();
    while (localIterator.hasNext()) {
      ((SignInConnectionListener)localIterator.next()).onComplete();
    }
    zaeh.clear();
  }
  
  private final void cleanup(int paramInt, boolean paramBoolean)
  {
    zaed.quit(paramInt, paramBoolean);
    zael = null;
    zaek = null;
  }
  
  private final void connect(Bundle paramBundle)
  {
    if (zaej == null)
    {
      zaej = paramBundle;
      return;
    }
    if (paramBundle != null) {
      zaej.putAll(paramBundle);
    }
  }
  
  private final void disconnect(ConnectionResult paramConnectionResult)
  {
    switch (zaeo)
    {
    default: 
      Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
      break;
    case 2: 
      zaed.removeAccount(paramConnectionResult);
    case 1: 
      cleanup();
    }
    zaeo = 0;
  }
  
  private final boolean encode(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    paramApiMethodImpl = paramApiMethodImpl.getClientKey();
    Preconditions.checkArgument(zaeg.containsKey(paramApiMethodImpl), "GoogleApiClient is not configured to use the API required for this call.");
    return ((zabe)zaeg.get(paramApiMethodImpl)).equals(zaef);
  }
  
  private static boolean isConnected(ConnectionResult paramConnectionResult)
  {
    return (paramConnectionResult != null) && (paramConnectionResult.isSuccess());
  }
  
  private final boolean next()
  {
    return (zael != null) && (zael.getErrorCode() == 4);
  }
  
  private final void reconnect()
  {
    if (isConnected(zaek))
    {
      if ((!isConnected(zael)) && (!next()))
      {
        if (zael != null)
        {
          if (zaeo == 1)
          {
            cleanup();
            return;
          }
          disconnect(zael);
          zaee.disconnect();
        }
      }
      else
      {
        switch (zaeo)
        {
        default: 
          Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
          break;
        case 2: 
          zaed.execute(zaej);
        case 1: 
          cleanup();
        }
        zaeo = 0;
      }
    }
    else
    {
      if ((zaek != null) && (isConnected(zael)))
      {
        zaef.disconnect();
        disconnect(zaek);
        return;
      }
      if ((zaek != null) && (zael != null))
      {
        ConnectionResult localConnectionResult = zaek;
        if (zaef.zahr < zaee.zahr) {
          localConnectionResult = zael;
        }
        disconnect(localConnectionResult);
      }
    }
  }
  
  public static SocketIOClient validate(Context paramContext, zaaw paramZaaw, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map paramMap1, ClientSettings paramClientSettings, Map paramMap2, Api.AbstractClientBuilder paramAbstractClientBuilder, ArrayList paramArrayList)
  {
    ArrayMap localArrayMap1 = new ArrayMap();
    ArrayMap localArrayMap2 = new ArrayMap();
    Object localObject2 = paramMap1.entrySet().iterator();
    paramMap1 = null;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Map.Entry)((Iterator)localObject2).next();
      localObject1 = (Api.Client)((Map.Entry)localObject3).getValue();
      if (((Api.Client)localObject1).providesSignIn()) {
        paramMap1 = (Map)localObject1;
      }
      if (((Api.Client)localObject1).requiresSignIn()) {
        localArrayMap1.put((com.google.android.android.common.aimsicd.Api.AnyClientKey)((Map.Entry)localObject3).getKey(), localObject1);
      } else {
        localArrayMap2.put((com.google.android.android.common.aimsicd.Api.AnyClientKey)((Map.Entry)localObject3).getKey(), localObject1);
      }
    }
    Preconditions.checkState(localArrayMap1.isEmpty() ^ true, "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
    Object localObject1 = new ArrayMap();
    localObject2 = new ArrayMap();
    Object localObject3 = paramMap2.keySet().iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (Sample)((Iterator)localObject3).next();
      com.google.android.android.common.aimsicd.Api.AnyClientKey localAnyClientKey = ((Sample)localObject4).getClientKey();
      if (localArrayMap1.containsKey(localAnyClientKey)) {
        ((Map)localObject1).put(localObject4, (Boolean)paramMap2.get(localObject4));
      } else if (localArrayMap2.containsKey(localAnyClientKey)) {
        ((Map)localObject2).put(localObject4, (Boolean)paramMap2.get(localObject4));
      } else {
        throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
      }
    }
    paramMap2 = new ArrayList();
    localObject3 = new ArrayList();
    paramArrayList = (ArrayList)paramArrayList;
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      localObject4 = paramArrayList.get(i);
      i += 1;
      localObject4 = (Logger)localObject4;
      if (((Map)localObject1).containsKey(mApi)) {
        paramMap2.add(localObject4);
      } else if (((Map)localObject2).containsKey(mApi)) {
        ((ArrayList)localObject3).add(localObject4);
      } else {
        throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
      }
    }
    return new SocketIOClient(paramContext, paramZaaw, paramLock, paramLooper, paramGoogleApiAvailabilityLight, localArrayMap1, localArrayMap2, paramClientSettings, paramAbstractClientBuilder, paramMap1, paramMap2, (ArrayList)localObject3, (Map)localObject1, (Map)localObject2);
  }
  
  private final PendingIntent zaaa()
  {
    if (zaei == null) {
      return null;
    }
    return PendingIntent.getActivity(mContext, System.identityHashCode(zaed), zaei.getSignInIntent(), 134217728);
  }
  
  public final ConnectionResult blockingConnect()
  {
    throw new UnsupportedOperationException();
  }
  
  public final ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit)
  {
    throw new UnsupportedOperationException();
  }
  
  public final void connect()
  {
    zaeo = 2;
    zaem = false;
    zael = null;
    zaek = null;
    zaee.connect();
    zaef.connect();
  }
  
  public final void disconnect()
  {
    zael = null;
    zaek = null;
    zaeo = 0;
    zaee.disconnect();
    zaef.disconnect();
    cleanup();
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.append(paramString).append("authClient").println(":");
    zaef.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.append(paramString).append("anonClient").println(":");
    zaee.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    if (encode(paramApiMethodImpl))
    {
      if (next())
      {
        paramApiMethodImpl.setFailedResult(new Status(4, null, zaaa()));
        return paramApiMethodImpl;
      }
      return zaef.enqueue(paramApiMethodImpl);
    }
    return zaee.enqueue(paramApiMethodImpl);
  }
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    if (encode(paramApiMethodImpl))
    {
      if (next())
      {
        paramApiMethodImpl.setFailedResult(new Status(4, null, zaaa()));
        return paramApiMethodImpl;
      }
      return zaef.execute(paramApiMethodImpl);
    }
    return zaee.execute(paramApiMethodImpl);
  }
  
  public final ConnectionResult getConnectionResult(Sample paramSample)
  {
    if (((zabe)zaeg.get(paramSample.getClientKey())).equals(zaef))
    {
      if (next()) {
        return new ConnectionResult(4, zaaa());
      }
      return zaef.getConnectionResult(paramSample);
    }
    return zaee.getConnectionResult(paramSample);
  }
  
  public final boolean isConnected()
  {
    zaen.lock();
    try
    {
      boolean bool1 = zaee.isConnected();
      boolean bool2 = true;
      if (bool1)
      {
        boolean bool3 = zaef.isConnected();
        bool1 = bool2;
        if (bool3) {
          break label69;
        }
        bool3 = next();
        bool1 = bool2;
        if (bool3) {
          break label69;
        }
        int i = zaeo;
        if (i == 1)
        {
          bool1 = bool2;
          break label69;
        }
      }
      bool1 = false;
      label69:
      zaen.unlock();
      return bool1;
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
      int i = zaeo;
      boolean bool;
      if (i == 2) {
        bool = true;
      } else {
        bool = false;
      }
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
      boolean bool = isConnecting();
      if (!bool)
      {
        bool = isConnected();
        if (!bool) {}
      }
      else
      {
        bool = zaef.isConnected();
        if (!bool)
        {
          zaeh.add(paramSignInConnectionListener);
          int i = zaeo;
          if (i == 0) {
            zaeo = 1;
          }
          zael = null;
          zaef.connect();
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
      boolean bool = isConnecting();
      zaef.disconnect();
      zael = new ConnectionResult(4);
      if (bool) {
        new Credentials(zabj).post(new LayerView.1(this));
      } else {
        cleanup();
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
  
  public final void removeAccount()
  {
    zaee.removeAccount();
    zaef.removeAccount();
  }
}
