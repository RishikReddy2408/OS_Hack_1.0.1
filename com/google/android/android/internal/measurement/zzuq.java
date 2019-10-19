package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

final class zzuq
  extends zzuo
{
  private final byte[] buffer;
  private int limit;
  private int pointer;
  private final boolean zzbum;
  private int zzbun;
  private int zzbuo;
  private int zzbup;
  private int zzbuq = Integer.MAX_VALUE;
  
  private zzuq(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(null);
    buffer = paramArrayOfByte;
    limit = (paramInt2 + paramInt1);
    pointer = paramInt1;
    zzbuo = pointer;
    zzbum = paramBoolean;
  }
  
  private final int zzuy()
    throws IOException
  {
    int j = pointer;
    if (limit != j)
    {
      byte[] arrayOfByte = buffer;
      int i = j + 1;
      int k = arrayOfByte[j];
      if (k >= 0)
      {
        pointer = i;
        return k;
      }
      if (limit - i >= 9)
      {
        j = i + 1;
        k ^= arrayOfByte[i] << 7;
        if (k < 0)
        {
          k ^= 0xFFFFFF80;
          i = j;
          j = k;
        }
        else
        {
          i = j + 1;
          k ^= arrayOfByte[j] << 14;
          if (k >= 0) {
            j = k ^ 0x3F80;
          }
          for (;;)
          {
            break;
            j = i + 1;
            i = k ^ arrayOfByte[i] << 21;
            if (i < 0)
            {
              k = i ^ 0xFFE03F80;
              i = j;
              j = k;
            }
            else
            {
              int m = j + 1;
              int n = arrayOfByte[j];
              k = i ^ n << 28 ^ 0xFE03F80;
              i = m;
              j = k;
              if (n < 0)
              {
                n = m + 1;
                i = n;
                j = k;
                if (arrayOfByte[m] < 0)
                {
                  m = n + 1;
                  i = m;
                  j = k;
                  if (arrayOfByte[n] < 0)
                  {
                    n = m + 1;
                    i = n;
                    j = k;
                    if (arrayOfByte[m] < 0)
                    {
                      m = n + 1;
                      i = m;
                      j = k;
                      if (arrayOfByte[n] < 0)
                      {
                        i = m + 1;
                        if (arrayOfByte[m] < 0) {
                          break label267;
                        }
                        j = k;
                      }
                    }
                  }
                }
              }
            }
          }
        }
        pointer = i;
        return j;
      }
    }
    label267:
    return (int)zzuv();
  }
  
  private final long zzuz()
    throws IOException
  {
    int i = pointer;
    if (limit != i)
    {
      byte[] arrayOfByte = buffer;
      int j = i + 1;
      int k = arrayOfByte[i];
      if (k >= 0)
      {
        pointer = j;
        return k;
      }
      if (limit - j >= 9)
      {
        i = j + 1;
        k ^= arrayOfByte[j] << 7;
        long l1;
        if (k < 0) {
          l1 = k ^ 0xFFFFFF80;
        }
        for (;;)
        {
          break;
          j = i + 1;
          k ^= arrayOfByte[i] << 14;
          if (k >= 0)
          {
            l1 = k ^ 0x3F80;
            i = j;
          }
          else
          {
            i = j + 1;
            j = k ^ arrayOfByte[j] << 21;
            if (j < 0)
            {
              l1 = j ^ 0xFFE03F80;
            }
            else
            {
              l1 = j;
              j = i + 1;
              l1 = arrayOfByte[i] << 28 ^ l1;
              if (l1 >= 0L)
              {
                l1 ^= 0xFE03F80;
                i = j;
              }
              else
              {
                i = j + 1;
                l1 ^= arrayOfByte[j] << 35;
                if (l1 < 0L) {
                  l1 = 0xFFFFFFF80FE03F80 ^ l1;
                }
                for (;;)
                {
                  break;
                  j = i + 1;
                  l1 ^= arrayOfByte[i] << 42;
                  if (l1 >= 0L)
                  {
                    l1 ^= 0x3F80FE03F80;
                    i = j;
                  }
                  else
                  {
                    i = j + 1;
                    l1 ^= arrayOfByte[j] << 49;
                    if (l1 < 0L)
                    {
                      l1 = 0xFFFE03F80FE03F80 ^ l1;
                      continue;
                    }
                    j = i + 1;
                    long l2 = l1 ^ arrayOfByte[i] << 56 ^ 0xFE03F80FE03F80;
                    i = j;
                    l1 = l2;
                    if (l2 < 0L)
                    {
                      i = j + 1;
                      if (arrayOfByte[j] < 0L) {
                        break label349;
                      }
                      l1 = l2;
                    }
                  }
                }
              }
            }
          }
        }
        pointer = i;
        return l1;
      }
    }
    label349:
    return zzuv();
  }
  
  private final int zzva()
    throws IOException
  {
    int i = pointer;
    if (limit - i >= 4)
    {
      byte[] arrayOfByte = buffer;
      pointer = (i + 4);
      int j = arrayOfByte[i];
      int k = arrayOfByte[(i + 1)];
      int m = arrayOfByte[(i + 2)];
      return (arrayOfByte[(i + 3)] & 0xFF) << 24 | j & 0xFF | (k & 0xFF) << 8 | (m & 0xFF) << 16;
    }
    throw zzvt.zzwk();
  }
  
  private final long zzvb()
    throws IOException
  {
    int i = pointer;
    if (limit - i >= 8)
    {
      byte[] arrayOfByte = buffer;
      pointer = (i + 8);
      long l1 = arrayOfByte[i];
      long l2 = arrayOfByte[(i + 1)];
      long l3 = arrayOfByte[(i + 2)];
      long l4 = arrayOfByte[(i + 3)];
      long l5 = arrayOfByte[(i + 4)];
      long l6 = arrayOfByte[(i + 5)];
      long l7 = arrayOfByte[(i + 6)];
      return (arrayOfByte[(i + 7)] & 0xFF) << 56 | l1 & 0xFF | (l2 & 0xFF) << 8 | (l3 & 0xFF) << 16 | (l4 & 0xFF) << 24 | (l5 & 0xFF) << 32 | (l6 & 0xFF) << 40 | (l7 & 0xFF) << 48;
    }
    throw zzvt.zzwk();
  }
  
  private final void zzvc()
  {
    limit += zzbun;
    int i = limit - zzbuo;
    if (i > zzbuq)
    {
      zzbun = (i - zzbuq);
      limit -= zzbun;
      return;
    }
    zzbun = 0;
  }
  
  private final byte zzvd()
    throws IOException
  {
    if (pointer != limit)
    {
      byte[] arrayOfByte = buffer;
      int i = pointer;
      pointer = (i + 1);
      return arrayOfByte[i];
    }
    throw zzvt.zzwk();
  }
  
  public final zzwt blur(zzxd paramZzxd, zzuz paramZzuz)
    throws IOException
  {
    int i = zzuy();
    if (zzbuh < zzbui)
    {
      i = zzaq(i);
      zzbuh += 1;
      paramZzxd = (zzwt)paramZzxd.subtract(this, paramZzuz);
      zzan(0);
      zzbuh -= 1;
      zzar(i);
      return paramZzxd;
    }
    throw zzvt.zzwp();
  }
  
  public final double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(zzvb());
  }
  
  public final float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(zzva());
  }
  
  public final String readString()
    throws IOException
  {
    int i = zzuy();
    if ((i > 0) && (i <= limit - pointer))
    {
      String str = new String(buffer, pointer, i, zzvo.UTF_8);
      pointer += i;
      return str;
    }
    if (i == 0) {
      return "";
    }
    if (i < 0) {
      throw zzvt.zzwl();
    }
    throw zzvt.zzwk();
  }
  
  public final void zzan(int paramInt)
    throws zzvt
  {
    if (zzbup == paramInt) {
      return;
    }
    throw zzvt.zzwn();
  }
  
  public final boolean zzao(int paramInt)
    throws IOException
  {
    int j = 0;
    int i = 0;
    switch (paramInt & 0x7)
    {
    default: 
      throw zzvt.zzwo();
    case 5: 
      zzas(4);
      return true;
    case 4: 
      return false;
    case 3: 
      do
      {
        i = zzug();
      } while ((i != 0) && (zzao(i)));
      zzan(paramInt >>> 3 << 3 | 0x4);
      return true;
    case 2: 
      zzas(zzuy());
      return true;
    case 1: 
      zzas(8);
      return true;
    }
    paramInt = j;
    if (limit - pointer >= 10)
    {
      paramInt = i;
      while (paramInt < 10)
      {
        byte[] arrayOfByte = buffer;
        i = pointer;
        pointer = (i + 1);
        if (arrayOfByte[i] >= 0) {
          break label212;
        }
        paramInt += 1;
      }
      throw zzvt.zzwm();
    }
    while (paramInt < 10)
    {
      if (zzvd() >= 0) {
        break label218;
      }
      paramInt += 1;
    }
    label212:
    return true;
    throw zzvt.zzwm();
    label218:
    return true;
  }
  
  public final int zzaq(int paramInt)
    throws zzvt
  {
    if (paramInt >= 0)
    {
      paramInt += zzux();
      int i = zzbuq;
      if (paramInt <= i)
      {
        zzbuq = paramInt;
        zzvc();
        return i;
      }
      throw zzvt.zzwk();
    }
    throw zzvt.zzwl();
  }
  
  public final void zzar(int paramInt)
  {
    zzbuq = paramInt;
    zzvc();
  }
  
  public final void zzas(int paramInt)
    throws IOException
  {
    if ((paramInt >= 0) && (paramInt <= limit - pointer))
    {
      pointer += paramInt;
      return;
    }
    if (paramInt < 0) {
      throw zzvt.zzwl();
    }
    throw zzvt.zzwk();
  }
  
  public final int zzug()
    throws IOException
  {
    if (zzuw())
    {
      zzbup = 0;
      return 0;
    }
    zzbup = zzuy();
    if (zzbup >>> 3 != 0) {
      return zzbup;
    }
    throw new zzvt("Protocol message contained an invalid tag (zero).");
  }
  
  public final long zzuh()
    throws IOException
  {
    return zzuz();
  }
  
  public final long zzui()
    throws IOException
  {
    return zzuz();
  }
  
  public final int zzuj()
    throws IOException
  {
    return zzuy();
  }
  
  public final long zzuk()
    throws IOException
  {
    return zzvb();
  }
  
  public final int zzul()
    throws IOException
  {
    return zzva();
  }
  
  public final boolean zzum()
    throws IOException
  {
    return zzuz() != 0L;
  }
  
  public final String zzun()
    throws IOException
  {
    int i = zzuy();
    if ((i > 0) && (i <= limit - pointer))
    {
      String str = zzyj.readInternal(buffer, pointer, i);
      pointer += i;
      return str;
    }
    if (i == 0) {
      return "";
    }
    if (i <= 0) {
      throw zzvt.zzwl();
    }
    throw zzvt.zzwk();
  }
  
  public final zzud zzuo()
    throws IOException
  {
    int i = zzuy();
    Object localObject;
    if ((i > 0) && (i <= limit - pointer))
    {
      localObject = zzud.getChars(buffer, pointer, i);
      pointer += i;
      return localObject;
    }
    if (i == 0) {
      return zzud.zzbtz;
    }
    if ((i > 0) && (i <= limit - pointer))
    {
      int j = pointer;
      pointer += i;
      localObject = Arrays.copyOfRange(buffer, j, pointer);
    }
    else
    {
      if (i > 0) {
        break label124;
      }
      if (i != 0) {
        break label120;
      }
      localObject = zzvo.zzbzj;
    }
    return zzud.bytesToHex((byte[])localObject);
    label120:
    throw zzvt.zzwl();
    label124:
    throw zzvt.zzwk();
  }
  
  public final int zzup()
    throws IOException
  {
    return zzuy();
  }
  
  public final int zzuq()
    throws IOException
  {
    return zzuy();
  }
  
  public final int zzur()
    throws IOException
  {
    return zzva();
  }
  
  public final long zzus()
    throws IOException
  {
    return zzvb();
  }
  
  public final int zzut()
    throws IOException
  {
    int i = zzuy();
    return -(i & 0x1) ^ i >>> 1;
  }
  
  public final long zzuu()
    throws IOException
  {
    long l = zzuz();
    return -(l & 1L) ^ l >>> 1;
  }
  
  final long zzuv()
    throws IOException
  {
    long l = 0L;
    int i = 0;
    while (i < 64)
    {
      int j = zzvd();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw zzvt.zzwm();
  }
  
  public final boolean zzuw()
    throws IOException
  {
    return pointer == limit;
  }
  
  public final int zzux()
  {
    return pointer - zzbuo;
  }
}
