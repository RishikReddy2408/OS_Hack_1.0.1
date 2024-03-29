package com.google.android.android.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.android.common.R.string;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class StringResourceValueReader
{
  private final Resources zzeu;
  private final String zzev;
  
  public StringResourceValueReader(Context paramContext)
  {
    Preconditions.checkNotNull(paramContext);
    zzeu = paramContext.getResources();
    zzev = zzeu.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
  }
  
  public String getString(String paramString)
  {
    int i = zzeu.getIdentifier(paramString, "string", zzev);
    if (i == 0) {
      return null;
    }
    return zzeu.getString(i);
  }
}
