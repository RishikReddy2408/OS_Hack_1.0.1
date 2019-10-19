package com.google.android.android.internal.measurement;

public final class zzzc
  implements Cloneable
{
  private static final zzzd zzcff = new zzzd();
  private int mSize;
  private boolean zzcfg = false;
  private int[] zzcfh;
  private zzzd[] zzcfi;
  
  zzzc()
  {
    this(10);
  }
  
  private zzzc(int paramInt)
  {
    paramInt = idealIntArraySize(paramInt);
    zzcfh = new int[paramInt];
    zzcfi = new zzzd[paramInt];
    mSize = 0;
  }
  
  private static int idealIntArraySize(int paramInt)
  {
    int j = paramInt << 2;
    paramInt = 4;
    int i;
    for (;;)
    {
      i = j;
      if (paramInt >= 32) {
        break;
      }
      i = (1 << paramInt) - 12;
      if (j <= i) {
        break;
      }
      paramInt += 1;
    }
    return i / 4;
  }
  
  private final int zzcd(int paramInt)
  {
    int j = mSize - 1;
    int i = 0;
    while (i <= j)
    {
      int k = i + j >>> 1;
      int m = zzcfh[k];
      if (m < paramInt) {
        i = k + 1;
      } else if (m > paramInt) {
        j = k - 1;
      } else {
        return k;
      }
    }
    return i;
  }
  
  final void ensureCapacity(int paramInt, zzzd paramZzzd)
  {
    int i = zzcd(paramInt);
    if (i >= 0)
    {
      zzcfi[i] = paramZzzd;
      return;
    }
    i = i;
    if ((i < mSize) && (zzcfi[i] == zzcff))
    {
      zzcfh[i] = paramInt;
      zzcfi[i] = paramZzzd;
      return;
    }
    int j;
    int[] arrayOfInt;
    Object localObject;
    if (mSize >= zzcfh.length)
    {
      j = idealIntArraySize(mSize + 1);
      arrayOfInt = new int[j];
      localObject = new zzzd[j];
      System.arraycopy(zzcfh, 0, arrayOfInt, 0, zzcfh.length);
      System.arraycopy(zzcfi, 0, localObject, 0, zzcfi.length);
      zzcfh = arrayOfInt;
      zzcfi = ((zzzd[])localObject);
    }
    if (mSize - i != 0)
    {
      arrayOfInt = zzcfh;
      localObject = zzcfh;
      j = i + 1;
      System.arraycopy(arrayOfInt, i, localObject, j, mSize - i);
      System.arraycopy(zzcfi, i, zzcfi, j, mSize - i);
    }
    zzcfh[i] = paramInt;
    zzcfi[i] = paramZzzd;
    mSize += 1;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzzc)) {
      return false;
    }
    paramObject = (zzzc)paramObject;
    if (mSize != mSize) {
      return false;
    }
    Object localObject = zzcfh;
    int[] arrayOfInt = zzcfh;
    int j = mSize;
    int i = 0;
    while (i < j)
    {
      if (localObject[i] != arrayOfInt[i])
      {
        i = 0;
        break label83;
      }
      i += 1;
    }
    i = 1;
    label83:
    if (i != 0)
    {
      localObject = zzcfi;
      paramObject = zzcfi;
      j = mSize;
      i = 0;
      while (i < j)
      {
        if (!localObject[i].equals(paramObject[i]))
        {
          i = 0;
          break label137;
        }
        i += 1;
      }
      i = 1;
      label137:
      if (i != 0) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    int j = 17;
    int i = 0;
    while (i < mSize)
    {
      j = (j * 31 + zzcfh[i]) * 31 + zzcfi[i].hashCode();
      i += 1;
    }
    return j;
  }
  
  public final boolean isEmpty()
  {
    return mSize == 0;
  }
  
  final int size()
  {
    return mSize;
  }
  
  final zzzd zzcb(int paramInt)
  {
    paramInt = zzcd(paramInt);
    if ((paramInt >= 0) && (zzcfi[paramInt] != zzcff)) {
      return zzcfi[paramInt];
    }
    return null;
  }
  
  final zzzd zzcc(int paramInt)
  {
    return zzcfi[paramInt];
  }
}
