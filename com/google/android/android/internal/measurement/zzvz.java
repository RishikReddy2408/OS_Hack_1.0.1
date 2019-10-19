package com.google.android.android.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzvz<K>
  implements Iterator<Map.Entry<K, Object>>
{
  private Iterator<Map.Entry<K, Object>> zzcac;
  
  public zzvz(Iterator paramIterator)
  {
    zzcac = paramIterator;
  }
  
  public final boolean hasNext()
  {
    return zzcac.hasNext();
  }
  
  public final void remove()
  {
    zzcac.remove();
  }
}
