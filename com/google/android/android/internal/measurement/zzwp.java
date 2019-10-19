package com.google.android.android.internal.measurement;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzwp
  implements zzwo
{
  zzwp() {}
  
  public final Object add(Object paramObject1, Object paramObject2)
  {
    zzwn localZzwn = (zzwn)paramObject1;
    paramObject2 = (zzwn)paramObject2;
    paramObject1 = localZzwn;
    if (!paramObject2.isEmpty())
    {
      paramObject1 = localZzwn;
      if (!localZzwn.isMutable()) {
        paramObject1 = localZzwn.zzxb();
      }
      paramObject1.setProperties(paramObject2);
    }
    return paramObject1;
  }
  
  public final Map get(Object paramObject)
  {
    return (zzwn)paramObject;
  }
  
  public final Map getList(Object paramObject)
  {
    return (zzwn)paramObject;
  }
  
  public final int register(int paramInt, Object paramObject1, Object paramObject2)
  {
    paramObject1 = (zzwn)paramObject1;
    if (paramObject1.isEmpty()) {
      return 0;
    }
    paramObject1 = paramObject1.entrySet().iterator();
    if (!paramObject1.hasNext()) {
      return 0;
    }
    paramObject1 = (Map.Entry)paramObject1.next();
    paramObject1.getKey();
    paramObject1.getValue();
    throw new NoSuchMethodError();
  }
  
  public final boolean zzaa(Object paramObject)
  {
    return !((zzwn)paramObject).isMutable();
  }
  
  public final Object zzab(Object paramObject)
  {
    ((zzwn)paramObject).zzsm();
    return paramObject;
  }
  
  public final Object zzac(Object paramObject)
  {
    return zzwn.zzxa().zzxb();
  }
  
  public final zzwm zzad(Object paramObject)
  {
    throw new NoSuchMethodError();
  }
}
