package com.google.android.android.common.internal.service;

import com.google.android.android.common.aimsicd.Sample;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.internal.service.zai;

public final class Common
{
  @KeepForSdk
  public static final com.google.android.gms.common.api.Api.ClientKey<zai> CLIENT_KEY = new com.google.android.android.common.aimsicd.Api.ClientKey();
  @KeepForSdk
  public static final Api<Api.ApiOptions.NoOptions> packageName = new Sample("Common.API", zapg, CLIENT_KEY);
  private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zapg = new BackupWrapper.FroyoAndBeyond();
  public static final Location zaph = new MXParser();
  
  public Common() {}
}
