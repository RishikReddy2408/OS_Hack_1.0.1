package com.google.android.android.tasks;

import com.google.android.gms.tasks.zzq;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

final class UnpackedObjectCache<TResult>
{
  private final java.lang.Object mLock = new java.lang.Object();
  @GuardedBy("mLock")
  private Queue<zzq<TResult>> queue;
  @GuardedBy("mLock")
  private boolean set;
  
  UnpackedObjectCache() {}
  
  public final void add(Object paramObject)
  {
    java.lang.Object localObject = mLock;
    try
    {
      if (queue == null) {
        queue = new ArrayDeque();
      }
      queue.add(paramObject);
      return;
    }
    catch (Throwable paramObject)
    {
      throw paramObject;
    }
  }
  
  public final void clear(Task paramTask)
  {
    java.lang.Object localObject = mLock;
    try
    {
      if ((queue != null) && (!set))
      {
        set = true;
        for (;;)
        {
          localObject = mLock;
          try
          {
            Object localObject1 = (Object)queue.poll();
            if (localObject1 == null)
            {
              set = false;
              return;
            }
            localObject1.onComplete(paramTask);
          }
          catch (Throwable paramTask)
          {
            throw paramTask;
          }
        }
      }
      return;
    }
    catch (Throwable paramTask)
    {
      throw paramTask;
    }
  }
}
