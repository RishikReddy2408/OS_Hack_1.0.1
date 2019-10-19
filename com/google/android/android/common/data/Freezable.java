package com.google.android.android.common.data;

public abstract interface Freezable<T>
{
  public abstract Object freeze();
  
  public abstract boolean isDataValid();
}
