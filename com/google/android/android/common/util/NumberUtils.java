package com.google.android.android.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
@VisibleForTesting
public class NumberUtils
{
  private NumberUtils() {}
  
  public static long parseHexLong(String paramString)
  {
    if (paramString.length() <= 16)
    {
      if (paramString.length() == 16)
      {
        long l = Long.parseLong(paramString.substring(8), 16);
        return Long.parseLong(paramString.substring(0, 8), 16) << 32 | l;
      }
      return Long.parseLong(paramString, 16);
    }
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 37);
    localStringBuilder.append("Invalid input: ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" exceeds 16 characters");
    throw new NumberFormatException(localStringBuilder.toString());
  }
}
