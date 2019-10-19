package com.google.android.android.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzsz
  extends WeakReference<Throwable>
{
  private final int zzbry;
  
  public zzsz(Throwable paramThrowable, ReferenceQueue paramReferenceQueue)
  {
    super(paramThrowable, null);
    if (paramThrowable != null)
    {
      zzbry = System.identityHashCode(paramThrowable);
      return;
    }
    throw new NullPointerException("The referent cannot be null");
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      if (paramObject.getClass() != getClass()) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (zzsz)paramObject;
      if ((zzbry == zzbry) && (get() == paramObject.get())) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return zzbry;
  }
}
