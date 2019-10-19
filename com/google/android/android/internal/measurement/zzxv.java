package com.google.android.android.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzxv
  extends AbstractSet<Map.Entry<K, V>>
{
  private zzxv(zzxm paramZzxm) {}
  
  public void clear()
  {
    zzcch.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    Object localObject = (Map.Entry)paramObject;
    paramObject = zzcch.get(((Map.Entry)localObject).getKey());
    localObject = ((Map.Entry)localObject).getValue();
    return (paramObject == localObject) || ((paramObject != null) && (paramObject.equals(localObject)));
  }
  
  public Iterator iterator()
  {
    return new zzxu(zzcch, null);
  }
  
  public boolean remove(Object paramObject)
  {
    paramObject = (Map.Entry)paramObject;
    if (contains(paramObject))
    {
      zzcch.remove(paramObject.getKey());
      return true;
    }
    return false;
  }
  
  public int size()
  {
    return zzcch.size();
  }
}
