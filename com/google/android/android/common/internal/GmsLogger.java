package com.google.android.android.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class GmsLogger
{
  private static final int zzef = 15;
  private static final String zzeg;
  private final String zzeh;
  private final String zzei;
  
  public GmsLogger(String paramString)
  {
    this(paramString, null);
  }
  
  public GmsLogger(String paramString1, String paramString2)
  {
    Preconditions.checkNotNull(paramString1, "log tag cannot be null");
    boolean bool;
    if (paramString1.length() <= 23) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
    zzeh = paramString1;
    if ((paramString2 != null) && (paramString2.length() > 0))
    {
      zzei = paramString2;
      return;
    }
    zzei = null;
  }
  
  private final String formatString(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, paramVarArgs);
    if (zzei == null) {
      return paramString;
    }
    return zzei.concat(paramString);
  }
  
  private final String toString(String paramString)
  {
    if (zzei == null) {
      return paramString;
    }
    return zzei.concat(paramString);
  }
  
  public final void append(String paramString1, String paramString2)
  {
    if (canLog(6)) {
      Log.e(paramString1, toString(paramString2));
    }
  }
  
  public final boolean canLog(int paramInt)
  {
    return Log.isLoggable(zzeh, paramInt);
  }
  
  public final boolean canLogPii()
  {
    return false;
  }
  
  public final void create(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLog(6)) {
      Log.e(paramString1, toString(paramString2), paramThrowable);
    }
  }
  
  public final void d(String paramString1, String paramString2)
  {
    if (canLog(3)) {
      Log.d(paramString1, toString(paramString2));
    }
  }
  
  public final void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLog(3)) {
      Log.d(paramString1, toString(paramString2), paramThrowable);
    }
  }
  
  public final void efmt(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (canLog(6)) {
      Log.e(paramString1, formatString(paramString2, paramVarArgs));
    }
  }
  
  public final void i(String paramString1, String paramString2)
  {
    if (canLog(4)) {
      Log.i(paramString1, toString(paramString2));
    }
  }
  
  public final void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLog(4)) {
      Log.i(paramString1, toString(paramString2), paramThrowable);
    }
  }
  
  public final void loadFile(String paramString1, String paramString2)
  {
    if (canLogPii())
    {
      paramString1 = String.valueOf(paramString1);
      String str = String.valueOf(" PII_LOG");
      if (str.length() != 0) {
        paramString1 = paramString1.concat(str);
      } else {
        paramString1 = new String(paramString1);
      }
      Log.i(paramString1, toString(paramString2));
    }
  }
  
  public final void loadFile(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLogPii())
    {
      paramString1 = String.valueOf(paramString1);
      String str = String.valueOf(" PII_LOG");
      if (str.length() != 0) {
        paramString1 = paramString1.concat(str);
      } else {
        paramString1 = new String(paramString1);
      }
      Log.i(paramString1, toString(paramString2), paramThrowable);
    }
  }
  
  public final void v(String paramString1, String paramString2)
  {
    if (canLog(2)) {
      Log.v(paramString1, toString(paramString2));
    }
  }
  
  public final void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLog(2)) {
      Log.v(paramString1, toString(paramString2), paramThrowable);
    }
  }
  
  public final void w(String paramString1, String paramString2)
  {
    if (canLog(5)) {
      Log.w(paramString1, toString(paramString2));
    }
  }
  
  public final void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLog(5)) {
      Log.w(paramString1, toString(paramString2), paramThrowable);
    }
  }
  
  public final void wfmt(String paramString1, String paramString2, Object... paramVarArgs)
  {
    if (canLog(5)) {
      Log.w(zzeh, formatString(paramString2, paramVarArgs));
    }
  }
  
  public final void wtf(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (canLog(7))
    {
      Log.e(paramString1, toString(paramString2), paramThrowable);
      Log.wtf(paramString1, toString(paramString2), paramThrowable);
    }
  }
}
