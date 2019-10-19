package com.google.firebase.events;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class Event<T>
{
  private final T data;
  private final Class<T> type;
  
  public Event(Class paramClass, Object paramObject)
  {
    type = ((Class)Preconditions.checkNotNull(paramClass));
    data = Preconditions.checkNotNull(paramObject);
  }
  
  public Object getPayload()
  {
    return data;
  }
  
  public Class getType()
  {
    return type;
  }
  
  public String toString()
  {
    return String.format("Event{type: %s, payload: %s}", new Object[] { type, data });
  }
}
