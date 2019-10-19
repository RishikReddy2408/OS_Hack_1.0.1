package com.google.android.android.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzyh
{
  private static final Logger logger;
  private static final Class<?> zzbtv;
  private static final boolean zzbuv;
  private static final Unsafe zzcay;
  private static final boolean zzccv;
  private static final boolean zzccw;
  private static final zzd zzccx;
  private static final boolean zzccy;
  private static final long zzccz;
  private static final long zzcda;
  private static final long zzcdb;
  private static final long zzcdc;
  private static final long zzcdd;
  private static final long zzcde;
  private static final long zzcdf;
  private static final long zzcdg;
  private static final long zzcdh;
  private static final long zzcdi;
  private static final long zzcdj;
  private static final long zzcdk;
  private static final long zzcdl;
  private static final long zzcdm;
  private static final boolean zzcdn;
  
  static
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a3 = a2\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  private zzyh() {}
  
  static double a(Object paramObject, long paramLong)
  {
    return zzccx.readDouble(paramObject, paramLong);
  }
  
  static void a(Object paramObject, long paramLong, int paramInt)
  {
    zzccx.writeInt(paramObject, paramLong, paramInt);
  }
  
  private static byte add(Object paramObject, long paramLong)
  {
    return (byte)(getValue(paramObject, 0xFFFFFFFFFFFFFFFC & paramLong) >>> (int)((paramLong & 0x3) << 3));
  }
  
  private static void add(Object paramObject, long paramLong, byte paramByte)
  {
    long l = 0xFFFFFFFFFFFFFFFC & paramLong;
    int i = getValue(paramObject, l);
    int j = ((int)paramLong & 0x3) << 3;
    a(paramObject, l, (0xFF & paramByte) << j | i & 255 << j);
  }
  
  static void add(Object paramObject1, long paramLong, Object paramObject2)
  {
    zzccxzzcdo.putObject(paramObject1, paramLong, paramObject2);
  }
  
  static void append(Object paramObject, long paramLong, double paramDouble)
  {
    zzccx.writeDouble(paramObject, paramLong, paramDouble);
  }
  
  static void append(Object paramObject, long paramLong, float paramFloat)
  {
    zzccx.writeFloat(paramObject, paramLong, paramFloat);
  }
  
  private static void append(Object paramObject, long paramLong, boolean paramBoolean)
  {
    add(paramObject, paramLong, (byte)paramBoolean);
  }
  
  static void close(Object paramObject, long paramLong1, long paramLong2)
  {
    zzccx.writeLong(paramObject, paramLong1, paramLong2);
  }
  
  private static boolean containsKey(Object paramObject, long paramLong)
  {
    return toString(paramObject, paramLong) != 0;
  }
  
  private static void debug(Object paramObject, long paramLong, boolean paramBoolean)
  {
    write(paramObject, paramLong, (byte)paramBoolean);
  }
  
  private static boolean f(Object paramObject, long paramLong)
  {
    return add(paramObject, paramLong) != 0;
  }
  
  private static Field find(Class paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      return paramClass;
    }
    catch (Throwable paramClass)
    {
      for (;;) {}
    }
    return null;
  }
  
  static Object get(Object paramObject, long paramLong)
  {
    return zzccxzzcdo.getObject(paramObject, paramLong);
  }
  
  private static boolean get(Class paramClass)
  {
    if (!zzua.zzty()) {
      return false;
    }
    try
    {
      Class localClass = zzbtv;
      localClass.getMethod("peekLong", new Class[] { paramClass, Boolean.TYPE });
      localClass.getMethod("pokeLong", new Class[] { paramClass, Long.TYPE, Boolean.TYPE });
      localClass.getMethod("pokeInt", new Class[] { paramClass, Integer.TYPE, Boolean.TYPE });
      localClass.getMethod("peekInt", new Class[] { paramClass, Boolean.TYPE });
      localClass.getMethod("pokeByte", new Class[] { paramClass, Byte.TYPE });
      localClass.getMethod("peekByte", new Class[] { paramClass });
      localClass.getMethod("pokeByteArray", new Class[] { paramClass, [B.class, Integer.TYPE, Integer.TYPE });
      localClass.getMethod("peekByteArray", new Class[] { paramClass, [B.class, Integer.TYPE, Integer.TYPE });
      return true;
    }
    catch (Throwable paramClass) {}
    return false;
  }
  
  private static int getField(Class paramClass)
  {
    if (zzbuv) {
      return zzccxzzcdo.arrayBaseOffset(paramClass);
    }
    return -1;
  }
  
  static long getId(Object paramObject, long paramLong)
  {
    return zzccx.readLong(paramObject, paramLong);
  }
  
  static boolean getName(Object paramObject, long paramLong)
  {
    return zzccx.get(paramObject, paramLong);
  }
  
  static int getValue(Object paramObject, long paramLong)
  {
    return zzccx.getInt(paramObject, paramLong);
  }
  
  static float invoke(Object paramObject, long paramLong)
  {
    return zzccx.readFloat(paramObject, paramLong);
  }
  
  static long position(ByteBuffer paramByteBuffer)
  {
    return zzccx.readLong(paramByteBuffer, zzcdm);
  }
  
  static byte read(byte[] paramArrayOfByte, long paramLong)
  {
    return zzccx.readByte(paramArrayOfByte, zzccz + paramLong);
  }
  
  private static int read(Class paramClass)
  {
    if (zzbuv) {
      return zzccxzzcdo.arrayIndexScale(paramClass);
    }
    return -1;
  }
  
  static void read(Object paramObject, long paramLong, boolean paramBoolean)
  {
    zzccx.setBytes(paramObject, paramLong, paramBoolean);
  }
  
  static void setBytes(byte[] paramArrayOfByte, long paramLong, byte paramByte)
  {
    zzccx.setBytes(paramArrayOfByte, zzccz + paramLong, paramByte);
  }
  
  static void toHex(long paramLong, byte paramByte)
  {
    zzccx.writeLong(paramLong, paramByte);
  }
  
  private static byte toString(Object paramObject, long paramLong)
  {
    return (byte)(getValue(paramObject, 0xFFFFFFFFFFFFFFFC & paramLong) >>> (int)((paramLong & 0x3) << 3));
  }
  
  private static void write(Object paramObject, long paramLong, byte paramByte)
  {
    long l = 0xFFFFFFFFFFFFFFFC & paramLong;
    int i = getValue(paramObject, l);
    int j = ((int)paramLong & 0x3) << 3;
    a(paramObject, l, (0xFF & paramByte) << j | i & 255 << j);
  }
  
  static void writeFile(byte[] paramArrayOfByte, long paramLong1, long paramLong2, long paramLong3)
  {
    zzccx.writeLong(paramArrayOfByte, paramLong1, paramLong2, paramLong3);
  }
  
  static boolean zzyi()
  {
    return zzbuv;
  }
  
  static boolean zzyj()
  {
    return zzccy;
  }
  
  static Unsafe zzyk()
  {
    try
    {
      Unsafe localUnsafe = (Unsafe)AccessController.doPrivileged(new zzyi());
      return localUnsafe;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static boolean zzyl()
  {
    if (zzcay == null) {
      return false;
    }
    try
    {
      localObject = zzcay.getClass();
      ((Class)localObject).getMethod("objectFieldOffset", new Class[] { Field.class });
      ((Class)localObject).getMethod("arrayBaseOffset", new Class[] { Class.class });
      ((Class)localObject).getMethod("arrayIndexScale", new Class[] { Class.class });
      ((Class)localObject).getMethod("getInt", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putInt", new Class[] { Object.class, Long.TYPE, Integer.TYPE });
      ((Class)localObject).getMethod("getLong", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putLong", new Class[] { Object.class, Long.TYPE, Long.TYPE });
      ((Class)localObject).getMethod("getObject", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putObject", new Class[] { Object.class, Long.TYPE, Object.class });
      boolean bool = zzua.zzty();
      if (bool) {
        return true;
      }
      ((Class)localObject).getMethod("getByte", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putByte", new Class[] { Object.class, Long.TYPE, Byte.TYPE });
      ((Class)localObject).getMethod("getBoolean", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putBoolean", new Class[] { Object.class, Long.TYPE, Boolean.TYPE });
      ((Class)localObject).getMethod("getFloat", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putFloat", new Class[] { Object.class, Long.TYPE, Float.TYPE });
      ((Class)localObject).getMethod("getDouble", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putDouble", new Class[] { Object.class, Long.TYPE, Double.TYPE });
      return true;
    }
    catch (Throwable localThrowable)
    {
      Object localObject = logger;
      Level localLevel = Level.WARNING;
      String str = String.valueOf(localThrowable);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 71);
      localStringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      localStringBuilder.append(str);
      ((Logger)localObject).logp(localLevel, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", localStringBuilder.toString());
    }
    return false;
  }
  
  private static boolean zzym()
  {
    if (zzcay == null) {
      return false;
    }
    try
    {
      localObject1 = zzcay.getClass();
      ((Class)localObject1).getMethod("objectFieldOffset", new Class[] { Field.class });
      ((Class)localObject1).getMethod("getLong", new Class[] { Object.class, Long.TYPE });
      localObject2 = zzyn();
      if (localObject2 == null) {
        return false;
      }
      boolean bool = zzua.zzty();
      if (bool) {
        return true;
      }
      ((Class)localObject1).getMethod("getByte", new Class[] { Long.TYPE });
      ((Class)localObject1).getMethod("putByte", new Class[] { Long.TYPE, Byte.TYPE });
      ((Class)localObject1).getMethod("getInt", new Class[] { Long.TYPE });
      ((Class)localObject1).getMethod("putInt", new Class[] { Long.TYPE, Integer.TYPE });
      ((Class)localObject1).getMethod("getLong", new Class[] { Long.TYPE });
      ((Class)localObject1).getMethod("putLong", new Class[] { Long.TYPE, Long.TYPE });
      ((Class)localObject1).getMethod("copyMemory", new Class[] { Long.TYPE, Long.TYPE, Long.TYPE });
      ((Class)localObject1).getMethod("copyMemory", new Class[] { Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE });
      return true;
    }
    catch (Throwable localThrowable)
    {
      Object localObject1 = logger;
      Object localObject2 = Level.WARNING;
      String str = String.valueOf(localThrowable);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 71);
      localStringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      localStringBuilder.append(str);
      ((Logger)localObject1).logp((Level)localObject2, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", localStringBuilder.toString());
    }
    return false;
  }
  
  private static Field zzyn()
  {
    if (zzua.zzty())
    {
      localField = find(Buffer.class, "effectiveDirectAddress");
      if (localField != null) {
        return localField;
      }
    }
    Field localField = find(Buffer.class, "address");
    if ((localField != null) && (localField.getType() == Long.TYPE)) {
      return localField;
    }
    return null;
  }
  
  final class zza
    extends zzyh.zzd
  {
    zza()
    {
      super();
    }
    
    public final boolean get(Object paramObject, long paramLong)
    {
      if (zzyh.zzvh()) {
        return zzyh.next(paramObject, paramLong);
      }
      return zzyh.accept(paramObject, paramLong);
    }
    
    public final byte readByte(Object paramObject, long paramLong)
    {
      if (zzyh.zzvh()) {
        return zzyh.readByte(paramObject, paramLong);
      }
      return zzyh.read(paramObject, paramLong);
    }
    
    public final double readDouble(Object paramObject, long paramLong)
    {
      return Double.longBitsToDouble(readLong(paramObject, paramLong));
    }
    
    public final float readFloat(Object paramObject, long paramLong)
    {
      return Float.intBitsToFloat(getInt(paramObject, paramLong));
    }
    
    public final void setBytes(Object paramObject, long paramLong, byte paramByte)
    {
      if (zzyh.zzvh())
      {
        zzyh.put(paramObject, paramLong, paramByte);
        return;
      }
      zzyh.append(paramObject, paramLong, paramByte);
    }
    
    public final void setBytes(Object paramObject, long paramLong, boolean paramBoolean)
    {
      if (zzyh.zzvh())
      {
        zzyh.add(paramObject, paramLong, paramBoolean);
        return;
      }
      zzyh.put(paramObject, paramLong, paramBoolean);
    }
    
    public final void writeDouble(Object paramObject, long paramLong, double paramDouble)
    {
      writeLong(paramObject, paramLong, Double.doubleToLongBits(paramDouble));
    }
    
    public final void writeFloat(Object paramObject, long paramLong, float paramFloat)
    {
      writeInt(paramObject, paramLong, Float.floatToIntBits(paramFloat));
    }
    
    public final void writeLong(long paramLong, byte paramByte)
    {
      Memory.pokeByte((int)(paramLong & 0xFFFFFFFFFFFFFFFF), paramByte);
    }
    
    public final void writeLong(byte[] paramArrayOfByte, long paramLong1, long paramLong2, long paramLong3)
    {
      Memory.pokeByteArray((int)(paramLong2 & 0xFFFFFFFFFFFFFFFF), paramArrayOfByte, (int)paramLong1, (int)paramLong3);
    }
  }
  
  final class zzb
    extends zzyh.zzd
  {
    zzb()
    {
      super();
    }
    
    public final boolean get(Object paramObject, long paramLong)
    {
      if (zzyh.zzvh()) {
        return zzyh.next(paramObject, paramLong);
      }
      return zzyh.accept(paramObject, paramLong);
    }
    
    public final byte readByte(Object paramObject, long paramLong)
    {
      if (zzyh.zzvh()) {
        return zzyh.readByte(paramObject, paramLong);
      }
      return zzyh.read(paramObject, paramLong);
    }
    
    public final double readDouble(Object paramObject, long paramLong)
    {
      return Double.longBitsToDouble(readLong(paramObject, paramLong));
    }
    
    public final float readFloat(Object paramObject, long paramLong)
    {
      return Float.intBitsToFloat(getInt(paramObject, paramLong));
    }
    
    public final void setBytes(Object paramObject, long paramLong, byte paramByte)
    {
      if (zzyh.zzvh())
      {
        zzyh.put(paramObject, paramLong, paramByte);
        return;
      }
      zzyh.append(paramObject, paramLong, paramByte);
    }
    
    public final void setBytes(Object paramObject, long paramLong, boolean paramBoolean)
    {
      if (zzyh.zzvh())
      {
        zzyh.add(paramObject, paramLong, paramBoolean);
        return;
      }
      zzyh.put(paramObject, paramLong, paramBoolean);
    }
    
    public final void writeDouble(Object paramObject, long paramLong, double paramDouble)
    {
      writeLong(paramObject, paramLong, Double.doubleToLongBits(paramDouble));
    }
    
    public final void writeFloat(Object paramObject, long paramLong, float paramFloat)
    {
      writeInt(paramObject, paramLong, Float.floatToIntBits(paramFloat));
    }
    
    public final void writeLong(long paramLong, byte paramByte)
    {
      Memory.pokeByte(paramLong, paramByte);
    }
    
    public final void writeLong(byte[] paramArrayOfByte, long paramLong1, long paramLong2, long paramLong3)
    {
      Memory.pokeByteArray(paramLong2, paramArrayOfByte, (int)paramLong1, (int)paramLong3);
    }
  }
  
  final class zzc
    extends zzyh.zzd
  {
    zzc()
    {
      super();
    }
    
    public final boolean get(Object paramObject, long paramLong)
    {
      return zzcdo.getBoolean(paramObject, paramLong);
    }
    
    public final byte readByte(Object paramObject, long paramLong)
    {
      return zzcdo.getByte(paramObject, paramLong);
    }
    
    public final double readDouble(Object paramObject, long paramLong)
    {
      return zzcdo.getDouble(paramObject, paramLong);
    }
    
    public final float readFloat(Object paramObject, long paramLong)
    {
      return zzcdo.getFloat(paramObject, paramLong);
    }
    
    public final void setBytes(Object paramObject, long paramLong, byte paramByte)
    {
      zzcdo.putByte(paramObject, paramLong, paramByte);
    }
    
    public final void setBytes(Object paramObject, long paramLong, boolean paramBoolean)
    {
      zzcdo.putBoolean(paramObject, paramLong, paramBoolean);
    }
    
    public final void writeDouble(Object paramObject, long paramLong, double paramDouble)
    {
      zzcdo.putDouble(paramObject, paramLong, paramDouble);
    }
    
    public final void writeFloat(Object paramObject, long paramLong, float paramFloat)
    {
      zzcdo.putFloat(paramObject, paramLong, paramFloat);
    }
    
    public final void writeLong(long paramLong, byte paramByte)
    {
      zzcdo.putByte(paramLong, paramByte);
    }
    
    public final void writeLong(byte[] paramArrayOfByte, long paramLong1, long paramLong2, long paramLong3)
    {
      zzcdo.copyMemory(paramArrayOfByte, zzyh.zzyo() + paramLong1, null, paramLong2, paramLong3);
    }
  }
  
  abstract class zzd
  {
    zzd() {}
    
    public abstract boolean get(Object paramObject, long paramLong);
    
    public final int getInt(Object paramObject, long paramLong)
    {
      return zzyh.this.getInt(paramObject, paramLong);
    }
    
    public abstract byte readByte(Object paramObject, long paramLong);
    
    public abstract double readDouble(Object paramObject, long paramLong);
    
    public abstract float readFloat(Object paramObject, long paramLong);
    
    public final long readLong(Object paramObject, long paramLong)
    {
      return getLong(paramObject, paramLong);
    }
    
    public abstract void setBytes(Object paramObject, long paramLong, byte paramByte);
    
    public abstract void setBytes(Object paramObject, long paramLong, boolean paramBoolean);
    
    public abstract void writeDouble(Object paramObject, long paramLong, double paramDouble);
    
    public abstract void writeFloat(Object paramObject, long paramLong, float paramFloat);
    
    public final void writeInt(Object paramObject, long paramLong, int paramInt)
    {
      putInt(paramObject, paramLong, paramInt);
    }
    
    public abstract void writeLong(long paramLong, byte paramByte);
    
    public final void writeLong(Object paramObject, long paramLong1, long paramLong2)
    {
      putLong(paramObject, paramLong1, paramLong2);
    }
    
    public abstract void writeLong(byte[] paramArrayOfByte, long paramLong1, long paramLong2, long paramLong3);
  }
}
