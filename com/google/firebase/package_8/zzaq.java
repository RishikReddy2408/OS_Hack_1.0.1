package com.google.firebase.package_8;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzaq
{
  private final Executor zzbj;
  @GuardedBy("this")
  private final Map<Pair<String, String>, com.google.android.gms.tasks.Task<String>> zzco = new ArrayMap();
  
  zzaq(Executor paramExecutor)
  {
    zzbj = paramExecutor;
  }
  
  final com.google.android.android.tasks.Task update(String paramString1, String paramString2, zzas paramZzas)
  {
    try
    {
      paramString1 = new Pair(paramString1, paramString2);
      paramString2 = (com.google.android.android.tasks.Task)zzco.get(paramString1);
      if (paramString2 != null)
      {
        if (Log.isLoggable("FirebaseInstanceId", 3))
        {
          paramString1 = String.valueOf(paramString1);
          paramZzas = new StringBuilder(String.valueOf(paramString1).length() + 29);
          paramZzas.append("Joining ongoing request for: ");
          paramZzas.append(paramString1);
          Log.d("FirebaseInstanceId", paramZzas.toString());
        }
        return paramString2;
      }
      if (Log.isLoggable("FirebaseInstanceId", 3))
      {
        paramString2 = String.valueOf(paramString1);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString2).length() + 24);
        localStringBuilder.append("Making new request for: ");
        localStringBuilder.append(paramString2);
        Log.d("FirebaseInstanceId", localStringBuilder.toString());
      }
      paramString2 = paramZzas.getRawText().continueWithTask(zzbj, new zzar(this, paramString1));
      zzco.put(paramString1, paramString2);
      return paramString2;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
}
