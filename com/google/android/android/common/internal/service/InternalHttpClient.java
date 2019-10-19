package com.google.android.android.common.internal.service;

import com.google.android.android.common.aimsicd.GoogleApiClient;

final class InternalHttpClient
  extends CloseableHttpClient
{
  InternalHttpClient(MXParser paramMXParser, GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
  }
}
