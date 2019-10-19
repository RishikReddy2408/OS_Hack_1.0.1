package com.google.firebase.inject;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface Provider<T>
{
  public abstract Object get();
}
