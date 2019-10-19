package com.google.android.android.common.util;

import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Base64Utils
{
  public Base64Utils() {}
  
  public static byte[] decode(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Base64.decode(paramString, 0);
  }
  
  public static byte[] decodeUrlSafe(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Base64.decode(paramString, 10);
  }
  
  public static byte[] decodeUrlSafeNoPadding(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Base64.decode(paramString, 11);
  }
  
  public static String encode(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 0);
  }
  
  public static String encodeUrlSafe(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 10);
  }
  
  public static String encodeUrlSafeNoPadding(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 11);
  }
}
