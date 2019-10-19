package com.google.android.android.common.images;

public final class Size
{
  private final int zand;
  private final int zane;
  
  public Size(int paramInt1, int paramInt2)
  {
    zand = paramInt1;
    zane = paramInt2;
  }
  
  private static NumberFormatException parseLong(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 16);
    localStringBuilder.append("Invalid Size: \"");
    localStringBuilder.append(paramString);
    localStringBuilder.append("\"");
    throw new NumberFormatException(localStringBuilder.toString());
  }
  
  public static Size parseSize(String paramString)
    throws NumberFormatException
  {
    int j;
    int i;
    if (paramString != null)
    {
      j = paramString.indexOf('*');
      i = j;
      if (j < 0) {
        i = paramString.indexOf('x');
      }
      if (i < 0) {}
    }
    try
    {
      j = Integer.parseInt(paramString.substring(0, i));
      Size localSize = new Size(j, Integer.parseInt(paramString.substring(i + 1)));
      return localSize;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    throw parseLong(paramString);
    throw parseLong(paramString);
    throw new IllegalArgumentException("string must not be null");
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof Size))
    {
      paramObject = (Size)paramObject;
      if ((zand == zand) && (zane == zane)) {
        return true;
      }
    }
    return false;
  }
  
  public final int getHeight()
  {
    return zane;
  }
  
  public final int getWidth()
  {
    return zand;
  }
  
  public final int hashCode()
  {
    return zane ^ (zand << 16 | zand >>> 16);
  }
  
  public final String toString()
  {
    int i = zand;
    int j = zane;
    StringBuilder localStringBuilder = new StringBuilder(23);
    localStringBuilder.append(i);
    localStringBuilder.append("x");
    localStringBuilder.append(j);
    return localStringBuilder.toString();
  }
}
