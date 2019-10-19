package com.google.firebase.components;

import android.support.annotation.VisibleForTesting;
import java.util.List;

@VisibleForTesting
abstract interface Future<T>
{
  public abstract List get(Object paramObject);
}
