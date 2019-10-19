package android.arch.core.internal;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
//Restrict
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V>
  implements Iterable<Map.Entry<K, V>>
{
  private Entry<K, V> mEnd;
  private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap();
  private int mSize = 0;
  private Entry<K, V> mStart;
  public SafeIterableMap() {}
  protected Entry append(Object paramObject1, Object paramObject2)
  {
    paramObject1 = new Entry(paramObject1, paramObject2);
    mSize += 1;
    if (mEnd == null)
    {
      mStart = paramObject1;
      mEnd = mStart;
      return paramObject1;
    }
    mEnd.mNext = paramObject1;
    mPrevious = mEnd;
    mEnd = paramObject1;
    return paramObject1;
  }
  public Iterator descendingIterator()
  {
    DescendingIterator localDescendingIterator = new DescendingIterator(mEnd, mStart);
    mIterators.put(localDescendingIterator, Boolean.valueOf(false));
    return localDescendingIterator;
  }
  public Map.Entry eldest()
  {
    return mStart;
  }
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof SafeIterableMap)) {
      return false;
    }
    Object localObject1 = (SafeIterableMap)paramObject;
    if (size() != ((SafeIterableMap)localObject1).size()) {
      return false;
    }
    paramObject = iterator();
    localObject1 = ((SafeIterableMap)localObject1).iterator();
    while ((paramObject.hasNext()) && (((Iterator)localObject1).hasNext()))
    {
      Map.Entry localEntry = (Map.Entry)paramObject.next();
      Object localObject2 = ((Iterator)localObject1).next();
      if ((localEntry == null) && (localObject2 != null)) {
        break label128;
      }
      if ((localEntry != null) && (!localEntry.equals(localObject2))) {
        return false;
      }
    }
    return (!paramObject.hasNext()) && (!((Iterator)localObject1).hasNext());
    label128:
    return false;
  }
  protected Entry get(Object paramObject)
  {
    for (Entry localEntry = mStart; localEntry != null; localEntry = mNext) {
      if (mKey.equals(paramObject)) {
        return localEntry;
      }
    }
    return localEntry;
  }
  public Iterator iterator()
  {
    AscendingIterator localAscendingIterator = new AscendingIterator(mStart, mEnd);
    mIterators.put(localAscendingIterator, Boolean.valueOf(false));
    return localAscendingIterator;
  }
  public IteratorWithAdditions iteratorWithAdditions()
  {
    IteratorWithAdditions localIteratorWithAdditions = new IteratorWithAdditions(null);
    mIterators.put(localIteratorWithAdditions, Boolean.valueOf(false));
    return localIteratorWithAdditions;
  }
  public Map.Entry newest()
  {
    return mEnd;
  }
  public Object putIfAbsent(Object paramObject1, Object paramObject2)
  {
    Entry localEntry = get(paramObject1);
    if (localEntry != null) {
      return mValue;
    }
    append(paramObject1, paramObject2);
    return null;
  }
  public Object remove(Object paramObject)
  {
    paramObject = get(paramObject);
    if (paramObject == null) {
      return null;
    }
    mSize -= 1;
    if (!mIterators.isEmpty())
    {
      Iterator localIterator = mIterators.keySet().iterator();
      while (localIterator.hasNext()) {
        ((SupportRemove)localIterator.next()).supportRemove(paramObject);
      }
    }
    if (mPrevious != null) {
      mPrevious.mNext = mNext;
    }
    else
    {
      mStart = mNext;
    }
    if (mNext != null) {
      mNext.mPrevious = mPrevious;
    }
    else
    {
      mEnd = mPrevious;
    }
    mNext = null;
    mPrevious = null;
    return mValue;
  }
  public int size()
  {
    return mSize;
  }
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((Map.Entry)localIterator.next()).toString());
      if (localIterator.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  static class AscendingIterator<K, V>
    extends SafeIterableMap.ListIterator<K, V>
  {
    AscendingIterator(SafeIterableMap.Entry paramEntry1, SafeIterableMap.Entry paramEntry2)
    {
      super(paramEntry2);
    } 
    SafeIterableMap.Entry backward(SafeIterableMap.Entry paramEntry)
    {
      return mPrevious;
    }
    SafeIterableMap.Entry forward(SafeIterableMap.Entry paramEntry)
    {
      return mNext;
    }
  }
  
  private static class DescendingIterator<K, V>
    extends SafeIterableMap.ListIterator<K, V>
  {
    DescendingIterator(SafeIterableMap.Entry paramEntry1, SafeIterableMap.Entry paramEntry2)
    {
      super(paramEntry2);
    }
    
    SafeIterableMap.Entry backward(SafeIterableMap.Entry paramEntry)
    {
      return mNext;
    }
    
    SafeIterableMap.Entry forward(SafeIterableMap.Entry paramEntry)
    {
      return mPrevious;
    }
  }
  
  static class Entry<K, V>
    implements Map.Entry<K, V>
  {
    @NonNull
    final K mKey;
    Entry<K, V> mNext;
    Entry<K, V> mPrevious;
    @NonNull
    final V mValue;
    
    Entry(Object paramObject1, Object paramObject2)
    {
      mKey = paramObject1;
      mValue = paramObject2;
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {
        return true;
      }
      if (!(paramObject instanceof Entry)) {
        return false;
      }
      paramObject = (Entry)paramObject;
      return (mKey.equals(mKey)) && (mValue.equals(mValue));
    }
    
    public Object getKey()
    {
      return mKey;
    }
    
    public Object getValue()
    {
      return mValue;
    }
    
    public Object setValue(Object paramObject)
    {
      throw new UnsupportedOperationException("An entry modification is not supported");
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(mKey);
      localStringBuilder.append("=");
      localStringBuilder.append(mValue);
      return localStringBuilder.toString();
    }
  }
  
  private class IteratorWithAdditions
    implements Iterator<Map.Entry<K, V>>, SafeIterableMap.SupportRemove<K, V>
  {
    private boolean mBeforeStart = true;
    private SafeIterableMap.Entry<K, V> mCurrent;
    private IteratorWithAdditions() {}
    public boolean hasNext()
    {
      if (mBeforeStart)
      {
        if (mStart != null) {
          return true;
        }
      }
      else if ((mCurrent != null) && (mCurrent.mNext != null)) {
        return true;
      }
      return false;
    }
    public Map.Entry next()
    {
      if (mBeforeStart)
      {
        mBeforeStart = false;
        mCurrent = mStart;
      }
      else
      {
        SafeIterableMap.Entry localEntry;
        if (mCurrent != null) {
          localEntry = mCurrent.mNext;
        } else {
          localEntry = null;
        }
        mCurrent = localEntry;
      }
      return mCurrent;
    }
    public void supportRemove(SafeIterableMap.Entry paramEntry)
    {
      if (paramEntry == mCurrent)
      {
        mCurrent = mCurrent.mPrevious;
        boolean bool;
        if (mCurrent == null) {
          bool = true;
        }
        else
        {
          bool = false;
        }
        mBeforeStart = bool;
      }
    }
  }
  private static abstract class ListIterator<K, V>
    implements Iterator<Map.Entry<K, V>>, SafeIterableMap.SupportRemove<K, V>
  {
    SafeIterableMap.Entry<K, V> mExpectedEnd;
    SafeIterableMap.Entry<K, V> mNext; 
    ListIterator(SafeIterableMap.Entry paramEntry1, SafeIterableMap.Entry paramEntry2)
    {
      mExpectedEnd = paramEntry2;
      mNext = paramEntry1;
    }
    private SafeIterableMap.Entry nextNode()
    {
      if ((mNext != mExpectedEnd) && (mExpectedEnd != null)) {
        return forward(mNext);
      }
      return null;
    }
    abstract SafeIterableMap.Entry backward(SafeIterableMap.Entry paramEntry);
    abstract SafeIterableMap.Entry forward(SafeIterableMap.Entry paramEntry);
    public boolean hasNext()
    {
      return mNext != null;
    }
    public Map.Entry next()
    {
      SafeIterableMap.Entry localEntry = mNext;
      mNext = nextNode();
      return localEntry;
    }
    public void supportRemove(SafeIterableMap.Entry paramEntry)
    {
      if ((mExpectedEnd == paramEntry) && (paramEntry == mNext))
      {
        mNext = null;
        mExpectedEnd = null;
      }
      if (mExpectedEnd == paramEntry) {
        mExpectedEnd = backward(mExpectedEnd);
      }
      if (mNext == paramEntry) {
        mNext = nextNode();
      }
    }
  }
  static abstract interface SupportRemove<K, V>
  {
    public abstract void supportRemove(SafeIterableMap.Entry paramEntry);
  }
}
