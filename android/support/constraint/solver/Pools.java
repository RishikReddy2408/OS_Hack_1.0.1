package android.support.constraint.solver;

final class Pools
{
  private static final boolean DEBUG = false;
  
  private Pools() {}
  
  static abstract interface Pool<T>
  {
    public abstract Object acquire();
    
    public abstract boolean release(Object paramObject);
    
    public abstract void releaseAll(Object[] paramArrayOfObject, int paramInt);
  }
  
  static class SimplePool<T>
    implements Pools.Pool<T>
  {
    private final Object[] mPool;
    private int mPoolSize;
    
    SimplePool(int paramInt)
    {
      if (paramInt > 0)
      {
        mPool = new Object[paramInt];
        return;
      }
      throw new IllegalArgumentException("The max pool size must be > 0");
    }
    
    private boolean isInPool(Object paramObject)
    {
      int i = 0;
      while (i < mPoolSize)
      {
        if (mPool[i] == paramObject) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public Object acquire()
    {
      if (mPoolSize > 0)
      {
        int i = mPoolSize - 1;
        Object localObject = mPool[i];
        mPool[i] = null;
        mPoolSize -= 1;
        return localObject;
      }
      return null;
    }
    
    public boolean release(Object paramObject)
    {
      if (mPoolSize < mPool.length)
      {
        mPool[mPoolSize] = paramObject;
        mPoolSize += 1;
        return true;
      }
      return false;
    }
    
    public void releaseAll(Object[] paramArrayOfObject, int paramInt)
    {
      int i = paramInt;
      if (paramInt > paramArrayOfObject.length) {
        i = paramArrayOfObject.length;
      }
      paramInt = 0;
      while (paramInt < i)
      {
        Object localObject = paramArrayOfObject[paramInt];
        if (mPoolSize < mPool.length)
        {
          mPool[mPoolSize] = localObject;
          mPoolSize += 1;
        }
        paramInt += 1;
      }
    }
  }
}
