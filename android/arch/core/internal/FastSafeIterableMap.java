package android.arch.core.internal;

import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map.Entry;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V>
  extends SafeIterableMap<K, V>
{
  private HashMap<K, SafeIterableMap.Entry<K, V>> mHashMap = new HashMap();
  
  public FastSafeIterableMap() {}
  
  public Map.Entry ceil(Object paramObject)
  {
    if (contains(paramObject)) {
      return mHashMap.get(paramObject)).mPrevious;
    }
    return null;
  }
  
  public boolean contains(Object paramObject)
  {
    return mHashMap.containsKey(paramObject);
  }
  
  protected SafeIterableMap.Entry get(Object paramObject)
  {
    return (SafeIterableMap.Entry)mHashMap.get(paramObject);
  }
  
  public Object putIfAbsent(Object paramObject1, Object paramObject2)
  {
    SafeIterableMap.Entry localEntry = get(paramObject1);
    if (localEntry != null) {
      return mValue;
    }
    mHashMap.put(paramObject1, append(paramObject1, paramObject2));
    return null;
  }
  
  public Object remove(Object paramObject)
  {
    Object localObject = super.remove(paramObject);
    mHashMap.remove(paramObject);
    return localObject;
  }
}
