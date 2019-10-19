package com.google.android.android.internal.common;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;

public class Timer
  extends Handler
{
  private static volatile PasswordProvider zzit;
  
  public Timer() {}
  
  public Timer(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public Timer(Looper paramLooper, Handler.Callback paramCallback)
  {
    super(paramLooper, paramCallback);
  }
}
