package com.google.android.android.common.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Asserts
{
  private Asserts()
  {
    throw new AssertionError("Uninstantiable");
  }
  
  public static void checkMainThread(String paramString)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
      return;
    }
    String str1 = String.valueOf(Thread.currentThread());
    String str2 = String.valueOf(Looper.getMainLooper().getThread());
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 57 + String.valueOf(str2).length());
    localStringBuilder.append("checkMainThread: current thread ");
    localStringBuilder.append(str1);
    localStringBuilder.append(" IS NOT the main thread ");
    localStringBuilder.append(str2);
    localStringBuilder.append("!");
    Log.e("Asserts", localStringBuilder.toString());
    throw new IllegalStateException(paramString);
  }
  
  public static void checkNotMainThread(String paramString)
  {
    if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
      return;
    }
    String str1 = String.valueOf(Thread.currentThread());
    String str2 = String.valueOf(Looper.getMainLooper().getThread());
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 56 + String.valueOf(str2).length());
    localStringBuilder.append("checkNotMainThread: current thread ");
    localStringBuilder.append(str1);
    localStringBuilder.append(" IS the main thread ");
    localStringBuilder.append(str2);
    localStringBuilder.append("!");
    Log.e("Asserts", localStringBuilder.toString());
    throw new IllegalStateException(paramString);
  }
  
  public static void checkNotNull(Object paramObject)
  {
    if (paramObject != null) {
      return;
    }
    throw new IllegalArgumentException("null reference");
  }
  
  public static void checkNotNull(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != null) {
      return;
    }
    throw new IllegalArgumentException(String.valueOf(paramObject2));
  }
  
  public static void checkNull(Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    throw new IllegalArgumentException("non-null reference");
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
}
