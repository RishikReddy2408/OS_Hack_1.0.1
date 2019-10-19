package com.google.android.android.internal.base;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;

public class Credentials
  extends Handler
{
  private static volatile Predicates.AlwaysTruePredicate zaro;
  
  public Credentials() {}
  
  public Credentials(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public Credentials(Looper paramLooper, Handler.Callback paramCallback)
  {
    super(paramLooper, paramCallback);
  }
}
