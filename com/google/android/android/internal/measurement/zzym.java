package com.google.android.android.internal.measurement;

import java.nio.ByteBuffer;

final class zzym
  extends zzyl
{
  zzym() {}
  
  final int decode(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int k = paramCharSequence.length();
    int m = paramInt2 + paramInt1;
    paramInt2 = 0;
    int n;
    while (paramInt2 < k)
    {
      j = paramInt2 + paramInt1;
      if (j >= m) {
        break;
      }
      n = paramCharSequence.charAt(paramInt2);
      if (n >= 128) {
        break;
      }
      paramArrayOfByte[j] = ((byte)n);
      paramInt2 += 1;
    }
    if (paramInt2 == k) {
      return paramInt1 + k;
    }
    int j = paramInt1 + paramInt2;
    paramInt1 = paramInt2;
    while (paramInt1 < k)
    {
      int i = paramCharSequence.charAt(paramInt1);
      if ((i < 128) && (j < m))
      {
        paramInt2 = j + 1;
        paramArrayOfByte[j] = ((byte)i);
      }
      for (;;)
      {
        break;
        if ((i < 2048) && (j <= m - 2))
        {
          n = j + 1;
          paramArrayOfByte[j] = ((byte)(i >>> 6 | 0x3C0));
          paramInt2 = n + 1;
          paramArrayOfByte[n] = ((byte)(i & 0x3F | 0x80));
        }
        else if (((i < 55296) || (57343 < i)) && (j <= m - 3))
        {
          paramInt2 = j + 1;
          paramArrayOfByte[j] = ((byte)(i >>> 12 | 0x1E0));
          j = paramInt2 + 1;
          paramArrayOfByte[paramInt2] = ((byte)(i >>> 6 & 0x3F | 0x80));
          paramInt2 = j + 1;
          paramArrayOfByte[j] = ((byte)(i & 0x3F | 0x80));
        }
        else
        {
          if (j > m - 4) {
            break label456;
          }
          paramInt2 = paramInt1 + 1;
          if (paramInt2 == paramCharSequence.length()) {
            break label443;
          }
          char c = paramCharSequence.charAt(paramInt2);
          if (!Character.isSurrogatePair(i, c)) {
            break label440;
          }
          paramInt1 = Character.toCodePoint(i, c);
          n = j + 1;
          paramArrayOfByte[j] = ((byte)(paramInt1 >>> 18 | 0xF0));
          j = n + 1;
          paramArrayOfByte[n] = ((byte)(paramInt1 >>> 12 & 0x3F | 0x80));
          n = j + 1;
          paramArrayOfByte[j] = ((byte)(paramInt1 >>> 6 & 0x3F | 0x80));
          j = n + 1;
          paramArrayOfByte[n] = ((byte)(paramInt1 & 0x3F | 0x80));
          paramInt1 = paramInt2;
          paramInt2 = j;
        }
      }
      paramInt1 += 1;
      j = paramInt2;
      continue;
      label440:
      paramInt1 = paramInt2;
      label443:
      throw new zzyn(paramInt1 - 1, k);
      label456:
      if ((55296 <= i) && (i <= 57343))
      {
        paramInt2 = paramInt1 + 1;
        if ((paramInt2 == paramCharSequence.length()) || (!Character.isSurrogatePair(i, paramCharSequence.charAt(paramInt2)))) {
          throw new zzyn(paramInt1, k);
        }
      }
      paramCharSequence = new StringBuilder(37);
      paramCharSequence.append("Failed writing ");
      paramCharSequence.append(i);
      paramCharSequence.append(" at index ");
      paramCharSequence.append(j);
      throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
    }
    return j;
  }
  
  final void encode(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    zzyl.decode(paramCharSequence, paramByteBuffer);
  }
  
  final int parse(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    while ((paramInt2 < paramInt3) && (paramArrayOfByte[paramInt2] >= 0)) {
      paramInt2 += 1;
    }
    paramInt1 = paramInt2;
    if (paramInt2 >= paramInt3) {
      return 0;
    }
    for (;;)
    {
      if (paramInt1 >= paramInt3) {
        return 0;
      }
      paramInt2 = paramInt1 + 1;
      int i = paramArrayOfByte[paramInt1];
      paramInt1 = paramInt2;
      if (i < 0) {
        if (i < -32)
        {
          if (paramInt2 >= paramInt3) {
            return i;
          }
          if (i < -62) {
            break;
          }
          paramInt1 = paramInt2 + 1;
          if (paramArrayOfByte[paramInt2] > -65) {
            return -1;
          }
        }
        else if (i < -16)
        {
          if (paramInt2 >= paramInt3 - 1) {
            return zzyj.process(paramArrayOfByte, paramInt2, paramInt3);
          }
          int j = paramInt2 + 1;
          paramInt1 = paramArrayOfByte[paramInt2];
          if ((paramInt1 > -65) || ((i == -32) && (paramInt1 < -96)) || ((i == -19) && (paramInt1 >= -96))) {
            break;
          }
          paramInt1 = j + 1;
          if (paramArrayOfByte[j] > -65) {
            return -1;
          }
        }
        else
        {
          if (paramInt2 >= paramInt3 - 2) {
            return zzyj.process(paramArrayOfByte, paramInt2, paramInt3);
          }
          paramInt1 = paramInt2 + 1;
          paramInt2 = paramArrayOfByte[paramInt2];
          if ((paramInt2 > -65) || ((i << 28) + (paramInt2 + 112) >> 30 != 0)) {
            break;
          }
          paramInt2 = paramInt1 + 1;
          if (paramArrayOfByte[paramInt1] > -65) {
            break;
          }
          paramInt1 = paramInt2 + 1;
          if (paramArrayOfByte[paramInt2] > -65) {
            return -1;
          }
        }
      }
    }
    return -1;
  }
  
  final String read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws zzvt
  {
    if ((paramInt1 | paramInt2 | paramArrayOfByte.length - paramInt1 - paramInt2) >= 0)
    {
      int k = paramInt1 + paramInt2;
      char[] arrayOfChar = new char[paramInt2];
      int i = 0;
      int j = paramInt1;
      byte b1;
      for (;;)
      {
        paramInt2 = j;
        paramInt1 = i;
        if (j >= k) {
          break;
        }
        b1 = paramArrayOfByte[j];
        paramInt2 = j;
        paramInt1 = i;
        if (!zzyk.put(b1)) {
          break;
        }
        j += 1;
        zzyk.write(b1, arrayOfChar, i);
        i += 1;
      }
      while (paramInt2 < k)
      {
        i = paramInt2 + 1;
        b1 = paramArrayOfByte[paramInt2];
        if (zzyk.put(b1))
        {
          paramInt2 = paramInt1 + 1;
          zzyk.write(b1, arrayOfChar, paramInt1);
          paramInt1 = paramInt2;
          paramInt2 = i;
          while (paramInt2 < k)
          {
            b1 = paramArrayOfByte[paramInt2];
            if (!zzyk.put(b1)) {
              break;
            }
            paramInt2 += 1;
            zzyk.write(b1, arrayOfChar, paramInt1);
            paramInt1 += 1;
          }
        }
        else if (zzyk.indexOf(b1))
        {
          if (i < k)
          {
            zzyk.append(b1, paramArrayOfByte[i], arrayOfChar, paramInt1);
            paramInt2 = i + 1;
            paramInt1 += 1;
          }
          else
          {
            throw zzvt.zzwr();
          }
        }
        else if (zzyk.add(b1))
        {
          if (i < k - 1)
          {
            paramInt2 = i + 1;
            zzyk.encode(b1, paramArrayOfByte[i], paramArrayOfByte[paramInt2], arrayOfChar, paramInt1);
            paramInt2 += 1;
            paramInt1 += 1;
          }
          else
          {
            throw zzvt.zzwr();
          }
        }
        else if (i < k - 2)
        {
          paramInt2 = i + 1;
          byte b2 = paramArrayOfByte[i];
          i = paramInt2 + 1;
          byte b3 = paramArrayOfByte[paramInt2];
          paramInt2 = i + 1;
          zzyk.update(b1, b2, b3, paramArrayOfByte[i], arrayOfChar, paramInt1);
          paramInt1 = paramInt1 + 1 + 1;
        }
        else
        {
          throw zzvt.zzwr();
        }
      }
      return new String(arrayOfChar, 0, paramInt1);
    }
    throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[] { Integer.valueOf(paramArrayOfByte.length), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }
}
