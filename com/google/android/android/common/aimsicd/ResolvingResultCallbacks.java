package com.google.android.android.common.aimsicd;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.util.Log;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.api.ResultCallbacks;

public abstract class ResolvingResultCallbacks<R extends com.google.android.gms.common.api.Result>
  extends ResultCallbacks<R>
{
  private final Activity mActivity;
  private final int zzan;
  
  protected ResolvingResultCallbacks(Activity paramActivity, int paramInt)
  {
    mActivity = ((Activity)Preconditions.checkNotNull(paramActivity, "Activity must not be null"));
    zzan = paramInt;
  }
  
  public final void onFailure(Status paramStatus)
  {
    if (paramStatus.hasResolution())
    {
      Activity localActivity = mActivity;
      int i = zzan;
      try
      {
        paramStatus.startResolutionForResult(localActivity, i);
        return;
      }
      catch (IntentSender.SendIntentException paramStatus)
      {
        Log.e("ResolvingResultCallback", "Failed to start resolution", paramStatus);
        onUnresolvableFailure(new Status(8));
        return;
      }
    }
    onUnresolvableFailure(paramStatus);
  }
  
  public abstract void onSuccess(Result paramResult);
  
  public abstract void onUnresolvableFailure(Status paramStatus);
}
