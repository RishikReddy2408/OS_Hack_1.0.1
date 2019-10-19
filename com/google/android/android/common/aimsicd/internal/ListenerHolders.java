package com.google.android.android.common.aimsicd.internal;

import android.os.Looper;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

@KeepForSdk
public class ListenerHolders
{
  private final Set<com.google.android.gms.common.api.internal.ListenerHolder<?>> zajn = Collections.newSetFromMap(new WeakHashMap());
  
  public ListenerHolders() {}
  
  public static ListenerHolder createListenerHolder(Object paramObject, Looper paramLooper, String paramString)
  {
    Preconditions.checkNotNull(paramObject, "Listener must not be null");
    Preconditions.checkNotNull(paramLooper, "Looper must not be null");
    Preconditions.checkNotNull(paramString, "Listener type must not be null");
    return new ListenerHolder(paramLooper, paramObject, paramString);
  }
  
  public static ListenerHolder.ListenerKey createListenerKey(Object paramObject, String paramString)
  {
    Preconditions.checkNotNull(paramObject, "Listener must not be null");
    Preconditions.checkNotNull(paramString, "Listener type must not be null");
    Preconditions.checkNotEmpty(paramString, "Listener type must not be empty");
    return new ListenerHolder.ListenerKey(paramObject, paramString);
  }
  
  public final ListenerHolder addOnChangeListener(Object paramObject, Looper paramLooper, String paramString)
  {
    paramObject = createListenerHolder(paramObject, paramLooper, paramString);
    zajn.add(paramObject);
    return paramObject;
  }
  
  public final void release()
  {
    Iterator localIterator = zajn.iterator();
    while (localIterator.hasNext()) {
      ((ListenerHolder)localIterator.next()).clear();
    }
    zajn.clear();
  }
}
