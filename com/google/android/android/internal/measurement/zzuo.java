package com.google.android.android.internal.measurement;

import java.io.IOException;

public abstract class zzuo
{
  int zzbuh;
  int zzbui = 100;
  private int zzbuj = Integer.MAX_VALUE;
  zzur zzbuk;
  private boolean zzbul = false;
  
  private zzuo() {}
  
  public static zzuo readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return readFully(paramArrayOfByte, paramInt1, paramInt2, false);
  }
  
  static zzuo readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramArrayOfByte = new zzuq(paramArrayOfByte, paramInt1, paramInt2, false, null);
    try
    {
      paramArrayOfByte.zzaq(paramInt2);
      return paramArrayOfByte;
    }
    catch (zzvt paramArrayOfByte)
    {
      throw new IllegalArgumentException(paramArrayOfByte);
    }
  }
  
  public abstract zzwt blur(zzxd paramZzxd, zzuz paramZzuz)
    throws IOException;
  
  public abstract double readDouble()
    throws IOException;
  
  public abstract float readFloat()
    throws IOException;
  
  public abstract String readString()
    throws IOException;
  
  public abstract void zzan(int paramInt)
    throws zzvt;
  
  public abstract boolean zzao(int paramInt)
    throws IOException;
  
  public final int zzap(int paramInt)
  {
    if (paramInt >= 0)
    {
      int i = zzbui;
      zzbui = paramInt;
      return i;
    }
    StringBuilder localStringBuilder = new StringBuilder(47);
    localStringBuilder.append("Recursion limit cannot be negative: ");
    localStringBuilder.append(paramInt);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public abstract int zzaq(int paramInt)
    throws zzvt;
  
  public abstract void zzar(int paramInt);
  
  public abstract void zzas(int paramInt)
    throws IOException;
  
  public abstract int zzug()
    throws IOException;
  
  public abstract long zzuh()
    throws IOException;
  
  public abstract long zzui()
    throws IOException;
  
  public abstract int zzuj()
    throws IOException;
  
  public abstract long zzuk()
    throws IOException;
  
  public abstract int zzul()
    throws IOException;
  
  public abstract boolean zzum()
    throws IOException;
  
  public abstract String zzun()
    throws IOException;
  
  public abstract zzud zzuo()
    throws IOException;
  
  public abstract int zzup()
    throws IOException;
  
  public abstract int zzuq()
    throws IOException;
  
  public abstract int zzur()
    throws IOException;
  
  public abstract long zzus()
    throws IOException;
  
  public abstract int zzut()
    throws IOException;
  
  public abstract long zzuu()
    throws IOException;
  
  abstract long zzuv()
    throws IOException;
  
  public abstract boolean zzuw()
    throws IOException;
  
  public abstract int zzux();
}
