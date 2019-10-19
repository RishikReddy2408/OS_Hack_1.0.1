package com.google.android.android.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.android.common.internal.Preconditions;

public final class zzbc
{
  private boolean value;
  private final boolean zzanw;
  private boolean zzanx;
  private final String zzoj;
  
  public zzbc(zzba paramZzba, String paramString, boolean paramBoolean)
  {
    Preconditions.checkNotEmpty(paramString);
    zzoj = paramString;
    zzanw = true;
  }
  
  public final boolean getBool()
  {
    if (!zzanx)
    {
      zzanx = true;
      value = zzba.prefs(zzany).getBoolean(zzoj, zzanw);
    }
    return value;
  }
  
  public final void store(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = zzba.prefs(zzany).edit();
    localEditor.putBoolean(zzoj, paramBoolean);
    localEditor.apply();
    value = paramBoolean;
  }
}
