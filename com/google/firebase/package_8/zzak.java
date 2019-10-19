package com.google.firebase.package_8;

import android.os.Bundle;
import android.util.Log;

abstract class zzak<T>
{
  final int what;
  final int zzcf;
  final com.google.android.gms.tasks.TaskCompletionSource<T> zzcg = new com.google.android.android.tasks.TaskCompletionSource();
  final Bundle zzch;
  
  zzak(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    zzcf = paramInt1;
    what = paramInt2;
    zzch = paramBundle;
  }
  
  final void finish(Object paramObject)
  {
    if (Log.isLoggable("MessengerIpcClient", 3))
    {
      String str1 = String.valueOf(this);
      String str2 = String.valueOf(paramObject);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 16 + String.valueOf(str2).length());
      localStringBuilder.append("Finishing ");
      localStringBuilder.append(str1);
      localStringBuilder.append(" with ");
      localStringBuilder.append(str2);
      Log.d("MessengerIpcClient", localStringBuilder.toString());
    }
    zzcg.setResult(paramObject);
  }
  
  public String toString()
  {
    int i = what;
    int j = zzcf;
    boolean bool = zzab();
    StringBuilder localStringBuilder = new StringBuilder(55);
    localStringBuilder.append("Request { what=");
    localStringBuilder.append(i);
    localStringBuilder.append(" id=");
    localStringBuilder.append(j);
    localStringBuilder.append(" oneWay=");
    localStringBuilder.append(bool);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  abstract void update(Bundle paramBundle);
  
  final void write(zzal paramZzal)
  {
    if (Log.isLoggable("MessengerIpcClient", 3))
    {
      String str1 = String.valueOf(this);
      String str2 = String.valueOf(paramZzal);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 14 + String.valueOf(str2).length());
      localStringBuilder.append("Failing ");
      localStringBuilder.append(str1);
      localStringBuilder.append(" with ");
      localStringBuilder.append(str2);
      Log.d("MessengerIpcClient", localStringBuilder.toString());
    }
    zzcg.setException(paramZzal);
  }
  
  abstract boolean zzab();
}
