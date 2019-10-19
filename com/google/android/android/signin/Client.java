package com.google.android.android.signin;

import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.internal.IAccountAccessor;
import com.google.android.android.signin.internal.Session;

public abstract interface Client
  extends Api.Client
{
  public abstract void connect();
  
  public abstract void disconnect(IAccountAccessor paramIAccountAccessor, boolean paramBoolean);
  
  public abstract void execute(Session paramSession);
  
  public abstract void zacv();
}
