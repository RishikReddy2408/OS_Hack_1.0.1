package com.google.android.android.dynamite;

import android.content.Context;

final class Macro
  implements DynamiteModule.VersionPolicy
{
  Macro() {}
  
  public final DynamiteModule.VersionPolicy.zzb blur(Context paramContext, String paramString, DynamiteModule.VersionPolicy.zza paramZza)
    throws DynamiteModule.LoadingException
  {
    DynamiteModule.VersionPolicy.zzb localZzb = new DynamiteModule.VersionPolicy.zzb();
    zzir = paramZza.copy(paramContext, paramString, true);
    if (zzir != 0)
    {
      zzis = 1;
      return localZzb;
    }
    zziq = paramZza.getLocalVersion(paramContext, paramString);
    if (zziq != 0) {
      zzis = -1;
    }
    return localZzb;
  }
}
