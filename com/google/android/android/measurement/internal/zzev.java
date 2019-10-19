package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;

final class zzev
{
  private long startTime;
  private final Clock zzrz;
  
  public zzev(Clock paramClock)
  {
    Preconditions.checkNotNull(paramClock);
    zzrz = paramClock;
  }
  
  public final void clear()
  {
    startTime = 0L;
  }
  
  public final void start()
  {
    startTime = zzrz.elapsedRealtime();
  }
  
  public final boolean update(long paramLong)
  {
    if (startTime == 0L) {
      return true;
    }
    return zzrz.elapsedRealtime() - startTime >= 3600000L;
  }
}
