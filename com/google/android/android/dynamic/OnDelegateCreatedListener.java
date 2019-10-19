package com.google.android.android.dynamic;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface OnDelegateCreatedListener<T extends com.google.android.gms.dynamic.LifecycleDelegate>
{
  public abstract void onDelegateCreated(LifecycleDelegate paramLifecycleDelegate);
}
