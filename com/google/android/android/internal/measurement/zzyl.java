package com.google.android.android.internal.measurement;

import java.nio.ByteBuffer;

abstract class zzyl
{
  zzyl() {}
  
  static void decode(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    int i2 = paramCharSequence.length();
    int i1 = paramByteBuffer.position();
    j = i1;
    k = 0;
    if (k < i2)
    {
      n = j;
      m = k;
    }
    for (;;)
    {
      try
      {
        int i3 = paramCharSequence.charAt(k);
        if (i3 < 128)
        {
          b = (byte)i3;
          n = j;
          m = k;
          paramByteBuffer.put(i1 + k, b);
          k += 1;
          break;
        }
        if (k == i2)
        {
          n = j;
          m = k;
          paramByteBuffer.position(i1 + k);
          return;
        }
        j = i1 + k;
        if (k < i2)
        {
          n = j;
          m = k;
          i = paramCharSequence.charAt(k);
          if (i < 128)
          {
            b = (byte)i;
            n = j;
            m = k;
            paramByteBuffer.put(j, b);
          }
          else if (i < 2048)
          {
            m = j + 1;
            b = (byte)(i >>> 6 | 0xC0);
          }
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException1)
      {
        byte b;
        int i;
        j = n;
        k = m;
        continue;
      }
      try
      {
        paramByteBuffer.put(j, b);
        b = (byte)(i & 0x3F | 0x80);
        paramByteBuffer.put(m, b);
        j = m;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException2)
      {
        j = m;
        continue;
        continue;
      }
      continue;
      if ((i >= 55296) && (57343 >= i))
      {
        m = k + 1;
        if (m == i2) {}
      }
      try
      {
        char c2 = paramCharSequence.charAt(m);
        boolean bool = Character.isSurrogatePair(i, c2);
        if (bool)
        {
          i1 = Character.toCodePoint(i, c2);
          k = j + 1;
          b = (byte)(i1 >>> 18 | 0xF0);
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException3)
      {
        continue;
      }
      try
      {
        paramByteBuffer.put(j, b);
        n = k + 1;
        b = (byte)(i1 >>> 12 & 0x3F | 0x80);
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException4)
      {
        j = k;
        continue;
      }
      try
      {
        paramByteBuffer.put(k, b);
        j = n + 1;
        b = (byte)(i1 >>> 6 & 0x3F | 0x80);
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException5)
      {
        j = n;
        continue;
      }
      try
      {
        paramByteBuffer.put(n, b);
        b = (byte)(i1 & 0x3F | 0x80);
        paramByteBuffer.put(j, b);
        k = m;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException6)
      {
        continue;
      }
      continue;
      k = m;
      continue;
      k = m;
      try
      {
        zzyn localZzyn = new zzyn(k, i2);
        throw localZzyn;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException7)
      {
        continue;
      }
      n = j + 1;
      b = (byte)(i >>> 12 | 0xE0);
      try
      {
        paramByteBuffer.put(j, b);
        j = n + 1;
        b = (byte)(i >>> 6 & 0x3F | 0x80);
        m = j;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException8)
      {
        char c1;
        j = n;
      }
      try
      {
        paramByteBuffer.put(n, b);
        b = (byte)(i & 0x3F | 0x80);
        m = j;
        paramByteBuffer.put(j, b);
        k += 1;
        j += 1;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException9)
      {
        j = m;
      }
    }
    m = j;
    paramByteBuffer.position(j);
    return;
    m = paramByteBuffer.position();
    j = Math.max(k, j - paramByteBuffer.position() + 1);
    c1 = paramCharSequence.charAt(k);
    paramCharSequence = new StringBuilder(37);
    paramCharSequence.append("Failed writing ");
    paramCharSequence.append(c1);
    paramCharSequence.append(" at index ");
    paramCharSequence.append(m + j);
    throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
  }
  
  abstract int decode(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  abstract void encode(CharSequence paramCharSequence, ByteBuffer paramByteBuffer);
  
  abstract int parse(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);
  
  final boolean processBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return parse(0, paramArrayOfByte, paramInt1, paramInt2) == 0;
  }
  
  abstract String read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws zzvt;
}
