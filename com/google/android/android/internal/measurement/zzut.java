package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzut
  extends zzuc
{
  private static final Logger logger = Logger.getLogger(com.google.android.gms.internal.measurement.zzut.class.getName());
  private static final boolean zzbuv = zzyh.zzyi();
  zzuv zzbuw;
  
  private zzut() {}
  
  public static int Decode(int paramInt, zzwt paramZzwt)
  {
    return (zzbb(1) << 1) + get(2, paramInt) + split(3, paramZzwt);
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + zzbc(paramInt2);
  }
  
  public static int a(int paramInt, long paramLong)
  {
    return zzbb(paramInt) + 8;
  }
  
  public static int accept(int paramInt, long paramLong)
  {
    return zzbb(paramInt) + 8;
  }
  
  public static int animateView(int paramInt, double paramDouble)
  {
    return zzbb(paramInt) + 8;
  }
  
  static int check(int paramInt, zzwt paramZzwt, zzxj paramZzxj)
  {
    return zzbb(paramInt) + getLocation(paramZzwt, paramZzxj);
  }
  
  public static int createBookmark(int paramInt, long paramLong)
  {
    return zzbb(paramInt) + zzaz(paramLong);
  }
  
  public static int deleteServer(int paramInt, long paramLong)
  {
    return zzbb(paramInt) + zzaz(zzbd(paramLong));
  }
  
  public static int e(int paramInt, boolean paramBoolean)
  {
    return zzbb(paramInt) + 1;
  }
  
  public static int get(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + zzbd(paramInt2);
  }
  
  public static int get(int paramInt, zzwa paramZzwa)
  {
    return (zzbb(1) << 1) + get(2, paramInt) + toByteArray(3, paramZzwa);
  }
  
  public static zzut getByteArray(byte[] paramArrayOfByte)
  {
    return new zza(0, paramArrayOfByte.length);
  }
  
  public static int getCard(int paramInt, long paramLong)
  {
    return zzbb(paramInt) + zzaz(paramLong);
  }
  
  public static int getDisplayTitle(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + 4;
  }
  
  public static int getDisplayTitle(int paramInt, String paramString)
  {
    return zzbb(paramInt) + zzfx(paramString);
  }
  
  public static int getID(zzwt paramZzwt)
  {
    int i = paramZzwt.zzvu();
    return zzbd(i) + i;
  }
  
  static int getLocation(zzwt paramZzwt, zzxj paramZzxj)
  {
    paramZzwt = (zztw)paramZzwt;
    int j = paramZzwt.zztu();
    int i = j;
    if (j == -1)
    {
      j = paramZzxj.zzae(paramZzwt);
      i = j;
      paramZzwt.zzah(j);
    }
    return zzbd(i) + i;
  }
  
  public static int getPath(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + 4;
  }
  
  public static int getShares(int paramInt, zzud paramZzud)
  {
    paramInt = zzbb(paramInt);
    int i = paramZzud.size();
    return paramInt + (zzbd(i) + i);
  }
  
  static int getSummary(int paramInt, zzwt paramZzwt, zzxj paramZzxj)
  {
    int j = zzbb(paramInt);
    paramZzwt = (zztw)paramZzwt;
    int i = paramZzwt.zztu();
    paramInt = i;
    if (i == -1)
    {
      i = paramZzxj.zzae(paramZzwt);
      paramInt = i;
      paramZzwt.zzah(i);
    }
    return (j << 1) + paramInt;
  }
  
  public static int insert(double paramDouble)
  {
    return 8;
  }
  
  public static int insert(boolean paramBoolean)
  {
    return 1;
  }
  
  public static int loadView(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + zzbc(paramInt2);
  }
  
  public static int min(zzud paramZzud)
  {
    int i = paramZzud.size();
    return zzbd(i) + i;
  }
  
  public static int min(zzwa paramZzwa)
  {
    int i = paramZzwa.zzvu();
    return zzbd(i) + i;
  }
  
  public static int min(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    return zzbd(i) + i;
  }
  
  public static int parseTrigger(int paramInt1, int paramInt2)
  {
    return zzbb(paramInt1) + zzbd(zzbi(paramInt2));
  }
  
  public static zzut shrink(ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer.hasArray()) {
      return new zzb();
    }
    if ((paramByteBuffer.isDirect()) && (!paramByteBuffer.isReadOnly()))
    {
      if (zzyh.zzyj()) {
        return new zze();
      }
      return new zzd();
    }
    throw new IllegalArgumentException("ByteBuffer is read-only");
  }
  
  public static int skipChar(int paramInt, zzud paramZzud)
  {
    return (zzbb(1) << 1) + get(2, paramInt) + getShares(3, paramZzud);
  }
  
  public static int split(int paramInt, zzwt paramZzwt)
  {
    return zzbb(paramInt) + getID(paramZzwt);
  }
  
  public static int toByteArray(int paramInt, zzwa paramZzwa)
  {
    paramInt = zzbb(paramInt);
    int i = paramZzwa.zzvu();
    return paramInt + (zzbd(i) + i);
  }
  
  public static int toHtml(int paramInt, float paramFloat)
  {
    return zzbb(paramInt) + 4;
  }
  
  public static int write(float paramFloat)
  {
    return 4;
  }
  
  public static int writeStream(zzwt paramZzwt)
  {
    return paramZzwt.zzvu();
  }
  
  public static int zzay(long paramLong)
  {
    return zzaz(paramLong);
  }
  
  public static int zzaz(long paramLong)
  {
    if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
      return 1;
    }
    if (paramLong < 0L) {
      return 10;
    }
    if ((0xFFFFFFF800000000 & paramLong) != 0L)
    {
      j = 6;
      paramLong >>>= 28;
    }
    else
    {
      j = 2;
    }
    int i = j;
    long l = paramLong;
    if ((0xFFFFFFFFFFE00000 & paramLong) != 0L)
    {
      i = j + 2;
      l = paramLong >>> 14;
    }
    int j = i;
    if ((l & 0xFFFFFFFFFFFFC000) != 0L) {
      j = i + 1;
    }
    return j;
  }
  
  public static int zzba(long paramLong)
  {
    return zzaz(zzbd(paramLong));
  }
  
  public static int zzbb(int paramInt)
  {
    return zzbd(paramInt << 3);
  }
  
  public static int zzbb(long paramLong)
  {
    return 8;
  }
  
  public static int zzbc(int paramInt)
  {
    if (paramInt >= 0) {
      return zzbd(paramInt);
    }
    return 10;
  }
  
  public static int zzbc(long paramLong)
  {
    return 8;
  }
  
  public static int zzbd(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((paramInt & 0xC000) == 0) {
      return 2;
    }
    if ((0xFFE00000 & paramInt) == 0) {
      return 3;
    }
    if ((paramInt & 0xF0000000) == 0) {
      return 4;
    }
    return 5;
  }
  
  private static long zzbd(long paramLong)
  {
    return paramLong >> 63 ^ paramLong << 1;
  }
  
  public static int zzbe(int paramInt)
  {
    return zzbd(zzbi(paramInt));
  }
  
  public static int zzbf(int paramInt)
  {
    return 4;
  }
  
  public static int zzbg(int paramInt)
  {
    return 4;
  }
  
  public static int zzbh(int paramInt)
  {
    return zzbc(paramInt);
  }
  
  private static int zzbi(int paramInt)
  {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  public static int zzbj(int paramInt)
  {
    return zzbd(paramInt);
  }
  
  public static int zzfx(String paramString)
  {
    try
    {
      i = zzyj.decode(paramString);
    }
    catch (zzyn localZzyn)
    {
      int i;
      for (;;) {}
    }
    i = paramString.getBytes(zzvo.UTF_8).length;
    return zzbd(i) + i;
  }
  
  public final void add(int paramInt1, int paramInt2)
    throws IOException
  {
    substring(paramInt1, zzbi(paramInt2));
  }
  
  public abstract void append(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void b(int paramInt, zzud paramZzud)
    throws IOException;
  
  public abstract void b(int paramInt, String paramString)
    throws IOException;
  
  abstract void f(int paramInt, zzwt paramZzwt, zzxj paramZzxj)
    throws IOException;
  
  abstract void f(zzwt paramZzwt, zzxj paramZzxj)
    throws IOException;
  
  public abstract void flush()
    throws IOException;
  
  public abstract void get(int paramInt, long paramLong)
    throws IOException;
  
  public final void inc(int paramInt, float paramFloat)
    throws IOException
  {
    put(paramInt, Float.floatToRawIntBits(paramFloat));
  }
  
  public abstract void l(int paramInt, zzud paramZzud)
    throws IOException;
  
  public abstract void l(zzud paramZzud)
    throws IOException;
  
  public abstract void parseColor(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void process(int paramInt, boolean paramBoolean)
    throws IOException;
  
  public abstract void put(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void readFrom(byte paramByte)
    throws IOException;
  
  public abstract void readFully(int paramInt, zzwt paramZzwt)
    throws IOException;
  
  public final void remove(int paramInt, long paramLong)
    throws IOException
  {
    trim(paramInt, zzbd(paramLong));
  }
  
  public final void set(double paramDouble)
    throws IOException
  {
    zzax(Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void set(float paramFloat)
    throws IOException
  {
    zzba(Float.floatToRawIntBits(paramFloat));
  }
  
  public final void set(int paramInt, double paramDouble)
    throws IOException
  {
    get(paramInt, Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void set(boolean paramBoolean)
    throws IOException
  {
    readFrom((byte)paramBoolean);
  }
  
  public abstract void substring(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void trim(int paramInt, long paramLong)
    throws IOException;
  
  public abstract void visitField(int paramInt, zzwt paramZzwt)
    throws IOException;
  
  final void warn(String paramString, zzyn paramZzyn)
    throws IOException
  {
    logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", paramZzyn);
    paramString = paramString.getBytes(zzvo.UTF_8);
    int i = paramString.length;
    try
    {
      zzay(i);
      i = paramString.length;
      append(paramString, 0, i);
      return;
    }
    catch (zzc paramString)
    {
      throw paramString;
    }
    catch (IndexOutOfBoundsException paramString)
    {
      throw new zzc(paramString);
    }
  }
  
  public abstract void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void writeField(zzwt paramZzwt)
    throws IOException;
  
  abstract void writeTag(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void zzav(long paramLong)
    throws IOException;
  
  public final void zzaw(long paramLong)
    throws IOException
  {
    zzav(zzbd(paramLong));
  }
  
  public abstract void zzax(int paramInt)
    throws IOException;
  
  public abstract void zzax(long paramLong)
    throws IOException;
  
  public abstract void zzay(int paramInt)
    throws IOException;
  
  public final void zzaz(int paramInt)
    throws IOException
  {
    zzay(zzbi(paramInt));
  }
  
  public abstract void zzba(int paramInt)
    throws IOException;
  
  public abstract void zzfw(String paramString)
    throws IOException;
  
  public abstract int zzvg();
  
  class zza
    extends zzut
  {
    private final int limit;
    private final int offset;
    private int position;
    
    zza(int paramInt1, int paramInt2)
    {
      super();
      if (zzut.this != null)
      {
        int i = zzut.this.length;
        int j = paramInt1 + paramInt2;
        if ((paramInt1 | paramInt2 | i - j) >= 0)
        {
          offset = paramInt1;
          position = paramInt1;
          limit = j;
          return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] { Integer.valueOf(zzut.this.length), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
      }
      throw new NullPointerException("buffer");
    }
    
    public final void append(int paramInt1, int paramInt2)
      throws IOException
    {
      zzay(paramInt1 << 3 | paramInt2);
    }
    
    public final void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      write(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public final void b(int paramInt, zzud paramZzud)
      throws IOException
    {
      append(1, 3);
      substring(2, paramInt);
      l(3, paramZzud);
      append(1, 4);
    }
    
    public final void b(int paramInt, String paramString)
      throws IOException
    {
      append(paramInt, 2);
      zzfw(paramString);
    }
    
    final void f(int paramInt, zzwt paramZzwt, zzxj paramZzxj)
      throws IOException
    {
      append(paramInt, 2);
      zztw localZztw = (zztw)paramZzwt;
      int i = localZztw.zztu();
      paramInt = i;
      if (i == -1)
      {
        i = paramZzxj.zzae(localZztw);
        paramInt = i;
        localZztw.zzah(i);
      }
      zzay(paramInt);
      paramZzxj.a(paramZzwt, zzbuw);
    }
    
    final void f(zzwt paramZzwt, zzxj paramZzxj)
      throws IOException
    {
      zztw localZztw = (zztw)paramZzwt;
      int j = localZztw.zztu();
      int i = j;
      if (j == -1)
      {
        j = paramZzxj.zzae(localZztw);
        i = j;
        localZztw.zzah(j);
      }
      zzay(i);
      paramZzxj.a(paramZzwt, zzbuw);
    }
    
    public void flush() {}
    
    public final void get(int paramInt, long paramLong)
      throws IOException
    {
      append(paramInt, 1);
      zzax(paramLong);
    }
    
    public final void l(int paramInt, zzud paramZzud)
      throws IOException
    {
      append(paramInt, 2);
      l(paramZzud);
    }
    
    public final void l(zzud paramZzud)
      throws IOException
    {
      zzay(paramZzud.size());
      paramZzud.d(this);
    }
    
    public final void parseColor(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 0);
      zzax(paramInt2);
    }
    
    public final void process(int paramInt, boolean paramBoolean)
      throws IOException
    {
      append(paramInt, 0);
      readFrom((byte)paramBoolean);
    }
    
    public final void put(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 5);
      zzba(paramInt2);
    }
    
    public final void readFrom(byte paramByte)
      throws IOException
    {
      byte[] arrayOfByte = zzut.this;
      int i = position;
      position = (i + 1);
      arrayOfByte[i] = paramByte;
    }
    
    public final void readFully(int paramInt, zzwt paramZzwt)
      throws IOException
    {
      append(paramInt, 2);
      writeField(paramZzwt);
    }
    
    public final void substring(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 0);
      zzay(paramInt2);
    }
    
    public final void trim(int paramInt, long paramLong)
      throws IOException
    {
      append(paramInt, 0);
      zzav(paramLong);
    }
    
    public final void visitField(int paramInt, zzwt paramZzwt)
      throws IOException
    {
      append(1, 3);
      substring(2, paramInt);
      readFully(3, paramZzwt);
      append(1, 4);
    }
    
    public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      byte[] arrayOfByte = zzut.this;
      int i = position;
      try
      {
        System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, i, paramInt2);
        position += paramInt2;
        return;
      }
      catch (IndexOutOfBoundsException paramArrayOfByte)
      {
        throw new zzut.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(position), Integer.valueOf(limit), Integer.valueOf(paramInt2) }), paramArrayOfByte);
      }
    }
    
    public final void writeField(zzwt paramZzwt)
      throws IOException
    {
      zzay(paramZzwt.zzvu());
      paramZzwt.writeValue(this);
    }
    
    public final void writeTag(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzay(paramInt2);
      write(paramArrayOfByte, 0, paramInt2);
    }
    
    public final void zzav(long paramLong)
      throws IOException
    {
      long l = paramLong;
      byte[] arrayOfByte;
      int i;
      if (zzut.zzvh())
      {
        l = paramLong;
        if (zzvg() >= 10) {
          for (;;)
          {
            if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L)
            {
              arrayOfByte = zzut.this;
              i = position;
              position = (i + 1);
              zzyh.setBytes(arrayOfByte, i, (byte)(int)paramLong);
              return;
            }
            arrayOfByte = zzut.this;
            i = position;
            position = (i + 1);
            zzyh.setBytes(arrayOfByte, i, (byte)((int)paramLong & 0x7F | 0x80));
            paramLong >>>= 7;
          }
        }
      }
      for (;;)
      {
        if ((l & 0xFFFFFFFFFFFFFF80) == 0L)
        {
          arrayOfByte = zzut.this;
          i = position;
          position = (i + 1);
          arrayOfByte[i] = ((byte)(int)l);
          return;
        }
        arrayOfByte = zzut.this;
        i = position;
        position = (i + 1);
        arrayOfByte[i] = ((byte)((int)l & 0x7F | 0x80));
        l >>>= 7;
      }
    }
    
    public final void zzax(int paramInt)
      throws IOException
    {
      if (paramInt >= 0)
      {
        zzay(paramInt);
        return;
      }
      zzav(paramInt);
    }
    
    public final void zzax(long paramLong)
      throws IOException
    {
      byte[] arrayOfByte = zzut.this;
      int i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)paramLong);
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 8));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 16));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 24));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 32));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 40));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 48));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(int)(paramLong >> 56));
    }
    
    public final void zzay(int paramInt)
      throws IOException
    {
      int i = paramInt;
      byte[] arrayOfByte;
      if (zzut.zzvh())
      {
        i = paramInt;
        if (zzvg() >= 10) {
          for (;;)
          {
            if ((paramInt & 0xFFFFFF80) == 0)
            {
              arrayOfByte = zzut.this;
              i = position;
              position = (i + 1);
              zzyh.setBytes(arrayOfByte, i, (byte)paramInt);
              return;
            }
            arrayOfByte = zzut.this;
            i = position;
            position = (i + 1);
            zzyh.setBytes(arrayOfByte, i, (byte)(paramInt & 0x7F | 0x80));
            paramInt >>>= 7;
          }
        }
      }
      for (;;)
      {
        if ((i & 0xFFFFFF80) == 0)
        {
          arrayOfByte = zzut.this;
          paramInt = position;
          position = (paramInt + 1);
          arrayOfByte[paramInt] = ((byte)i);
          return;
        }
        arrayOfByte = zzut.this;
        paramInt = position;
        position = (paramInt + 1);
        arrayOfByte[paramInt] = ((byte)(i & 0x7F | 0x80));
        i >>>= 7;
      }
    }
    
    public final void zzba(int paramInt)
      throws IOException
    {
      byte[] arrayOfByte = zzut.this;
      int i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)paramInt);
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(paramInt >> 8));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(paramInt >> 16));
      arrayOfByte = zzut.this;
      i = position;
      position = (i + 1);
      arrayOfByte[i] = ((byte)(paramInt >> 24));
    }
    
    public final void zzfw(String paramString)
      throws IOException
    {
      int i = position;
      try
      {
        int j = paramString.length();
        int k = zzut.zzbd(j * 3);
        j = zzut.zzbd(paramString.length());
        if (j == k)
        {
          position = (i + j);
          arrayOfByte = zzut.this;
          k = position;
          k = zzyj.write(paramString, arrayOfByte, k, zzvg());
          position = i;
          zzay(k - i - j);
          position = k;
          return;
        }
        zzay(zzyj.decode(paramString));
        byte[] arrayOfByte = zzut.this;
        j = position;
        j = zzyj.write(paramString, arrayOfByte, j, zzvg());
        position = j;
        return;
      }
      catch (IndexOutOfBoundsException paramString)
      {
        throw new zzut.zzc(paramString);
      }
      catch (zzyn localZzyn)
      {
        position = i;
        warn(paramString, localZzyn);
      }
    }
    
    public final int zzvg()
    {
      return limit - position;
    }
    
    public final int zzvi()
    {
      return position - offset;
    }
  }
  
  final class zzb
    extends zzut.zza
  {
    private int zzbuy = position();
    
    zzb()
    {
      super(arrayOffset() + position(), remaining());
    }
    
    public final void flush()
    {
      position(zzbuy + zzvi());
    }
  }
  
  public final class zzc
    extends IOException
  {
    zzc()
    {
      super();
    }
    
    zzc()
    {
      super();
    }
    
    zzc(Throwable paramThrowable)
    {
      super(paramThrowable);
    }
    
    zzc()
    {
      super(this$1);
    }
  }
  
  final class zzd
    extends zzut
  {
    private final int zzbuy = position();
    private final ByteBuffer zzbva = duplicate().order(ByteOrder.LITTLE_ENDIAN);
    
    zzd()
    {
      super();
    }
    
    private final void zzfy(String paramString)
      throws IOException
    {
      ByteBuffer localByteBuffer = zzbva;
      try
      {
        zzyj.write(paramString, localByteBuffer);
        return;
      }
      catch (IndexOutOfBoundsException paramString)
      {
        throw new zzut.zzc(paramString);
      }
    }
    
    public final void append(int paramInt1, int paramInt2)
      throws IOException
    {
      zzay(paramInt1 << 3 | paramInt2);
    }
    
    public final void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      write(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public final void b(int paramInt, zzud paramZzud)
      throws IOException
    {
      append(1, 3);
      substring(2, paramInt);
      l(3, paramZzud);
      append(1, 4);
    }
    
    public final void b(int paramInt, String paramString)
      throws IOException
    {
      append(paramInt, 2);
      zzfw(paramString);
    }
    
    final void f(int paramInt, zzwt paramZzwt, zzxj paramZzxj)
      throws IOException
    {
      append(paramInt, 2);
      f(paramZzwt, paramZzxj);
    }
    
    final void f(zzwt paramZzwt, zzxj paramZzxj)
      throws IOException
    {
      zztw localZztw = (zztw)paramZzwt;
      int j = localZztw.zztu();
      int i = j;
      if (j == -1)
      {
        j = paramZzxj.zzae(localZztw);
        i = j;
        localZztw.zzah(j);
      }
      zzay(i);
      paramZzxj.a(paramZzwt, zzbuw);
    }
    
    public final void flush()
    {
      position(zzbva.position());
    }
    
    public final void get(int paramInt, long paramLong)
      throws IOException
    {
      append(paramInt, 1);
      zzax(paramLong);
    }
    
    public final void l(int paramInt, zzud paramZzud)
      throws IOException
    {
      append(paramInt, 2);
      l(paramZzud);
    }
    
    public final void l(zzud paramZzud)
      throws IOException
    {
      zzay(paramZzud.size());
      paramZzud.d(this);
    }
    
    public final void parseColor(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 0);
      zzax(paramInt2);
    }
    
    public final void process(int paramInt, boolean paramBoolean)
      throws IOException
    {
      append(paramInt, 0);
      readFrom((byte)paramBoolean);
    }
    
    public final void put(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 5);
      zzba(paramInt2);
    }
    
    public final void readFrom(byte paramByte)
      throws IOException
    {
      ByteBuffer localByteBuffer = zzbva;
      try
      {
        localByteBuffer.put(paramByte);
        return;
      }
      catch (BufferOverflowException localBufferOverflowException)
      {
        throw new zzut.zzc(localBufferOverflowException);
      }
    }
    
    public final void readFully(int paramInt, zzwt paramZzwt)
      throws IOException
    {
      append(paramInt, 2);
      writeField(paramZzwt);
    }
    
    public final void substring(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 0);
      zzay(paramInt2);
    }
    
    public final void trim(int paramInt, long paramLong)
      throws IOException
    {
      append(paramInt, 0);
      zzav(paramLong);
    }
    
    public final void visitField(int paramInt, zzwt paramZzwt)
      throws IOException
    {
      append(1, 3);
      substring(2, paramInt);
      readFully(3, paramZzwt);
      append(1, 4);
    }
    
    public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      ByteBuffer localByteBuffer = zzbva;
      try
      {
        localByteBuffer.put(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      catch (BufferOverflowException paramArrayOfByte)
      {
        throw new zzut.zzc(paramArrayOfByte);
      }
      catch (IndexOutOfBoundsException paramArrayOfByte)
      {
        throw new zzut.zzc(paramArrayOfByte);
      }
    }
    
    public final void writeField(zzwt paramZzwt)
      throws IOException
    {
      zzay(paramZzwt.zzvu());
      paramZzwt.writeValue(this);
    }
    
    public final void writeTag(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzay(paramInt2);
      write(paramArrayOfByte, 0, paramInt2);
    }
    
    public final void zzav(long paramLong)
      throws IOException
    {
      ByteBuffer localByteBuffer2;
      for (;;)
      {
        if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
        {
          ByteBuffer localByteBuffer1 = zzbva;
          b = (byte)(int)paramLong;
          try
          {
            localByteBuffer1.put(b);
            return;
          }
          catch (BufferOverflowException localBufferOverflowException) {}
        }
        localByteBuffer2 = zzbva;
        byte b = (byte)((int)paramLong & 0x7F | 0x80);
        localByteBuffer2.put(b);
        paramLong >>>= 7;
      }
      throw new zzut.zzc(localByteBuffer2);
    }
    
    public final void zzax(int paramInt)
      throws IOException
    {
      if (paramInt >= 0)
      {
        zzay(paramInt);
        return;
      }
      zzav(paramInt);
    }
    
    public final void zzax(long paramLong)
      throws IOException
    {
      ByteBuffer localByteBuffer = zzbva;
      try
      {
        localByteBuffer.putLong(paramLong);
        return;
      }
      catch (BufferOverflowException localBufferOverflowException)
      {
        throw new zzut.zzc(localBufferOverflowException);
      }
    }
    
    public final void zzay(int paramInt)
      throws IOException
    {
      ByteBuffer localByteBuffer2;
      for (;;)
      {
        if ((paramInt & 0xFFFFFF80) == 0)
        {
          ByteBuffer localByteBuffer1 = zzbva;
          b = (byte)paramInt;
          try
          {
            localByteBuffer1.put(b);
            return;
          }
          catch (BufferOverflowException localBufferOverflowException) {}
        }
        localByteBuffer2 = zzbva;
        byte b = (byte)(paramInt & 0x7F | 0x80);
        localByteBuffer2.put(b);
        paramInt >>>= 7;
      }
      throw new zzut.zzc(localByteBuffer2);
    }
    
    public final void zzba(int paramInt)
      throws IOException
    {
      ByteBuffer localByteBuffer = zzbva;
      try
      {
        localByteBuffer.putInt(paramInt);
        return;
      }
      catch (BufferOverflowException localBufferOverflowException)
      {
        throw new zzut.zzc(localBufferOverflowException);
      }
    }
    
    public final void zzfw(String paramString)
      throws IOException
    {
      int i = zzbva.position();
      try
      {
        int j = paramString.length();
        int k = zzut.zzbd(j * 3);
        j = zzut.zzbd(paramString.length());
        if (j == k)
        {
          ByteBuffer localByteBuffer = zzbva;
          k = localByteBuffer.position();
          j = k + j;
          localByteBuffer = zzbva;
          localByteBuffer.position(j);
          zzfy(paramString);
          localByteBuffer = zzbva;
          k = localByteBuffer.position();
          localByteBuffer = zzbva;
          localByteBuffer.position(i);
          zzay(k - j);
          localByteBuffer = zzbva;
          localByteBuffer.position(k);
          return;
        }
        zzay(zzyj.decode(paramString));
        zzfy(paramString);
        return;
      }
      catch (IllegalArgumentException paramString)
      {
        throw new zzut.zzc(paramString);
      }
      catch (zzyn localZzyn)
      {
        zzbva.position(i);
        warn(paramString, localZzyn);
      }
    }
    
    public final int zzvg()
    {
      return zzbva.remaining();
    }
  }
  
  final class zze
    extends zzut
  {
    private final ByteBuffer zzbva = duplicate().order(ByteOrder.LITTLE_ENDIAN);
    private final long zzbvb = zzyh.position(zzut.this);
    private final long zzbvc = zzbvb + position();
    private final long zzbvd = zzbvb + limit();
    private final long zzbve = zzbvd - 10L;
    private long zzbvf = zzbvc;
    
    zze()
    {
      super();
    }
    
    private final void zzbe(long paramLong)
    {
      zzbva.position((int)(paramLong - zzbvb));
    }
    
    public final void append(int paramInt1, int paramInt2)
      throws IOException
    {
      zzay(paramInt1 << 3 | paramInt2);
    }
    
    public final void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      write(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    public final void b(int paramInt, zzud paramZzud)
      throws IOException
    {
      append(1, 3);
      substring(2, paramInt);
      l(3, paramZzud);
      append(1, 4);
    }
    
    public final void b(int paramInt, String paramString)
      throws IOException
    {
      append(paramInt, 2);
      zzfw(paramString);
    }
    
    final void f(int paramInt, zzwt paramZzwt, zzxj paramZzxj)
      throws IOException
    {
      append(paramInt, 2);
      f(paramZzwt, paramZzxj);
    }
    
    final void f(zzwt paramZzwt, zzxj paramZzxj)
      throws IOException
    {
      zztw localZztw = (zztw)paramZzwt;
      int j = localZztw.zztu();
      int i = j;
      if (j == -1)
      {
        j = paramZzxj.zzae(localZztw);
        i = j;
        localZztw.zzah(j);
      }
      zzay(i);
      paramZzxj.a(paramZzwt, zzbuw);
    }
    
    public final void flush()
    {
      position((int)(zzbvf - zzbvb));
    }
    
    public final void get(int paramInt, long paramLong)
      throws IOException
    {
      append(paramInt, 1);
      zzax(paramLong);
    }
    
    public final void l(int paramInt, zzud paramZzud)
      throws IOException
    {
      append(paramInt, 2);
      l(paramZzud);
    }
    
    public final void l(zzud paramZzud)
      throws IOException
    {
      zzay(paramZzud.size());
      paramZzud.d(this);
    }
    
    public final void parseColor(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 0);
      zzax(paramInt2);
    }
    
    public final void process(int paramInt, boolean paramBoolean)
      throws IOException
    {
      append(paramInt, 0);
      readFrom((byte)paramBoolean);
    }
    
    public final void put(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 5);
      zzba(paramInt2);
    }
    
    public final void readFrom(byte paramByte)
      throws IOException
    {
      if (zzbvf < zzbvd)
      {
        long l = zzbvf;
        zzbvf = (1L + l);
        zzyh.toHex(l, paramByte);
        return;
      }
      throw new zzut.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(zzbvf), Long.valueOf(zzbvd), Integer.valueOf(1) }));
    }
    
    public final void readFully(int paramInt, zzwt paramZzwt)
      throws IOException
    {
      append(paramInt, 2);
      writeField(paramZzwt);
    }
    
    public final void substring(int paramInt1, int paramInt2)
      throws IOException
    {
      append(paramInt1, 0);
      zzay(paramInt2);
    }
    
    public final void trim(int paramInt, long paramLong)
      throws IOException
    {
      append(paramInt, 0);
      zzav(paramLong);
    }
    
    public final void visitField(int paramInt, zzwt paramZzwt)
      throws IOException
    {
      append(1, 3);
      substring(2, paramInt);
      readFully(3, paramZzwt);
      append(1, 4);
    }
    
    public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if ((paramArrayOfByte != null) && (paramInt1 >= 0) && (paramInt2 >= 0) && (paramArrayOfByte.length - paramInt2 >= paramInt1))
      {
        long l1 = zzbvd;
        long l2 = paramInt2;
        if (l1 - l2 >= zzbvf)
        {
          zzyh.writeFile(paramArrayOfByte, paramInt1, zzbvf, l2);
          zzbvf += l2;
          return;
        }
      }
      if (paramArrayOfByte == null) {
        throw new NullPointerException("value");
      }
      throw new zzut.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(zzbvf), Long.valueOf(zzbvd), Integer.valueOf(paramInt2) }));
    }
    
    public final void writeField(zzwt paramZzwt)
      throws IOException
    {
      zzay(paramZzwt.zzvu());
      paramZzwt.writeValue(this);
    }
    
    public final void writeTag(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzay(paramInt2);
      write(paramArrayOfByte, 0, paramInt2);
    }
    
    public final void zzav(long paramLong)
      throws IOException
    {
      long l = paramLong;
      if (zzbvf <= zzbve) {
        for (;;)
        {
          if ((paramLong & 0xFFFFFFFFFFFFFF80) == 0L)
          {
            l = zzbvf;
            zzbvf = (1L + l);
            zzyh.toHex(l, (byte)(int)paramLong);
            return;
          }
          l = zzbvf;
          zzbvf = (l + 1L);
          zzyh.toHex(l, (byte)((int)paramLong & 0x7F | 0x80));
          paramLong >>>= 7;
        }
      }
      while (zzbvf < zzbvd)
      {
        if ((l & 0xFFFFFFFFFFFFFF80) == 0L)
        {
          paramLong = zzbvf;
          zzbvf = (1L + paramLong);
          zzyh.toHex(paramLong, (byte)(int)l);
          return;
        }
        paramLong = zzbvf;
        zzbvf = (paramLong + 1L);
        zzyh.toHex(paramLong, (byte)((int)l & 0x7F | 0x80));
        l >>>= 7;
      }
      throw new zzut.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(zzbvf), Long.valueOf(zzbvd), Integer.valueOf(1) }));
    }
    
    public final void zzax(int paramInt)
      throws IOException
    {
      if (paramInt >= 0)
      {
        zzay(paramInt);
        return;
      }
      zzav(paramInt);
    }
    
    public final void zzax(long paramLong)
      throws IOException
    {
      zzbva.putLong((int)(zzbvf - zzbvb), paramLong);
      zzbvf += 8L;
    }
    
    public final void zzay(int paramInt)
      throws IOException
    {
      long l = zzbvf;
      zze localZze1 = this;
      zze localZze2 = localZze1;
      int i = paramInt;
      if (l <= zzbve) {
        for (;;)
        {
          if ((paramInt & 0xFFFFFF80) == 0)
          {
            l = zzbvf;
            zzbvf = (1L + l);
            zzyh.toHex(l, (byte)paramInt);
            return;
          }
          l = zzbvf;
          zzbvf = (l + 1L);
          zzyh.toHex(l, (byte)(paramInt & 0x7F | 0x80));
          paramInt >>>= 7;
        }
      }
      while (zzbvf < zzbvd)
      {
        if ((i & 0xFFFFFF80) == 0)
        {
          l = zzbvf;
          zzbvf = (1L + l);
          zzyh.toHex(l, (byte)i);
          return;
        }
        l = zzbvf;
        zzbvf = (l + 1L);
        zzyh.toHex(l, (byte)(i & 0x7F | 0x80));
        i >>>= 7;
      }
      throw new zzut.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(zzbvf), Long.valueOf(zzbvd), Integer.valueOf(1) }));
    }
    
    public final void zzba(int paramInt)
      throws IOException
    {
      zzbva.putInt((int)(zzbvf - zzbvb), paramInt);
      zzbvf += 4L;
    }
    
    public final void zzfw(String paramString)
      throws IOException
    {
      long l1 = zzbvf;
      try
      {
        int i = paramString.length();
        i = zzut.zzbd(i * 3);
        int j = zzut.zzbd(paramString.length());
        if (j == i)
        {
          i = (int)(zzbvf - zzbvb) + j;
          localByteBuffer = zzbva;
          localByteBuffer.position(i);
          localByteBuffer = zzbva;
          zzyj.write(paramString, localByteBuffer);
          localByteBuffer = zzbva;
          j = localByteBuffer.position();
          i = j - i;
          zzay(i);
          zzbvf += i;
          return;
        }
        i = zzyj.decode(paramString);
        zzay(i);
        long l2 = zzbvf;
        zzbe(l2);
        ByteBuffer localByteBuffer = zzbva;
        zzyj.write(paramString, localByteBuffer);
        zzbvf += i;
        return;
      }
      catch (IndexOutOfBoundsException paramString)
      {
        throw new zzut.zzc(paramString);
      }
      catch (IllegalArgumentException paramString)
      {
        throw new zzut.zzc(paramString);
      }
      catch (zzyn localZzyn)
      {
        zzbvf = l1;
        zzbe(zzbvf);
        warn(paramString, localZzyn);
      }
    }
    
    public final int zzvg()
    {
      return (int)(zzbvd - zzbvf);
    }
  }
}
