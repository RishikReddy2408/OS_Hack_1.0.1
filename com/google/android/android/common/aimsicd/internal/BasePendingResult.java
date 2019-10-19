package com.google.android.android.common.aimsicd.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.android.common.aimsicd.Releasable;
import com.google.android.android.common.aimsicd.ResultTransform;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.aimsicd.TransformedResult;
import com.google.android.android.common.internal.ICancelToken;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.internal.base.Credentials;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zal;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
@KeepName
public abstract class BasePendingResult<R extends com.google.android.gms.common.api.Result>
  extends com.google.android.gms.common.api.PendingResult<R>
{
  static final ThreadLocal<Boolean> zadm = new CycleDetectingLockFactory.1();
  @KeepName
  private com.google.android.gms.common.api.internal.BasePendingResult.zaa mResultGuardian;
  private Status mStatus;
  private R zaci;
  private final Object zadn = new Object();
  private final com.google.android.gms.common.api.internal.BasePendingResult.CallbackHandler<R> zado;
  private final WeakReference<com.google.android.gms.common.api.GoogleApiClient> zadp;
  private final CountDownLatch zadq = new CountDownLatch(1);
  private final ArrayList<com.google.android.gms.common.api.PendingResult.StatusListener> zadr = new ArrayList();
  private com.google.android.gms.common.api.ResultCallback<? super R> zads;
  private final AtomicReference<com.google.android.gms.common.api.internal.zacs> zadt = new AtomicReference();
  private volatile boolean zadu;
  private boolean zadv;
  private boolean zadw;
  private ICancelToken zadx;
  private volatile com.google.android.gms.common.api.internal.zacm<R> zady;
  private boolean zadz = false;
  
  BasePendingResult()
  {
    zado = new CallbackHandler(Looper.getMainLooper());
    zadp = new WeakReference(null);
  }
  
  protected BasePendingResult(Looper paramLooper)
  {
    zado = new CallbackHandler(paramLooper);
    zadp = new WeakReference(null);
  }
  
  protected BasePendingResult(com.google.android.android.common.aimsicd.GoogleApiClient paramGoogleApiClient)
  {
    Looper localLooper;
    if (paramGoogleApiClient != null) {
      localLooper = paramGoogleApiClient.getLooper();
    } else {
      localLooper = Looper.getMainLooper();
    }
    zado = new CallbackHandler(localLooper);
    zadp = new WeakReference(paramGoogleApiClient);
  }
  
  protected BasePendingResult(CallbackHandler paramCallbackHandler)
  {
    zado = ((CallbackHandler)Preconditions.checkNotNull(paramCallbackHandler, "CallbackHandler must not be null"));
    zadp = new WeakReference(null);
  }
  
  public static void clear(com.google.android.android.common.aimsicd.Result paramResult)
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
        Log.w("BasePendingResult", localStringBuilder.toString(), localRuntimeException);
      }
    }
  }
  
  private final void close(com.google.android.android.common.aimsicd.Result paramResult)
  {
    zaci = paramResult;
    zadx = null;
    zadq.countDown();
    mStatus = zaci.getStatus();
    if (zadv)
    {
      zads = null;
    }
    else if (zads == null)
    {
      if ((zaci instanceof Releasable)) {
        mResultGuardian = new zaa(null);
      }
    }
    else
    {
      zado.removeMessages(2);
      zado.onPostExecute(zads, iterator());
    }
    paramResult = (ArrayList)zadr;
    int j = paramResult.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramResult.get(i);
      i += 1;
      ((com.google.android.android.common.aimsicd.PendingResult.StatusListener)localObject).onComplete(mStatus);
    }
    zadr.clear();
  }
  
  private final com.google.android.android.common.aimsicd.Result iterator()
  {
    Object localObject = zadn;
    try
    {
      Preconditions.checkState(zadu ^ true, "Result has already been consumed.");
      Preconditions.checkState(isReady(), "Result is not ready.");
      com.google.android.android.common.aimsicd.Result localResult = zaci;
      zaci = null;
      zads = null;
      zadu = true;
      localObject = (zacs)zadt.getAndSet(null);
      if (localObject != null)
      {
        ((zacs)localObject).andNot(this);
        return localResult;
      }
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
    return localThrowable;
  }
  
  public final void addStatusListener(com.google.android.android.common.aimsicd.PendingResult.StatusListener paramStatusListener)
  {
    boolean bool;
    if (paramStatusListener != null) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "Callback cannot be null.");
    Object localObject = zadn;
    try
    {
      if (isReady()) {
        paramStatusListener.onComplete(mStatus);
      } else {
        zadr.add(paramStatusListener);
      }
      return;
    }
    catch (Throwable paramStatusListener)
    {
      throw paramStatusListener;
    }
  }
  
  public final com.google.android.android.common.aimsicd.Result await()
  {
    Preconditions.checkNotMainThread("await must not be called on the UI thread");
    boolean bool2 = zadu;
    boolean bool1 = true;
    Preconditions.checkState(bool2 ^ true, "Result has already been consumed");
    if (zady != null) {
      bool1 = false;
    }
    Preconditions.checkState(bool1, "Cannot await if then() has been called.");
    CountDownLatch localCountDownLatch = zadq;
    try
    {
      localCountDownLatch.await();
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    await(Status.RESULT_INTERRUPTED);
    Preconditions.checkState(isReady(), "Result is not ready.");
    return iterator();
  }
  
  public final com.google.android.android.common.aimsicd.Result await(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong > 0L) {
      Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
    }
    boolean bool2 = zadu;
    boolean bool1 = true;
    Preconditions.checkState(bool2 ^ true, "Result has already been consumed.");
    if (zady != null) {
      bool1 = false;
    }
    Preconditions.checkState(bool1, "Cannot await if then() has been called.");
    CountDownLatch localCountDownLatch = zadq;
    try
    {
      bool1 = localCountDownLatch.await(paramLong, paramTimeUnit);
      if (bool1) {
        break label90;
      }
      paramTimeUnit = Status.RESULT_TIMEOUT;
      await(paramTimeUnit);
    }
    catch (InterruptedException paramTimeUnit)
    {
      for (;;) {}
    }
    await(Status.RESULT_INTERRUPTED);
    label90:
    Preconditions.checkState(isReady(), "Result is not ready.");
    return iterator();
  }
  
  public final void await(Status paramStatus)
  {
    Object localObject = zadn;
    try
    {
      if (!isReady())
      {
        setResult(createFailedResult(paramStatus));
        zadw = true;
      }
      return;
    }
    catch (Throwable paramStatus)
    {
      throw paramStatus;
    }
  }
  
  /* Error */
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 67	com/google/android/android/common/aimsicd/internal/BasePendingResult:zadn	Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 177	com/google/android/android/common/aimsicd/internal/BasePendingResult:zadv	Z
    //   11: ifne +59 -> 70
    //   14: aload_0
    //   15: getfield 216	com/google/android/android/common/aimsicd/internal/BasePendingResult:zadu	Z
    //   18: ifeq +6 -> 24
    //   21: goto +49 -> 70
    //   24: aload_0
    //   25: getfield 164	com/google/android/android/common/aimsicd/internal/BasePendingResult:zadx	Lcom/google/android/android/common/internal/ICancelToken;
    //   28: astore_2
    //   29: aload_2
    //   30: ifnull +14 -> 44
    //   33: aload_0
    //   34: getfield 164	com/google/android/android/common/aimsicd/internal/BasePendingResult:zadx	Lcom/google/android/android/common/internal/ICancelToken;
    //   37: astore_2
    //   38: aload_2
    //   39: invokeinterface 300 1 0
    //   44: aload_0
    //   45: getfield 162	com/google/android/android/common/aimsicd/internal/BasePendingResult:zaci	Lcom/google/android/android/common/aimsicd/Result;
    //   48: invokestatic 302	com/google/android/android/common/aimsicd/internal/BasePendingResult:clear	(Lcom/google/android/android/common/aimsicd/Result;)V
    //   51: aload_0
    //   52: iconst_1
    //   53: putfield 177	com/google/android/android/common/aimsicd/internal/BasePendingResult:zadv	Z
    //   56: aload_0
    //   57: aload_0
    //   58: getstatic 305	com/google/android/android/common/aimsicd/Status:RESULT_CANCELED	Lcom/google/android/android/common/aimsicd/Status;
    //   61: invokevirtual 288	com/google/android/android/common/aimsicd/internal/BasePendingResult:createFailedResult	(Lcom/google/android/android/common/aimsicd/Status;)Lcom/google/android/android/common/aimsicd/Result;
    //   64: invokespecial 307	com/google/android/android/common/aimsicd/internal/BasePendingResult:close	(Lcom/google/android/android/common/aimsicd/Result;)V
    //   67: aload_1
    //   68: monitorexit
    //   69: return
    //   70: aload_1
    //   71: monitorexit
    //   72: return
    //   73: astore_2
    //   74: aload_1
    //   75: monitorexit
    //   76: aload_2
    //   77: athrow
    //   78: astore_2
    //   79: goto -35 -> 44
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	this	BasePendingResult
    //   4	71	1	localObject	Object
    //   28	11	2	localICancelToken	ICancelToken
    //   73	4	2	localThrowable	Throwable
    //   78	1	2	localRemoteException	android.os.RemoteException
    // Exception table:
    //   from	to	target	type
    //   7	21	73	java/lang/Throwable
    //   24	29	73	java/lang/Throwable
    //   38	44	73	java/lang/Throwable
    //   44	69	73	java/lang/Throwable
    //   70	72	73	java/lang/Throwable
    //   74	76	73	java/lang/Throwable
    //   38	44	78	android/os/RemoteException
  }
  
  public final boolean compareAndSet()
  {
    Object localObject = zadn;
    try
    {
      if (((com.google.android.android.common.aimsicd.GoogleApiClient)zadp.get() == null) || (!zadz)) {
        cancel();
      }
      boolean bool = isCanceled();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected abstract com.google.android.android.common.aimsicd.Result createFailedResult(Status paramStatus);
  
  public final Integer getValue()
  {
    return null;
  }
  
  public boolean isCanceled()
  {
    Object localObject = zadn;
    try
    {
      boolean bool = zadv;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final boolean isReady()
  {
    return zadq.getCount() == 0L;
  }
  
  public final void put()
  {
    boolean bool;
    if ((!zadz) && (!((Boolean)zadm.get()).booleanValue())) {
      bool = false;
    } else {
      bool = true;
    }
    zadz = bool;
  }
  
  public final void remove(zacs paramZacs)
  {
    zadt.set(paramZacs);
  }
  
  protected final void setCancelToken(ICancelToken paramICancelToken)
  {
    Object localObject = zadn;
    try
    {
      zadx = paramICancelToken;
      return;
    }
    catch (Throwable paramICancelToken)
    {
      throw paramICancelToken;
    }
  }
  
  public final void setResult(com.google.android.android.common.aimsicd.Result paramResult)
  {
    Object localObject = zadn;
    try
    {
      if ((!zadw) && (!zadv))
      {
        isReady();
        Preconditions.checkState(isReady() ^ true, "Results have already been set");
        Preconditions.checkState(zadu ^ true, "Result has already been consumed");
        close(paramResult);
        return;
      }
      clear(paramResult);
      return;
    }
    catch (Throwable paramResult)
    {
      throw paramResult;
    }
  }
  
  public final void setResultCallback(com.google.android.android.common.aimsicd.ResultCallback paramResultCallback)
  {
    Object localObject = zadn;
    boolean bool1;
    if (paramResultCallback == null)
    {
      try
      {
        zads = null;
        return;
      }
      catch (Throwable paramResultCallback) {}
    }
    else
    {
      boolean bool2 = zadu;
      bool1 = true;
      Preconditions.checkState(bool2 ^ true, "Result has already been consumed.");
      if (zady != null) {
        break label105;
      }
    }
    for (;;)
    {
      Preconditions.checkState(bool1, "Cannot set callbacks if then() has been called.");
      if (isCanceled()) {
        return;
      }
      if (isReady()) {
        zado.onPostExecute(paramResultCallback, iterator());
      } else {
        zads = paramResultCallback;
      }
      return;
      throw paramResultCallback;
      label105:
      bool1 = false;
    }
  }
  
  public final void setResultCallback(com.google.android.android.common.aimsicd.ResultCallback paramResultCallback, long paramLong, TimeUnit paramTimeUnit)
  {
    Object localObject = zadn;
    boolean bool1;
    if (paramResultCallback == null)
    {
      try
      {
        zads = null;
        return;
      }
      catch (Throwable paramResultCallback) {}
    }
    else
    {
      boolean bool2 = zadu;
      bool1 = true;
      Preconditions.checkState(bool2 ^ true, "Result has already been consumed.");
      if (zady != null) {
        break label133;
      }
    }
    for (;;)
    {
      Preconditions.checkState(bool1, "Cannot set callbacks if then() has been called.");
      if (isCanceled()) {
        return;
      }
      if (isReady())
      {
        zado.onPostExecute(paramResultCallback, iterator());
      }
      else
      {
        zads = paramResultCallback;
        paramResultCallback = zado;
        paramLong = paramTimeUnit.toMillis(paramLong);
        paramResultCallback.sendMessageDelayed(paramResultCallback.obtainMessage(2, this), paramLong);
      }
      return;
      throw paramResultCallback;
      label133:
      bool1 = false;
    }
  }
  
  public TransformedResult then(ResultTransform paramResultTransform)
  {
    Preconditions.checkState(zadu ^ true, "Result has already been consumed.");
    Object localObject = zadn;
    for (;;)
    {
      try
      {
        zacm localZacm = zady;
        boolean bool2 = false;
        if (localZacm == null)
        {
          bool1 = true;
          Preconditions.checkState(bool1, "Cannot call then() twice.");
          bool1 = bool2;
          if (zads == null) {
            bool1 = true;
          }
          Preconditions.checkState(bool1, "Cannot call then() if callbacks are set.");
          Preconditions.checkState(zadv ^ true, "Cannot call then() if result was canceled.");
          zadz = true;
          zady = new zacm(zadp);
          paramResultTransform = zady.then(paramResultTransform);
          if (isReady()) {
            zado.onPostExecute(zady, iterator());
          } else {
            zads = zady;
          }
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
  
  @VisibleForTesting
  public class CallbackHandler<R extends com.google.android.gms.common.api.Result>
    extends zal
  {
    public CallbackHandler()
    {
      this();
    }
    
    public CallbackHandler()
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        int i = what;
        paramMessage = new StringBuilder(45);
        paramMessage.append("Don't know how to handle message: ");
        paramMessage.append(i);
        Log.wtf("BasePendingResult", paramMessage.toString(), new Exception());
        return;
      case 2: 
        ((BasePendingResult)obj).await(Status.RESULT_TIMEOUT);
        return;
      }
      Object localObject = (Pair)obj;
      paramMessage = (com.google.android.android.common.aimsicd.ResultCallback)first;
      localObject = (com.google.android.android.common.aimsicd.Result)second;
      try
      {
        paramMessage.onResult((com.google.android.android.common.aimsicd.Result)localObject);
        return;
      }
      catch (RuntimeException paramMessage)
      {
        BasePendingResult.clear((com.google.android.android.common.aimsicd.Result)localObject);
        throw paramMessage;
      }
    }
    
    public final void onPostExecute(com.google.android.android.common.aimsicd.ResultCallback paramResultCallback, com.google.android.android.common.aimsicd.Result paramResult)
    {
      sendMessage(obtainMessage(1, new Pair(paramResultCallback, paramResult)));
    }
  }
  
  final class zaa
  {
    private zaa() {}
    
    protected final void finalize()
      throws Throwable
    {
      BasePendingResult.clear(BasePendingResult.readMessage(BasePendingResult.this));
      super.finalize();
    }
  }
}
