package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface ComponentFactory<T>
{
  public abstract Object create(ComponentContainer paramComponentContainer);
}
