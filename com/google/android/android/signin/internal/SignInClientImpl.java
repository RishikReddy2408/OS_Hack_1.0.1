package com.google.android.android.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.android.auth.params.signin.GoogleSignInAccount;
import com.google.android.android.auth.params.signin.internal.Storage;
import com.google.android.android.common.aimsicd.GoogleApiClient.ConnectionCallbacks;
import com.google.android.android.common.aimsicd.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.android.common.internal.BaseGmsClient;
import com.google.android.android.common.internal.BaseGmsClient.LegacyClientCallbackAdapter;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.IAccountAccessor;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.ResolveAccountRequest;
import com.google.android.android.signin.SignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.signin.internal.zaf;
import com.google.android.gms.signin.zad;

@KeepForSdk
public class SignInClientImpl
  extends com.google.android.gms.common.internal.GmsClient<zaf>
  implements zad
{
  private final ClientSettings zaes;
  private Integer zaod;
  private final boolean zary = true;
  private final Bundle zarz;
  
  private SignInClientImpl(Context paramContext, Looper paramLooper, boolean paramBoolean, ClientSettings paramClientSettings, Bundle paramBundle, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 44, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
    zaes = paramClientSettings;
    zarz = paramBundle;
    zaod = paramClientSettings.getClientSessionId();
  }
  
  public SignInClientImpl(Context paramContext, Looper paramLooper, boolean paramBoolean, ClientSettings paramClientSettings, SignInOptions paramSignInOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, true, paramClientSettings, createBundleFromClientSettings(paramClientSettings), paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public static Bundle createBundleFromClientSettings(ClientSettings paramClientSettings)
  {
    SignInOptions localSignInOptions = paramClientSettings.getSignInOptions();
    Integer localInteger = paramClientSettings.getClientSessionId();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", paramClientSettings.getAccount());
    if (localInteger != null) {
      localBundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", localInteger.intValue());
    }
    if (localSignInOptions != null)
    {
      localBundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", localSignInOptions.isOfflineAccessRequested());
      localBundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", localSignInOptions.isIdTokenRequested());
      localBundle.putString("com.google.android.gms.signin.internal.serverClientId", localSignInOptions.getServerClientId());
      localBundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
      localBundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", localSignInOptions.isForceCodeForRefreshToken());
      localBundle.putString("com.google.android.gms.signin.internal.hostedDomain", localSignInOptions.getHostedDomain());
      localBundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", localSignInOptions.waitForAccessTokenRefresh());
      if (localSignInOptions.getAuthApiSignInModuleVersion() != null) {
        localBundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", localSignInOptions.getAuthApiSignInModuleVersion().longValue());
      }
      if (localSignInOptions.getRealClientLibraryVersion() != null) {
        localBundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", localSignInOptions.getRealClientLibraryVersion().longValue());
      }
    }
    return localBundle;
  }
  
  public final void connect()
  {
    connect(new BaseGmsClient.LegacyClientCallbackAdapter(this));
  }
  
  public final void disconnect(IAccountAccessor paramIAccountAccessor, boolean paramBoolean)
  {
    try
    {
      Object localObject = getService();
      localObject = (Channel)localObject;
      Integer localInteger = zaod;
      ((Channel)localObject).write(paramIAccountAccessor, localInteger.intValue(), paramBoolean);
      return;
    }
    catch (RemoteException paramIAccountAccessor)
    {
      for (;;) {}
    }
    Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
  }
  
  public final void execute(Session paramSession)
  {
    Preconditions.checkNotNull(paramSession, "Expecting a valid ISignInCallbacks");
    Object localObject1 = zaes;
    try
    {
      Object localObject2 = ((ClientSettings)localObject1).getAccountOrDefault();
      localObject1 = null;
      Object localObject3 = name;
      boolean bool = "<<default account>>".equals(localObject3);
      if (bool) {
        localObject1 = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount();
      }
      localObject3 = zaod;
      localObject1 = new ResolveAccountRequest((Account)localObject2, ((Integer)localObject3).intValue(), (GoogleSignInAccount)localObject1);
      localObject2 = getService();
      localObject2 = (Channel)localObject2;
      ((Channel)localObject2).write(new OperationResult((ResolveAccountRequest)localObject1), paramSession);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
    }
    try
    {
      paramSession.notifyProgress(new Server(8));
      return;
    }
    catch (RemoteException paramSession)
    {
      for (;;) {}
    }
    Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", localRemoteException);
  }
  
  protected Bundle getGetServiceRequestExtraArgs()
  {
    String str = zaes.getRealClientPackageName();
    if (!getContext().getPackageName().equals(str)) {
      zarz.putString("com.google.android.gms.signin.internal.realClientPackageName", zaes.getRealClientPackageName());
    }
    return zarz;
  }
  
  public int getMinApkVersion()
  {
    return 12451000;
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.signin.internal.ISignInService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.signin.service.START";
  }
  
  public boolean requiresSignIn()
  {
    return zary;
  }
  
  public final void zacv()
  {
    try
    {
      Object localObject = getService();
      localObject = (Channel)localObject;
      Integer localInteger = zaod;
      ((Channel)localObject).write(localInteger.intValue());
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
    Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
  }
}
