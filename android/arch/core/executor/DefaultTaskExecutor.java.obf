package android.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DefaultTaskExecutor
  extends TaskExecutor
{
  private ExecutorService mDiskIO = Executors.newFixedThreadPool(2);
  private final Object mLock = new Object();
  @Nullable
  private volatile Handler mMainHandler;
  
  public DefaultTaskExecutor() {}
  
  public void executeOnDiskIO(Runnable paramRunnable)
  {
    mDiskIO.execute(paramRunnable);
  }
  
  public boolean isMainThread()
  {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }
  
  public void postToMainThread(Runnable paramRunnable)
  {
    if (mMainHandler == null) {
      synchronized (mLock)
      {
        if (mMainHandler == null) {
          mMainHandler = new Handler(Looper.getMainLooper());
        }
      }
    }
    mMainHandler.post(paramRunnable);
  }
}
