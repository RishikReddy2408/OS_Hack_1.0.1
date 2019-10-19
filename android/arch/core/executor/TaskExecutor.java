//java code for executing tasks
package android.arch.core.executor;
import android.support.annotation.RestrictTo;
//Restriction
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class TaskExecutor
{
  public TaskExecutor() //Task Executer
  {
    
  }
  public abstract void executeOnDiskIO(Runnable paramRunnable);
  public void executeOnMainThread(Runnable paramRunnable)
  {
    if (isMainThread())
    {
      paramRunnable.run();
      return;
    }
    postToMainThread(paramRunnable);
  }
  public abstract boolean isMainThread();
  public abstract void postToMainThread(Runnable paramRunnable);
}
