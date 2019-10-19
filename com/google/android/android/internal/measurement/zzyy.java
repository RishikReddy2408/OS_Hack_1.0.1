package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzyy
{
  private final ByteBuffer zzbva;
  private zzut zzcfa;
  private int zzcfb;
  
  private zzyy(ByteBuffer paramByteBuffer)
  {
    zzbva = paramByteBuffer;
    zzbva.order(ByteOrder.LITTLE_ENDIAN);
  }
  
  private zzyy(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this(ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public static int addMenuItem(int paramInt, zzzg paramZzzg)
  {
    paramInt = zzbb(paramInt);
    int i = paramZzzg.zzvu();
    return paramInt + (zzbj(i) + i);
  }
  
  private static int decode(CharSequence paramCharSequence)
  {
    int n = paramCharSequence.length();
    int m = 0;
    int j = 0;
    while ((j < n) && (paramCharSequence.charAt(j) < '?')) {
      j += 1;
    }
    int i = n;
    for (;;)
    {
      k = i;
      if (j >= n) {
        break label232;
      }
      k = paramCharSequence.charAt(j);
      if (k >= 2048) {
        break;
      }
      i += (127 - k >>> 31);
      j += 1;
    }
    int i2 = paramCharSequence.length();
    int k = m;
    while (j < i2)
    {
      int i3 = paramCharSequence.charAt(j);
      if (i3 < 2048)
      {
        k += (127 - i3 >>> 31);
        m = j;
      }
      else
      {
        int i1 = k + 2;
        k = i1;
        m = j;
        if (55296 <= i3)
        {
          k = i1;
          m = j;
          if (i3 <= 57343) {
            if (Character.codePointAt(paramCharSequence, j) >= 65536)
            {
              m = j + 1;
              k = i1;
            }
            else
            {
              paramCharSequence = new StringBuilder(39);
              paramCharSequence.append("Unpaired surrogate at index ");
              paramCharSequence.append(j);
              throw new IllegalArgumentException(paramCharSequence.toString());
            }
          }
        }
      }
      j = m + 1;
    }
    k = i + k;
    label232:
    if (k >= n) {
      return k;
    }
    long l = k;
    paramCharSequence = new StringBuilder(54);
    paramCharSequence.append("UTF-8 length does not fit in int: ");
    paramCharSequence.append(l + 4294967296L);
    throw new IllegalArgumentException(paramCharSequence.toString());
  }
  
  private static void decode(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    if (!paramByteBuffer.isReadOnly())
    {
      boolean bool = paramByteBuffer.hasArray();
      int m = 0;
      int k = 0;
      char c;
      if (bool) {
        try
        {
          byte[] arrayOfByte = paramByteBuffer.array();
          m = paramByteBuffer.arrayOffset();
          n = paramByteBuffer.position();
          m += n;
          n = paramByteBuffer.remaining();
          int i1 = paramCharSequence.length();
          int i2 = n + m;
          int i3;
          while (k < i1)
          {
            n = k + m;
            if (n >= i2) {
              break;
            }
            i3 = paramCharSequence.charAt(k);
            if (i3 >= 128) {
              break;
            }
            arrayOfByte[n] = ((byte)i3);
            k += 1;
          }
          if (k == i1)
          {
            n = m + i1;
          }
          else
          {
            m += k;
            int i;
            for (;;)
            {
              n = m;
              if (k >= i1) {
                break label604;
              }
              i = paramCharSequence.charAt(k);
              if ((i < 128) && (m < i2))
              {
                n = m + 1;
                arrayOfByte[m] = ((byte)i);
                m = n;
              }
              for (;;)
              {
                break;
                if ((i < 2048) && (m <= i2 - 2))
                {
                  n = m + 1;
                  arrayOfByte[m] = ((byte)(i >>> 6 | 0x3C0));
                  m = n + 1;
                  arrayOfByte[n] = ((byte)(i & 0x3F | 0x80));
                }
                else if (((i < 55296) || (57343 < i)) && (m <= i2 - 3))
                {
                  n = m + 1;
                  arrayOfByte[m] = ((byte)(i >>> 12 | 0x1E0));
                  i3 = n + 1;
                  arrayOfByte[n] = ((byte)(i >>> 6 & 0x3F | 0x80));
                  m = i3 + 1;
                  arrayOfByte[i3] = ((byte)(i & 0x3F | 0x80));
                }
                else
                {
                  if (m > i2 - 4) {
                    break label555;
                  }
                  n = k + 1;
                  i3 = paramCharSequence.length();
                  if (n == i3) {
                    break label515;
                  }
                  c = paramCharSequence.charAt(n);
                  bool = Character.isSurrogatePair(i, c);
                  if (!bool) {
                    break label511;
                  }
                  k = Character.toCodePoint(i, c);
                  i3 = m + 1;
                  arrayOfByte[m] = ((byte)(k >>> 18 | 0xF0));
                  m = i3 + 1;
                  arrayOfByte[i3] = ((byte)(k >>> 12 & 0x3F | 0x80));
                  i3 = m + 1;
                  arrayOfByte[m] = ((byte)(k >>> 6 & 0x3F | 0x80));
                  m = i3 + 1;
                  arrayOfByte[i3] = ((byte)(k & 0x3F | 0x80));
                  k = n;
                }
              }
              k += 1;
            }
            label511:
            k = n;
            label515:
            paramCharSequence = new StringBuilder(39);
            paramCharSequence.append("Unpaired surrogate at index ");
            paramCharSequence.append(k - 1);
            paramCharSequence = new IllegalArgumentException(paramCharSequence.toString());
            throw paramCharSequence;
            label555:
            paramCharSequence = new StringBuilder(37);
            paramCharSequence.append("Failed writing ");
            paramCharSequence.append(i);
            paramCharSequence.append(" at index ");
            paramCharSequence.append(m);
            throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
          }
          label604:
          k = paramByteBuffer.arrayOffset();
          paramByteBuffer.position(n - k);
          return;
        }
        catch (ArrayIndexOutOfBoundsException paramCharSequence)
        {
          paramByteBuffer = new BufferOverflowException();
          paramByteBuffer.initCause(paramCharSequence);
          throw paramByteBuffer;
        }
      }
      int n = paramCharSequence.length();
      k = m;
      while (k < n)
      {
        int j = paramCharSequence.charAt(k);
        if (j < 128)
        {
          paramByteBuffer.put((byte)j);
        }
        else if (j < 2048)
        {
          paramByteBuffer.put((byte)(j >>> 6 | 0x3C0));
          paramByteBuffer.put((byte)(j & 0x3F | 0x80));
        }
        else if ((j >= 55296) && (57343 >= j))
        {
          m = k + 1;
          if (m != paramCharSequence.length())
          {
            c = paramCharSequence.charAt(m);
            if (Character.isSurrogatePair(j, c))
            {
              k = Character.toCodePoint(j, c);
              paramByteBuffer.put((byte)(k >>> 18 | 0xF0));
              paramByteBuffer.put((byte)(k >>> 12 & 0x3F | 0x80));
              paramByteBuffer.put((byte)(k >>> 6 & 0x3F | 0x80));
              paramByteBuffer.put((byte)(k & 0x3F | 0x80));
              k = m;
            }
            else
            {
              k = m;
            }
          }
          else
          {
            paramCharSequence = new StringBuilder(39);
            paramCharSequence.append("Unpaired surrogate at index ");
            paramCharSequence.append(k - 1);
            throw new IllegalArgumentException(paramCharSequence.toString());
          }
        }
        else
        {
          paramByteBuffer.put((byte)(j >>> 12 | 0x1E0));
          paramByteBuffer.put((byte)(j >>> 6 & 0x3F | 0x80));
          paramByteBuffer.put((byte)(j & 0x3F | 0x80));
        }
        k += 1;
      }
      return;
    }
    throw new ReadOnlyBufferException();
  }
  
  public static zzyy readFully(byte[] paramArrayOfByte)
  {
    return toString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static int sendCommand(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + zzbc(paramInt2);
  }
  
  public static int setProperty(int paramInt, String paramString)
  {
    return zzbb(paramInt) + zzfx(paramString);
  }
  
  public static zzyy toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzyy(paramArrayOfByte, 0, paramInt2);
  }
  
  public static int write(int paramInt, long paramLong)
  {
    return zzbb(paramInt) + zzbi(paramLong);
  }
  
  public static int zzbb(int paramInt)
  {
    return zzbj(paramInt << 3);
  }
  
  public static int zzbc(int paramInt)
  {
    if (paramInt >= 0) {
      return zzbj(paramInt);
    }
    return 10;
  }
  
  private final void zzbh(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
      {
        zzbz((int)paramLong);
        return;
      }
      zzbz((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }
  
  public static int zzbi(long paramLong)
  {
    if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
      return 1;
    }
    if ((0xFFFFFFFFFFFFC000 & paramLong) == 0L) {
      return 2;
    }
    if ((0xFFFFFFFFFFE00000 & paramLong) == 0L) {
      return 3;
    }
    if ((0xFFFFFFFFF0000000 & paramLong) == 0L) {
      return 4;
    }
    if ((0xFFFFFFF800000000 & paramLong) == 0L) {
      return 5;
    }
    if ((0xFFFFFC0000000000 & paramLong) == 0L) {
      return 6;
    }
    if ((0xFFFE000000000000 & paramLong) == 0L) {
      return 7;
    }
    if ((0xFF00000000000000 & paramLong) == 0L) {
      return 8;
    }
    if ((paramLong & 0x8000000000000000) == 0L) {
      return 9;
    }
    return 10;
  }
  
  public static int zzbj(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((paramInt & 0xC000) == 0) {
      return 2;
    }
    if ((0xFFE00000 & paramInt) == 0) {
      return 3;
    }
    if ((paramInt & 0xF0000000) == 0) {
      return 4;
    }
    return 5;
  }
  
  private final void zzbz(int paramInt)
    throws IOException
  {
    byte b = (byte)paramInt;
    if (zzbva.hasRemaining())
    {
      zzbva.put(b);
      return;
    }
    throw new zzyz(zzbva.position(), zzbva.limit());
  }
  
  public static int zzfx(String paramString)
  {
    int i = decode(paramString);
    return zzbj(i) + i;
  }
  
  private final zzut zzys()
    throws IOException
  {
    if (zzcfa == null)
    {
      zzcfa = zzut.shrink(zzbva);
      zzcfb = zzbva.position();
    }
    else if (zzcfb != zzbva.position())
    {
      zzcfa.write(zzbva.array(), zzcfb, zzbva.position() - zzcfb);
      zzcfb = zzbva.position();
    }
    return zzcfa;
  }
  
  public final void add(int paramInt, double paramDouble)
    throws IOException
  {
    add(paramInt, 1);
    long l = Double.doubleToLongBits(paramDouble);
    if (zzbva.remaining() >= 8)
    {
      zzbva.putLong(l);
      return;
    }
    throw new zzyz(zzbva.position(), zzbva.limit());
  }
  
  public final void add(int paramInt1, int paramInt2)
    throws IOException
  {
    zzca(paramInt1 << 3 | paramInt2);
  }
  
  public final void add(int paramInt, long paramLong)
    throws IOException
  {
    add(paramInt, 0);
    zzbh(paramLong);
  }
  
  public final void add(int paramInt, String paramString)
    throws IOException
  {
    add(paramInt, 2);
    try
    {
      paramInt = zzbj(paramString.length());
      int i = paramString.length();
      i = zzbj(i * 3);
      if (paramInt == i)
      {
        localObject = zzbva;
        i = ((ByteBuffer)localObject).position();
        localObject = zzbva;
        int j = ((ByteBuffer)localObject).remaining();
        if (j >= paramInt)
        {
          localObject = zzbva;
          ((ByteBuffer)localObject).position(i + paramInt);
          localObject = zzbva;
          decode(paramString, (ByteBuffer)localObject);
          paramString = zzbva;
          j = paramString.position();
          paramString = zzbva;
          paramString.position(i);
          zzca(j - i - paramInt);
          paramString = zzbva;
          paramString.position(j);
          return;
        }
        paramString = zzbva;
        paramString = new zzyz(i + paramInt, paramString.limit());
        throw paramString;
      }
      zzca(decode(paramString));
      localObject = zzbva;
      decode(paramString, (ByteBuffer)localObject);
      return;
    }
    catch (BufferOverflowException paramString)
    {
      Object localObject = new zzyz(zzbva.position(), zzbva.limit());
      ((IOException)localObject).initCause(paramString);
      throw ((Throwable)localObject);
    }
  }
  
  public final void add(int paramInt, boolean paramBoolean)
    throws IOException
  {
    add(paramInt, 0);
    byte b = (byte)paramBoolean;
    if (zzbva.hasRemaining())
    {
      zzbva.put(b);
      return;
    }
    throw new zzyz(zzbva.position(), zzbva.limit());
  }
  
  public final void add(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = paramArrayOfByte.length;
    if (zzbva.remaining() >= i)
    {
      zzbva.put(paramArrayOfByte, 0, i);
      return;
    }
    throw new zzyz(zzbva.position(), zzbva.limit());
  }
  
  public final void addItem(int paramInt1, int paramInt2)
    throws IOException
  {
    add(paramInt1, 0);
    if (paramInt2 >= 0)
    {
      zzca(paramInt2);
      return;
    }
    zzbh(paramInt2);
  }
  
  public final void multiply(int paramInt, long paramLong)
    throws IOException
  {
    add(paramInt, 0);
    zzbh(paramLong);
  }
  
  public final void newLine(zzzg paramZzzg)
    throws IOException
  {
    zzca(paramZzzg.zzza());
    paramZzzg.multiply(this);
  }
  
  public final void write(int paramInt, float paramFloat)
    throws IOException
  {
    add(paramInt, 5);
    paramInt = Float.floatToIntBits(paramFloat);
    if (zzbva.remaining() >= 4)
    {
      zzbva.putInt(paramInt);
      return;
    }
    throw new zzyz(zzbva.position(), zzbva.limit());
  }
  
  public final void writeHeader(int paramInt, zzzg paramZzzg)
    throws IOException
  {
    add(paramInt, 2);
    newLine(paramZzzg);
  }
  
  public final void writeSampleData(int paramInt, zzwt paramZzwt)
    throws IOException
  {
    zzut localZzut = zzys();
    localZzut.readFully(paramInt, paramZzwt);
    localZzut.flush();
    zzcfb = zzbva.position();
  }
  
  public final void zzca(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        zzbz(paramInt);
        return;
      }
      zzbz(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }
  
  public final void zzyt()
  {
    if (zzbva.remaining() == 0) {
      return;
    }
    throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[] { Integer.valueOf(zzbva.remaining()) }));
  }
}
