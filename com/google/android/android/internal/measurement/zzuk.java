package com.google.android.android.internal.measurement;

final class zzuk
{
  private final byte[] buffer;
  private final zzut zzbuf;
  
  private zzuk(int paramInt)
  {
    buffer = new byte[paramInt];
    zzbuf = zzut.getByteArray(buffer);
  }
  
  public final zzud zzue()
  {
    if (zzbuf.zzvg() == 0) {
      return new zzum(buffer);
    }
    throw new IllegalStateException("Did not write as much data as expected.");
  }
  
  public final zzut zzuf()
  {
    return zzbuf;
  }
}
