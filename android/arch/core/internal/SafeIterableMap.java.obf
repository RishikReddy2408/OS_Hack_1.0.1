package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V>
  implements Iterable<Map.Entry<K, V>>
{
  private Entry<K, V> mEnd;
  private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap();
  private int mSize = 0;
  private Entry<K, V> mStart;
  
  public SafeIterableMap() {}
  
  public Iterator<Map.Entry<K, V>> descendingIterator()
  {
    DescendingIterator localDescendingIterator = new DescendingIterator(mEnd, mStart);
    mIterators.put(localDescendingIterator, Boolean.valueOf(false));
    return localDescendingIterator;
  }
  
  public Map.Entry<K, V> eldest()
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
      if (((localEntry == null) && (localObject2 != null)) || ((localEntry != null) && (!localEntry.equals(localObject2)))) {
        return false;
      }
    }
    return (!paramObject.hasNext()) && (!((Iterator)localObject1).hasNext());
  }
  
  protected Entry<K, V> get(K paramK)
  {
    for (Entry localEntry = mStart; localEntry != null; localEntry = mNext) {
      if (mKey.equals(paramK)) {
        return localEntry;
      }
    }
    return localEntry;
  }
  
  @NonNull
  public Iterator<Map.Entry<K, V>> iterator()
  {
    AscendingIterator localAscendingIterator = new AscendingIterator(mStart, mEnd);
    mIterators.put(localAscendingIterator, Boolean.valueOf(false));
    return localAscendingIterator;
  }
  
  public SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions()
  {
    IteratorWithAdditions localIteratorWithAdditions = new IteratorWithAdditions(null);
    mIterators.put(localIteratorWithAdditions, Boolean.valueOf(false));
    return localIteratorWithAdditions;
  }
  
  public Map.Entry<K, V> newest()
  {
    return mEnd;
  }
  
  protected Entry<K, V> put(@NonNull K paramK, @NonNull V paramV)
  {
    paramK = new Entry(paramK, paramV);
    mSize += 1;
    if (mEnd == null)
    {
      mStart = paramK;
      mEnd = mStart;
      return paramK;
    }
    mEnd.mNext = paramK;
    mPrevious = mEnd;
    mEnd = paramK;
    return paramK;
  }
  
  public V putIfAbsent(@NonNull K paramK, @NonNull V paramV)
  {
    Entry localEntry = get(paramK);
    if (localEntry != null) {
      return mValue;
    }
    put(paramK, paramV);
    return null;
  }
  
  public V remove(@NonNull K paramK)
  {
    paramK = get(paramK);
    if (paramK == null) {
      return null;
    }
    mSize -= 1;
    if (!mIterators.isEmpty())
    {
      Iterator localIterator = mIterators.keySet().iterator();
      while (localIterator.hasNext()) {
        ((SupportRemove)localIterator.next()).supportRemove(paramK);
      }
    }
    if (mPrevious != null) {
      mPrevious.mNext = mNext;
    } else {
      mStart = mNext;
    }
    if (mNext != null) {
      mNext.mPrevious = mPrevious;
    } else {
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
    AscendingIterator(SafeIterableMap.Entry<K, V> paramEntry1, SafeIterableMap.Entry<K, V> paramEntry2)
    {
      super(paramEntry2);
    }
    
    SafeIterableMap.Entry<K, V> backward(SafeIterableMap.Entry<K, V> paramEntry)
    {
      return mPrevious;
    }
    
    SafeIterableMap.Entry<K, V> forward(SafeIterableMap.Entry<K, V> paramEntry)
    {
      return mNext;
    }
  }
  
  private static class DescendingIterator<K, V>
    extends SafeIterableMap.ListIterator<K, V>
  {
    DescendingIterator(SafeIterableMap.Entry<K, V> paramEntry1, SafeIterableMap.Entry<K, V> paramEntry2)
    {
      super(paramEntry2);
    }
    
    SafeIterableMap.Entry<K, V> backward(SafeIterableMap.Entry<K, V> paramEntry)
    {
      return mNext;
    }
    
    SafeIterableMap.Entry<K, V> forward(SafeIterableMap.Entry<K, V> paramEntry)
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
    
    Entry(@NonNull K paramK, @NonNull V paramV)
    {
      mKey = paramK;
      mValue = paramV;
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
    
    @NonNull
    public K getKey()
    {
      return mKey;
    }
    
    @NonNull
    public V getValue()
    {
      return mValue;
    }
    
    public V setValue(V paramV)
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
      boolean bool3 = mBeforeStart;
      boolean bool2 = false;
      boolean bool1 = false;
      if (bool3)
      {
        if (mStart != null) {
          bool1 = true;
        }
        return bool1;
      }
      bool1 = bool2;
      if (mCurrent != null)
      {
        bool1 = bool2;
        if (mCurrent.mNext != null) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public Map.Entry<K, V> next()
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
    
    public void supportRemove(@NonNull SafeIterableMap.Entry<K, V> paramEntry)
    {
      if (paramEntry == mCurrent)
      {
        mCurrent = mCurrent.mPrevious;
        boolean bool;
        if (mCurrent == null) {
          bool = true;
        } else {
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
    
    ListIterator(SafeIterableMap.Entry<K, V> paramEntry1, SafeIterableMap.Entry<K, V> paramEntry2)
    {
      mExpectedEnd = paramEntry2;
      mNext = paramEntry1;
    }
    
    private SafeIterableMap.Entry<K, V> nextNode()
    {
      if ((mNext != mExpectedEnd) && (mExpectedEnd != null)) {
        return forward(mNext);
      }
      return null;
    }
    
    abstract SafeIterableMap.Entry<K, V> backward(SafeIterableMap.Entry<K, V> paramEntry);
    
    abstract SafeIterableMap.Entry<K, V> forward(SafeIterableMap.Entry<K, V> paramEntry);
    
    public boolean hasNext()
    {
      return mNext != null;
    }
    
    public Map.Entry<K, V> next()
    {
      SafeIterableMap.Entry localEntry = mNext;
      mNext = nextNode();
      return localEntry;
    }
    
    public void supportRemove(@NonNull SafeIterableMap.Entry<K, V> paramEntry)
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
    public abstract void supportRemove(@NonNull SafeIterableMap.Entry<K, V> paramEntry);
  }
}
