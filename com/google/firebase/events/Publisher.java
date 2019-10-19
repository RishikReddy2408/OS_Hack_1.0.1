package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface Publisher
{
  public abstract void publish(Event paramEvent);
}
