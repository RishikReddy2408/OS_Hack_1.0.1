package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.inject.Provider;

@KeepForSdk
public abstract interface ComponentContainer
{
  public abstract Object get(Class paramClass);
  
  public abstract Provider getProvider(Class paramClass);
}
