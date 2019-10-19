package com.google.android.android.signin;

import com.google.android.android.common.aimsicd.Api.ApiOptions.Optional;

public final class SignInOptions
  implements Api.ApiOptions.Optional
{
  public static final SignInOptions DEFAULT = new SignInOptions(false, false, null, false, null, false, null, null);
  private final boolean derived = false;
  private final boolean zaaa = false;
  private final String zaab = null;
  private final String zaac = null;
  private final boolean zars = false;
  private final boolean zart = false;
  private final Long zaru = null;
  private final Long zarv = null;
  
  static
  {
    new zaa();
  }
  
  private SignInOptions(boolean paramBoolean1, boolean paramBoolean2, String paramString1, boolean paramBoolean3, String paramString2, boolean paramBoolean4, Long paramLong1, Long paramLong2) {}
  
  public final Long getAuthApiSignInModuleVersion()
  {
    return zaru;
  }
  
  public final String getHostedDomain()
  {
    return zaac;
  }
  
  public final Long getRealClientLibraryVersion()
  {
    return zarv;
  }
  
  public final String getServerClientId()
  {
    return zaab;
  }
  
  public final boolean isForceCodeForRefreshToken()
  {
    return zaaa;
  }
  
  public final boolean isIdTokenRequested()
  {
    return derived;
  }
  
  public final boolean isOfflineAccessRequested()
  {
    return zars;
  }
  
  public final boolean waitForAccessTokenRefresh()
  {
    return zart;
  }
  
  public final class zaa
  {
    public zaa() {}
  }
}
