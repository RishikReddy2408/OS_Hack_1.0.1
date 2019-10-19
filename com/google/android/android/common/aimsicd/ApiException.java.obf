package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ApiException
  extends Exception
{
  protected final Status mStatus;
  
  public ApiException(@NonNull Status paramStatus)
  {
    super(localStringBuilder.toString());
    mStatus = paramStatus;
  }
  
  public int getStatusCode()
  {
    return mStatus.getStatusCode();
  }
  
  @Deprecated
  @Nullable
  public String getStatusMessage()
  {
    return mStatus.getStatusMessage();
  }
}
