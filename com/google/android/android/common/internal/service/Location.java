package com.google.android.android.common.internal.service;

import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.PendingResult;

public abstract interface Location
{
  public abstract PendingResult getName(GoogleApiClient paramGoogleApiClient);
}
