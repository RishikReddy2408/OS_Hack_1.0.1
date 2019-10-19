package android.arch.core.internal;
import java.util.HashMap;
import java.util.Map.Entry;
import android.support.annotation.RestrictTo;
//Restriction
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V>
  extends SafeIterableMap<K, V>
{
  private HashMap<K, SafeIterableMap.Entry<K, V>> mHashMap = new HashMap();//Using HashMaps
  public FastSafeIterableMap() {}
  public Map.Entry ceil(Object paramObject)
  {
    if (contains(paramObject)) {
      return mHashMap.get(paramObject)).mPrevious;
    }
    return null;
  }
  public boolean contains(Object paramObject)  //Checking the 
  {
    return mHashMap.containsKey(paramObject);
  }
  protected SafeIterableMap.Entry get(Object paramObject)  //Entry
  {
    return (SafeIterableMap.Entry)mHashMap.get(paramObject);
  }
  public Object putIfAbsent(Object paramObject1, Object paramObject2)  //Taking the parameters
  {
    SafeIterableMap.Entry localEntry = get(paramObject1);
    if (localEntry != null) {
      return mValue;
    }
    mHashMap.put(paramObject1, append(paramObject1, paramObject2));
    return null;
  }
  public Object remove(Object paramObject)  //Removing the parameter paramObject
  {
    Object localObject = super.remove(paramObject);
    mHashMap.remove(paramObject);
    return localObject;
  }
}
