package android.arch.core.executor;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Executor;
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
// This will be handling the input-output by different appliances 
public class ArchTaskExecutor
  extends TaskExecutor
{
  @NonNull
  private static final Executor sIOThreadExecutor = new Executor()
  {
  // ececuting a particular task using task manager class
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
  // using a task executer class to manage the different multiple threads without loosing any information 
  private TaskExecutor mDefaultTaskExecutor = new DefaultTaskExecutor();
  @NonNull
  private TaskExecutor mDelegate = mDefaultTaskExecutor;
  
  private ArchTaskExecutor() {}
  
  public static Executor getIOThreadExecutor()
  {
    return sIOThreadExecutor;// this will return executor
  }
  
  public static ArchTaskExecutor getInstance()
  {
    if (sInstance != null) {
      return sInstance;
    }
    // this block handles the exception 
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
    mDelegate.executeOnDiskIO(paramRunnable); // return nothing 
  }
  
  public boolean isMainThread()
  {
    return mDelegate.isMainThread();// return boolean value
  }
  
  public void postToMainThread(Runnable paramRunnable)
  {
    mDelegate.postToMainThread(paramRunnable);// return nothing 
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
// end of file 
