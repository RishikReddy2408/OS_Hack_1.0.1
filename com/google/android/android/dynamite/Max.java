package com.google.android.android.dynamite;

import android.content.Context;

final class Max
  implements DynamiteModule.VersionPolicy.zza
{
  Max() {}
  
  public final int copy(Context paramContext, String paramString, boolean paramBoolean)
    throws DynamiteModule.LoadingException
  {
    return DynamiteModule.create(paramContext, paramString, paramBoolean);
  }
  
  public final int getLocalVersion(Context paramContext, String paramString)
  {
    return DynamiteModule.getLocalVersion(paramContext, paramString);
  }
}
