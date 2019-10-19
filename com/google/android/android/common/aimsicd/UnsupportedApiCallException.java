package com.google.android.android.common.aimsicd;

import com.google.android.android.common.Feature;

public final class UnsupportedApiCallException
  extends UnsupportedOperationException
{
  private final Feature zzar;
  
  public UnsupportedApiCallException(Feature paramFeature)
  {
    zzar = paramFeature;
  }
  
  public final String getMessage()
  {
    String str = String.valueOf(zzar);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 8);
    localStringBuilder.append("Missing ");
    localStringBuilder.append(str);
    return localStringBuilder.toString();
  }
}
