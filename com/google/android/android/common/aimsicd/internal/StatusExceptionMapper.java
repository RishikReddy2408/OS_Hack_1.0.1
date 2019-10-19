package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.aimsicd.Status;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface StatusExceptionMapper
{
  public abstract Exception getException(Status paramStatus);
}
