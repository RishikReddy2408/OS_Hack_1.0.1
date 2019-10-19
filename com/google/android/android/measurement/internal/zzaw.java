package com.google.android.android.measurement.internal;

import android.support.annotation.WorkerThread;
import com.google.android.android.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

@WorkerThread
final class zzaw
  implements Runnable
{
  private final String packageName;
  private final int status;
  private final zzav zzamr;
  private final Throwable zzams;
  private final byte[] zzamt;
  private final Map<String, List<String>> zzamu;
  
  private zzaw(String paramString, zzav paramZzav, int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte, Map paramMap)
  {
    Preconditions.checkNotNull(paramZzav);
    zzamr = paramZzav;
    status = paramInt;
    zzams = paramThrowable;
    zzamt = paramArrayOfByte;
    packageName = paramString;
    zzamu = paramMap;
  }
  
  public final void run()
  {
    zzamr.deleteMessages(packageName, status, zzams, zzamt, zzamu);
  }
}
