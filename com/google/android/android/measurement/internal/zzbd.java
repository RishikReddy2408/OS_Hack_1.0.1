package com.google.android.android.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.android.common.internal.Preconditions;

public final class zzbd
{
  private long value;
  private boolean zzanx;
  private final long zzanz;
  private final String zzoj;
  
  public zzbd(zzba paramZzba, String paramString, long paramLong)
  {
    Preconditions.checkNotEmpty(paramString);
    zzoj = paramString;
    zzanz = paramLong;
  }
  
  public final void getFolder(long paramLong)
  {
    SharedPreferences.Editor localEditor = zzba.prefs(zzany).edit();
    localEditor.putLong(zzoj, paramLong);
    localEditor.apply();
    value = paramLong;
  }
  
  public final long readLong()
  {
    if (!zzanx)
    {
      zzanx = true;
      value = zzba.prefs(zzany).getLong(zzoj, zzanz);
    }
    return value;
  }
}
