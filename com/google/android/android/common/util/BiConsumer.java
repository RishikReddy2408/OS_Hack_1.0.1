package com.google.android.android.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface BiConsumer<T, U>
{
  public abstract void accept(Object paramObject1, Object paramObject2);
}
