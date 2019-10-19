package android.support.v4.util;

public final class Pools
{
  private Pools() {}
  
  public static abstract interface Pool<T>
  {
    public abstract Object acquire();
    
    public abstract boolean release(Object paramObject);
  }
  
  public static class SimplePool<T>
    implements Pools.Pool<T>
  {
    private final Object[] mPool;
    private int mPoolSize;
    
    public SimplePool(int paramInt)
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
      if (!isInPool(paramObject))
      {
        if (mPoolSize < mPool.length)
        {
          mPool[mPoolSize] = paramObject;
          mPoolSize += 1;
          return true;
        }
        return false;
      }
      throw new IllegalStateException("Already in the pool!");
    }
  }
  
  public static class SynchronizedPool<T>
    extends Pools.SimplePool<T>
  {
    private final Object mLock = new Object();
    
    public SynchronizedPool(int paramInt)
    {
      super();
    }
    
    public Object acquire()
    {
      Object localObject1 = mLock;
      try
      {
        Object localObject2 = super.acquire();
        return localObject2;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public boolean release(Object paramObject)
    {
      Object localObject = mLock;
      try
      {
        boolean bool = super.release(paramObject);
        return bool;
      }
      catch (Throwable paramObject)
      {
        throw paramObject;
      }
    }
  }
}
