package com.google.android.android.measurement.internal;

import android.os.Bundle;

public final class zzak
{
  public final String origin;
  public final long zzadt;
  public final long zzadu;
  public final boolean zzadv;
  public final String zzadw;
  public final String zzadx;
  public final Bundle zzady;
  
  zzak(long paramLong1, long paramLong2, boolean paramBoolean, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    zzadt = paramLong1;
    zzadu = paramLong2;
    zzadv = paramBoolean;
    zzadw = paramString1;
    origin = paramString2;
    zzadx = paramString3;
    zzady = paramBundle;
  }
  
  public static final zzak getValue(Bundle paramBundle)
  {
    return new zzak(0L, 0L, true, null, null, null, paramBundle);
  }
}
