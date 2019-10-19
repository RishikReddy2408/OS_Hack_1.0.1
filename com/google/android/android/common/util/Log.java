package com.google.android.android.common.util;

import android.os.Looper;

public final class Log
{
  public static boolean isMainThread()
  {
    return Looper.getMainLooper() == Looper.myLooper();
  }
}
