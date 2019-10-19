package com.google.android.android.internal.measurement;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzxm<K extends Comparable<K>, V>
  extends AbstractMap<K, V>
{
  private boolean zzbpo;
  private final int zzcca;
  private List<com.google.android.gms.internal.measurement.zzxt> zzccb;
  private Map<K, V> zzccc;
  private volatile com.google.android.gms.internal.measurement.zzxv zzccd;
  private Map<K, V> zzcce;
  private volatile com.google.android.gms.internal.measurement.zzxp zzccf;
  
  private zzxm(int paramInt)
  {
    zzcca = paramInt;
    zzccb = Collections.emptyList();
    zzccc = Collections.emptyMap();
    zzcce = Collections.emptyMap();
  }
  
  private final int binarySearch(Comparable paramComparable)
  {
    int j = zzccb.size() - 1;
    if (j >= 0)
    {
      i = paramComparable.compareTo((Comparable)((zzxt)zzccb.get(j)).getKey());
      if (i > 0) {
        return -(j + 2);
      }
      if (i == 0) {
        return j;
      }
    }
    int i = 0;
    while (i <= j)
    {
      int k = (i + j) / 2;
      int m = paramComparable.compareTo((Comparable)((zzxt)zzccb.get(k)).getKey());
      if (m < 0) {
        j = k - 1;
      } else if (m > 0) {
        i = k + 1;
      } else {
        return k;
      }
    }
    return -(i + 1);
  }
  
  static zzxm zzbt(int paramInt)
  {
    return new zzxn(paramInt);
  }
  
  private final Object zzbv(int paramInt)
  {
    zzxz();
    Object localObject = ((zzxt)zzccb.remove(paramInt)).getValue();
    if (!zzccc.isEmpty())
    {
      Iterator localIterator = zzya().entrySet().iterator();
      zzccb.add(new zzxt(this, (Map.Entry)localIterator.next()));
      localIterator.remove();
    }
    return localObject;
  }
  
  private final void zzxz()
  {
    if (!zzbpo) {
      return;
    }
    throw new UnsupportedOperationException();
  }
  
  private final SortedMap zzya()
  {
    zzxz();
    if ((zzccc.isEmpty()) && (!(zzccc instanceof TreeMap)))
    {
      zzccc = new TreeMap();
      zzcce = ((TreeMap)zzccc).descendingMap();
    }
    return (SortedMap)zzccc;
  }
  
  public void clear()
  {
    zzxz();
    if (!zzccb.isEmpty()) {
      zzccb.clear();
    }
    if (!zzccc.isEmpty()) {
      zzccc.clear();
    }
  }
  
  public boolean containsKey(Object paramObject)
  {
    paramObject = (Comparable)paramObject;
    return (binarySearch(paramObject) >= 0) || (zzccc.containsKey(paramObject));
  }
  
  public Set entrySet()
  {
    if (zzccd == null) {
      zzccd = new zzxv(this, null);
    }
    return zzccd;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzxm)) {
      return super.equals(paramObject);
    }
    paramObject = (zzxm)paramObject;
    int j = size();
    if (j != paramObject.size()) {
      return false;
    }
    int k = zzxw();
    if (k != paramObject.zzxw()) {
      return entrySet().equals(paramObject.entrySet());
    }
    int i = 0;
    while (i < k)
    {
      if (!zzbu(i).equals(paramObject.zzbu(i))) {
        return false;
      }
      i += 1;
    }
    if (k != j) {
      return zzccc.equals(zzccc);
    }
    return true;
  }
  
  public Object get(Object paramObject)
  {
    paramObject = (Comparable)paramObject;
    int i = binarySearch(paramObject);
    if (i >= 0) {
      return ((zzxt)zzccb.get(i)).getValue();
    }
    return zzccc.get(paramObject);
  }
  
  public int hashCode()
  {
    int k = zzxw();
    int j = 0;
    int i = 0;
    while (j < k)
    {
      i += ((zzxt)zzccb.get(j)).hashCode();
      j += 1;
    }
    j = i;
    if (zzccc.size() > 0) {
      j = i + zzccc.hashCode();
    }
    return j;
  }
  
  public final boolean isImmutable()
  {
    return zzbpo;
  }
  
  public final Object put(Comparable paramComparable, Object paramObject)
  {
    zzxz();
    int i = binarySearch(paramComparable);
    if (i >= 0) {
      return ((zzxt)zzccb.get(i)).setValue(paramObject);
    }
    zzxz();
    if ((zzccb.isEmpty()) && (!(zzccb instanceof ArrayList))) {
      zzccb = new ArrayList(zzcca);
    }
    i = -(i + 1);
    if (i >= zzcca) {
      return zzya().put(paramComparable, paramObject);
    }
    if (zzccb.size() == zzcca)
    {
      zzxt localZzxt = (zzxt)zzccb.remove(zzcca - 1);
      zzya().put((Comparable)localZzxt.getKey(), localZzxt.getValue());
    }
    zzccb.add(i, new zzxt(this, paramComparable, paramObject));
    return null;
  }
  
  public Object remove(Object paramObject)
  {
    zzxz();
    paramObject = (Comparable)paramObject;
    int i = binarySearch(paramObject);
    if (i >= 0) {
      return zzbv(i);
    }
    if (zzccc.isEmpty()) {
      return null;
    }
    return zzccc.remove(paramObject);
  }
  
  public int size()
  {
    return zzccb.size() + zzccc.size();
  }
  
  public final Map.Entry zzbu(int paramInt)
  {
    return (Map.Entry)zzccb.get(paramInt);
  }
  
  public void zzsm()
  {
    if (!zzbpo)
    {
      Map localMap;
      if (zzccc.isEmpty()) {
        localMap = Collections.emptyMap();
      } else {
        localMap = Collections.unmodifiableMap(zzccc);
      }
      zzccc = localMap;
      if (zzcce.isEmpty()) {
        localMap = Collections.emptyMap();
      } else {
        localMap = Collections.unmodifiableMap(zzcce);
      }
      zzcce = localMap;
      zzbpo = true;
    }
  }
  
  public final int zzxw()
  {
    return zzccb.size();
  }
  
  public final Iterable zzxx()
  {
    if (zzccc.isEmpty()) {
      return zzxq.zzyc();
    }
    return zzccc.entrySet();
  }
  
  final Set zzxy()
  {
    if (zzccf == null) {
      zzccf = new zzxp(this, null);
    }
    return zzccf;
  }
}
