package com.google.android.android.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.android.common.internal.Preconditions;

public final class zzbf
{
  private String value;
  private boolean zzanx;
  private final String zzaod;
  private final String zzoj;
  
  public zzbf(zzba paramZzba, String paramString1, String paramString2)
  {
    Preconditions.checkNotEmpty(paramString1);
    zzoj = paramString1;
    zzaod = null;
  }
  
  public final void zzcc(String paramString)
  {
    if (zzfk.verifySignature(paramString, value)) {
      return;
    }
    SharedPreferences.Editor localEditor = zzba.prefs(zzany).edit();
    localEditor.putString(zzoj, paramString);
    localEditor.apply();
    value = paramString;
  }
  
  public final String zzjz()
  {
    if (!zzanx)
    {
      zzanx = true;
      value = zzba.prefs(zzany).getString(zzoj, null);
    }
    return value;
  }
}
