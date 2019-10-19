package com.google.android.android.tasks;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.zzu;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public final class Tasks
{
  private Tasks() {}
  
  public static Object await(Task paramTask)
    throws ExecutionException, InterruptedException
  {
    Preconditions.checkNotMainThread();
    Preconditions.checkNotNull(paramTask, "Task must not be null");
    if (paramTask.isComplete()) {
      return execute(paramTask);
    }
    zza localZza = new zza(null);
    createNew(paramTask, localZza);
    localZza.await();
    return execute(paramTask);
  }
  
  public static Object await(Task paramTask, long paramLong, TimeUnit paramTimeUnit)
    throws ExecutionException, InterruptedException, TimeoutException
  {
    Preconditions.checkNotMainThread();
    Preconditions.checkNotNull(paramTask, "Task must not be null");
    Preconditions.checkNotNull(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete()) {
      return execute(paramTask);
    }
    zza localZza = new zza(null);
    createNew(paramTask, localZza);
    if (localZza.await(paramLong, paramTimeUnit)) {
      return execute(paramTask);
    }
    throw new TimeoutException("Timed out waiting for Task");
  }
  
  public static Task call(Callable paramCallable)
  {
    return call(TaskExecutors.MAIN_THREAD, paramCallable);
  }
  
  public static Task call(Executor paramExecutor, Callable paramCallable)
  {
    Preconditions.checkNotNull(paramExecutor, "Executor must not be null");
    Preconditions.checkNotNull(paramCallable, "Callback must not be null");
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    paramExecutor.execute(new Task.2(localAbstractDataSource, paramCallable));
    return localAbstractDataSource;
  }
  
  private static void createNew(Task paramTask, zzb paramZzb)
  {
    paramTask.addOnSuccessListener(TaskExecutors.executor, paramZzb);
    paramTask.addOnFailureListener(TaskExecutors.executor, paramZzb);
    paramTask.addOnCanceledListener(TaskExecutors.executor, paramZzb);
  }
  
  private static Object execute(Task paramTask)
    throws ExecutionException
  {
    if (paramTask.isSuccessful()) {
      return paramTask.getResult();
    }
    if (paramTask.isCanceled()) {
      throw new CancellationException("Task is already canceled");
    }
    throw new ExecutionException(paramTask.getException());
  }
  
  public static Task forCanceled()
  {
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    localAbstractDataSource.close();
    return localAbstractDataSource;
  }
  
  public static Task forException(Exception paramException)
  {
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    localAbstractDataSource.setException(paramException);
    return localAbstractDataSource;
  }
  
  public static Task forResult(Object paramObject)
  {
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    localAbstractDataSource.setResult(paramObject);
    return localAbstractDataSource;
  }
  
  public static Task whenAll(Collection paramCollection)
  {
    if (paramCollection.isEmpty()) {
      return forResult(null);
    }
    Object localObject = paramCollection.iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((Task)((Iterator)localObject).next() == null) {
        throw new NullPointerException("null tasks are not accepted");
      }
    }
    localObject = new AbstractDataSource();
    zzc localZzc = new zzc(paramCollection.size(), (AbstractDataSource)localObject);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      createNew((Task)paramCollection.next(), localZzc);
    }
    return localObject;
  }
  
  public static Task whenAll(Task... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return forResult(null);
    }
    return whenAll(Arrays.asList(paramVarArgs));
  }
  
  public static Task whenAllComplete(Collection paramCollection)
  {
    return whenAll(paramCollection).continueWithTask(new Task.4(paramCollection));
  }
  
  public static Task whenAllComplete(Task... paramVarArgs)
  {
    return whenAllComplete(Arrays.asList(paramVarArgs));
  }
  
  public static Task whenAllSuccess(Collection paramCollection)
  {
    return whenAll(paramCollection).continueWith(new Task.3(paramCollection));
  }
  
  public static Task whenAllSuccess(Task... paramVarArgs)
  {
    return whenAllSuccess(Arrays.asList(paramVarArgs));
  }
  
  final class zza
    implements Tasks.zzb
  {
    private final CountDownLatch zzaf = new CountDownLatch(1);
    
    private zza() {}
    
    public final void await()
      throws InterruptedException
    {
      zzaf.await();
    }
    
    public final boolean await(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException
    {
      return zzaf.await(paramLong, paramTimeUnit);
    }
    
    public final void onCanceled()
    {
      zzaf.countDown();
    }
    
    public final void onFailure(Exception paramException)
    {
      zzaf.countDown();
    }
    
    public final void onSuccess(Object paramObject)
    {
      zzaf.countDown();
    }
  }
  
  abstract interface zzb
    extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object>
  {}
  
  final class zzc
    implements Tasks.zzb
  {
    private final Object mLock = new Object();
    private final zzu<Void> this$0;
    @GuardedBy("mLock")
    private Exception zzab;
    private final int zzag;
    @GuardedBy("mLock")
    private int zzah;
    @GuardedBy("mLock")
    private int zzai;
    @GuardedBy("mLock")
    private int zzaj;
    @GuardedBy("mLock")
    private boolean zzak;
    
    public zzc(AbstractDataSource paramAbstractDataSource)
    {
      zzag = this$1;
      this$0 = paramAbstractDataSource;
    }
    
    private final void close()
    {
      if (zzah + zzai + zzaj == zzag)
      {
        if (zzab != null)
        {
          AbstractDataSource localAbstractDataSource = this$0;
          int i = zzai;
          int j = zzag;
          StringBuilder localStringBuilder = new StringBuilder(54);
          localStringBuilder.append(i);
          localStringBuilder.append(" out of ");
          localStringBuilder.append(j);
          localStringBuilder.append(" underlying tasks failed");
          localAbstractDataSource.setException(new ExecutionException(localStringBuilder.toString(), zzab));
          return;
        }
        if (zzak)
        {
          this$0.close();
          return;
        }
        this$0.setResult(null);
      }
    }
    
    public final void onCanceled()
    {
      Object localObject = mLock;
      try
      {
        zzaj += 1;
        zzak = true;
        close();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public final void onFailure(Exception paramException)
    {
      Object localObject = mLock;
      try
      {
        zzai += 1;
        zzab = paramException;
        close();
        return;
      }
      catch (Throwable paramException)
      {
        throw paramException;
      }
    }
    
    public final void onSuccess(Object paramObject)
    {
      paramObject = mLock;
      try
      {
        zzah += 1;
        close();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
}
