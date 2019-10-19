package com.google.android.android.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class Attribute
  extends WeakReference<Throwable>
{
  private final int id;
  
  public Attribute(Throwable paramThrowable, ReferenceQueue paramReferenceQueue)
  {
    super(paramThrowable, paramReferenceQueue);
    if (paramThrowable != null)
    {
      id = System.identityHashCode(paramThrowable);
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
      paramObject = (Attribute)paramObject;
      if ((id == id) && (get() == paramObject.get())) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return id;
  }
}
