package com.google.android.android.internal.measurement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzwb
  extends com.google.android.gms.internal.measurement.zztz<String>
  implements com.google.android.gms.internal.measurement.zzwc, RandomAccess
{
  private static final zzwb zzcag;
  private static final zzwc zzcah = zzcag;
  private final List<Object> zzcai;
  
  static
  {
    zzwb localZzwb = new zzwb();
    zzcag = localZzwb;
    localZzwb.zzsm();
  }
  
  public zzwb()
  {
    this(10);
  }
  
  public zzwb(int paramInt)
  {
    this(new ArrayList(paramInt));
  }
  
  private zzwb(ArrayList paramArrayList)
  {
    zzcai = paramArrayList;
  }
  
  private static String asString(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((paramObject instanceof zzud)) {
      return ((zzud)paramObject).zzua();
    }
    return zzvo.create((byte[])paramObject);
  }
  
  public final void add(zzud paramZzud)
  {
    zztx();
    zzcai.add(paramZzud);
    modCount += 1;
  }
  
  public final boolean addAll(int paramInt, Collection paramCollection)
  {
    zztx();
    Object localObject = paramCollection;
    if ((paramCollection instanceof zzwc)) {
      localObject = ((zzwc)paramCollection).zzwv();
    }
    boolean bool = zzcai.addAll(paramInt, (Collection)localObject);
    modCount += 1;
    return bool;
  }
  
  public final boolean addAll(Collection paramCollection)
  {
    return addAll(size(), paramCollection);
  }
  
  public final void clear()
  {
    zztx();
    zzcai.clear();
    modCount += 1;
  }
  
  public final Object getRaw(int paramInt)
  {
    return zzcai.get(paramInt);
  }
  
  public final int size()
  {
    return zzcai.size();
  }
  
  public final List zzwv()
  {
    return Collections.unmodifiableList(zzcai);
  }
  
  public final zzwc zzww()
  {
    if (zztw()) {
      return new zzye(this);
    }
    return this;
  }
}
