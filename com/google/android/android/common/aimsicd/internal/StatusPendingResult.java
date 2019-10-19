package com.google.android.android.common.aimsicd.internal;

import android.os.Looper;
import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;

@KeepForSdk
public class StatusPendingResult
  extends com.google.android.gms.common.api.internal.BasePendingResult<Status>
{
  public StatusPendingResult(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public StatusPendingResult(GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
  }
}
