package com.google.android.android.internal.measurement;

import java.io.IOException;

public abstract class zzzg
{
  protected volatile int zzcfm = -1;
  
  public zzzg() {}
  
  public static final zzzg createCopy(zzzg paramZzzg, byte[] paramArrayOfByte)
    throws zzzf
  {
    return createCopy(paramZzzg, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private static final zzzg createCopy(zzzg paramZzzg, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws zzzf
  {
    try
    {
      paramArrayOfByte = zzyx.get(paramArrayOfByte, 0, paramInt2);
      paramZzzg.digest(paramArrayOfByte);
      paramArrayOfByte.zzan(0);
      return paramZzzg;
    }
    catch (IOException paramZzzg)
    {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", paramZzzg);
    }
    catch (zzzf paramZzzg)
    {
      throw paramZzzg;
    }
  }
  
  public static final void toByteArray(zzzg paramZzzg, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      paramArrayOfByte = zzyy.toString(paramArrayOfByte, 0, paramInt2);
      paramZzzg.multiply(paramArrayOfByte);
      paramArrayOfByte.zzyt();
      return;
    }
    catch (IOException paramZzzg)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", paramZzzg);
    }
  }
  
  public abstract zzzg digest(zzyx paramZzyx)
    throws IOException;
  
  protected int multiply()
  {
    return 0;
  }
  
  public void multiply(zzyy paramZzyy)
    throws IOException
  {}
  
  public String toString()
  {
    return zzzh.print(this);
  }
  
  public final int zzvu()
  {
    int i = multiply();
    zzcfm = i;
    return i;
  }
  
  public zzzg zzyu()
    throws CloneNotSupportedException
  {
    return (zzzg)super.clone();
  }
  
  public final int zzza()
  {
    if (zzcfm < 0) {
      zzvu();
    }
    return zzcfm;
  }
}
