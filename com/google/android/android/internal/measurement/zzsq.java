package com.google.android.android.internal.measurement;

import android.util.Log;

final class zzsq
  extends com.google.android.gms.internal.measurement.zzsl<Integer>
{
  zzsq(zzsv paramZzsv, String paramString, Integer paramInteger)
  {
    super(paramZzsv, paramString, paramInteger, null);
  }
  
  private final Integer zzfl(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return Integer.valueOf(i);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      String str;
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    str = zzbrc;
    localStringBuilder = new StringBuilder(String.valueOf(str).length() + 28 + String.valueOf(paramString).length());
    localStringBuilder.append("Invalid integer value for ");
    localStringBuilder.append(str);
    localStringBuilder.append(": ");
    localStringBuilder.append(paramString);
    Log.e("PhenotypeFlag", localStringBuilder.toString());
    return null;
  }
}
