package com.google.android.android.common.util.concurrent;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public class NumberedThreadFactory
  implements ThreadFactory
{
  private final int priority;
  private final ThreadFactory zzhp = Executors.defaultThreadFactory();
  private final String zzhq;
  private final AtomicInteger zzhr = new AtomicInteger();
  
  public NumberedThreadFactory(String paramString)
  {
    this(paramString, 0);
  }
  
  private NumberedThreadFactory(String paramString, int paramInt)
  {
    zzhq = ((String)Preconditions.checkNotNull(paramString, "Name must not be null"));
    priority = 0;
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = zzhp.newThread(new EngineRunnable(paramRunnable, 0));
    String str = zzhq;
    int i = zzhr.getAndIncrement();
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 13);
    localStringBuilder.append(str);
    localStringBuilder.append("[");
    localStringBuilder.append(i);
    localStringBuilder.append("]");
    paramRunnable.setName(localStringBuilder.toString());
    return paramRunnable;
  }
}
