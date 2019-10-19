package com.google.android.android.common.aimsicd;

import android.util.Log;
import com.google.android.gms.common.api.ResultCallback;

public abstract class ResultCallbacks<R extends com.google.android.gms.common.api.Result>
  implements ResultCallback<R>
{
  public ResultCallbacks() {}
  
  public abstract void onFailure(Status paramStatus);
  
  public final void onResult(Result paramResult)
  {
    Status localStatus = paramResult.getStatus();
    if (localStatus.isSuccess())
    {
      onSuccess(paramResult);
      return;
    }
    onFailure(localStatus);
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
        Log.w("ResultCallbacks", localStringBuilder.toString(), localRuntimeException);
      }
    }
  }
  
  public abstract void onSuccess(Result paramResult);
}
