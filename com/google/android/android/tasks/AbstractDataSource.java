package com.google.android.android.tasks;

import android.app.Activity;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.tasks.zzr;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class AbstractDataSource<TResult>
  extends com.google.android.gms.tasks.Task<TResult>
{
  @GuardedBy("mLock")
  private boolean error;
  private volatile boolean mIsCancelled;
  private final Object mLock = new Object();
  private final zzr<TResult> unpackedObjectCache = new UnpackedObjectCache();
  @GuardedBy("mLock")
  private TResult zzaa;
  @GuardedBy("mLock")
  private Exception zzab;
  
  AbstractDataSource() {}
  
  private final void get()
  {
    Preconditions.checkState(error, "Task is not yet complete");
  }
  
  private final void release()
  {
    Preconditions.checkState(error ^ true, "Task is already complete");
  }
  
  private final void setResult()
  {
    if (!mIsCancelled) {
      return;
    }
    throw new CancellationException("Task is already canceled.");
  }
  
  private final void startLoader()
  {
    Object localObject = mLock;
    try
    {
      if (!error) {
        return;
      }
      unpackedObjectCache.clear(this);
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Task addOnCanceledListener(Activity paramActivity, OnCanceledListener paramOnCanceledListener)
  {
    paramOnCanceledListener = new MediaSessionCompat.MediaSessionImplBase(TaskExecutors.MAIN_THREAD, paramOnCanceledListener);
    unpackedObjectCache.add(paramOnCanceledListener);
    zzu.zza.openDocument(paramActivity).notifyProgress(paramOnCanceledListener);
    startLoader();
    return this;
  }
  
  public final Task addOnCanceledListener(OnCanceledListener paramOnCanceledListener)
  {
    return addOnCanceledListener(TaskExecutors.MAIN_THREAD, paramOnCanceledListener);
  }
  
  public final Task addOnCanceledListener(Executor paramExecutor, OnCanceledListener paramOnCanceledListener)
  {
    unpackedObjectCache.add(new MediaSessionCompat.MediaSessionImplBase(paramExecutor, paramOnCanceledListener));
    startLoader();
    return this;
  }
  
  public final Task addOnCompleteListener(Activity paramActivity, OnCompleteListener paramOnCompleteListener)
  {
    paramOnCompleteListener = new SyncCampaign(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
    unpackedObjectCache.add(paramOnCompleteListener);
    zzu.zza.openDocument(paramActivity).notifyProgress(paramOnCompleteListener);
    startLoader();
    return this;
  }
  
  public final Task addOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    return addOnCompleteListener(TaskExecutors.MAIN_THREAD, paramOnCompleteListener);
  }
  
  public final Task addOnCompleteListener(Executor paramExecutor, OnCompleteListener paramOnCompleteListener)
  {
    unpackedObjectCache.add(new SyncCampaign(paramExecutor, paramOnCompleteListener));
    startLoader();
    return this;
  }
  
  public final Task addOnFailureListener(Activity paramActivity, OnFailureListener paramOnFailureListener)
  {
    paramOnFailureListener = new ServiceHelper(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
    unpackedObjectCache.add(paramOnFailureListener);
    zzu.zza.openDocument(paramActivity).notifyProgress(paramOnFailureListener);
    startLoader();
    return this;
  }
  
  public final Task addOnFailureListener(OnFailureListener paramOnFailureListener)
  {
    return addOnFailureListener(TaskExecutors.MAIN_THREAD, paramOnFailureListener);
  }
  
  public final Task addOnFailureListener(Executor paramExecutor, OnFailureListener paramOnFailureListener)
  {
    unpackedObjectCache.add(new ServiceHelper(paramExecutor, paramOnFailureListener));
    startLoader();
    return this;
  }
  
  public final Task addOnSuccessListener(Activity paramActivity, OnSuccessListener paramOnSuccessListener)
  {
    paramOnSuccessListener = new AppAdapter(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
    unpackedObjectCache.add(paramOnSuccessListener);
    zzu.zza.openDocument(paramActivity).notifyProgress(paramOnSuccessListener);
    startLoader();
    return this;
  }
  
  public final Task addOnSuccessListener(OnSuccessListener paramOnSuccessListener)
  {
    return addOnSuccessListener(TaskExecutors.MAIN_THREAD, paramOnSuccessListener);
  }
  
  public final Task addOnSuccessListener(Executor paramExecutor, OnSuccessListener paramOnSuccessListener)
  {
    unpackedObjectCache.add(new AppAdapter(paramExecutor, paramOnSuccessListener));
    startLoader();
    return this;
  }
  
  public final boolean close()
  {
    Object localObject = mLock;
    try
    {
      if (error) {
        return false;
      }
      error = true;
      mIsCancelled = true;
      unpackedObjectCache.clear(this);
      return true;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Task continueWith(Continuation paramContinuation)
  {
    return continueWith(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  public final Task continueWith(Executor paramExecutor, Continuation paramContinuation)
  {
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    unpackedObjectCache.add(new CallbackRunnable(paramExecutor, paramContinuation, localAbstractDataSource));
    startLoader();
    return localAbstractDataSource;
  }
  
  public final Task continueWithTask(Continuation paramContinuation)
  {
    return continueWithTask(TaskExecutors.MAIN_THREAD, paramContinuation);
  }
  
  public final Task continueWithTask(Executor paramExecutor, Continuation paramContinuation)
  {
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    unpackedObjectCache.add(new SearchFragment.2(paramExecutor, paramContinuation, localAbstractDataSource));
    startLoader();
    return localAbstractDataSource;
  }
  
  public final Exception getException()
  {
    Object localObject = mLock;
    try
    {
      Exception localException = zzab;
      return localException;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Object getResult()
  {
    Object localObject1 = mLock;
    try
    {
      get();
      setResult();
      if (zzab == null)
      {
        Object localObject2 = zzaa;
        return localObject2;
      }
      throw new RuntimeExecutionException(zzab);
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Object getResult(Class paramClass)
    throws Throwable
  {
    Object localObject = mLock;
    try
    {
      get();
      setResult();
      if (!paramClass.isInstance(zzab))
      {
        if (zzab == null)
        {
          paramClass = zzaa;
          return paramClass;
        }
        throw new RuntimeExecutionException(zzab);
      }
      throw ((Throwable)paramClass.cast(zzab));
    }
    catch (Throwable paramClass)
    {
      throw paramClass;
    }
  }
  
  public final boolean isCanceled()
  {
    return mIsCancelled;
  }
  
  public final boolean isComplete()
  {
    Object localObject = mLock;
    try
    {
      boolean bool = error;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final boolean isSuccessful()
  {
    Object localObject = mLock;
    for (;;)
    {
      try
      {
        if ((error) && (!mIsCancelled) && (zzab == null))
        {
          bool = true;
          return bool;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      boolean bool = false;
    }
  }
  
  public final Task onSuccessTask(SuccessContinuation paramSuccessContinuation)
  {
    return onSuccessTask(TaskExecutors.MAIN_THREAD, paramSuccessContinuation);
  }
  
  public final Task onSuccessTask(Executor paramExecutor, SuccessContinuation paramSuccessContinuation)
  {
    AbstractDataSource localAbstractDataSource = new AbstractDataSource();
    unpackedObjectCache.add(new RssReader(paramExecutor, paramSuccessContinuation, localAbstractDataSource));
    startLoader();
    return localAbstractDataSource;
  }
  
  public final void setException(Exception paramException)
  {
    Preconditions.checkNotNull(paramException, "Exception must not be null");
    Object localObject = mLock;
    try
    {
      release();
      error = true;
      zzab = paramException;
      unpackedObjectCache.clear(this);
      return;
    }
    catch (Throwable paramException)
    {
      throw paramException;
    }
  }
  
  public final void setResult(Object paramObject)
  {
    Object localObject = mLock;
    try
    {
      release();
      error = true;
      zzaa = paramObject;
      unpackedObjectCache.clear(this);
      return;
    }
    catch (Throwable paramObject)
    {
      throw paramObject;
    }
  }
  
  public final boolean trySetException(Exception paramException)
  {
    Preconditions.checkNotNull(paramException, "Exception must not be null");
    Object localObject = mLock;
    try
    {
      if (error) {
        return false;
      }
      error = true;
      zzab = paramException;
      unpackedObjectCache.clear(this);
      return true;
    }
    catch (Throwable paramException)
    {
      throw paramException;
    }
  }
  
  public final boolean trySetResult(Object paramObject)
  {
    Object localObject = mLock;
    try
    {
      if (error) {
        return false;
      }
      error = true;
      zzaa = paramObject;
      unpackedObjectCache.clear(this);
      return true;
    }
    catch (Throwable paramObject)
    {
      throw paramObject;
    }
  }
}
