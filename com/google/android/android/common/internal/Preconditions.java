package com.google.android.android.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.android.common.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Preconditions
{
  private Preconditions()
  {
    throw new AssertionError("Uninstantiable");
  }
  
  public static void checkArgument(boolean paramBoolean)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalArgumentException();
  }
  
  public static void checkArgument(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static void checkArgument(boolean paramBoolean, String paramString, Object... paramVarArgs)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalArgumentException(String.format(paramString, paramVarArgs));
  }
  
  public static void checkHandlerThread(Handler paramHandler)
  {
    if (Looper.myLooper() == paramHandler.getLooper()) {
      return;
    }
    throw new IllegalStateException("Must be called on the handler thread");
  }
  
  public static void checkMainThread(String paramString)
  {
    if (Log.isMainThread()) {
      return;
    }
    throw new IllegalStateException(paramString);
  }
  
  public static String checkNotEmpty(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    throw new IllegalArgumentException("Given String is empty or null");
  }
  
  public static String checkNotEmpty(String paramString, Object paramObject)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static void checkNotMainThread()
  {
    checkNotMainThread("Must not be called on the main application thread");
  }
  
  public static void checkNotMainThread(String paramString)
  {
    if (!Log.isMainThread()) {
      return;
    }
    throw new IllegalStateException(paramString);
  }
  
  public static Object checkNotNull(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject;
    }
    throw new NullPointerException("null reference");
  }
  
  public static Object checkNotNull(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != null) {
      return paramObject1;
    }
    throw new NullPointerException(String.valueOf(paramObject2));
  }
  
  public static int checkNotZero(int paramInt)
  {
    if (paramInt != 0) {
      return paramInt;
    }
    throw new IllegalArgumentException("Given Integer is zero");
  }
  
  public static int checkNotZero(int paramInt, Object paramObject)
  {
    if (paramInt != 0) {
      return paramInt;
    }
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static long checkNotZero(long paramLong)
  {
    if (paramLong != 0L) {
      return paramLong;
    }
    throw new IllegalArgumentException("Given Long is zero");
  }
  
  public static long checkNotZero(long paramLong, Object paramObject)
  {
    if (paramLong != 0L) {
      return paramLong;
    }
    throw new IllegalArgumentException(String.valueOf(paramObject));
  }
  
  public static void checkState(boolean paramBoolean)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalStateException();
  }
  
  public static void checkState(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalStateException(String.valueOf(paramObject));
  }
  
  public static void checkState(boolean paramBoolean, String paramString, Object... paramVarArgs)
  {
    if (paramBoolean) {
      return;
    }
    throw new IllegalStateException(String.format(paramString, paramVarArgs));
  }
}
