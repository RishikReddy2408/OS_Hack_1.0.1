package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;

final class zzfj
{
  final String name;
  final String origin;
  final Object value;
  final long zzaue;
  final String zztt;
  
  zzfj(String paramString1, String paramString2, String paramString3, long paramLong, Object paramObject)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString3);
    Preconditions.checkNotNull(paramObject);
    zztt = paramString1;
    origin = paramString2;
    name = paramString3;
    zzaue = paramLong;
    value = paramObject;
  }
}
