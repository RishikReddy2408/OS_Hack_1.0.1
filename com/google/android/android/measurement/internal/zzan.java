package com.google.android.android.measurement.internal;

import android.os.Bundle;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.measurement.AppMeasurement.Event;
import com.google.android.android.measurement.AppMeasurement.Param;
import com.google.android.android.measurement.AppMeasurement.UserProperty;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public final class zzan
  extends zzcp
{
  private static final AtomicReference<String[]> zzalt = new AtomicReference();
  private static final AtomicReference<String[]> zzalu = new AtomicReference();
  private static final AtomicReference<String[]> zzalv = new AtomicReference();
  
  zzan(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final String getAbsolutePath(zzaa paramZzaa)
  {
    if (paramZzaa == null) {
      return null;
    }
    if (!zzjc()) {
      return paramZzaa.toString();
    }
    return toString(paramZzaa.zziv());
  }
  
  private static String verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, AtomicReference paramAtomicReference)
  {
    Preconditions.checkNotNull(paramArrayOfString1);
    Preconditions.checkNotNull(paramArrayOfString2);
    Preconditions.checkNotNull(paramAtomicReference);
    int j = paramArrayOfString1.length;
    int k = paramArrayOfString2.length;
    int i = 0;
    boolean bool;
    if (j == k) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    while (i < paramArrayOfString1.length)
    {
      if (zzfk.verifySignature(paramString, paramArrayOfString1[i])) {
        try
        {
          Object localObject = (String[])paramAtomicReference.get();
          paramString = (String)localObject;
          if (localObject == null)
          {
            paramString = new String[paramArrayOfString2.length];
            paramAtomicReference.set(paramString);
          }
          if (paramString[i] == null)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(paramArrayOfString2[i]);
            ((StringBuilder)localObject).append("(");
            ((StringBuilder)localObject).append(paramArrayOfString1[i]);
            ((StringBuilder)localObject).append(")");
            paramString[i] = ((StringBuilder)localObject).toString();
          }
          paramString = paramString[i];
          return paramString;
        }
        catch (Throwable paramString)
        {
          throw paramString;
        }
      }
      i += 1;
    }
    return paramString;
  }
  
  private final boolean zzjc()
  {
    zzgr();
    return (zzadj.zzkj()) && (zzadj.zzgo().isLoggable(3));
  }
  
  protected final String getItemName(zzad paramZzad)
  {
    if (paramZzad == null) {
      return null;
    }
    if (!zzjc()) {
      return paramZzad.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("origin=");
    localStringBuilder.append(origin);
    localStringBuilder.append(",name=");
    localStringBuilder.append(zzbs(name));
    localStringBuilder.append(",params=");
    localStringBuilder.append(getAbsolutePath(zzaid));
    return localStringBuilder.toString();
  }
  
  protected final String process(Resource paramResource)
  {
    if (paramResource == null) {
      return null;
    }
    if (!zzjc()) {
      return paramResource.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Event{appId='");
    localStringBuilder.append(zztt);
    localStringBuilder.append("', name='");
    localStringBuilder.append(zzbs(name));
    localStringBuilder.append("', params=");
    localStringBuilder.append(getAbsolutePath(zzaid));
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  protected final String toString(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return null;
    }
    if (!zzjc()) {
      return paramBundle.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (localStringBuilder.length() != 0) {
        localStringBuilder.append(", ");
      } else {
        localStringBuilder.append("Bundle[{");
      }
      localStringBuilder.append(zzbt(str));
      localStringBuilder.append("=");
      localStringBuilder.append(paramBundle.get(str));
    }
    localStringBuilder.append("}]");
    return localStringBuilder.toString();
  }
  
  protected final String zzbs(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (!zzjc()) {
      return paramString;
    }
    return verify(paramString, AppMeasurement.Event.zzadl, AppMeasurement.Event.zzadk, zzalt);
  }
  
  protected final String zzbt(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (!zzjc()) {
      return paramString;
    }
    return verify(paramString, AppMeasurement.Param.zzadn, AppMeasurement.Param.zzadm, zzalu);
  }
  
  protected final String zzbu(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (!zzjc()) {
      return paramString;
    }
    if (paramString.startsWith("_exp_"))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("experiment_id");
      localStringBuilder.append("(");
      localStringBuilder.append(paramString);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    }
    return verify(paramString, AppMeasurement.UserProperty.zzadp, AppMeasurement.UserProperty.zzado, zzalv);
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
}
