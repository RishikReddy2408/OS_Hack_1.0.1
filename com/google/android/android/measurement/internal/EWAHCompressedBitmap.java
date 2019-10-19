package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;

final class EWAHCompressedBitmap
{
  final String name;
  final long zzaie;
  final long zzaif;
  final long zzaig;
  final long zzaih;
  final Long zzaii;
  final Long zzaij;
  final Long zzaik;
  final Boolean zzail;
  final String zztt;
  
  EWAHCompressedBitmap(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, Long paramLong5, Long paramLong6, Long paramLong7, Boolean paramBoolean)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    boolean bool2 = false;
    if (paramLong1 >= 0L) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    Preconditions.checkArgument(bool1);
    if (paramLong2 >= 0L) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    Preconditions.checkArgument(bool1);
    boolean bool1 = bool2;
    if (paramLong4 >= 0L) {
      bool1 = true;
    }
    Preconditions.checkArgument(bool1);
    zztt = paramString1;
    name = paramString2;
    zzaie = paramLong1;
    zzaif = paramLong2;
    zzaig = paramLong3;
    zzaih = paramLong4;
    zzaii = paramLong5;
    zzaij = paramLong6;
    zzaik = paramLong7;
    zzail = paramBoolean;
  }
  
  final EWAHCompressedBitmap get(long paramLong1, long paramLong2)
  {
    return new EWAHCompressedBitmap(zztt, name, zzaie, zzaif, zzaig, paramLong1, Long.valueOf(paramLong2), zzaij, zzaik, zzail);
  }
  
  final EWAHCompressedBitmap toString(Long paramLong1, Long paramLong2, Boolean paramBoolean)
  {
    Boolean localBoolean = paramBoolean;
    if (paramBoolean != null)
    {
      localBoolean = paramBoolean;
      if (!paramBoolean.booleanValue()) {
        localBoolean = null;
      }
    }
    return new EWAHCompressedBitmap(zztt, name, zzaie, zzaif, zzaig, zzaih, zzaii, paramLong1, paramLong2, localBoolean);
  }
  
  final EWAHCompressedBitmap zzai(long paramLong)
  {
    return new EWAHCompressedBitmap(zztt, name, zzaie, zzaif, paramLong, zzaih, zzaii, zzaij, zzaik, zzail);
  }
  
  final EWAHCompressedBitmap zziu()
  {
    return new EWAHCompressedBitmap(zztt, name, zzaie + 1L, 1L + zzaif, zzaig, zzaih, zzaii, zzaij, zzaik, zzail);
  }
}
