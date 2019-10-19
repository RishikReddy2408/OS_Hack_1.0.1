package com.google.android.android.internal.firebase_messaging;

import com.google.android.gms.internal.firebase_messaging.zzf;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class ArrayDeque
{
  private final ConcurrentHashMap<zzf, List<Throwable>> attributes = new ConcurrentHashMap(16, 0.75F, 10);
  private final ReferenceQueue<Throwable> elements = new ReferenceQueue();
  
  ArrayDeque() {}
  
  public final List clear(Throwable paramThrowable, boolean paramBoolean)
  {
    for (Object localObject = elements.poll(); localObject != null; localObject = elements.poll()) {
      attributes.remove(localObject);
    }
    localObject = new Attribute(paramThrowable, null);
    localObject = (List)attributes.get(localObject);
    if (localObject != null) {
      return localObject;
    }
    localObject = new Vector(2);
    paramThrowable = (List)attributes.putIfAbsent(new Attribute(paramThrowable, elements), localObject);
    if (paramThrowable == null) {
      return localObject;
    }
    return paramThrowable;
  }
}
