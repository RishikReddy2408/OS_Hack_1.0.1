package com.google.android.android.common;

import java.util.Arrays;

final class Sha256Hash
  extends Type
{
  private final byte[] bytes;
  
  Sha256Hash(byte[] paramArrayOfByte)
  {
    super(Arrays.copyOfRange(paramArrayOfByte, 0, 25));
    bytes = paramArrayOfByte;
  }
  
  final byte[] getBytes()
  {
    return bytes;
  }
}
