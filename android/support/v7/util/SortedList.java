package android.support.v7.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList<T>
{
  private static final int CAPACITY_GROWTH = 10;
  private static final int DELETION = 2;
  private static final int INSERTION = 1;
  public static final int INVALID_POSITION = -1;
  private static final int LOOKUP = 4;
  private static final int MIN_CAPACITY = 10;
  private BatchedCallback mBatchedCallback;
  private Callback mCallback;
  T[] mData;
  private int mNewDataStart;
  private T[] mOldData;
  private int mOldDataSize;
  private int mOldDataStart;
  private int mSize;
  private final Class<T> mTClass;
  
  public SortedList(Class paramClass, Callback paramCallback)
  {
    this(paramClass, paramCallback, 10);
  }
  
  public SortedList(Class paramClass, Callback paramCallback, int paramInt)
  {
    mTClass = paramClass;
    mData = ((Object[])Array.newInstance(paramClass, paramInt));
    mCallback = paramCallback;
    mSize = 0;
  }
  
  private int add(Object paramObject, boolean paramBoolean)
  {
    int k = findIndexOf(paramObject, mData, 0, mSize, 1);
    int i = k;
    int j;
    if (k == -1)
    {
      j = 0;
    }
    else
    {
      j = i;
      if (k < mSize)
      {
        Object localObject = mData[k];
        j = i;
        if (mCallback.areItemsTheSame(localObject, paramObject))
        {
          if (mCallback.areContentsTheSame(localObject, paramObject))
          {
            mData[k] = paramObject;
            return k;
          }
          mData[k] = paramObject;
          mCallback.onChanged(k, 1, mCallback.getChangePayload(localObject, paramObject));
          return k;
        }
      }
    }
    addToData(j, paramObject);
    if (paramBoolean) {
      mCallback.onInserted(j, 1);
    }
    return j;
  }
  
  private void addAllInternal(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length < 1) {
      return;
    }
    int i = sortAndDedup(paramArrayOfObject);
    if (mSize == 0)
    {
      mData = paramArrayOfObject;
      mSize = i;
      mCallback.onInserted(0, i);
      return;
    }
    merge(paramArrayOfObject, i);
  }
  
  private void addToData(int paramInt, Object paramObject)
  {
    if (paramInt <= mSize)
    {
      if (mSize == mData.length)
      {
        Object[] arrayOfObject = (Object[])Array.newInstance(mTClass, mData.length + 10);
        System.arraycopy(mData, 0, arrayOfObject, 0, paramInt);
        arrayOfObject[paramInt] = paramObject;
        System.arraycopy(mData, paramInt, arrayOfObject, paramInt + 1, mSize - paramInt);
        mData = arrayOfObject;
      }
      else
      {
        System.arraycopy(mData, paramInt, mData, paramInt + 1, mSize - paramInt);
        mData[paramInt] = paramObject;
      }
      mSize += 1;
      return;
    }
    paramObject = new StringBuilder();
    paramObject.append("cannot add item to ");
    paramObject.append(paramInt);
    paramObject.append(" because size is ");
    paramObject.append(mSize);
    throw new IndexOutOfBoundsException(paramObject.toString());
  }
  
  private Object[] copyArray(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = (Object[])Array.newInstance(mTClass, paramArrayOfObject.length);
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length);
    return arrayOfObject;
  }
  
  private int findIndexOf(Object paramObject, Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
  {
    while (paramInt1 < paramInt2)
    {
      int i = (paramInt1 + paramInt2) / 2;
      Object localObject = paramArrayOfObject[i];
      int j = mCallback.compare(localObject, paramObject);
      if (j < 0)
      {
        paramInt1 = i + 1;
      }
      else
      {
        if (j == 0)
        {
          if (mCallback.areItemsTheSame(localObject, paramObject)) {
            return i;
          }
          paramInt1 = linearEqualitySearch(paramObject, i, paramInt1, paramInt2);
          if (paramInt3 == 1)
          {
            if (paramInt1 != -1) {
              return paramInt1;
            }
            return i;
          }
          return paramInt1;
        }
        paramInt2 = i;
      }
    }
    if (paramInt3 == 1) {
      return paramInt1;
    }
    return -1;
    return paramInt1;
  }
  
  private int findSameItem(Object paramObject, Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      if (mCallback.areItemsTheSame(paramArrayOfObject[paramInt1], paramObject)) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return -1;
  }
  
  private int linearEqualitySearch(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = paramInt1 - 1;
    int i;
    Object localObject;
    for (;;)
    {
      i = paramInt1;
      if (j < paramInt2) {
        break;
      }
      localObject = mData[j];
      if (mCallback.compare(localObject, paramObject) != 0)
      {
        i = paramInt1;
        break;
      }
      if (mCallback.areItemsTheSame(localObject, paramObject)) {
        return j;
      }
      j -= 1;
    }
    do
    {
      paramInt1 = i + 1;
      if (paramInt1 >= paramInt3) {
        break;
      }
      localObject = mData[paramInt1];
      if (mCallback.compare(localObject, paramObject) != 0) {
        break;
      }
      i = paramInt1;
    } while (!mCallback.areItemsTheSame(localObject, paramObject));
    return paramInt1;
    return -1;
  }
  
  private void merge(Object[] paramArrayOfObject, int paramInt)
  {
    boolean bool = mCallback instanceof BatchedCallback;
    int i = 0;
    int j;
    if (!bool) {
      j = 1;
    } else {
      j = 0;
    }
    if (j != 0) {
      beginBatchedUpdates();
    }
    mOldData = mData;
    mOldDataStart = 0;
    mOldDataSize = mSize;
    int k = mSize;
    mData = ((Object[])Array.newInstance(mTClass, k + paramInt + 10));
    mNewDataStart = 0;
    for (;;)
    {
      if ((mOldDataStart < mOldDataSize) || (i < paramInt)) {
        if (mOldDataStart == mOldDataSize)
        {
          paramInt -= i;
          System.arraycopy(paramArrayOfObject, i, mData, mNewDataStart, paramInt);
          mNewDataStart += paramInt;
          mSize += paramInt;
          mCallback.onInserted(mNewDataStart - paramInt, paramInt);
        }
        else
        {
          if (i != paramInt) {
            break label229;
          }
          paramInt = mOldDataSize - mOldDataStart;
          System.arraycopy(mOldData, mOldDataStart, mData, mNewDataStart, paramInt);
          mNewDataStart += paramInt;
        }
      }
      mOldData = null;
      if (j == 0) {
        break;
      }
      endBatchedUpdates();
      return;
      label229:
      Object localObject1 = mOldData[mOldDataStart];
      Object localObject2 = paramArrayOfObject[i];
      k = mCallback.compare(localObject1, localObject2);
      if (k > 0)
      {
        localObject1 = mData;
        k = mNewDataStart;
        mNewDataStart = (k + 1);
        localObject1[k] = localObject2;
        mSize += 1;
        i += 1;
        mCallback.onInserted(mNewDataStart - 1, 1);
      }
      else if ((k == 0) && (mCallback.areItemsTheSame(localObject1, localObject2)))
      {
        Object[] arrayOfObject = mData;
        k = mNewDataStart;
        mNewDataStart = (k + 1);
        arrayOfObject[k] = localObject2;
        k = i + 1;
        mOldDataStart += 1;
        i = k;
        if (!mCallback.areContentsTheSame(localObject1, localObject2))
        {
          mCallback.onChanged(mNewDataStart - 1, 1, mCallback.getChangePayload(localObject1, localObject2));
          i = k;
        }
      }
      else
      {
        localObject2 = mData;
        k = mNewDataStart;
        mNewDataStart = (k + 1);
        localObject2[k] = localObject1;
        mOldDataStart += 1;
      }
    }
  }
  
  private boolean remove(Object paramObject, boolean paramBoolean)
  {
    int i = findIndexOf(paramObject, mData, 0, mSize, 2);
    if (i == -1) {
      return false;
    }
    removeItemAtIndex(i, paramBoolean);
    return true;
  }
  
  private void removeItemAtIndex(int paramInt, boolean paramBoolean)
  {
    System.arraycopy(mData, paramInt + 1, mData, paramInt, mSize - paramInt - 1);
    mSize -= 1;
    mData[mSize] = null;
    if (paramBoolean) {
      mCallback.onRemoved(paramInt, 1);
    }
  }
  
  private void replaceAllInsert(Object paramObject)
  {
    mData[mNewDataStart] = paramObject;
    mNewDataStart += 1;
    mSize += 1;
    mCallback.onInserted(mNewDataStart - 1, 1);
  }
  
  private void replaceAllInternal(Object[] paramArrayOfObject)
  {
    int i;
    if (!(mCallback instanceof BatchedCallback)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      beginBatchedUpdates();
    }
    mOldDataStart = 0;
    mOldDataSize = mSize;
    mOldData = mData;
    mNewDataStart = 0;
    int j = sortAndDedup(paramArrayOfObject);
    mData = ((Object[])Array.newInstance(mTClass, j));
    for (;;)
    {
      if ((mNewDataStart < j) || (mOldDataStart < mOldDataSize)) {
        if (mOldDataStart >= mOldDataSize)
        {
          k = mNewDataStart;
          j -= mNewDataStart;
          System.arraycopy(paramArrayOfObject, k, mData, k, j);
          mNewDataStart += j;
          mSize += j;
          mCallback.onInserted(k, j);
        }
        else
        {
          if (mNewDataStart < j) {
            break label215;
          }
          j = mOldDataSize - mOldDataStart;
          mSize -= j;
          mCallback.onRemoved(mNewDataStart, j);
        }
      }
      mOldData = null;
      if (i == 0) {
        break;
      }
      endBatchedUpdates();
      return;
      label215:
      Object localObject1 = mOldData[mOldDataStart];
      Object localObject2 = paramArrayOfObject[mNewDataStart];
      int k = mCallback.compare(localObject1, localObject2);
      if (k < 0)
      {
        replaceAllRemove();
      }
      else if (k > 0)
      {
        replaceAllInsert(localObject2);
      }
      else if (!mCallback.areItemsTheSame(localObject1, localObject2))
      {
        replaceAllRemove();
        replaceAllInsert(localObject2);
      }
      else
      {
        mData[mNewDataStart] = localObject2;
        mOldDataStart += 1;
        mNewDataStart += 1;
        if (!mCallback.areContentsTheSame(localObject1, localObject2)) {
          mCallback.onChanged(mNewDataStart - 1, 1, mCallback.getChangePayload(localObject1, localObject2));
        }
      }
    }
  }
  
  private void replaceAllRemove()
  {
    mSize -= 1;
    mOldDataStart += 1;
    mCallback.onRemoved(mNewDataStart, 1);
  }
  
  private int sortAndDedup(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    int k = 0;
    if (i == 0) {
      return 0;
    }
    Arrays.sort(paramArrayOfObject, mCallback);
    int j = 1;
    i = 1;
    while (j < paramArrayOfObject.length)
    {
      Object localObject = paramArrayOfObject[j];
      if (mCallback.compare(paramArrayOfObject[k], localObject) == 0)
      {
        int m = findSameItem(localObject, paramArrayOfObject, k, i);
        if (m != -1)
        {
          paramArrayOfObject[m] = localObject;
        }
        else
        {
          if (i != j) {
            paramArrayOfObject[i] = localObject;
          }
          i += 1;
        }
      }
      else
      {
        if (i != j) {
          paramArrayOfObject[i] = localObject;
        }
        k = i;
        i += 1;
      }
      j += 1;
    }
    return i;
  }
  
  private void throwIfInMutationOperation()
  {
    if (mOldData == null) {
      return;
    }
    throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
  }
  
  public int add(Object paramObject)
  {
    throwIfInMutationOperation();
    return add(paramObject, true);
  }
  
  public void addAll(Collection paramCollection)
  {
    addAll(paramCollection.toArray((Object[])Array.newInstance(mTClass, paramCollection.size())), true);
  }
  
  public void addAll(Object... paramVarArgs)
  {
    addAll(paramVarArgs, false);
  }
  
  public void addAll(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    throwIfInMutationOperation();
    if (paramArrayOfObject.length == 0) {
      return;
    }
    if (paramBoolean)
    {
      addAllInternal(paramArrayOfObject);
      return;
    }
    addAllInternal(copyArray(paramArrayOfObject));
  }
  
  public void beginBatchedUpdates()
  {
    throwIfInMutationOperation();
    if ((mCallback instanceof BatchedCallback)) {
      return;
    }
    if (mBatchedCallback == null) {
      mBatchedCallback = new BatchedCallback(mCallback);
    }
    mCallback = mBatchedCallback;
  }
  
  public void clear()
  {
    throwIfInMutationOperation();
    if (mSize == 0) {
      return;
    }
    int i = mSize;
    Arrays.fill(mData, 0, i, null);
    mSize = 0;
    mCallback.onRemoved(0, i);
  }
  
  public void endBatchedUpdates()
  {
    throwIfInMutationOperation();
    if ((mCallback instanceof BatchedCallback)) {
      ((BatchedCallback)mCallback).dispatchLastEvent();
    }
    if (mCallback == mBatchedCallback) {
      mCallback = mBatchedCallback.mWrappedCallback;
    }
  }
  
  public Object get(int paramInt)
    throws IndexOutOfBoundsException
  {
    if ((paramInt < mSize) && (paramInt >= 0))
    {
      if ((mOldData != null) && (paramInt >= mNewDataStart)) {
        return mOldData[(paramInt - mNewDataStart + mOldDataStart)];
      }
      return mData[paramInt];
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Asked to get item at ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" but size is ");
    localStringBuilder.append(mSize);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }
  
  public int indexOf(Object paramObject)
  {
    if (mOldData != null)
    {
      int i = findIndexOf(paramObject, mData, 0, mNewDataStart, 4);
      if (i != -1) {
        return i;
      }
      i = findIndexOf(paramObject, mOldData, mOldDataStart, mOldDataSize, 4);
      if (i != -1) {
        return i - mOldDataStart + mNewDataStart;
      }
      return -1;
    }
    return findIndexOf(paramObject, mData, 0, mSize, 4);
  }
  
  public void recalculatePositionOfItemAt(int paramInt)
  {
    throwIfInMutationOperation();
    Object localObject = get(paramInt);
    removeItemAtIndex(paramInt, false);
    int i = add(localObject, false);
    if (paramInt != i) {
      mCallback.onMoved(paramInt, i);
    }
  }
  
  public boolean remove(Object paramObject)
  {
    throwIfInMutationOperation();
    return remove(paramObject, true);
  }
  
  public Object removeItemAt(int paramInt)
  {
    throwIfInMutationOperation();
    Object localObject = get(paramInt);
    removeItemAtIndex(paramInt, true);
    return localObject;
  }
  
  public void replaceAll(Collection paramCollection)
  {
    replaceAll(paramCollection.toArray((Object[])Array.newInstance(mTClass, paramCollection.size())), true);
  }
  
  public void replaceAll(Object... paramVarArgs)
  {
    replaceAll(paramVarArgs, false);
  }
  
  public void replaceAll(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    throwIfInMutationOperation();
    if (paramBoolean)
    {
      replaceAllInternal(paramArrayOfObject);
      return;
    }
    replaceAllInternal(copyArray(paramArrayOfObject));
  }
  
  public int size()
  {
    return mSize;
  }
  
  public void updateItemAt(int paramInt, Object paramObject)
  {
    throwIfInMutationOperation();
    Object localObject = get(paramInt);
    int i;
    if ((localObject != paramObject) && (mCallback.areContentsTheSame(localObject, paramObject))) {
      i = 0;
    } else {
      i = 1;
    }
    if ((localObject != paramObject) && (mCallback.compare(localObject, paramObject) == 0))
    {
      mData[paramInt] = paramObject;
      if (i != 0) {
        mCallback.onChanged(paramInt, 1, mCallback.getChangePayload(localObject, paramObject));
      }
    }
    else
    {
      if (i != 0) {
        mCallback.onChanged(paramInt, 1, mCallback.getChangePayload(localObject, paramObject));
      }
      removeItemAtIndex(paramInt, false);
      i = add(paramObject, false);
      if (paramInt != i) {
        mCallback.onMoved(paramInt, i);
      }
    }
  }
  
  public static class BatchedCallback<T2>
    extends SortedList.Callback<T2>
  {
    private final BatchingListUpdateCallback mBatchingListUpdateCallback;
    final SortedList.Callback<T2> mWrappedCallback;
    
    public BatchedCallback(SortedList.Callback paramCallback)
    {
      mWrappedCallback = paramCallback;
      mBatchingListUpdateCallback = new BatchingListUpdateCallback(mWrappedCallback);
    }
    
    public boolean areContentsTheSame(Object paramObject1, Object paramObject2)
    {
      return mWrappedCallback.areContentsTheSame(paramObject1, paramObject2);
    }
    
    public boolean areItemsTheSame(Object paramObject1, Object paramObject2)
    {
      return mWrappedCallback.areItemsTheSame(paramObject1, paramObject2);
    }
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      return mWrappedCallback.compare(paramObject1, paramObject2);
    }
    
    public void dispatchLastEvent()
    {
      mBatchingListUpdateCallback.dispatchLastEvent();
    }
    
    public Object getChangePayload(Object paramObject1, Object paramObject2)
    {
      return mWrappedCallback.getChangePayload(paramObject1, paramObject2);
    }
    
    public void onChanged(int paramInt1, int paramInt2)
    {
      mBatchingListUpdateCallback.onChanged(paramInt1, paramInt2, null);
    }
    
    public void onChanged(int paramInt1, int paramInt2, Object paramObject)
    {
      mBatchingListUpdateCallback.onChanged(paramInt1, paramInt2, paramObject);
    }
    
    public void onInserted(int paramInt1, int paramInt2)
    {
      mBatchingListUpdateCallback.onInserted(paramInt1, paramInt2);
    }
    
    public void onMoved(int paramInt1, int paramInt2)
    {
      mBatchingListUpdateCallback.onMoved(paramInt1, paramInt2);
    }
    
    public void onRemoved(int paramInt1, int paramInt2)
    {
      mBatchingListUpdateCallback.onRemoved(paramInt1, paramInt2);
    }
  }
  
  public static abstract class Callback<T2>
    implements Comparator<T2>, ListUpdateCallback
  {
    public Callback() {}
    
    public abstract boolean areContentsTheSame(Object paramObject1, Object paramObject2);
    
    public abstract boolean areItemsTheSame(Object paramObject1, Object paramObject2);
    
    public abstract int compare(Object paramObject1, Object paramObject2);
    
    public Object getChangePayload(Object paramObject1, Object paramObject2)
    {
      return null;
    }
    
    public abstract void onChanged(int paramInt1, int paramInt2);
    
    public void onChanged(int paramInt1, int paramInt2, Object paramObject)
    {
      onChanged(paramInt1, paramInt2);
    }
  }
}
