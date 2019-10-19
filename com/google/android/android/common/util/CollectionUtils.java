package com.google.android.android.common.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@KeepForSdk
public final class CollectionUtils
{
  private CollectionUtils() {}
  
  private static Set get(int paramInt, boolean paramBoolean)
  {
    float f;
    if (paramBoolean) {
      f = 0.75F;
    } else {
      f = 1.0F;
    }
    int i;
    if (paramBoolean) {
      i = 128;
    } else {
      i = 256;
    }
    if (paramInt <= i) {
      return new ArraySet(paramInt);
    }
    return new HashSet(paramInt, f);
  }
  
  public static boolean isEmpty(Collection paramCollection)
  {
    if (paramCollection == null) {
      return true;
    }
    return paramCollection.isEmpty();
  }
  
  public static List listOf()
  {
    return Collections.emptyList();
  }
  
  public static List listOf(Object paramObject)
  {
    return Collections.singletonList(paramObject);
  }
  
  public static List listOf(Object... paramVarArgs)
  {
    switch (paramVarArgs.length)
    {
    default: 
      return Collections.unmodifiableList(Arrays.asList(paramVarArgs));
    case 1: 
      return listOf(paramVarArgs[0]);
    }
    return listOf();
  }
  
  public static Map mapOf(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    Map localMap = put(3, false);
    localMap.put(paramObject1, paramObject2);
    localMap.put(paramObject3, paramObject4);
    localMap.put(paramObject5, paramObject6);
    return Collections.unmodifiableMap(localMap);
  }
  
  public static Map mapOf(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7, Object paramObject8, Object paramObject9, Object paramObject10, Object paramObject11, Object paramObject12)
  {
    Map localMap = put(6, false);
    localMap.put(paramObject1, paramObject2);
    localMap.put(paramObject3, paramObject4);
    localMap.put(paramObject5, paramObject6);
    localMap.put(paramObject7, paramObject8);
    localMap.put(paramObject9, paramObject10);
    localMap.put(paramObject11, paramObject12);
    return Collections.unmodifiableMap(localMap);
  }
  
  public static Map mapOfKeyValueArrays(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    if (paramArrayOfObject1.length == paramArrayOfObject2.length)
    {
      j = paramArrayOfObject1.length;
      i = 0;
      Map localMap;
      switch (j)
      {
      default: 
        localMap = put(paramArrayOfObject1.length, false);
        break;
      case 1: 
        return Collections.singletonMap(paramArrayOfObject1[0], paramArrayOfObject2[0]);
      case 0: 
        return Collections.emptyMap();
      }
      while (i < paramArrayOfObject1.length)
      {
        localMap.put(paramArrayOfObject1[i], paramArrayOfObject2[i]);
        i += 1;
      }
      return Collections.unmodifiableMap(localMap);
    }
    int i = paramArrayOfObject1.length;
    int j = paramArrayOfObject2.length;
    paramArrayOfObject1 = new StringBuilder(66);
    paramArrayOfObject1.append("Key and values array lengths not equal: ");
    paramArrayOfObject1.append(i);
    paramArrayOfObject1.append(" != ");
    paramArrayOfObject1.append(j);
    throw new IllegalArgumentException(paramArrayOfObject1.toString());
  }
  
  public static Set mutableSetOfWithSize(int paramInt)
  {
    if (paramInt == 0) {
      return new ArraySet();
    }
    return get(paramInt, true);
  }
  
  private static Map put(int paramInt, boolean paramBoolean)
  {
    if (paramInt <= 256) {
      return new ArrayMap(paramInt);
    }
    return new HashMap(paramInt, 1.0F);
  }
  
  public static Set setOf(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Set localSet = get(3, false);
    localSet.add(paramObject1);
    localSet.add(paramObject2);
    localSet.add(paramObject3);
    return Collections.unmodifiableSet(localSet);
  }
  
  public static Set setOf(Object... paramVarArgs)
  {
    Object localObject1;
    Object localObject2;
    switch (paramVarArgs.length)
    {
    default: 
      localObject1 = get(paramVarArgs.length, false);
      Collections.addAll((Collection)localObject1, paramVarArgs);
      return Collections.unmodifiableSet((Set)localObject1);
    case 4: 
      localObject1 = paramVarArgs[0];
      localObject2 = paramVarArgs[1];
      Object localObject3 = paramVarArgs[2];
      paramVarArgs = paramVarArgs[3];
      Set localSet = get(4, false);
      localSet.add(localObject1);
      localSet.add(localObject2);
      localSet.add(localObject3);
      localSet.add(paramVarArgs);
      return Collections.unmodifiableSet(localSet);
    case 3: 
      return setOf(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2]);
    case 2: 
      localObject1 = paramVarArgs[0];
      paramVarArgs = paramVarArgs[1];
      localObject2 = get(2, false);
      ((Set)localObject2).add(localObject1);
      ((Set)localObject2).add(paramVarArgs);
      return Collections.unmodifiableSet((Set)localObject2);
    case 1: 
      return Collections.singleton(paramVarArgs[0]);
    }
    return Collections.emptySet();
  }
}
