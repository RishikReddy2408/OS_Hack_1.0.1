package com.google.android.android.common.aimsicd.internal;

import android.os.Handler;
import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.Result;
import com.google.android.android.common.aimsicd.ResultTransform;
import java.lang.ref.WeakReference;

final class zacn
  implements Runnable
{
  zacn(zacm paramZacm, Result paramResult) {}
  
  public final void run()
  {
    Object localObject = BasePendingResult.zadm;
    GoogleApiClient localGoogleApiClient1;
    try
    {
      ((ThreadLocal)localObject).set(Boolean.valueOf(true));
      localObject = zacm.getBasePath(zakv).onSuccess(zaku);
      zacm.access$getMMsgHandler(zakv).sendMessage(zacm.access$getMMsgHandler(zakv).obtainMessage(0, localObject));
      BasePendingResult.zadm.set(Boolean.valueOf(false));
      zacm.handleResult(zakv, zaku);
      localObject = (GoogleApiClient)zacm.access$getRootView(zakv).get();
      if (localObject == null) {
        return;
      }
      ((GoogleApiClient)localObject).removeAccount(zakv);
      return;
    }
    catch (Throwable localThrowable) {}catch (RuntimeException localRuntimeException)
    {
      zacm.access$getMMsgHandler(zakv).sendMessage(zacm.access$getMMsgHandler(zakv).obtainMessage(1, localRuntimeException));
      BasePendingResult.zadm.set(Boolean.valueOf(false));
      zacm.handleResult(zakv, zaku);
      localGoogleApiClient1 = (GoogleApiClient)zacm.access$getRootView(zakv).get();
      if (localGoogleApiClient1 == null) {
        return;
      }
    }
    localGoogleApiClient1.removeAccount(zakv);
    return;
    BasePendingResult.zadm.set(Boolean.valueOf(false));
    zacm.handleResult(zakv, zaku);
    GoogleApiClient localGoogleApiClient2 = (GoogleApiClient)zacm.access$getRootView(zakv).get();
    if (localGoogleApiClient2 != null) {
      localGoogleApiClient2.removeAccount(zakv);
    }
    throw localGoogleApiClient1;
  }
}
