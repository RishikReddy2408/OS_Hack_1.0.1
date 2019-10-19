package com.google.firebase.internal;

import com.google.android.android.tasks.Task;
import com.google.android.gms.common.annotation.KeepForSdk;

@Deprecated
@KeepForSdk
public abstract interface InternalTokenProvider
{
  public abstract Task getAccessToken(boolean paramBoolean);
  
  public abstract String getUid();
}
