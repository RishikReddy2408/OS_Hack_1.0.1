package com.google.android.android.measurement.internal;

abstract class Log
  extends class_2
{
  private boolean zzvz;
  
  Log(zzbt paramZzbt)
  {
    super(paramZzbt);
    zzadj.writeShort(this);
  }
  
  final boolean isInitialized()
  {
    return zzvz;
  }
  
  public final void logError()
  {
    if (!zzvz)
    {
      if (!zzgt())
      {
        zzadj.zzkq();
        zzvz = true;
      }
    }
    else {
      throw new IllegalStateException("Can't initialize twice");
    }
  }
  
  protected final void zzcl()
  {
    if (isInitialized()) {
      return;
    }
    throw new IllegalStateException("Not initialized");
  }
  
  public final void zzgs()
  {
    if (!zzvz)
    {
      zzgu();
      zzadj.zzkq();
      zzvz = true;
      return;
    }
    throw new IllegalStateException("Can't initialize twice");
  }
  
  protected abstract boolean zzgt();
  
  protected void zzgu() {}
}
