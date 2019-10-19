package com.google.android.android.common.aimsicd.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.android.common.aimsicd.Releasable;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.api.ResultCallback;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public final class zacm<R extends com.google.android.gms.common.api.Result>
  extends com.google.android.gms.common.api.TransformedResult<R>
  implements ResultCallback<R>
{
  private final Object zadn = new Object();
  private final WeakReference<com.google.android.gms.common.api.GoogleApiClient> zadp;
  private com.google.android.gms.common.api.ResultTransform<? super R, ? extends com.google.android.gms.common.api.Result> zakn = null;
  private com.google.android.gms.common.api.internal.zacm<? extends com.google.android.gms.common.api.Result> zako = null;
  private volatile com.google.android.gms.common.api.ResultCallbacks<? super R> zakp = null;
  private com.google.android.gms.common.api.PendingResult<R> zakq = null;
  private Status zakr = null;
  private final com.google.android.gms.common.api.internal.zaco zaks;
  private boolean zakt = false;
  
  public zacm(WeakReference paramWeakReference)
  {
    Preconditions.checkNotNull(paramWeakReference, "GoogleApiClient reference must not be null");
    zadp = paramWeakReference;
    paramWeakReference = (com.google.android.android.common.aimsicd.GoogleApiClient)zadp.get();
    if (paramWeakReference != null) {
      paramWeakReference = paramWeakReference.getLooper();
    } else {
      paramWeakReference = Looper.getMainLooper();
    }
    zaks = new zaco(this, paramWeakReference);
  }
  
  private final void add(Status paramStatus)
  {
    Object localObject = zadn;
    try
    {
      zakr = paramStatus;
      enqueue(zakr);
      return;
    }
    catch (Throwable paramStatus)
    {
      throw paramStatus;
    }
  }
  
  private final void enqueue(Status paramStatus)
  {
    Object localObject = zadn;
    try
    {
      if (zakn != null)
      {
        paramStatus = zakn.onFailure(paramStatus);
        Preconditions.checkNotNull(paramStatus, "onFailure must not return null");
        zako.add(paramStatus);
      }
      else if (zabw())
      {
        zakp.onFailure(paramStatus);
      }
      return;
    }
    catch (Throwable paramStatus)
    {
      throw paramStatus;
    }
  }
  
  private static void parse(com.google.android.android.common.aimsicd.Result paramResult)
  {
    if ((paramResult instanceof Releasable)) {
      try
      {
        ((Releasable)paramResult).release();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        paramResult = String.valueOf(paramResult);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramResult).length() + 18);
        localStringBuilder.append("Unable to release ");
        localStringBuilder.append(paramResult);
        Log.w("TransformedResultImpl", localStringBuilder.toString(), localRuntimeException);
      }
    }
  }
  
  private final void zabu()
  {
    if ((zakn == null) && (zakp == null)) {
      return;
    }
    com.google.android.android.common.aimsicd.GoogleApiClient localGoogleApiClient = (com.google.android.android.common.aimsicd.GoogleApiClient)zadp.get();
    if ((!zakt) && (zakn != null) && (localGoogleApiClient != null))
    {
      localGoogleApiClient.ensureInitialized(this);
      zakt = true;
    }
    if (zakr != null)
    {
      enqueue(zakr);
      return;
    }
    if (zakq != null) {
      zakq.setResultCallback(this);
    }
  }
  
  private final boolean zabw()
  {
    com.google.android.android.common.aimsicd.GoogleApiClient localGoogleApiClient = (com.google.android.android.common.aimsicd.GoogleApiClient)zadp.get();
    return (zakp != null) && (localGoogleApiClient != null);
  }
  
  public final void andFinally(com.google.android.android.common.aimsicd.ResultCallbacks paramResultCallbacks)
  {
    Object localObject = zadn;
    for (;;)
    {
      try
      {
        com.google.android.android.common.aimsicd.ResultCallbacks localResultCallbacks = zakp;
        boolean bool2 = false;
        if (localResultCallbacks == null)
        {
          bool1 = true;
          Preconditions.checkState(bool1, "Cannot call andFinally() twice.");
          bool1 = bool2;
          if (zakn == null) {
            bool1 = true;
          }
          Preconditions.checkState(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          zakp = paramResultCallbacks;
          zabu();
          return;
        }
      }
      catch (Throwable paramResultCallbacks)
      {
        throw paramResultCallbacks;
      }
      boolean bool1 = false;
    }
  }
  
  public final void onResult(com.google.android.android.common.aimsicd.Result paramResult)
  {
    Object localObject = zadn;
    try
    {
      if (paramResult.getStatus().isSuccess())
      {
        if (zakn != null) {
          zacc.zabb().submit(new zacn(this, paramResult));
        } else if (zabw()) {
          zakp.onSuccess(paramResult);
        }
      }
      else
      {
        add(paramResult.getStatus());
        parse(paramResult);
      }
      return;
    }
    catch (Throwable paramResult)
    {
      throw paramResult;
    }
  }
  
  public final void onResultReceived(com.google.android.android.common.aimsicd.PendingResult paramPendingResult)
  {
    Object localObject = zadn;
    try
    {
      zakq = paramPendingResult;
      zabu();
      return;
    }
    catch (Throwable paramPendingResult)
    {
      throw paramPendingResult;
    }
  }
  
  public final com.google.android.android.common.aimsicd.TransformedResult then(com.google.android.android.common.aimsicd.ResultTransform paramResultTransform)
  {
    Object localObject = zadn;
    for (;;)
    {
      try
      {
        com.google.android.android.common.aimsicd.ResultTransform localResultTransform = zakn;
        boolean bool2 = false;
        if (localResultTransform == null)
        {
          bool1 = true;
          Preconditions.checkState(bool1, "Cannot call then() twice.");
          bool1 = bool2;
          if (zakp == null) {
            bool1 = true;
          }
          Preconditions.checkState(bool1, "Cannot call then() and andFinally() on the same TransformedResult.");
          zakn = paramResultTransform;
          paramResultTransform = new zacm(zadp);
          zako = paramResultTransform;
          zabu();
          return paramResultTransform;
        }
      }
      catch (Throwable paramResultTransform)
      {
        throw paramResultTransform;
      }
      boolean bool1 = false;
    }
  }
  
  final void zabv()
  {
    zakp = null;
  }
}
