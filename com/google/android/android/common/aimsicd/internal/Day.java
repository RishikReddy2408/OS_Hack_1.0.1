package com.google.android.android.common.aimsicd.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;

public final class Day
  extends ActivityLifecycleObserver
{
  private final WeakReference<com.google.android.gms.common.api.internal.zaa.zaa> zack;
  
  public Day(Activity paramActivity)
  {
    this(zaa.zaa.descend(paramActivity));
  }
  
  private Day(zaa.zaa paramZaa)
  {
    zack = new WeakReference(paramZaa);
  }
  
  public final ActivityLifecycleObserver onStopCallOnce(Runnable paramRunnable)
  {
    zaa.zaa localZaa = (zaa.zaa)zack.get();
    if (localZaa != null)
    {
      zaa.zaa.runInBackground(localZaa, paramRunnable);
      return this;
    }
    throw new IllegalStateException("The target activity has already been GC'd");
  }
}
