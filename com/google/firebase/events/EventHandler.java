package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface EventHandler<T>
{
  public abstract void handle(Event paramEvent);
}
