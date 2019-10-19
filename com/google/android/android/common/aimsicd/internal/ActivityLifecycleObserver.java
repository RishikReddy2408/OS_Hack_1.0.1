package com.google.android.android.common.aimsicd.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class ActivityLifecycleObserver
{
  public ActivityLifecycleObserver() {}
  
  public static final ActivityLifecycleObserver getData(Activity paramActivity)
  {
    return new Day(paramActivity);
  }
  
  public abstract ActivityLifecycleObserver onStopCallOnce(Runnable paramRunnable);
}
