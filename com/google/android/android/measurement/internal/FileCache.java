package com.google.android.android.measurement.internal;

import com.google.android.android.internal.measurement.zzgf;
import com.google.android.android.internal.measurement.zzgi;

abstract interface FileCache
{
  public abstract void append(zzgi paramZzgi);
  
  public abstract boolean get(long paramLong, zzgf paramZzgf);
}
