package com.google.android.android.common.aimsicd.internal;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.AvailabilityException;
import com.google.android.android.common.aimsicd.GoogleApi;
import com.google.android.android.tasks.Task;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.tasks.OnCompleteListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

final class zaaa
  implements OnCompleteListener<Map<zai<?>, String>>
{
  private SignInConnectionListener zafi;
  
  zaaa(TaskManager paramTaskManager, SignInConnectionListener paramSignInConnectionListener)
  {
    zafi = paramSignInConnectionListener;
  }
  
  final void cancel()
  {
    zafi.onComplete();
  }
  
  public final void onComplete(Task paramTask)
  {
    TaskManager.getLock(zafh).lock();
    try
    {
      boolean bool = TaskManager.removeTask(zafh);
      if (!bool)
      {
        zafi.onComplete();
        TaskManager.getLock(zafh).unlock();
        return;
      }
      bool = paramTask.isSuccessful();
      Object localObject;
      if (bool)
      {
        TaskManager.sort(zafh, new ArrayMap(TaskManager.getMountPoints(zafh).size()));
        paramTask = TaskManager.getMountPoints(zafh).values().iterator();
        for (;;)
        {
          bool = paramTask.hasNext();
          if (!bool) {
            break;
          }
          localObject = (Errors)paramTask.next();
          TaskManager.next(zafh).put(((GoogleApi)localObject).get(), ConnectionResult.RESULT_SUCCESS);
        }
      }
      bool = paramTask.getException() instanceof AvailabilityException;
      if (bool)
      {
        paramTask = (AvailabilityException)paramTask.getException();
        bool = TaskManager.isConnected(zafh);
        if (bool)
        {
          TaskManager.sort(zafh, new ArrayMap(TaskManager.getMountPoints(zafh).size()));
          localObject = TaskManager.getMountPoints(zafh).values().iterator();
          for (;;)
          {
            bool = ((Iterator)localObject).hasNext();
            if (!bool) {
              break;
            }
            Errors localErrors = (Errors)((Iterator)localObject).next();
            Msg localMsg = localErrors.get();
            ConnectionResult localConnectionResult = paramTask.getConnectionResult(localErrors);
            bool = TaskManager.next(zafh, localErrors, localConnectionResult);
            if (bool) {
              TaskManager.next(zafh).put(localMsg, new ConnectionResult(16));
            } else {
              TaskManager.next(zafh).put(localMsg, localConnectionResult);
            }
          }
        }
        TaskManager.sort(zafh, paramTask.getString());
      }
      else
      {
        Log.e("ConnectionlessGAC", "Unexpected availability exception", paramTask.getException());
        TaskManager.sort(zafh, Collections.emptyMap());
      }
      bool = zafh.isConnected();
      if (bool)
      {
        TaskManager.get(zafh).putAll(TaskManager.next(zafh));
        paramTask = TaskManager.getString(zafh);
        if (paramTask == null)
        {
          TaskManager.onRestart(zafh);
          TaskManager.onPause(zafh);
          TaskManager.doToast(zafh).signalAll();
        }
      }
      zafi.onComplete();
      TaskManager.getLock(zafh).unlock();
      return;
    }
    catch (Throwable paramTask)
    {
      TaskManager.getLock(zafh).unlock();
      throw paramTask;
    }
  }
}
