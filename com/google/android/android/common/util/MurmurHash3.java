package com.google.android.android.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class MurmurHash3
{
  private MurmurHash3() {}
  
  public static int murmurhash3_x86_32(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = (paramInt2 & 0xFFFFFFFC) + paramInt1;
    while (paramInt1 < j)
    {
      i = (paramArrayOfByte[paramInt1] & 0xFF | (paramArrayOfByte[(paramInt1 + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 2)] & 0xFF) << 16 | paramArrayOfByte[(paramInt1 + 3)] << 24) * -862048943;
      paramInt3 ^= (i << 15 | i >>> 17) * 461845907;
      paramInt3 = (paramInt3 >>> 19 | paramInt3 << 13) * 5 - 430675100;
      paramInt1 += 4;
    }
    int i = 0;
    paramInt1 = 0;
    switch (paramInt2 & 0x3)
    {
    default: 
      break;
    case 3: 
      paramInt1 = (paramArrayOfByte[(j + 2)] & 0xFF) << 16;
    case 2: 
      i = paramInt1 | (paramArrayOfByte[(j + 1)] & 0xFF) << 8;
    case 1: 
      paramInt1 = (paramArrayOfByte[j] & 0xFF | i) * -862048943;
      paramInt3 ^= (paramInt1 >>> 17 | paramInt1 << 15) * 461845907;
    }
    paramInt1 = paramInt3 ^ paramInt2;
    paramInt1 = (paramInt1 ^ paramInt1 >>> 16) * -2048144789;
    paramInt1 = (paramInt1 ^ paramInt1 >>> 13) * -1028477387;
    return paramInt1 ^ paramInt1 >>> 16;
  }
}
