package com.google.android.android.internal.measurement;

import android.net.Uri;

public final class zzsv
{
  private final String zzbrm = null;
  private final Uri zzbrn;
  private final String zzbro;
  private final String zzbrp;
  private final boolean zzbrq;
  private final boolean zzbrr;
  private final boolean zzbrs;
  
  public zzsv(Uri paramUri)
  {
    this(null, paramUri, "", "", false, false, false);
  }
  
  private zzsv(String paramString1, Uri paramUri, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    zzbrn = paramUri;
    zzbro = paramString2;
    zzbrp = paramString3;
    zzbrq = false;
    zzbrr = false;
    zzbrs = false;
  }
  
  public final zzsl apply(String paramString, double paramDouble)
  {
    return zzsl.format(this, paramString, paramDouble);
  }
  
  public final zzsl cast(String paramString, int paramInt)
  {
    return zzsl.toString(this, paramString, paramInt);
  }
  
  public final zzsl insertOrThrow(String paramString1, String paramString2)
  {
    return zzsl.getVersion(this, paramString1, paramString2);
  }
  
  public final zzsl set(String paramString, long paramLong)
  {
    return zzsl.getTime(this, paramString, paramLong);
  }
  
  public final zzsl value(String paramString, boolean paramBoolean)
  {
    return zzsl.toString(this, paramString, paramBoolean);
  }
}
