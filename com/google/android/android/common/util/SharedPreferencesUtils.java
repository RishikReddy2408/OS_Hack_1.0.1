package com.google.android.android.common.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.File;

@KeepForSdk
public class SharedPreferencesUtils
{
  private SharedPreferencesUtils() {}
  
  public static void publishWorldReadableSharedPreferences(Context paramContext, SharedPreferences.Editor paramEditor, String paramString)
  {
    paramContext = new File(getApplicationInfodataDir, "shared_prefs");
    File localFile = paramContext.getParentFile();
    if (localFile != null) {
      localFile.setExecutable(true, false);
    }
    paramContext.setExecutable(true, false);
    paramEditor.commit();
    new File(paramContext, String.valueOf(paramString).concat(".xml")).setReadable(true, false);
  }
}
