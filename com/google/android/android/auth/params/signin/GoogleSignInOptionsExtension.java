package com.google.android.android.auth.params.signin;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

public abstract interface GoogleSignInOptionsExtension
{
  @KeepForSdk
  public static final int FITNESS = 3;
  @KeepForSdk
  public static final int GAMES = 1;
  
  public abstract int getExtensionType();
  
  public abstract List getImpliedScopes();
  
  public abstract Bundle toBundle();
}
