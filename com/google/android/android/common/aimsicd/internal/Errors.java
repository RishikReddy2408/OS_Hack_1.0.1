package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;

public final class Errors<O extends Api.ApiOptions>
  extends com.google.android.gms.common.api.GoogleApi<O>
{
  private final com.google.android.gms.common.api.Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
  private final Api.Client zaeq;
  private final Logger zaer;
  private final ClientSettings zaes;
  
  public Errors(Context paramContext, Sample paramSample, Looper paramLooper, Api.Client paramClient, Logger paramLogger, ClientSettings paramClientSettings, com.google.android.android.common.aimsicd.Api.AbstractClientBuilder paramAbstractClientBuilder)
  {
    super(paramContext, paramSample, paramLooper);
    zaeq = paramClient;
    zaer = paramLogger;
    zaes = paramClientSettings;
    zacd = paramAbstractClientBuilder;
    zabm.respondWith(this);
  }
  
  public final Api.Client showToast(Looper paramLooper, GoogleApiManager.zaa paramZaa)
  {
    zaer.v(paramZaa);
    return zaeq;
  }
  
  public final zace showToast(Context paramContext, Handler paramHandler)
  {
    return new zace(paramContext, paramHandler, zaes, zacd);
  }
  
  public final Api.Client zaab()
  {
    return zaeq;
  }
}
