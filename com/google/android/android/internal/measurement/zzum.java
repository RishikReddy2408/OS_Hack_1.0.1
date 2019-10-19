package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

class zzum
  extends zzul
{
  protected final byte[] zzbug;
  
  zzum(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      zzbug = paramArrayOfByte;
      return;
    }
    throw new NullPointerException();
  }
  
  protected final int computeHashCode(int paramInt1, int paramInt2, int paramInt3)
  {
    return zzvo.hashCode(paramInt1, zzbug, zzud(), paramInt3);
  }
  
  final void d(zzuc paramZzuc)
    throws IOException
  {
    paramZzuc.append(zzbug, zzud(), size());
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzud)) {
      return false;
    }
    if (size() != ((zzud)paramObject).size()) {
      return false;
    }
    if (size() == 0) {
      return true;
    }
    if ((paramObject instanceof zzum))
    {
      paramObject = (zzum)paramObject;
      int i = zzuc();
      int j = paramObject.zzuc();
      if ((i != 0) && (j != 0) && (i != j)) {
        return false;
      }
      return matches(paramObject, 0, size());
    }
    return paramObject.equals(this);
  }
  
  public final zzud getAttributeName(int paramInt1, int paramInt2)
  {
    paramInt1 = zzud.append(0, paramInt2, size());
    if (paramInt1 == 0) {
      return zzud.zzbtz;
    }
    return new zzuh(zzbug, zzud(), paramInt1);
  }
  
  final boolean matches(zzud paramZzud, int paramInt1, int paramInt2)
  {
    if (paramInt2 <= paramZzud.size())
    {
      if (paramInt2 <= paramZzud.size())
      {
        if ((paramZzud instanceof zzum))
        {
          paramZzud = (zzum)paramZzud;
          byte[] arrayOfByte1 = zzbug;
          byte[] arrayOfByte2 = zzbug;
          int j = zzud();
          int i = zzud();
          paramInt1 = paramZzud.zzud();
          while (i < j + paramInt2)
          {
            if (arrayOfByte1[i] != arrayOfByte2[paramInt1]) {
              return false;
            }
            i += 1;
            paramInt1 += 1;
          }
          return true;
        }
        return paramZzud.getAttributeName(0, paramInt2).equals(getAttributeName(0, paramInt2));
      }
      paramInt1 = paramZzud.size();
      paramZzud = new StringBuilder(59);
      paramZzud.append("Ran off end of other: 0, ");
      paramZzud.append(paramInt2);
      paramZzud.append(", ");
      paramZzud.append(paramInt1);
      throw new IllegalArgumentException(paramZzud.toString());
    }
    paramInt1 = size();
    paramZzud = new StringBuilder(40);
    paramZzud.append("Length too large: ");
    paramZzud.append(paramInt2);
    paramZzud.append(paramInt1);
    throw new IllegalArgumentException(paramZzud.toString());
  }
  
  public int size()
  {
    return zzbug.length;
  }
  
  protected final String writeText(Charset paramCharset)
  {
    return new String(zzbug, zzud(), size(), paramCharset);
  }
  
  public byte zzal(int paramInt)
  {
    return zzbug[paramInt];
  }
  
  public final boolean zzub()
  {
    int i = zzud();
    return zzyj.processBytes(zzbug, i, size() + i);
  }
  
  protected int zzud()
  {
    return 0;
  }
}
