package com.google.android.android.common.aimsicd.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.android.common.aimsicd.ApiException;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.gms.common.api.internal.zac;

abstract class AbstractBuffer<T>
  extends zac
{
  protected final com.google.android.gms.tasks.TaskCompletionSource<T> zacm;
  
  public AbstractBuffer(int paramInt, com.google.android.android.tasks.TaskCompletionSource paramTaskCompletionSource)
  {
    super(paramInt);
    zacm = paramTaskCompletionSource;
  }
  
  protected abstract void forEach(GoogleApiManager.zaa paramZaa)
    throws RemoteException;
  
  public final void readFrom(GoogleApiManager.zaa paramZaa)
    throws DeadObjectException
  {
    try
    {
      forEach(paramZaa);
      return;
    }
    catch (RuntimeException paramZaa)
    {
      toString(paramZaa);
      return;
    }
    catch (RemoteException paramZaa)
    {
      toString(Location.call(paramZaa));
      return;
    }
    catch (DeadObjectException paramZaa)
    {
      toString(Location.call(paramZaa));
      throw paramZaa;
    }
  }
  
  public void readFrom(zaab paramZaab, boolean paramBoolean) {}
  
  public void toString(Status paramStatus)
  {
    zacm.trySetException(new ApiException(paramStatus));
  }
  
  public void toString(RuntimeException paramRuntimeException)
  {
    zacm.trySetException(paramRuntimeException);
  }
}
