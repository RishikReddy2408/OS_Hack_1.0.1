package com.google.android.android.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzwn<K, V>
  extends LinkedHashMap<K, V>
{
  private static final zzwn zzcau;
  private boolean zzbtu = true;
  
  static
  {
    zzwn localZzwn = new zzwn();
    zzcau = localZzwn;
    zzbtu = false;
  }
  
  private zzwn() {}
  
  private zzwn(Map paramMap)
  {
    super(paramMap);
  }
  
  private static int hash(Object paramObject)
  {
    if ((paramObject instanceof byte[])) {
      return zzvo.hashCode((byte[])paramObject);
    }
    if (!(paramObject instanceof zzvp)) {
      return paramObject.hashCode();
    }
    throw new UnsupportedOperationException();
  }
  
  public static zzwn zzxa()
  {
    return zzcau;
  }
  
  private final void zzxc()
  {
    if (zzbtu) {
      return;
    }
    throw new UnsupportedOperationException();
  }
  
  public final void clear()
  {
    zzxc();
    super.clear();
  }
  
  public final Set entrySet()
  {
    if (isEmpty()) {
      return Collections.emptySet();
    }
    return super.entrySet();
  }
  
  public final boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Map))
    {
      paramObject = (Map)paramObject;
      if (this != paramObject)
      {
        if (size() != paramObject.size()) {}
        for (;;)
        {
          i = 0;
          break label165;
          Iterator localIterator = entrySet().iterator();
          boolean bool;
          do
          {
            if (!localIterator.hasNext()) {
              break label163;
            }
            Object localObject2 = (Map.Entry)localIterator.next();
            if (!paramObject.containsKey(((Map.Entry)localObject2).getKey())) {
              break;
            }
            Object localObject1 = ((Map.Entry)localObject2).getValue();
            localObject2 = paramObject.get(((Map.Entry)localObject2).getKey());
            if (((localObject1 instanceof byte[])) && ((localObject2 instanceof byte[]))) {
              bool = Arrays.equals((byte[])localObject1, (byte[])localObject2);
            } else {
              bool = localObject1.equals(localObject2);
            }
          } while (bool);
        }
      }
      label163:
      int i = 1;
      label165:
      if (i != 0) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    Iterator localIterator = entrySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      int j = hash(localEntry.getKey());
      i += (hash(localEntry.getValue()) ^ j);
    }
    return i;
  }
  
  public final boolean isMutable()
  {
    return zzbtu;
  }
  
  public final Object put(Object paramObject1, Object paramObject2)
  {
    zzxc();
    zzvo.checkNotNull(paramObject1);
    zzvo.checkNotNull(paramObject2);
    return super.put(paramObject1, paramObject2);
  }
  
  public final void putAll(Map paramMap)
  {
    zzxc();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      zzvo.checkNotNull(localObject);
      zzvo.checkNotNull(paramMap.get(localObject));
    }
    super.putAll(paramMap);
  }
  
  public final Object remove(Object paramObject)
  {
    zzxc();
    return super.remove(paramObject);
  }
  
  public final void setProperties(zzwn paramZzwn)
  {
    zzxc();
    if (!paramZzwn.isEmpty()) {
      putAll(paramZzwn);
    }
  }
  
  public final void zzsm()
  {
    zzbtu = false;
  }
  
  public final zzwn zzxb()
  {
    if (isEmpty()) {
      return new zzwn();
    }
    return new zzwn(this);
  }
}
