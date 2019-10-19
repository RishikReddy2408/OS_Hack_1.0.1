package android.arch.core.executor;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ArchTaskExecutor
  extends TaskExecutor
{
  @NonNull
  private static final Executor sIOThreadExecutor = new Executor()
  {
    public void execute(Runnable paramAnonymousRunnable)
    {
      ArchTaskExecutor.getInstance().executeOnDiskIO(paramAnonymousRunnable);
    }
  };
  private static volatile ArchTaskExecutor sInstance;
  @NonNull
  private static final Executor sMainThreadExecutor = new Executor()
  {
    public void execute(Runnable paramAnonymousRunnable)
    {
      ArchTaskExecutor.getInstance().postToMainThread(paramAnonymousRunnable);
    }
  };
  @NonNull
  private TaskExecutor mDefaultTaskExecutor = new DefaultTaskExecutor();
  @NonNull
  private TaskExecutor mDelegate = mDefaultTaskExecutor;
  
  private ArchTaskExecutor() {}
  
  public static Executor getIOThreadExecutor()
  {
    return sIOThreadExecutor;
  }
  
  public static ArchTaskExecutor getInstance()
  {
    if (sInstance != null) {
      return sInstance;
    }
    try
    {
      if (sInstance == null) {
        sInstance = new ArchTaskExecutor();
      }
      return sInstance;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static Executor getMainThreadExecutor()
  {
    return sMainThreadExecutor;
  }
  
  public void executeOnDiskIO(Runnable paramRunnable)
  {
    mDelegate.executeOnDiskIO(paramRunnable);
  }
  
  public boolean isMainThread()
  {
    return mDelegate.isMainThread();
  }
  
  public void postToMainThread(Runnable paramRunnable)
  {
    mDelegate.postToMainThread(paramRunnable);
  }
  
  public void setDelegate(TaskExecutor paramTaskExecutor)
  {
    TaskExecutor localTaskExecutor = paramTaskExecutor;
    if (paramTaskExecutor == null) {
      localTaskExecutor = mDefaultTaskExecutor;
    }
    mDelegate = localTaskExecutor;
  }
}
