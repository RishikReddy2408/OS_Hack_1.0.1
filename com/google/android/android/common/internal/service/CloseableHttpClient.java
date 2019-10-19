package com.google.android.android.common.internal.service;

import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.service.zag;

abstract class CloseableHttpClient
  extends zag<Status>
{
  public CloseableHttpClient(GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
  }
}
