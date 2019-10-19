package com.google.android.android.signin;

import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Scope;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.SignInClientImpl;

public final class Subscription
{
  private static final Scope $VALUES = new Scope("email");
  private static final com.google.android.gms.common.api.Api.ClientKey<SignInClientImpl> CLIENT_KEY = new com.google.android.android.common.aimsicd.Api.ClientKey();
  private static final Scope GLOBAL;
  public static final Api<SignInOptions> NULL_KEY = new Sample("SignIn.API", zapg, CLIENT_KEY);
  public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zapg;
  @ShowFirstParty
  private static final com.google.android.gms.common.api.Api.ClientKey<SignInClientImpl> zarp = new com.google.android.android.common.aimsicd.Api.ClientKey();
  private static final Api.AbstractClientBuilder<SignInClientImpl, Object> zarq;
  private static final Api<Object> zarr = new Sample("SignIn.INTERNAL_API", zarq, zarp);
  
  static
  {
    zapg = new ASN1Null();
    zarq = new ASN1Integer();
    GLOBAL = new Scope("profile");
  }
}
