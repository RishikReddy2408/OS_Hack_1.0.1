package com.google.android.android.common.stats;

import com.google.android.android.common.internal.ReflectedParcelable;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class StatsEvent
  extends AbstractSafeParcelable
  implements ReflectedParcelable
{
  public StatsEvent() {}
  
  public abstract long getDateAsString();
  
  public abstract int getEventType();
  
  public abstract String getRawText();
  
  public abstract long getTimeMillis();
  
  public String toString()
  {
    long l1 = getTimeMillis();
    int i = getEventType();
    long l2 = getDateAsString();
    String str = getRawText();
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 53);
    localStringBuilder.append(l1);
    localStringBuilder.append("\t");
    localStringBuilder.append(i);
    localStringBuilder.append("\t");
    localStringBuilder.append(l2);
    localStringBuilder.append(str);
    return localStringBuilder.toString();
  }
  
  @KeepForSdk
  public abstract interface Types
  {
    @KeepForSdk
    public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
    @KeepForSdk
    public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
  }
}
