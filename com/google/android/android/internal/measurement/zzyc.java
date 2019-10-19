package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

public final class zzyc
{
  private static final zzyc zzcco = new zzyc(0, new int[0], new Object[0], false);
  private int count;
  private boolean zzbtu;
  private int zzbyn = -1;
  private Object[] zzcba;
  private int[] zzccp;
  
  private zzyc()
  {
    this(0, new int[8], new Object[8], true);
  }
  
  private zzyc(int paramInt, int[] paramArrayOfInt, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    count = paramInt;
    zzccp = paramArrayOfInt;
    zzcba = paramArrayOfObject;
    zzbtu = paramBoolean;
  }
  
  private static void a(int paramInt, Object paramObject, zzyw paramZzyw)
    throws IOException
  {
    int i = paramInt >>> 3;
    paramInt &= 0x7;
    if (paramInt != 5)
    {
      switch (paramInt)
      {
      default: 
        throw new RuntimeException(zzvt.zzwo());
      case 3: 
        if (paramZzyw.zzvj() == zzvm.zze.zzbze)
        {
          paramZzyw.zzbk(i);
          ((zzyc)paramObject).visitFrame(paramZzyw);
          paramZzyw.zzbl(i);
          return;
        }
        paramZzyw.zzbl(i);
        ((zzyc)paramObject).visitFrame(paramZzyw);
        paramZzyw.zzbk(i);
        return;
      case 2: 
        paramZzyw.b(i, (zzud)paramObject);
        return;
      case 1: 
        paramZzyw.visitLocalVariable(i, ((Long)paramObject).longValue());
        return;
      }
      paramZzyw.a(i, ((Long)paramObject).longValue());
      return;
    }
    paramZzyw.valueOf(i, ((Integer)paramObject).intValue());
  }
  
  static zzyc create(zzyc paramZzyc1, zzyc paramZzyc2)
  {
    int i = count + count;
    int[] arrayOfInt = Arrays.copyOf(zzccp, i);
    System.arraycopy(zzccp, 0, arrayOfInt, count, count);
    Object[] arrayOfObject = Arrays.copyOf(zzcba, i);
    System.arraycopy(zzcba, 0, arrayOfObject, count, count);
    return new zzyc(i, arrayOfInt, arrayOfObject, true);
  }
  
  public static zzyc zzyf()
  {
    return zzcco;
  }
  
  static zzyc zzyg()
  {
    return new zzyc();
  }
  
  final void append(int paramInt, Object paramObject)
  {
    if (zzbtu)
    {
      if (count == zzccp.length)
      {
        if (count < 4) {
          i = 8;
        } else {
          i = count >> 1;
        }
        int i = count + i;
        zzccp = Arrays.copyOf(zzccp, i);
        zzcba = Arrays.copyOf(zzcba, i);
      }
      zzccp[count] = paramInt;
      zzcba[count] = paramObject;
      count += 1;
      return;
    }
    throw new UnsupportedOperationException();
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject == null) {
      return false;
    }
    if (!(paramObject instanceof zzyc)) {
      return false;
    }
    paramObject = (zzyc)paramObject;
    if (count == count)
    {
      Object localObject = zzccp;
      int[] arrayOfInt = zzccp;
      int j = count;
      int i = 0;
      while (i < j)
      {
        if (localObject[i] != arrayOfInt[i])
        {
          i = 0;
          break label87;
        }
        i += 1;
      }
      i = 1;
      label87:
      if (i != 0)
      {
        localObject = zzcba;
        paramObject = zzcba;
        j = count;
        i = 0;
        while (i < j)
        {
          if (!localObject[i].equals(paramObject[i]))
          {
            i = 0;
            break label141;
          }
          i += 1;
        }
        i = 1;
        label141:
        return i != 0;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    int n = count;
    Object localObject = zzccp;
    int i1 = count;
    int m = 0;
    int k = 17;
    int j = 0;
    int i = 17;
    while (j < i1)
    {
      i = i * 31 + localObject[j];
      j += 1;
    }
    localObject = zzcba;
    i1 = count;
    j = m;
    while (j < i1)
    {
      k = k * 31 + localObject[j].hashCode();
      j += 1;
    }
    return ((n + 527) * 31 + i) * 31 + k;
  }
  
  final void index(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < count)
    {
      zzww.create(paramStringBuilder, paramInt, String.valueOf(zzccp[i] >>> 3), zzcba[i]);
      i += 1;
    }
  }
  
  final void toByteArray(zzyw paramZzyw)
    throws IOException
  {
    if (paramZzyw.zzvj() == zzvm.zze.zzbzf)
    {
      i = count - 1;
      while (i >= 0)
      {
        paramZzyw.a(zzccp[i] >>> 3, zzcba[i]);
        i -= 1;
      }
      return;
    }
    int i = 0;
    while (i < count)
    {
      paramZzyw.a(zzccp[i] >>> 3, zzcba[i]);
      i += 1;
    }
  }
  
  public final void visitFrame(zzyw paramZzyw)
    throws IOException
  {
    if (count == 0) {
      return;
    }
    if (paramZzyw.zzvj() == zzvm.zze.zzbze)
    {
      i = 0;
      while (i < count)
      {
        a(zzccp[i], zzcba[i], paramZzyw);
        i += 1;
      }
      return;
    }
    int i = count - 1;
    while (i >= 0)
    {
      a(zzccp[i], zzcba[i], paramZzyw);
      i -= 1;
    }
  }
  
  public final void zzsm()
  {
    zzbtu = false;
  }
  
  public final int zzvu()
  {
    int i = zzbyn;
    if (i != -1) {
      return i;
    }
    int j = 0;
    i = 0;
    while (j < count)
    {
      int m = zzccp[j];
      int k = m >>> 3;
      m &= 0x7;
      if (m != 5) {
        switch (m)
        {
        default: 
          throw new IllegalStateException(zzvt.zzwo());
        case 3: 
          i += (zzut.zzbb(k) << 1) + ((zzyc)zzcba[j]).zzvu();
          break;
        case 2: 
          i += zzut.getShares(k, (zzud)zzcba[j]);
          break;
        case 1: 
          i += zzut.a(k, ((Long)zzcba[j]).longValue());
          break;
        case 0: 
          i += zzut.getCard(k, ((Long)zzcba[j]).longValue());
          break;
        }
      } else {
        i += zzut.getPath(k, ((Integer)zzcba[j]).intValue());
      }
      j += 1;
    }
    zzbyn = i;
    return i;
  }
  
  public final int zzyh()
  {
    int i = zzbyn;
    if (i != -1) {
      return i;
    }
    i = 0;
    int j = 0;
    while (i < count)
    {
      j += zzut.skipChar(zzccp[i] >>> 3, (zzud)zzcba[i]);
      i += 1;
    }
    zzbyn = j;
    return j;
  }
}
