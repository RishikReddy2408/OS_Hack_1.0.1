package com.google.android.android.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.android.common.Feature;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState;
import java.util.Iterator;
import java.util.Set;

@KeepForSdk
public abstract class GmsClient<T extends IInterface>
  extends com.google.android.gms.common.internal.BaseGmsClient<T>
  implements Api.Client, GmsClientEventManager.GmsClientEventState
{
  private final Account account;
  private final Set<com.google.android.gms.common.api.Scope> mScopes;
  private final ClientSettings zaes;
  
  protected GmsClient(Context paramContext, Handler paramHandler, int paramInt, ClientSettings paramClientSettings)
  {
    this(paramContext, paramHandler, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramClientSettings, null, null);
  }
  
  protected GmsClient(Context paramContext, Handler paramHandler, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramHandler, paramGmsClientSupervisor, paramGoogleApiAvailability, paramInt, log1p(paramConnectionCallbacks), getBody(paramOnConnectionFailedListener));
    zaes = ((ClientSettings)Preconditions.checkNotNull(paramClientSettings));
    account = paramClientSettings.getAccount();
    mScopes = create(paramClientSettings.getAllRequestedScopes());
  }
  
  protected GmsClient(Context paramContext, Looper paramLooper, int paramInt, ClientSettings paramClientSettings)
  {
    this(paramContext, paramLooper, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramClientSettings, null, null);
  }
  
  protected GmsClient(Context paramContext, Looper paramLooper, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramClientSettings, (GoogleApiClient.ConnectionCallbacks)Preconditions.checkNotNull(paramConnectionCallbacks), (GoogleApiClient.OnConnectionFailedListener)Preconditions.checkNotNull(paramOnConnectionFailedListener));
  }
  
  protected GmsClient(Context paramContext, Looper paramLooper, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramGmsClientSupervisor, paramGoogleApiAvailability, paramInt, log1p(paramConnectionCallbacks), getBody(paramOnConnectionFailedListener), paramClientSettings.getRealClientClassName());
    zaes = paramClientSettings;
    account = paramClientSettings.getAccount();
    mScopes = create(paramClientSettings.getAllRequestedScopes());
  }
  
  private final Set create(Set paramSet)
  {
    Set localSet = validateScopes(paramSet);
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext()) {
      if (!paramSet.contains((com.google.android.android.common.aimsicd.Scope)localIterator.next())) {
        throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
      }
    }
    return localSet;
  }
  
  private static BaseGmsClient.BaseOnConnectionFailedListener getBody(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    if (paramOnConnectionFailedListener == null) {
      return null;
    }
    return new Root.2(paramOnConnectionFailedListener);
  }
  
  private static BaseGmsClient.BaseConnectionCallbacks log1p(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    if (paramConnectionCallbacks == null) {
      return null;
    }
    return new MediaBrowserCompat.ConnectionCallback.StubApi21(paramConnectionCallbacks);
  }
  
  public final Account getAccount()
  {
    return account;
  }
  
  protected final ClientSettings getClientSettings()
  {
    return zaes;
  }
  
  public int getMinApkVersion()
  {
    return super.getMinApkVersion();
  }
  
  public Feature[] getRequiredFeatures()
  {
    return new Feature[0];
  }
  
  protected final Set getScopes()
  {
    return mScopes;
  }
  
  protected Set validateScopes(Set paramSet)
  {
    return paramSet;
  }
}
