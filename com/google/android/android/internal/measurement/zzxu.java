package com.google.android.android.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzxu
  implements Iterator<Map.Entry<K, V>>
{
  private int currentId = -1;
  private Iterator<Map.Entry<K, V>> zzccg;
  private boolean zzccl;
  
  private zzxu(zzxm paramZzxm) {}
  
  private final Iterator zzyb()
  {
    if (zzccg == null) {
      zzccg = zzxm.getPresences(zzcch).entrySet().iterator();
    }
    return zzccg;
  }
  
  public final boolean hasNext()
  {
    if (currentId + 1 >= zzxm.access$getResults(zzcch).size()) {
      return (!zzxm.getPresences(zzcch).isEmpty()) && (zzyb().hasNext());
    }
    return true;
  }
  
  public final void remove()
  {
    if (zzccl)
    {
      zzccl = false;
      zzxm.setAnswer(zzcch);
      if (currentId < zzxm.access$getResults(zzcch).size())
      {
        zzxm localZzxm = zzcch;
        int i = currentId;
        currentId = (i - 1);
        zzxm.valueOf(localZzxm, i);
        return;
      }
      zzyb().remove();
      return;
    }
    throw new IllegalStateException("remove() was called before next()");
  }
}
