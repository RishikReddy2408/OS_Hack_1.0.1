package com.google.android.android.common.data;

import java.util.ArrayList;
import java.util.Iterator;

public final class FreezableUtils
{
  public FreezableUtils() {}
  
  public static ArrayList freeze(ArrayList paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((Freezable)paramArrayList.get(i)).freeze());
      i += 1;
    }
    return localArrayList;
  }
  
  public static ArrayList freeze(Freezable[] paramArrayOfFreezable)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfFreezable.length);
    int i = 0;
    while (i < paramArrayOfFreezable.length)
    {
      localArrayList.add(paramArrayOfFreezable[i].freeze());
      i += 1;
    }
    return localArrayList;
  }
  
  public static ArrayList freezeIterable(Iterable paramIterable)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add(((Freezable)paramIterable.next()).freeze());
    }
    return localArrayList;
  }
}
