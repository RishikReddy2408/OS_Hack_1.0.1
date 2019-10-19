package com.google.android.android.internal.measurement;

final class zzyk
{
  private static void add(byte paramByte1, byte paramByte2, char[] paramArrayOfChar, int paramInt)
    throws zzvt
  {
    if ((paramByte1 >= -62) && (!update(paramByte2)))
    {
      paramArrayOfChar[paramInt] = ((char)((paramByte1 & 0x1F) << 6 | paramByte2 & 0x3F));
      return;
    }
    throw zzvt.zzwr();
  }
  
  private static void decode(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, char[] paramArrayOfChar, int paramInt)
    throws zzvt
  {
    if ((!update(paramByte2)) && ((paramByte1 << 28) + (paramByte2 + 112) >> 30 == 0) && (!update(paramByte3)) && (!update(paramByte4)))
    {
      paramByte1 = (paramByte1 & 0x7) << 18 | (paramByte2 & 0x3F) << 12 | (paramByte3 & 0x3F) << 6 | paramByte4 & 0x3F;
      paramArrayOfChar[paramInt] = ((char)((paramByte1 >>> 10) + 55232));
      paramArrayOfChar[(paramInt + 1)] = ((char)((paramByte1 & 0x3FF) + 56320));
      return;
    }
    throw zzvt.zzwr();
  }
  
  private static void doFinal(byte paramByte1, byte paramByte2, byte paramByte3, char[] paramArrayOfChar, int paramInt)
    throws zzvt
  {
    if ((!update(paramByte2)) && ((paramByte1 != -32) || (paramByte2 >= -96)) && ((paramByte1 != -19) || (paramByte2 < -96)) && (!update(paramByte3)))
    {
      paramArrayOfChar[paramInt] = ((char)((paramByte1 & 0xF) << 12 | (paramByte2 & 0x3F) << 6 | paramByte3 & 0x3F));
      return;
    }
    throw zzvt.zzwr();
  }
  
  private static boolean get(byte paramByte)
  {
    return paramByte >= 0;
  }
  
  private static boolean index(byte paramByte)
  {
    return paramByte < -32;
  }
  
  private static boolean process(byte paramByte)
  {
    return paramByte < -16;
  }
  
  private static void splice(byte paramByte, char[] paramArrayOfChar, int paramInt)
  {
    paramArrayOfChar[paramInt] = ((char)paramByte);
  }
  
  private static boolean update(byte paramByte)
  {
    return paramByte > -65;
  }
}
