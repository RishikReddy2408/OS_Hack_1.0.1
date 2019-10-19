package com.google.android.android.internal.measurement;

import java.util.List;

abstract class zzwd
{
  private static final zzwd zzcaj = new zzwf(null);
  private static final zzwd zzcak = new zzwg(null);
  
  private zzwd() {}
  
  static zzwd zzwx()
  {
    return zzcaj;
  }
  
  static zzwd zzwy()
  {
    return zzcak;
  }
  
  abstract void getField(Object paramObject, long paramLong);
  
  abstract List read(Object paramObject, long paramLong);
  
  abstract void visitTypeInsn(Object paramObject1, Object paramObject2, long paramLong);
}
