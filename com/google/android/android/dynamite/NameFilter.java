package com.google.android.android.dynamite;

import android.content.Context;

final class NameFilter
  implements DynamiteModule.VersionPolicy
{
  NameFilter() {}
  
  public final DynamiteModule.VersionPolicy.zzb blur(Context paramContext, String paramString, DynamiteModule.VersionPolicy.zza paramZza)
    throws DynamiteModule.LoadingException
  {
    DynamiteModule.VersionPolicy.zzb localZzb = new DynamiteModule.VersionPolicy.zzb();
    zziq = paramZza.getLocalVersion(paramContext, paramString);
    if (zziq != 0)
    {
      zzis = -1;
      return localZzb;
    }
    zzir = paramZza.copy(paramContext, paramString, true);
    if (zzir != 0) {
      zzis = 1;
    }
    return localZzb;
  }
}
