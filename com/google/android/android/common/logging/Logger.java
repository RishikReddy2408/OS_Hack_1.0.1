package com.google.android.android.common.logging;

import android.util.Log;
import com.google.android.android.common.internal.GmsLogger;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Locale;

@KeepForSdk
public class Logger
{
  private final String mTag;
  private final String zzei;
  private final GmsLogger zzew;
  private final int zzex;
  
  private Logger(String paramString1, String paramString2)
  {
    zzei = paramString2;
    mTag = paramString1;
    zzew = new GmsLogger(paramString1);
    int i = 2;
    while ((7 >= i) && (!Log.isLoggable(mTag, i))) {
      i += 1;
    }
    zzex = i;
  }
  
  public Logger(String paramString, String... paramVarArgs)
  {
    this(paramString, paramVarArgs);
  }
  
  private final String format(String paramString, Object... paramVarArgs)
  {
    String str = paramString;
    if (paramVarArgs != null)
    {
      str = paramString;
      if (paramVarArgs.length > 0) {
        str = String.format(Locale.US, paramString, paramVarArgs);
      }
    }
    return zzei.concat(str);
  }
  
  public void d(String paramString, Object... paramVarArgs)
  {
    if (isLoggable(3)) {
      Log.d(mTag, format(paramString, paramVarArgs));
    }
  }
  
  public void e(String paramString, Throwable paramThrowable, Object... paramVarArgs)
  {
    Log.e(mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  public void i(String paramString, Object... paramVarArgs)
  {
    Log.i(mTag, format(paramString, paramVarArgs));
  }
  
  public boolean isLoggable(int paramInt)
  {
    return zzex <= paramInt;
  }
  
  public void log(String paramString, Throwable paramThrowable, Object... paramVarArgs)
  {
    Log.wtf(mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  public void log(String paramString, Object... paramVarArgs)
  {
    Log.e(mTag, format(paramString, paramVarArgs));
  }
  
  public void log(Throwable paramThrowable)
  {
    Log.wtf(mTag, paramThrowable);
  }
  
  public void v(String paramString, Object... paramVarArgs)
  {
    if (isLoggable(2)) {
      Log.v(mTag, format(paramString, paramVarArgs));
    }
  }
  
  public void w(String paramString, Object... paramVarArgs)
  {
    Log.w(mTag, format(paramString, paramVarArgs));
  }
}
