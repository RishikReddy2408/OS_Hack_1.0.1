package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.ResolveAccountResponse;
import com.google.android.android.signin.Client;
import com.google.android.android.signin.Subscription;
import com.google.android.android.signin.internal.Server;
import com.google.android.android.signin.internal.Signature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.util.Set;

public final class zace
  extends Signature
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  private static com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, SignInOptions> zakh = Subscription.zapg;
  private final Context mContext;
  private final Handler mHandler;
  private Set<Scope> mScopes;
  private final com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, SignInOptions> zaau;
  private ClientSettings zaes;
  private Client zaga;
  private zach zaki;
  
  public zace(Context paramContext, Handler paramHandler, ClientSettings paramClientSettings)
  {
    this(paramContext, paramHandler, paramClientSettings, zakh);
  }
  
  public zace(Context paramContext, Handler paramHandler, ClientSettings paramClientSettings, com.google.android.android.common.aimsicd.Api.AbstractClientBuilder paramAbstractClientBuilder)
  {
    mContext = paramContext;
    mHandler = paramHandler;
    zaes = ((ClientSettings)Preconditions.checkNotNull(paramClientSettings, "ClientSettings must not be null"));
    mScopes = paramClientSettings.getRequiredScopes();
    zaau = paramAbstractClientBuilder;
  }
  
  private final void setServer(Server paramServer)
  {
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
        Log.wtf("SignInCoordinator", localStringBuilder.toString(), new Exception());
        zaki.ignore(paramServer);
        zaga.disconnect();
        return;
      }
      zaki.startSession(((ResolveAccountResponse)localObject).getAccountAccessor(), mScopes);
    }
    else
    {
      zaki.ignore((ConnectionResult)localObject);
    }
    zaga.disconnect();
  }
  
  public final void notifyProgress(Server paramServer)
  {
    mHandler.post(new zacg(this, paramServer));
  }
  
  public final void onConnected(Bundle paramBundle)
  {
    zaga.execute(this);
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    zaki.ignore(paramConnectionResult);
  }
  
  public final void onConnectionSuspended(int paramInt)
  {
    zaga.disconnect();
  }
  
  public final void stop(zach paramZach)
  {
    if (zaga != null) {
      zaga.disconnect();
    }
    zaes.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
    zaga = ((Client)zaau.buildClient(mContext, mHandler.getLooper(), zaes, zaes.getSignInOptions(), this, this));
    zaki = paramZach;
    if ((mScopes != null) && (!mScopes.isEmpty()))
    {
      zaga.connect();
      return;
    }
    mHandler.post(new zacf(this));
  }
  
  public final Client zabq()
  {
    return zaga;
  }
  
  public final void zabs()
  {
    if (zaga != null) {
      zaga.disconnect();
    }
  }
}
