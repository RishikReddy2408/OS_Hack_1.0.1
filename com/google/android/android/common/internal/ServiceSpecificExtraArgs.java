package com.google.android.android.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ServiceSpecificExtraArgs
{
  private ServiceSpecificExtraArgs() {}
  
  @KeepForSdk
  public abstract interface CastExtraArgs
  {
    @KeepForSdk
    public static final String LISTENER = "listener";
  }
  
  @KeepForSdk
  public abstract interface GamesExtraArgs
  {
    @KeepForSdk
    public static final String DESIRED_LOCALE = "com.google.android.gms.games.key.desiredLocale";
    @KeepForSdk
    public static final String GAME_PACKAGE_NAME = "com.google.android.gms.games.key.gamePackageName";
    @KeepForSdk
    public static final String SIGNIN_OPTIONS = "com.google.android.gms.games.key.signInOptions";
    @KeepForSdk
    public static final String WINDOW_TOKEN = "com.google.android.gms.games.key.popupWindowToken";
  }
  
  @KeepForSdk
  public abstract interface PlusExtraArgs
  {
    @KeepForSdk
    public static final String PLUS_AUTH_PACKAGE = "auth_package";
  }
}
