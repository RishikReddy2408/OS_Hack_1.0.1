package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzvs;
import com.google.android.gms.internal.measurement.zzxe;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzuw
  extends com.google.android.gms.internal.measurement.zztz<Double>
  implements zzvs<Double>, zzxe, RandomAccess
{
  private static final zzuw zzbvg;
  private int size;
  private double[] zzbvh;
  
  static
  {
    zzuw localZzuw = new zzuw();
    zzbvg = localZzuw;
    localZzuw.zzsm();
  }
  
  zzuw()
  {
    this(new double[10], 0);
  }
  
  private zzuw(double[] paramArrayOfDouble, int paramInt)
  {
    zzbvh = paramArrayOfDouble;
    size = paramInt;
  }
  
  private final void add(int paramInt, double paramDouble)
  {
    zztx();
    if ((paramInt >= 0) && (paramInt <= size))
    {
      if (size < zzbvh.length)
      {
        System.arraycopy(zzbvh, paramInt, zzbvh, paramInt + 1, size - paramInt);
      }
      else
      {
        double[] arrayOfDouble = new double[size * 3 / 2 + 1];
        System.arraycopy(zzbvh, 0, arrayOfDouble, 0, paramInt);
        System.arraycopy(zzbvh, paramInt, arrayOfDouble, paramInt + 1, size - paramInt);
        zzbvh = arrayOfDouble;
      }
      zzbvh[paramInt] = paramDouble;
      size += 1;
      modCount += 1;
      return;
    }
    throw new IndexOutOfBoundsException(zzaj(paramInt));
  }
  
  private final void zzai(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < size)) {
      return;
    }
    throw new IndexOutOfBoundsException(zzaj(paramInt));
  }
  
  private final String zzaj(int paramInt)
  {
    int i = size;
    StringBuilder localStringBuilder = new StringBuilder(35);
    localStringBuilder.append("Index:");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(", Size:");
    localStringBuilder.append(i);
    return localStringBuilder.toString();
  }
  
  public final void add(double paramDouble)
  {
    add(size, paramDouble);
  }
  
  public final boolean addAll(Collection paramCollection)
  {
    zztx();
    zzvo.checkNotNull(paramCollection);
    if (!(paramCollection instanceof zzuw)) {
      return super.addAll(paramCollection);
    }
    paramCollection = (zzuw)paramCollection;
    if (size == 0) {
      return false;
    }
    if (Integer.MAX_VALUE - size >= size)
    {
      int i = size + size;
      if (i > zzbvh.length) {
        zzbvh = Arrays.copyOf(zzbvh, i);
      }
      System.arraycopy(zzbvh, 0, zzbvh, size, size);
      size = i;
      modCount += 1;
      return true;
    }
    throw new OutOfMemoryError();
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzuw)) {
      return super.equals(paramObject);
    }
    paramObject = (zzuw)paramObject;
    if (size != size) {
      return false;
    }
    paramObject = zzbvh;
    int i = 0;
    while (i < size)
    {
      if (zzbvh[i] != paramObject[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public final int hashCode()
  {
    int j = 1;
    int i = 0;
    while (i < size)
    {
      j = j * 31 + zzvo.zzbf(Double.doubleToLongBits(zzbvh[i]));
      i += 1;
    }
    return j;
  }
  
  public final boolean remove(Object paramObject)
  {
    zztx();
    int i = 0;
    while (i < size)
    {
      if (paramObject.equals(Double.valueOf(zzbvh[i])))
      {
        System.arraycopy(zzbvh, i + 1, zzbvh, i, size - i);
        size -= 1;
        modCount += 1;
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  protected final void removeRange(int paramInt1, int paramInt2)
  {
    zztx();
    if (paramInt2 >= paramInt1)
    {
      System.arraycopy(zzbvh, paramInt2, zzbvh, paramInt1, size - paramInt2);
      size -= paramInt2 - paramInt1;
      modCount += 1;
      return;
    }
    throw new IndexOutOfBoundsException("toIndex < fromIndex");
  }
  
  public final int size()
  {
    return size;
  }
}
