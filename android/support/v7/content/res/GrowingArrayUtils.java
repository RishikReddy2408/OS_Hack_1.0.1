package android.support.v7.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils
{
  private GrowingArrayUtils() {}
  
  public static int[] append(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = paramArrayOfInt;
    if (paramInt1 + 1 > paramArrayOfInt.length)
    {
      arrayOfInt = new int[growSize(paramInt1)];
      System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramInt1);
    }
    arrayOfInt[paramInt1] = paramInt2;
    return arrayOfInt;
  }
  
  public static long[] append(long[] paramArrayOfLong, int paramInt, long paramLong)
  {
    long[] arrayOfLong = paramArrayOfLong;
    if (paramInt + 1 > paramArrayOfLong.length)
    {
      arrayOfLong = new long[growSize(paramInt)];
      System.arraycopy(paramArrayOfLong, 0, arrayOfLong, 0, paramInt);
    }
    arrayOfLong[paramInt] = paramLong;
    return arrayOfLong;
  }
  
  public static Object[] append(Object[] paramArrayOfObject, int paramInt, Object paramObject)
  {
    Object[] arrayOfObject = paramArrayOfObject;
    if (paramInt + 1 > paramArrayOfObject.length)
    {
      arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), growSize(paramInt));
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramInt);
    }
    arrayOfObject[paramInt] = paramObject;
    return arrayOfObject;
  }
  
  public static boolean[] append(boolean[] paramArrayOfBoolean, int paramInt, boolean paramBoolean)
  {
    boolean[] arrayOfBoolean = paramArrayOfBoolean;
    if (paramInt + 1 > paramArrayOfBoolean.length)
    {
      arrayOfBoolean = new boolean[growSize(paramInt)];
      System.arraycopy(paramArrayOfBoolean, 0, arrayOfBoolean, 0, paramInt);
    }
    arrayOfBoolean[paramInt] = paramBoolean;
    return arrayOfBoolean;
  }
  
  public static int growSize(int paramInt)
  {
    if (paramInt <= 4) {
      return 8;
    }
    return paramInt * 2;
  }
  
  public static int[] insert(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 + 1 <= paramArrayOfInt.length)
    {
      System.arraycopy(paramArrayOfInt, paramInt2, paramArrayOfInt, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfInt[paramInt2] = paramInt3;
      return paramArrayOfInt;
    }
    int[] arrayOfInt = new int[growSize(paramInt1)];
    System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramInt2);
    arrayOfInt[paramInt2] = paramInt3;
    System.arraycopy(paramArrayOfInt, paramInt2, arrayOfInt, paramInt2 + 1, paramArrayOfInt.length - paramInt2);
    return arrayOfInt;
  }
  
  public static long[] insert(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramInt1 + 1 <= paramArrayOfLong.length)
    {
      System.arraycopy(paramArrayOfLong, paramInt2, paramArrayOfLong, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfLong[paramInt2] = paramLong;
      return paramArrayOfLong;
    }
    long[] arrayOfLong = new long[growSize(paramInt1)];
    System.arraycopy(paramArrayOfLong, 0, arrayOfLong, 0, paramInt2);
    arrayOfLong[paramInt2] = paramLong;
    System.arraycopy(paramArrayOfLong, paramInt2, arrayOfLong, paramInt2 + 1, paramArrayOfLong.length - paramInt2);
    return arrayOfLong;
  }
  
  public static Object[] insert(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject)
  {
    if (paramInt1 + 1 <= paramArrayOfObject.length)
    {
      System.arraycopy(paramArrayOfObject, paramInt2, paramArrayOfObject, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfObject[paramInt2] = paramObject;
      return paramArrayOfObject;
    }
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), growSize(paramInt1));
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramInt2);
    arrayOfObject[paramInt2] = paramObject;
    System.arraycopy(paramArrayOfObject, paramInt2, arrayOfObject, paramInt2 + 1, paramArrayOfObject.length - paramInt2);
    return arrayOfObject;
  }
  
  public static boolean[] insert(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt1 + 1 <= paramArrayOfBoolean.length)
    {
      System.arraycopy(paramArrayOfBoolean, paramInt2, paramArrayOfBoolean, paramInt2 + 1, paramInt1 - paramInt2);
      paramArrayOfBoolean[paramInt2] = paramBoolean;
      return paramArrayOfBoolean;
    }
    boolean[] arrayOfBoolean = new boolean[growSize(paramInt1)];
    System.arraycopy(paramArrayOfBoolean, 0, arrayOfBoolean, 0, paramInt2);
    arrayOfBoolean[paramInt2] = paramBoolean;
    System.arraycopy(paramArrayOfBoolean, paramInt2, arrayOfBoolean, paramInt2 + 1, paramArrayOfBoolean.length - paramInt2);
    return arrayOfBoolean;
  }
}
