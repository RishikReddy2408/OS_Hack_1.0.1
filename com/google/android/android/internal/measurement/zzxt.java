package com.google.android.android.internal.measurement;

import java.util.Map.Entry;

final class zzxt
  implements Comparable<com.google.android.gms.internal.measurement.zzxt>, Map.Entry<K, V>
{
  private V value;
  private final K zzcck;
  
  zzxt(zzxm paramZzxm, Comparable paramComparable, Object paramObject)
  {
    zzcck = paramComparable;
    value = paramObject;
  }
  
  zzxt(zzxm paramZzxm, Map.Entry paramEntry)
  {
    this(paramZzxm, (Comparable)paramEntry.getKey(), paramEntry.getValue());
  }
  
  private static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      return paramObject2 == null;
    }
    return paramObject1.equals(paramObject2);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof Map.Entry)) {
      return false;
    }
    paramObject = (Map.Entry)paramObject;
    return (equals(zzcck, paramObject.getKey())) && (equals(value, paramObject.getValue()));
  }
  
  public final Object getValue()
  {
    return value;
  }
  
  public final int hashCode()
  {
    Comparable localComparable = zzcck;
    int j = 0;
    int i;
    if (localComparable == null) {
      i = 0;
    } else {
      i = zzcck.hashCode();
    }
    if (value != null) {
      j = value.hashCode();
    }
    return i ^ j;
  }
  
  public final Object setValue(Object paramObject)
  {
    zzxm.setAnswer(zzcch);
    Object localObject = value;
    value = paramObject;
    return localObject;
  }
  
  public final String toString()
  {
    String str1 = String.valueOf(zzcck);
    String str2 = String.valueOf(value);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 1 + String.valueOf(str2).length());
    localStringBuilder.append(str1);
    localStringBuilder.append("=");
    localStringBuilder.append(str2);
    return localStringBuilder.toString();
  }
}
