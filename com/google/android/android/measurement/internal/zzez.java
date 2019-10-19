package com.google.android.android.measurement.internal;

abstract class zzez
  extends zzey
{
  private boolean zzvz;
  
  zzez(zzfa paramZzfa)
  {
    super(paramZzfa);
    zzamz.intersect(this);
  }
  
  public final void blur()
  {
    if (!zzvz)
    {
      zzgt();
      zzamz.zzma();
      zzvz = true;
      return;
    }
    throw new IllegalStateException("Can't initialize twice");
  }
  
  final boolean isInitialized()
  {
    return zzvz;
  }
  
  protected final void zzcl()
  {
    if (isInitialized()) {
      return;
    }
    throw new IllegalStateException("Not initialized");
  }
  
  protected abstract boolean zzgt();
}
