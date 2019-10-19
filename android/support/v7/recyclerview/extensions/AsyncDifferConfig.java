package android.support.v7.recyclerview.extensions;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil.ItemCallback;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class AsyncDifferConfig<T>
{
  @NonNull
  private final Executor mBackgroundThreadExecutor;
  @NonNull
  private final DiffUtil.ItemCallback<T> mDiffCallback;
  @NonNull
  private final Executor mMainThreadExecutor;
  
  private AsyncDifferConfig(Executor paramExecutor1, Executor paramExecutor2, DiffUtil.ItemCallback paramItemCallback)
  {
    mMainThreadExecutor = paramExecutor1;
    mBackgroundThreadExecutor = paramExecutor2;
    mDiffCallback = paramItemCallback;
  }
  
  public Executor getBackgroundThreadExecutor()
  {
    return mBackgroundThreadExecutor;
  }
  
  public DiffUtil.ItemCallback getDiffCallback()
  {
    return mDiffCallback;
  }
  
  public Executor getMainThreadExecutor()
  {
    return mMainThreadExecutor;
  }
  
  public static final class Builder<T>
  {
    private static Executor sDiffExecutor = null;
    private static final Object sExecutorLock = new Object();
    private static final Executor sMainThreadExecutor = new MainThreadExecutor(null);
    private Executor mBackgroundThreadExecutor;
    private final DiffUtil.ItemCallback<T> mDiffCallback;
    private Executor mMainThreadExecutor;
    
    public Builder(DiffUtil.ItemCallback paramItemCallback)
    {
      mDiffCallback = paramItemCallback;
    }
    
    public AsyncDifferConfig build()
    {
      if (mMainThreadExecutor == null) {
        mMainThreadExecutor = sMainThreadExecutor;
      }
      if (mBackgroundThreadExecutor == null)
      {
        Object localObject = sExecutorLock;
        try
        {
          if (sDiffExecutor == null) {
            sDiffExecutor = Executors.newFixedThreadPool(2);
          }
          mBackgroundThreadExecutor = sDiffExecutor;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      return new AsyncDifferConfig(mMainThreadExecutor, mBackgroundThreadExecutor, mDiffCallback, null);
    }
    
    public Builder setBackgroundThreadExecutor(Executor paramExecutor)
    {
      mBackgroundThreadExecutor = paramExecutor;
      return this;
    }
    
    public Builder setMainThreadExecutor(Executor paramExecutor)
    {
      mMainThreadExecutor = paramExecutor;
      return this;
    }
    
    private static class MainThreadExecutor
      implements Executor
    {
      final Handler mHandler = new Handler(Looper.getMainLooper());
      
      private MainThreadExecutor() {}
      
      public void execute(Runnable paramRunnable)
      {
        mHandler.post(paramRunnable);
      }
    }
  }
}
