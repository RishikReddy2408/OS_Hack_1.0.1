package com.google.android.android.internal.measurement;

final class zzuh
  extends zzum
{
  private final int zzbud;
  private final int zzbue;
  
  zzuh(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramArrayOfByte);
    zzud.append(paramInt1, paramInt1 + paramInt2, paramArrayOfByte.length);
    zzbud = paramInt1;
    zzbue = paramInt2;
  }
  
  public final int size()
  {
    return zzbue;
  }
  
  public final byte zzal(int paramInt)
  {
    int i = size();
    if ((i - (paramInt + 1) | paramInt) < 0)
    {
      if (paramInt < 0)
      {
        localStringBuilder = new StringBuilder(22);
        localStringBuilder.append("Index < 0: ");
        localStringBuilder.append(paramInt);
        throw new ArrayIndexOutOfBoundsException(localStringBuilder.toString());
      }
      StringBuilder localStringBuilder = new StringBuilder(40);
      localStringBuilder.append("Index > length: ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(", ");
      localStringBuilder.append(i);
      throw new ArrayIndexOutOfBoundsException(localStringBuilder.toString());
    }
    return zzbug[(zzbud + paramInt)];
  }
  
  protected final int zzud()
  {
    return zzbud;
  }
}
