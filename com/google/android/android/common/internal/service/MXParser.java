package com.google.android.android.common.internal.service;

import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.PendingResult;

public final class MXParser
  implements Location
{
  public MXParser() {}
  
  public final PendingResult getName(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.execute(new InternalHttpClient(this, paramGoogleApiClient));
  }
}
