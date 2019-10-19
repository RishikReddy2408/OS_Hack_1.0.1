package com.google.android.android.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
@ShowFirstParty
public abstract interface Clock
{
  public abstract long currentThreadTimeMillis();
  
  public abstract long currentTimeMillis();
  
  public abstract long elapsedRealtime();
  
  public abstract long nanoTime();
}
