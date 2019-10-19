package com.google.android.android.internal.measurement;

import android.util.Log;

final class zzss
  extends com.google.android.gms.internal.measurement.zzsl<Double>
{
  zzss(zzsv paramZzsv, String paramString, Double paramDouble)
  {
    super(paramZzsv, paramString, paramDouble, null);
  }
  
  private final Double zzfm(String paramString)
  {
    try
    {
      double d = Double.parseDouble(paramString);
      return Double.valueOf(d);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      String str;
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    str = zzbrc;
    localStringBuilder = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(paramString).length());
    localStringBuilder.append("Invalid double value for ");
    localStringBuilder.append(str);
    localStringBuilder.append(": ");
    localStringBuilder.append(paramString);
    Log.e("PhenotypeFlag", localStringBuilder.toString());
    return null;
  }
}
