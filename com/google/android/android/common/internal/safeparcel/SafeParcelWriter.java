package com.google.android.android.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter
{
  private SafeParcelWriter() {}
  
  public static int beginObjectHeader(Parcel paramParcel)
  {
    return writeValue(paramParcel, 20293);
  }
  
  public static void finishObjectHeader(Parcel paramParcel, int paramInt)
  {
    tableSwitch(paramParcel, paramInt);
  }
  
  private static void marshall(Parcel paramParcel, Parcelable paramParcelable, int paramInt)
  {
    int i = paramParcel.dataPosition();
    paramParcel.writeInt(1);
    int j = paramParcel.dataPosition();
    paramParcelable.writeToParcel(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - j);
    paramParcel.setDataPosition(paramInt);
  }
  
  private static void tableSwitch(Parcel paramParcel, int paramInt)
  {
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt - 4);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
  
  public static void writeBigDecimal(Parcel paramParcel, int paramInt, BigDecimal paramBigDecimal, boolean paramBoolean)
  {
    if (paramBigDecimal == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeByteArray(paramBigDecimal.unscaledValue().toByteArray());
      paramParcel.writeInt(paramBigDecimal.scale());
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeBigDecimalArray(Parcel paramParcel, int paramInt, BigDecimal[] paramArrayOfBigDecimal, boolean paramBoolean)
  {
    int i = 0;
    if (paramArrayOfBigDecimal == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramArrayOfBigDecimal.length;
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeByteArray(paramArrayOfBigDecimal[paramInt].unscaledValue().toByteArray());
        paramParcel.writeInt(paramArrayOfBigDecimal[paramInt].scale());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeBigInteger(Parcel paramParcel, int paramInt, BigInteger paramBigInteger, boolean paramBoolean)
  {
    if (paramBigInteger == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeByteArray(paramBigInteger.toByteArray());
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeBigIntegerArray(Parcel paramParcel, int paramInt, BigInteger[] paramArrayOfBigInteger, boolean paramBoolean)
  {
    int i = 0;
    if (paramArrayOfBigInteger == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramArrayOfBigInteger.length;
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeByteArray(paramArrayOfBigInteger[paramInt].toByteArray());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeBoolean(Parcel paramParcel, int paramInt, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void writeBooleanArray(Parcel paramParcel, int paramInt, boolean[] paramArrayOfBoolean, boolean paramBoolean)
  {
    if (paramArrayOfBoolean == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeBooleanArray(paramArrayOfBoolean);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeBooleanList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void writeBooleanObject(Parcel paramParcel, int paramInt, Boolean paramBoolean, boolean paramBoolean1)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void writeBundle(Parcel paramParcel, int paramInt, Bundle paramBundle, boolean paramBoolean)
  {
    if (paramBundle == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeBundle(paramBundle);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeByte(Parcel paramParcel, int paramInt, byte paramByte)
  {
    writeByte(paramParcel, paramInt, 4);
    paramParcel.writeInt(paramByte);
  }
  
  private static void writeByte(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 65535)
    {
      paramParcel.writeInt(paramInt1 | 0xFFFF0000);
      paramParcel.writeInt(paramInt2);
      return;
    }
    paramParcel.writeInt(paramInt1 | paramInt2 << 16);
  }
  
  public static void writeByteArray(Parcel paramParcel, int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramArrayOfByte == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeByteArray(paramArrayOfByte);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeByteArrayArray(Parcel paramParcel, int paramInt, byte[][] paramArrayOfByte, boolean paramBoolean)
  {
    int i = 0;
    if (paramArrayOfByte == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramArrayOfByte.length;
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeByteArray(paramArrayOfByte[paramInt]);
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeByteArraySparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        paramParcel.writeByteArray((byte[])paramSparseArray.valueAt(paramInt));
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeChar(Parcel paramParcel, int paramInt, char paramChar)
  {
    writeByte(paramParcel, paramInt, 4);
    paramParcel.writeInt(paramChar);
  }
  
  public static void writeCharArray(Parcel paramParcel, int paramInt, char[] paramArrayOfChar, boolean paramBoolean)
  {
    if (paramArrayOfChar == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeCharArray(paramArrayOfChar);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeDouble(Parcel paramParcel, int paramInt, double paramDouble)
  {
    writeByte(paramParcel, paramInt, 8);
    paramParcel.writeDouble(paramDouble);
  }
  
  public static void writeDoubleArray(Parcel paramParcel, int paramInt, double[] paramArrayOfDouble, boolean paramBoolean)
  {
    if (paramArrayOfDouble == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeDoubleArray(paramArrayOfDouble);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeDoubleList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    int i = 0;
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramList.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeDouble(((Double)paramList.get(paramInt)).doubleValue());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeDoubleObject(Parcel paramParcel, int paramInt, Double paramDouble, boolean paramBoolean)
  {
    if (paramDouble == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      writeByte(paramParcel, paramInt, 8);
      paramParcel.writeDouble(paramDouble.doubleValue());
    }
  }
  
  public static void writeDoubleSparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        paramParcel.writeDouble(((Double)paramSparseArray.valueAt(paramInt)).doubleValue());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeFloat(Parcel paramParcel, int paramInt, float paramFloat)
  {
    writeByte(paramParcel, paramInt, 4);
    paramParcel.writeFloat(paramFloat);
  }
  
  public static void writeFloatArray(Parcel paramParcel, int paramInt, float[] paramArrayOfFloat, boolean paramBoolean)
  {
    if (paramArrayOfFloat == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeFloatArray(paramArrayOfFloat);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeFloatList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    int i = 0;
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramList.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeFloat(((Float)paramList.get(paramInt)).floatValue());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeFloatObject(Parcel paramParcel, int paramInt, Float paramFloat, boolean paramBoolean)
  {
    if (paramFloat == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      writeByte(paramParcel, paramInt, 4);
      paramParcel.writeFloat(paramFloat.floatValue());
    }
  }
  
  public static void writeFloatSparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        paramParcel.writeFloat(((Float)paramSparseArray.valueAt(paramInt)).floatValue());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeIBinder(Parcel paramParcel, int paramInt, IBinder paramIBinder, boolean paramBoolean)
  {
    if (paramIBinder == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeStrongBinder(paramIBinder);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeIBinderArray(Parcel paramParcel, int paramInt, IBinder[] paramArrayOfIBinder, boolean paramBoolean)
  {
    if (paramArrayOfIBinder == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeBinderArray(paramArrayOfIBinder);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeIBinderList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeBinderList(paramList);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeIBinderSparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        paramParcel.writeStrongBinder((IBinder)paramSparseArray.valueAt(paramInt));
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeInt(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    writeByte(paramParcel, paramInt1, 4);
    paramParcel.writeInt(paramInt2);
  }
  
  public static void writeIntArray(Parcel paramParcel, int paramInt, int[] paramArrayOfInt, boolean paramBoolean)
  {
    if (paramArrayOfInt == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeIntArray(paramArrayOfInt);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeIntegerList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    int i = 0;
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramList.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeIntegerObject(Parcel paramParcel, int paramInt, Integer paramInteger, boolean paramBoolean)
  {
    if (paramInteger == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      writeByte(paramParcel, paramInt, 4);
      paramParcel.writeInt(paramInteger.intValue());
    }
  }
  
  public static void writeList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeList(paramList);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeLong(Parcel paramParcel, int paramInt, long paramLong)
  {
    writeByte(paramParcel, paramInt, 8);
    paramParcel.writeLong(paramLong);
  }
  
  public static void writeLongArray(Parcel paramParcel, int paramInt, long[] paramArrayOfLong, boolean paramBoolean)
  {
    if (paramArrayOfLong == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeLongArray(paramArrayOfLong);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeLongList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    int i = 0;
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramList.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeLong(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeLongObject(Parcel paramParcel, int paramInt, Long paramLong, boolean paramBoolean)
  {
    if (paramLong == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      writeByte(paramParcel, paramInt, 8);
      paramParcel.writeLong(paramLong.longValue());
    }
  }
  
  public static void writeParcel(Parcel paramParcel1, int paramInt, Parcel paramParcel2, boolean paramBoolean)
  {
    if (paramParcel2 == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel1, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel1, paramInt);
      paramParcel1.appendFrom(paramParcel2, 0, paramParcel2.dataSize());
      tableSwitch(paramParcel1, paramInt);
    }
  }
  
  public static void writeParcelArray(Parcel paramParcel, int paramInt, Parcel[] paramArrayOfParcel, boolean paramBoolean)
  {
    if (paramArrayOfParcel == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int i = writeValue(paramParcel, paramInt);
      int j = paramArrayOfParcel.length;
      paramParcel.writeInt(j);
      paramInt = 0;
      while (paramInt < j)
      {
        Parcel localParcel = paramArrayOfParcel[paramInt];
        if (localParcel != null)
        {
          paramParcel.writeInt(localParcel.dataSize());
          paramParcel.appendFrom(localParcel, 0, localParcel.dataSize());
        }
        else
        {
          paramParcel.writeInt(0);
        }
        paramInt += 1;
      }
      tableSwitch(paramParcel, i);
    }
  }
  
  public static void writeParcelList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int i = writeValue(paramParcel, paramInt);
      int j = paramList.size();
      paramParcel.writeInt(j);
      paramInt = 0;
      while (paramInt < j)
      {
        Parcel localParcel = (Parcel)paramList.get(paramInt);
        if (localParcel != null)
        {
          paramParcel.writeInt(localParcel.dataSize());
          paramParcel.appendFrom(localParcel, 0, localParcel.dataSize());
        }
        else
        {
          paramParcel.writeInt(0);
        }
        paramInt += 1;
      }
      tableSwitch(paramParcel, i);
    }
  }
  
  public static void writeParcelSparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int i = writeValue(paramParcel, paramInt);
      int j = paramSparseArray.size();
      paramParcel.writeInt(j);
      paramInt = 0;
      while (paramInt < j)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        Parcel localParcel = (Parcel)paramSparseArray.valueAt(paramInt);
        if (localParcel != null)
        {
          paramParcel.writeInt(localParcel.dataSize());
          paramParcel.appendFrom(localParcel, 0, localParcel.dataSize());
        }
        else
        {
          paramParcel.writeInt(0);
        }
        paramInt += 1;
      }
      tableSwitch(paramParcel, i);
    }
  }
  
  public static void writeParcelable(Parcel paramParcel, int paramInt1, Parcelable paramParcelable, int paramInt2, boolean paramBoolean)
  {
    if (paramParcelable == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt1, 0);
      }
    }
    else
    {
      paramInt1 = writeValue(paramParcel, paramInt1);
      paramParcelable.writeToParcel(paramParcel, paramInt2);
      tableSwitch(paramParcel, paramInt1);
    }
  }
  
  public static void writeShort(Parcel paramParcel, int paramInt, short paramShort)
  {
    writeByte(paramParcel, paramInt, 4);
    paramParcel.writeInt(paramShort);
  }
  
  public static void writeSparseBooleanArray(Parcel paramParcel, int paramInt, SparseBooleanArray paramSparseBooleanArray, boolean paramBoolean)
  {
    if (paramSparseBooleanArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeSparseBooleanArray(paramSparseBooleanArray);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeSparseIntArray(Parcel paramParcel, int paramInt, SparseIntArray paramSparseIntArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseIntArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseIntArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseIntArray.keyAt(paramInt));
        paramParcel.writeInt(paramSparseIntArray.valueAt(paramInt));
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeSparseLongArray(Parcel paramParcel, int paramInt, SparseLongArray paramSparseLongArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseLongArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseLongArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseLongArray.keyAt(paramInt));
        paramParcel.writeLong(paramSparseLongArray.valueAt(paramInt));
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeString(Parcel paramParcel, int paramInt, String paramString, boolean paramBoolean)
  {
    if (paramString == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeString(paramString);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeStringArray(Parcel paramParcel, int paramInt, String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramArrayOfString == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeStringArray(paramArrayOfString);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeStringList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      paramInt = writeValue(paramParcel, paramInt);
      paramParcel.writeStringList(paramList);
      tableSwitch(paramParcel, paramInt);
    }
  }
  
  public static void writeStringSparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int i = 0;
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int j = writeValue(paramParcel, paramInt);
      int k = paramSparseArray.size();
      paramParcel.writeInt(k);
      paramInt = i;
      while (paramInt < k)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        paramParcel.writeString((String)paramSparseArray.valueAt(paramInt));
        paramInt += 1;
      }
      tableSwitch(paramParcel, j);
    }
  }
  
  public static void writeTypedArray(Parcel paramParcel, int paramInt1, Parcelable[] paramArrayOfParcelable, int paramInt2, boolean paramBoolean)
  {
    if (paramArrayOfParcelable == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt1, 0);
      }
    }
    else
    {
      int i = writeValue(paramParcel, paramInt1);
      int j = paramArrayOfParcelable.length;
      paramParcel.writeInt(j);
      paramInt1 = 0;
      while (paramInt1 < j)
      {
        Parcelable localParcelable = paramArrayOfParcelable[paramInt1];
        if (localParcelable == null) {
          paramParcel.writeInt(0);
        } else {
          marshall(paramParcel, localParcelable, paramInt2);
        }
        paramInt1 += 1;
      }
      tableSwitch(paramParcel, i);
    }
  }
  
  public static void writeTypedList(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int i = writeValue(paramParcel, paramInt);
      int j = paramList.size();
      paramParcel.writeInt(j);
      paramInt = 0;
      while (paramInt < j)
      {
        Parcelable localParcelable = (Parcelable)paramList.get(paramInt);
        if (localParcelable == null) {
          paramParcel.writeInt(0);
        } else {
          marshall(paramParcel, localParcelable, 0);
        }
        paramInt += 1;
      }
      tableSwitch(paramParcel, i);
    }
  }
  
  public static void writeTypedSparseArray(Parcel paramParcel, int paramInt, SparseArray paramSparseArray, boolean paramBoolean)
  {
    if (paramSparseArray == null)
    {
      if (paramBoolean) {
        writeByte(paramParcel, paramInt, 0);
      }
    }
    else
    {
      int i = writeValue(paramParcel, paramInt);
      int j = paramSparseArray.size();
      paramParcel.writeInt(j);
      paramInt = 0;
      while (paramInt < j)
      {
        paramParcel.writeInt(paramSparseArray.keyAt(paramInt));
        Parcelable localParcelable = (Parcelable)paramSparseArray.valueAt(paramInt);
        if (localParcelable == null) {
          paramParcel.writeInt(0);
        } else {
          marshall(paramParcel, localParcelable, 0);
        }
        paramInt += 1;
      }
      tableSwitch(paramParcel, i);
    }
  }
  
  private static int writeValue(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(paramInt | 0xFFFF0000);
    paramParcel.writeInt(0);
    return paramParcel.dataPosition();
  }
}
