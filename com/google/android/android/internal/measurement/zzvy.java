package com.google.android.android.internal.measurement;

import java.util.Map.Entry;

final class zzvy<K>
  implements Map.Entry<K, Object>
{
  private Map.Entry<K, com.google.android.gms.internal.measurement.zzvw> zzcab;
  
  private zzvy(Map.Entry paramEntry)
  {
    zzcab = paramEntry;
  }
  
  public final Object getKey()
  {
    return zzcab.getKey();
  }
  
  public final Object getValue()
  {
    if ((zzvw)zzcab.getValue() == null) {
      return null;
    }
    return zzvw.zzwt();
  }
  
  public final Object setValue(Object paramObject)
  {
    if ((paramObject instanceof zzwt)) {
      return ((zzvw)zzcab.getValue()).addValue((zzwt)paramObject);
    }
    throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
  }
  
  public final zzvw zzwu()
  {
    return (zzvw)zzcab.getValue();
  }
}
