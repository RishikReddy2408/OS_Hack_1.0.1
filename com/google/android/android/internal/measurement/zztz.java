package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzvs;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zztz<E>
  extends AbstractList<E>
  implements zzvs<E>
{
  private boolean zzbtu = true;
  
  zztz() {}
  
  public void add(int paramInt, Object paramObject)
  {
    zztx();
    super.add(paramInt, paramObject);
  }
  
  public boolean add(Object paramObject)
  {
    zztx();
    return super.add(paramObject);
  }
  
  public boolean addAll(int paramInt, Collection paramCollection)
  {
    zztx();
    return super.addAll(paramInt, paramCollection);
  }
  
  public boolean addAll(Collection paramCollection)
  {
    zztx();
    return super.addAll(paramCollection);
  }
  
  public void clear()
  {
    zztx();
    super.clear();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof List)) {
      return false;
    }
    if (!(paramObject instanceof RandomAccess)) {
      return super.equals(paramObject);
    }
    paramObject = (List)paramObject;
    int j = size();
    if (j != paramObject.size()) {
      return false;
    }
    int i = 0;
    while (i < j)
    {
      if (!get(i).equals(paramObject.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public int hashCode()
  {
    int k = size();
    int j = 1;
    int i = 0;
    while (i < k)
    {
      j = j * 31 + get(i).hashCode();
      i += 1;
    }
    return j;
  }
  
  public Object remove(int paramInt)
  {
    zztx();
    return super.remove(paramInt);
  }
  
  public boolean remove(Object paramObject)
  {
    zztx();
    return super.remove(paramObject);
  }
  
  public boolean removeAll(Collection paramCollection)
  {
    zztx();
    return super.removeAll(paramCollection);
  }
  
  public boolean retainAll(Collection paramCollection)
  {
    zztx();
    return super.retainAll(paramCollection);
  }
  
  public Object set(int paramInt, Object paramObject)
  {
    zztx();
    return super.set(paramInt, paramObject);
  }
  
  public final void zzsm()
  {
    zzbtu = false;
  }
  
  public boolean zztw()
  {
    return zzbtu;
  }
  
  protected final void zztx()
  {
    if (zzbtu) {
      return;
    }
    throw new UnsupportedOperationException();
  }
}
