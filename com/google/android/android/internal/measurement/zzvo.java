package com.google.android.android.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzvo
{
  private static final Charset ISO_8859_1;
  static final Charset UTF_8 = Charset.forName("UTF-8");
  public static final byte[] zzbzj;
  private static final ByteBuffer zzbzk;
  private static final zzuo zzbzl;
  
  static
  {
    ISO_8859_1 = Charset.forName("ISO-8859-1");
    byte[] arrayOfByte = new byte[0];
    zzbzj = arrayOfByte;
    zzbzk = ByteBuffer.wrap(arrayOfByte);
    arrayOfByte = zzbzj;
    zzbzl = zzuo.readFully(arrayOfByte, 0, arrayOfByte.length, false);
  }
  
  static Object add(Object paramObject1, Object paramObject2)
  {
    return ((zzwt)paramObject1).zzwd().multiply((zzwt)paramObject2).zzwi();
  }
  
  static Object attribute(Object paramObject, String paramString)
  {
    if (paramObject != null) {
      return paramObject;
    }
    throw new NullPointerException(paramString);
  }
  
  static Object checkNotNull(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject;
    }
    throw new NullPointerException();
  }
  
  public static String create(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, UTF_8);
  }
  
  static int hashCode(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int i = paramInt1;
    paramInt1 = paramInt2;
    while (paramInt1 < paramInt2 + paramInt3)
    {
      i = i * 31 + paramArrayOfByte[paramInt1];
      paramInt1 += 1;
    }
    return i;
  }
  
  public static int hashCode(boolean paramBoolean)
  {
    if (paramBoolean) {
      return 1231;
    }
    return 1237;
  }
  
  public static int hashCode(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    i = hashCode(i, paramArrayOfByte, 0, i);
    if (i == 0) {
      return 1;
    }
    return i;
  }
  
  public static boolean processBytes(byte[] paramArrayOfByte)
  {
    return zzyj.processBytes(paramArrayOfByte);
  }
  
  static boolean writeTag(zzwt paramZzwt)
  {
    return false;
  }
  
  public static int zzbf(long paramLong)
  {
    return (int)(paramLong ^ paramLong >>> 32);
  }
}
