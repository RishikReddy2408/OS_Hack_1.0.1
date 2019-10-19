package com.google.android.android.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzxo
  implements Iterator<Map.Entry<K, V>>
{
  private int nextIndex = zzxm.access$getResults(zzcch).size();
  private Iterator<Map.Entry<K, V>> zzccg;
  
  private zzxo(zzxm paramZzxm) {}
  
  private final Iterator zzyb()
  {
    if (zzccg == null) {
      zzccg = zzxm.getAreas(zzcch).entrySet().iterator();
    }
    return zzccg;
  }
  
  public final boolean hasNext()
  {
    return ((nextIndex > 0) && (nextIndex <= zzxm.access$getResults(zzcch).size())) || (zzyb().hasNext());
  }
  
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}
