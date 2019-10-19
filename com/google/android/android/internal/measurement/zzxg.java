package com.google.android.android.internal.measurement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

final class zzxg<E>
  extends com.google.android.gms.internal.measurement.zztz<E>
{
  private static final com.google.android.gms.internal.measurement.zzxg<Object> zzcbv;
  private final List<E> zzcai;
  
  static
  {
    zzxg localZzxg = new zzxg();
    zzcbv = localZzxg;
    localZzxg.zzsm();
  }
  
  zzxg()
  {
    this(new ArrayList(10));
  }
  
  private zzxg(List paramList)
  {
    zzcai = paramList;
  }
  
  public static zzxg zzxo()
  {
    return zzcbv;
  }
  
  public final void add(int paramInt, Object paramObject)
  {
    zztx();
    zzcai.add(paramInt, paramObject);
    modCount += 1;
  }
  
  public final Object get(int paramInt)
  {
    return zzcai.get(paramInt);
  }
  
  public final Object remove(int paramInt)
  {
    zztx();
    Object localObject = zzcai.remove(paramInt);
    modCount += 1;
    return localObject;
  }
  
  public final Object set(int paramInt, Object paramObject)
  {
    zztx();
    paramObject = zzcai.set(paramInt, paramObject);
    modCount += 1;
    return paramObject;
  }
  
  public final int size()
  {
    return zzcai.size();
  }
}
