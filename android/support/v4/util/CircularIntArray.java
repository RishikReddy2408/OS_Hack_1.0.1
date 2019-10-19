package android.support.v4.util;

public final class CircularIntArray
{
  private int mCapacityBitmask;
  private int[] mElements;
  private int mHead;
  private int mTail;
  
  public CircularIntArray()
  {
    this(8);
  }
  
  public CircularIntArray(int paramInt)
  {
    if (paramInt >= 1)
    {
      if (paramInt <= 1073741824)
      {
        int i = paramInt;
        if (Integer.bitCount(paramInt) != 1) {
          i = Integer.highestOneBit(paramInt - 1) << 1;
        }
        mCapacityBitmask = (i - 1);
        mElements = new int[i];
        return;
      }
      throw new IllegalArgumentException("capacity must be <= 2^30");
    }
    throw new IllegalArgumentException("capacity must be >= 1");
  }
  
  private void doubleCapacity()
  {
    int i = mElements.length;
    int j = i - mHead;
    int k = i << 1;
    if (k >= 0)
    {
      int[] arrayOfInt = new int[k];
      System.arraycopy(mElements, mHead, arrayOfInt, 0, j);
      System.arraycopy(mElements, 0, arrayOfInt, j, mHead);
      mElements = arrayOfInt;
      mHead = 0;
      mTail = i;
      mCapacityBitmask = (k - 1);
      return;
    }
    throw new RuntimeException("Max array capacity exceeded");
  }
  
  public void addFirst(int paramInt)
  {
    mHead = (mHead - 1 & mCapacityBitmask);
    mElements[mHead] = paramInt;
    if (mHead == mTail) {
      doubleCapacity();
    }
  }
  
  public void addLast(int paramInt)
  {
    mElements[mTail] = paramInt;
    mTail = (mTail + 1 & mCapacityBitmask);
    if (mTail == mHead) {
      doubleCapacity();
    }
  }
  
  public void clear()
  {
    mTail = mHead;
  }
  
  public int get(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < size()))
    {
      int[] arrayOfInt = mElements;
      int i = mHead;
      return arrayOfInt[(mCapacityBitmask & i + paramInt)];
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public int getFirst()
  {
    if (mHead != mTail) {
      return mElements[mHead];
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public int getLast()
  {
    if (mHead != mTail) {
      return mElements[(mTail - 1 & mCapacityBitmask)];
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public boolean isEmpty()
  {
    return mHead == mTail;
  }
  
  public int popFirst()
  {
    if (mHead != mTail)
    {
      int i = mElements[mHead];
      mHead = (mHead + 1 & mCapacityBitmask);
      return i;
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public int popLast()
  {
    if (mHead != mTail)
    {
      int i = mTail - 1 & mCapacityBitmask;
      int j = mElements[i];
      mTail = i;
      return j;
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public void removeFromEnd(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    if (paramInt <= size())
    {
      int i = mTail;
      mTail = (mCapacityBitmask & i - paramInt);
      return;
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public void removeFromStart(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    if (paramInt <= size())
    {
      int i = mHead;
      mHead = (mCapacityBitmask & i + paramInt);
      return;
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public int size()
  {
    return mTail - mHead & mCapacityBitmask;
  }
}
