package android.support.v4.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LruCache<K, V>
{
  private int createCount;
  private int evictionCount;
  private int hitCount;
  private final LinkedHashMap<K, V> map;
  private int maxSize;
  private int missCount;
  private int putCount;
  private int size;
  
  public LruCache(int paramInt)
  {
    if (paramInt > 0)
    {
      maxSize = paramInt;
      map = new LinkedHashMap(0, 0.75F, true);
      return;
    }
    throw new IllegalArgumentException("maxSize <= 0");
  }
  
  private int safeSizeOf(Object paramObject1, Object paramObject2)
  {
    int i = sizeOf(paramObject1, paramObject2);
    if (i >= 0) {
      return i;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Negative size: ");
    localStringBuilder.append(paramObject1);
    localStringBuilder.append("=");
    localStringBuilder.append(paramObject2);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  protected Object create(Object paramObject)
  {
    return null;
  }
  
  public final int createCount()
  {
    try
    {
      int i = createCount;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected void entryRemoved(boolean paramBoolean, Object paramObject1, Object paramObject2, Object paramObject3) {}
  
  public final void evictAll()
  {
    trimToSize(-1);
  }
  
  public final int evictionCount()
  {
    try
    {
      int i = evictionCount;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Object get(Object paramObject)
  {
    if (paramObject != null) {
      try
      {
        Object localObject1 = map.get(paramObject);
        if (localObject1 != null)
        {
          hitCount += 1;
          return localObject1;
        }
        missCount += 1;
        localObject1 = create(paramObject);
        if (localObject1 == null) {
          return null;
        }
        try
        {
          createCount += 1;
          Object localObject2 = map.put(paramObject, localObject1);
          if (localObject2 != null) {
            map.put(paramObject, localObject2);
          } else {
            size += safeSizeOf(paramObject, localObject1);
          }
          if (localObject2 != null)
          {
            entryRemoved(false, paramObject, localObject1, localObject2);
            return localObject2;
          }
          trimToSize(maxSize);
          return localObject1;
        }
        catch (Throwable paramObject)
        {
          throw paramObject;
        }
        throw new NullPointerException("key == null");
      }
      catch (Throwable paramObject)
      {
        throw paramObject;
      }
    }
  }
  
  public final int hitCount()
  {
    try
    {
      int i = hitCount;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final int maxSize()
  {
    try
    {
      int i = maxSize;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final int missCount()
  {
    try
    {
      int i = missCount;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Object put(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 != null) && (paramObject2 != null)) {
      try
      {
        putCount += 1;
        size += safeSizeOf(paramObject1, paramObject2);
        Object localObject = map.put(paramObject1, paramObject2);
        if (localObject != null) {
          size -= safeSizeOf(paramObject1, localObject);
        }
        if (localObject != null) {
          entryRemoved(false, paramObject1, localObject, paramObject2);
        }
        trimToSize(maxSize);
        return localObject;
      }
      catch (Throwable paramObject1)
      {
        throw paramObject1;
      }
    }
    throw new NullPointerException("key == null || value == null");
  }
  
  public final int putCount()
  {
    try
    {
      int i = putCount;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Object remove(Object paramObject)
  {
    Object localObject;
    if (paramObject != null) {
      try
      {
        localObject = map.remove(paramObject);
        if (localObject != null) {
          size -= safeSizeOf(paramObject, localObject);
        }
        if (localObject == null) {
          return localObject;
        }
        entryRemoved(false, paramObject, localObject, null);
        return localObject;
      }
      catch (Throwable paramObject)
      {
        throw paramObject;
      }
    }
    throw new NullPointerException("key == null");
    return localObject;
  }
  
  public void resize(int paramInt)
  {
    if (paramInt > 0) {
      try
      {
        maxSize = paramInt;
        trimToSize(paramInt);
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    throw new IllegalArgumentException("maxSize <= 0");
  }
  
  public final int size()
  {
    try
    {
      int i = size;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected int sizeOf(Object paramObject1, Object paramObject2)
  {
    return 1;
  }
  
  public final Map snapshot()
  {
    try
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap(map);
      return localLinkedHashMap;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final String toString()
  {
    for (;;)
    {
      try
      {
        i = hitCount + missCount;
        if (i != 0)
        {
          i = hitCount * 100 / i;
          String str = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { Integer.valueOf(maxSize), Integer.valueOf(hitCount), Integer.valueOf(missCount), Integer.valueOf(i) });
          return str;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      int i = 0;
    }
  }
  
  public void trimToSize(int paramInt)
  {
    Object localObject2 = this;
    for (;;)
    {
      Object localObject1 = localObject2;
      try
      {
        int i = size;
        Object localObject3 = localObject2;
        if (i >= 0)
        {
          localObject1 = localObject3;
          if (map.isEmpty())
          {
            localObject1 = localObject3;
            if (size != 0) {}
          }
          else
          {
            localObject1 = localObject2;
            i = size;
            if (i > paramInt)
            {
              localObject1 = localObject2;
              if (!map.isEmpty())
              {
                localObject1 = localObject2;
                localObject3 = (Map.Entry)map.entrySet().iterator().next();
                localObject1 = localObject2;
                Object localObject4 = ((Map.Entry)localObject3).getKey();
                localObject1 = localObject2;
                Object localObject5 = ((Map.Entry)localObject3).getValue();
                localObject1 = localObject2;
                map.remove(localObject4);
                localObject1 = localObject2;
                i = size;
                localObject3 = localObject2;
                localObject1 = localObject3;
                size = (i - ((LruCache)localObject2).safeSizeOf(localObject4, localObject5));
                localObject1 = localObject3;
                i = evictionCount;
                localObject2 = localObject3;
                localObject1 = localObject2;
                evictionCount = (i + 1);
                localObject1 = localObject2;
                ((LruCache)localObject2).entryRemoved(true, localObject4, localObject5, null);
                continue;
              }
            }
            localObject1 = localObject2;
            return;
          }
        }
        localObject1 = localObject2;
        localObject3 = new StringBuilder();
        localObject1 = localObject2;
        ((StringBuilder)localObject3).append(localObject2.getClass().getName());
        localObject1 = localObject2;
        ((StringBuilder)localObject3).append(".sizeOf() is reporting inconsistent results!");
        localObject1 = localObject2;
        throw new IllegalStateException(((StringBuilder)localObject3).toString());
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
}
