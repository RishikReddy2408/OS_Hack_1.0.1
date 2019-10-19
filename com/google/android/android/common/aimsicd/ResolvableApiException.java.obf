package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;

public class ResolvableApiException
  extends ApiException
{
  public ResolvableApiException(@NonNull Status paramStatus)
  {
    super(paramStatus);
  }
  
  public PendingIntent getResolution()
  {
    return mStatus.getResolution();
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    mStatus.startResolutionForResult(paramActivity, paramInt);
  }
}
