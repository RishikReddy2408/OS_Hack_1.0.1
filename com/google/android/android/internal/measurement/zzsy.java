package com.google.android.android.internal.measurement;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzsy
{
  private final ConcurrentHashMap<com.google.android.gms.internal.measurement.zzsz, List<Throwable>> zzbrw = new ConcurrentHashMap(16, 0.75F, 10);
  private final ReferenceQueue<Throwable> zzbrx = new ReferenceQueue();
  
  zzsy() {}
  
  public final List get(Throwable paramThrowable, boolean paramBoolean)
  {
    for (Reference localReference = zzbrx.poll(); localReference != null; localReference = zzbrx.poll()) {
      zzbrw.remove(localReference);
    }
    paramThrowable = new zzsz(paramThrowable, null);
    return (List)zzbrw.get(paramThrowable);
  }
}
