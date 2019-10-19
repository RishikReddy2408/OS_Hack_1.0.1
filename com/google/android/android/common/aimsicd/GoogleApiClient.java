package com.google.android.android.common.aimsicd;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.package_7.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.aimsicd.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.android.common.aimsicd.internal.Elements;
import com.google.android.android.common.aimsicd.internal.LifecycleActivity;
import com.google.android.android.common.aimsicd.internal.ListenerHolder;
import com.google.android.android.common.aimsicd.internal.Logger;
import com.google.android.android.common.aimsicd.internal.SignInConnectionListener;
import com.google.android.android.common.aimsicd.internal.zaaw;
import com.google.android.android.common.aimsicd.internal.zacm;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.signin.Subscription;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class GoogleApiClient
{
  @KeepForSdk
  public static final String DEFAULT_ACCOUNT = "<<default account>>";
  public static final int SIGN_IN_MODE_OPTIONAL = 2;
  public static final int SIGN_IN_MODE_REQUIRED = 1;
  @GuardedBy("sAllClients")
  private static final Set<com.google.android.gms.common.api.GoogleApiClient> zabq = Collections.newSetFromMap(new WeakHashMap());
  
  public GoogleApiClient() {}
  
  public static void dumpAll(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    Set localSet = zabq;
    int i = 0;
    try
    {
      String str = String.valueOf(paramString).concat("  ");
      Iterator localIterator = zabq.iterator();
      while (localIterator.hasNext())
      {
        GoogleApiClient localGoogleApiClient = (GoogleApiClient)localIterator.next();
        paramPrintWriter.append(paramString).append("GoogleApiClient#").println(i);
        localGoogleApiClient.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        i += 1;
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public static Set getAllClients()
  {
    Set localSet1 = zabq;
    try
    {
      Set localSet2 = zabq;
      return localSet2;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract PendingResult clearDefaultAccountAndReconnect();
  
  public abstract void connect();
  
  public void connect(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    throw new UnsupportedOperationException();
  }
  
  public void ensureInitialized(zacm paramZacm)
  {
    throw new UnsupportedOperationException();
  }
  
  public BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    throw new UnsupportedOperationException();
  }
  
  public Api.Client getClient(Api.AnyClientKey paramAnyClientKey)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract ConnectionResult getConnectionResult(Sample paramSample);
  
  public Context getContext()
  {
    throw new UnsupportedOperationException();
  }
  
  public Looper getLooper()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean hasApi(Sample paramSample)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean hasConnectedApi(Sample paramSample);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener)
  {
    throw new UnsupportedOperationException();
  }
  
  public void maybeSignOut()
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public ListenerHolder registerListener(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public void removeAccount(zacm paramZacm)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void stopAutoManage(FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @KeepForSdk
  public final class Builder
  {
    private Account textAlignment;
    private Looper zabj = getMainLooper();
    private final Set<com.google.android.gms.common.api.Scope> zabr = new HashSet();
    private final Set<com.google.android.gms.common.api.Scope> zabs = new HashSet();
    private int zabt;
    private View zabu;
    private String zabv = getPackageName();
    private String zabw = getClass().getName();
    private final Map<Api<?>, com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings> zabx = new ArrayMap();
    private final Map<Api<?>, com.google.android.gms.common.api.Api.ApiOptions> zaby = new ArrayMap();
    private LifecycleActivity zabz;
    private int zaca = -1;
    private GoogleApiClient.OnConnectionFailedListener zacb;
    private GoogleApiAvailability zacc = GoogleApiAvailability.getInstance();
    private com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, com.google.android.gms.signin.SignInOptions> zacd = Subscription.zapg;
    private final ArrayList<com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks> zace = new ArrayList();
    private final ArrayList<com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener> zacf = new ArrayList();
    private boolean zacg = false;
    
    public Builder() {}
    
    public Builder(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this();
      Preconditions.checkNotNull(paramConnectionCallbacks, "Must provide a connected listener");
      zace.add(paramConnectionCallbacks);
      Preconditions.checkNotNull(paramOnConnectionFailedListener, "Must provide a connection failed listener");
      zacf.add(paramOnConnectionFailedListener);
    }
    
    private final void writeTo(Sample paramSample, Api.ApiOptions paramApiOptions, Scope... paramVarArgs)
    {
      paramApiOptions = new HashSet(paramSample.getValue().getImpliedScopes(paramApiOptions));
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        paramApiOptions.add(paramVarArgs[i]);
        i += 1;
      }
      zabx.put(paramSample, new com.google.android.android.common.internal.ClientSettings.OptionalApiSettings(paramApiOptions));
    }
    
    public final Builder addApi(Sample paramSample)
    {
      Preconditions.checkNotNull(paramSample, "Api must not be null");
      zaby.put(paramSample, null);
      paramSample = paramSample.getValue().getImpliedScopes(null);
      zabs.addAll(paramSample);
      zabr.addAll(paramSample);
      return this;
    }
    
    public final Builder addApi(Sample paramSample, Api.ApiOptions.HasOptions paramHasOptions)
    {
      Preconditions.checkNotNull(paramSample, "Api must not be null");
      Preconditions.checkNotNull(paramHasOptions, "Null options are not permitted for this Api");
      zaby.put(paramSample, paramHasOptions);
      paramSample = paramSample.getValue().getImpliedScopes(paramHasOptions);
      zabs.addAll(paramSample);
      zabr.addAll(paramSample);
      return this;
    }
    
    public final Builder addApiIfAvailable(Sample paramSample, Api.ApiOptions.HasOptions paramHasOptions, Scope... paramVarArgs)
    {
      Preconditions.checkNotNull(paramSample, "Api must not be null");
      Preconditions.checkNotNull(paramHasOptions, "Null options are not permitted for this Api");
      zaby.put(paramSample, paramHasOptions);
      writeTo(paramSample, paramHasOptions, paramVarArgs);
      return this;
    }
    
    public final Builder addApiIfAvailable(Sample paramSample, Scope... paramVarArgs)
    {
      Preconditions.checkNotNull(paramSample, "Api must not be null");
      zaby.put(paramSample, null);
      writeTo(paramSample, null, paramVarArgs);
      return this;
    }
    
    public final Builder addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      Preconditions.checkNotNull(paramConnectionCallbacks, "Listener must not be null");
      zace.add(paramConnectionCallbacks);
      return this;
    }
    
    public final Builder addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      Preconditions.checkNotNull(paramOnConnectionFailedListener, "Listener must not be null");
      zacf.add(paramOnConnectionFailedListener);
      return this;
    }
    
    public final Builder addScope(Scope paramScope)
    {
      Preconditions.checkNotNull(paramScope, "Scope must not be null");
      zabr.add(paramScope);
      return this;
    }
    
    public final Builder addScopeNames(String[] paramArrayOfString)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        zabr.add(new Scope(paramArrayOfString[i]));
        i += 1;
      }
      return this;
    }
    
    public final GoogleApiClient build()
    {
      Preconditions.checkArgument(zaby.isEmpty() ^ true, "must call addApi() to add at least one API");
      Object localObject3 = buildClientSettings();
      Object localObject1 = null;
      Map localMap = ((ClientSettings)localObject3).getOptionalApiSettings();
      ArrayMap localArrayMap1 = new ArrayMap();
      ArrayMap localArrayMap2 = new ArrayMap();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = zaby.keySet().iterator();
      int j = 0;
      boolean bool;
      while (localIterator.hasNext())
      {
        localObject2 = (Sample)localIterator.next();
        Object localObject4 = zaby.get(localObject2);
        if (localMap.get(localObject2) != null) {
          bool = true;
        } else {
          bool = false;
        }
        localArrayMap1.put(localObject2, Boolean.valueOf(bool));
        Object localObject5 = new Logger((Sample)localObject2, bool);
        localArrayList.add(localObject5);
        Api.AbstractClientBuilder localAbstractClientBuilder = ((Sample)localObject2).start();
        localObject5 = localAbstractClientBuilder.buildClient(GoogleApiClient.this, zabj, (ClientSettings)localObject3, localObject4, (GoogleApiClient.ConnectionCallbacks)localObject5, (GoogleApiClient.OnConnectionFailedListener)localObject5);
        localArrayMap2.put(((Sample)localObject2).getClientKey(), localObject5);
        i = j;
        if (localAbstractClientBuilder.getPriority() == 1) {
          if (localObject4 != null) {
            i = 1;
          } else {
            i = 0;
          }
        }
        j = i;
        if (((Api.Client)localObject5).providesSignIn()) {
          if (localObject1 == null)
          {
            localObject1 = localObject2;
            j = i;
          }
          else
          {
            localObject2 = ((Sample)localObject2).getName();
            localObject1 = ((Sample)localObject1).getName();
            localObject3 = new StringBuilder(String.valueOf(localObject2).length() + 21 + String.valueOf(localObject1).length());
            ((StringBuilder)localObject3).append((String)localObject2);
            ((StringBuilder)localObject3).append(" cannot be used with ");
            ((StringBuilder)localObject3).append((String)localObject1);
            throw new IllegalStateException(((StringBuilder)localObject3).toString());
          }
        }
      }
      if (localObject1 != null) {
        if (j == 0)
        {
          if (textAlignment == null) {
            bool = true;
          } else {
            bool = false;
          }
          Preconditions.checkState(bool, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { ((Sample)localObject1).getName() });
          Preconditions.checkState(zabr.equals(zabs), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { ((Sample)localObject1).getName() });
        }
        else
        {
          localObject1 = ((Sample)localObject1).getName();
          localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 82);
          ((StringBuilder)localObject2).append("With using ");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
          throw new IllegalStateException(((StringBuilder)localObject2).toString());
        }
      }
      int i = zaaw.transform(localArrayMap2.values(), true);
      Object localObject2 = new zaaw(GoogleApiClient.this, new ReentrantLock(), zabj, (ClientSettings)localObject3, zacc, zacd, localArrayMap1, zace, zacf, localArrayMap2, zaca, i, localArrayList, false);
      localObject1 = GoogleApiClient.getTrustAnchors();
      try
      {
        GoogleApiClient.getTrustAnchors().add(localObject2);
        if (zaca >= 0)
        {
          Elements.select(zabz).get(zaca, (GoogleApiClient)localObject2, zacb);
          return localObject2;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      return localThrowable;
    }
    
    public final ClientSettings buildClientSettings()
    {
      com.google.android.android.signin.SignInOptions localSignInOptions = com.google.android.android.signin.SignInOptions.DEFAULT;
      if (zaby.containsKey(Subscription.NULL_KEY)) {
        localSignInOptions = (com.google.android.android.signin.SignInOptions)zaby.get(Subscription.NULL_KEY);
      }
      return new ClientSettings(textAlignment, zabr, zabx, zabt, zabu, zabv, zabw, localSignInOptions);
    }
    
    public final Builder enableAutoManage(FragmentActivity paramFragmentActivity, int paramInt, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      paramFragmentActivity = new LifecycleActivity(paramFragmentActivity);
      boolean bool;
      if (paramInt >= 0) {
        bool = true;
      } else {
        bool = false;
      }
      Preconditions.checkArgument(bool, "clientId must be non-negative");
      zaca = paramInt;
      zacb = paramOnConnectionFailedListener;
      zabz = paramFragmentActivity;
      return this;
    }
    
    public final Builder enableAutoManage(FragmentActivity paramFragmentActivity, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return enableAutoManage(paramFragmentActivity, 0, paramOnConnectionFailedListener);
    }
    
    public final Builder setAccountName(String paramString)
    {
      if (paramString == null) {
        paramString = null;
      } else {
        paramString = new Account(paramString, "com.google");
      }
      textAlignment = paramString;
      return this;
    }
    
    public final Builder setGravityForPopups(int paramInt)
    {
      zabt = paramInt;
      return this;
    }
    
    public final Builder setHandler(Handler paramHandler)
    {
      Preconditions.checkNotNull(paramHandler, "Handler must not be null");
      zabj = paramHandler.getLooper();
      return this;
    }
    
    public final Builder setViewForPopups(View paramView)
    {
      Preconditions.checkNotNull(paramView, "View must not be null");
      zabu = paramView;
      return this;
    }
    
    public final Builder useDefaultAccount()
    {
      return setAccountName("<<default account>>");
    }
  }
  
  public abstract interface ConnectionCallbacks
  {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    public abstract void onConnected(Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public abstract interface OnConnectionFailedListener
  {
    public abstract void onConnectionFailed(ConnectionResult paramConnectionResult);
  }
}
