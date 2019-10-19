package com.google.android.android.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Set;

public final class Resource
{
  final String name;
  private final String origin;
  final long timestamp;
  final long zzaic;
  final zzaa zzaid;
  final String zztt;
  
  Resource(zzbt paramZzbt, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, Bundle paramBundle)
  {
    Preconditions.checkNotEmpty(paramString2);
    Preconditions.checkNotEmpty(paramString3);
    zztt = paramString2;
    name = paramString3;
    paramString3 = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      paramString3 = null;
    }
    origin = paramString3;
    timestamp = paramLong1;
    zzaic = paramLong2;
    if ((zzaic != 0L) && (zzaic > timestamp)) {
      paramZzbt.zzgo().zzjg().append("Event created with reverse previous/current timestamps. appId", zzap.zzbv(paramString2));
    }
    if ((paramBundle != null) && (!paramBundle.isEmpty()))
    {
      paramString1 = new Bundle(paramBundle);
      paramString2 = paramString1.keySet().iterator();
      while (paramString2.hasNext())
      {
        paramString3 = (String)paramString2.next();
        if (paramString3 == null)
        {
          paramZzbt.zzgo().zzjd().zzbx("Param name can't be null");
          paramString2.remove();
        }
        else
        {
          paramBundle = paramZzbt.zzgm().read(paramString3, paramString1.get(paramString3));
          if (paramBundle == null)
          {
            paramZzbt.zzgo().zzjg().append("Param value can't be null", paramZzbt.zzgl().zzbt(paramString3));
            paramString2.remove();
          }
          else
          {
            paramZzbt.zzgm().add(paramString1, paramString3, paramBundle);
          }
        }
      }
      paramZzbt = new zzaa(paramString1);
    }
    else
    {
      paramZzbt = new zzaa(new Bundle());
    }
    zzaid = paramZzbt;
  }
  
  private Resource(zzbt paramZzbt, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, zzaa paramZzaa)
  {
    Preconditions.checkNotEmpty(paramString2);
    Preconditions.checkNotEmpty(paramString3);
    Preconditions.checkNotNull(paramZzaa);
    zztt = paramString2;
    name = paramString3;
    String str = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      str = null;
    }
    origin = str;
    timestamp = paramLong1;
    zzaic = paramLong2;
    if ((zzaic != 0L) && (zzaic > timestamp)) {
      paramZzbt.zzgo().zzjg().append("Event created with reverse previous/current timestamps. appId, name", zzap.zzbv(paramString2), zzap.zzbv(paramString3));
    }
    zzaid = paramZzaa;
  }
  
  final Resource toString(zzbt paramZzbt, long paramLong)
  {
    return new Resource(paramZzbt, origin, zztt, name, timestamp, paramLong, zzaid);
  }
  
  public final String toString()
  {
    String str1 = zztt;
    String str2 = name;
    String str3 = String.valueOf(zzaid);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 33 + String.valueOf(str2).length() + String.valueOf(str3).length());
    localStringBuilder.append("Event{appId='");
    localStringBuilder.append(str1);
    localStringBuilder.append("', name='");
    localStringBuilder.append(str2);
    localStringBuilder.append("', params=");
    localStringBuilder.append(str3);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
