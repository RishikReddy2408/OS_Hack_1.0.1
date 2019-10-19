package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import sun.misc.Unsafe;

final class zzwx<T>
  implements com.google.android.gms.internal.measurement.zzxj<T>
{
  private static final int[] zzcax = new int[0];
  private static final Unsafe zzcay = zzyh.zzyk();
  private final int[] zzcaz;
  private final Object[] zzcba;
  private final int zzcbb;
  private final int zzcbc;
  private final zzwt zzcbd;
  private final boolean zzcbe;
  private final boolean zzcbf;
  private final boolean zzcbg;
  private final boolean zzcbh;
  private final int[] zzcbi;
  private final int zzcbj;
  private final int zzcbk;
  private final zzxa zzcbl;
  private final zzwd zzcbm;
  private final com.google.android.gms.internal.measurement.zzyb<?, ?> zzcbn;
  private final com.google.android.gms.internal.measurement.zzva<?> zzcbo;
  private final zzwo zzcbp;
  
  private zzwx(int[] paramArrayOfInt1, Object[] paramArrayOfObject, int paramInt1, int paramInt2, zzwt paramZzwt, boolean paramBoolean1, boolean paramBoolean2, int[] paramArrayOfInt2, int paramInt3, int paramInt4, zzxa paramZzxa, zzwd paramZzwd, zzyb paramZzyb, zzva paramZzva, zzwo paramZzwo)
  {
    zzcaz = paramArrayOfInt1;
    zzcba = paramArrayOfObject;
    zzcbb = paramInt1;
    zzcbc = paramInt2;
    zzcbf = (paramZzwt instanceof zzvm);
    zzcbg = paramBoolean1;
    if ((paramZzva != null) && (paramZzva.accepts(paramZzwt))) {
      paramBoolean1 = true;
    } else {
      paramBoolean1 = false;
    }
    zzcbe = paramBoolean1;
    zzcbh = false;
    zzcbi = paramArrayOfInt2;
    zzcbj = paramInt3;
    zzcbk = paramInt4;
    zzcbl = paramZzxa;
    zzcbm = paramZzwd;
    zzcbn = paramZzyb;
    zzcbo = paramZzva;
    zzcbd = paramZzwt;
    zzcbp = paramZzwo;
  }
  
  private static void a(int paramInt, Object paramObject, zzyw paramZzyw)
    throws IOException
  {
    if ((paramObject instanceof String))
    {
      paramZzyw.visitTypeInsn(paramInt, (String)paramObject);
      return;
    }
    paramZzyw.b(paramInt, (zzud)paramObject);
  }
  
  private final void a(Object paramObject1, Object paramObject2, int paramInt)
  {
    long l = zzbq(paramInt) & 0xFFFFF;
    if (!a(paramObject2, paramInt)) {
      return;
    }
    Object localObject = zzyh.get(paramObject1, l);
    paramObject2 = zzyh.get(paramObject2, l);
    if ((localObject != null) && (paramObject2 != null))
    {
      zzyh.add(paramObject1, l, zzvo.add(localObject, paramObject2));
      write(paramObject1, paramInt);
      return;
    }
    if (paramObject2 != null)
    {
      zzyh.add(paramObject1, l, paramObject2);
      write(paramObject1, paramInt);
    }
  }
  
  private final boolean a(Object paramObject, int paramInt)
  {
    if (zzcbg)
    {
      paramInt = zzbq(paramInt);
      long l = paramInt & 0xFFFFF;
      switch ((paramInt & 0xFF00000) >>> 20)
      {
      default: 
        throw new IllegalArgumentException();
      case 17: 
        return zzyh.get(paramObject, l) != null;
      case 16: 
        return zzyh.getId(paramObject, l) != 0L;
      case 15: 
        return zzyh.getValue(paramObject, l) != 0;
      case 14: 
        return zzyh.getId(paramObject, l) != 0L;
      case 13: 
        return zzyh.getValue(paramObject, l) != 0;
      case 12: 
        return zzyh.getValue(paramObject, l) != 0;
      case 11: 
        return zzyh.getValue(paramObject, l) != 0;
      case 10: 
        return !zzud.zzbtz.equals(zzyh.get(paramObject, l));
      case 9: 
        return zzyh.get(paramObject, l) != null;
      case 8: 
        paramObject = zzyh.get(paramObject, l);
        if ((paramObject instanceof String)) {
          return !((String)paramObject).isEmpty();
        }
        if ((paramObject instanceof zzud)) {
          return !zzud.zzbtz.equals(paramObject);
        }
        throw new IllegalArgumentException();
      case 7: 
        return zzyh.getName(paramObject, l);
      case 6: 
        return zzyh.getValue(paramObject, l) != 0;
      case 5: 
        return zzyh.getId(paramObject, l) != 0L;
      case 4: 
        return zzyh.getValue(paramObject, l) != 0;
      case 3: 
        return zzyh.getId(paramObject, l) != 0L;
      case 2: 
        return zzyh.getId(paramObject, l) != 0L;
      case 1: 
        return zzyh.invoke(paramObject, l) != 0.0F;
      }
      return zzyh.a(paramObject, l) != 0.0D;
    }
    paramInt = zzbr(paramInt);
    return (zzyh.getValue(paramObject, paramInt & 0xFFFFF) & 1 << (paramInt >>> 20)) != 0;
  }
  
  private final boolean a(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    if (zzcbg) {
      return a(paramObject, paramInt1);
    }
    return (paramInt2 & paramInt3) != 0;
  }
  
  private static boolean a(Object paramObject, int paramInt, zzxj paramZzxj)
  {
    return paramZzxj.zzaf(zzyh.get(paramObject, paramInt & 0xFFFFF));
  }
  
  private final void add(Object paramObject, int paramInt1, int paramInt2)
  {
    zzyh.a(paramObject, zzbr(paramInt2) & 0xFFFFF, paramInt1);
  }
  
  static zzwx decode(Class paramClass, zzwr paramZzwr, zzxa paramZzxa, zzwd paramZzwd, zzyb paramZzyb, zzva paramZzva, zzwo paramZzwo)
  {
    if ((paramZzwr instanceof zzxh))
    {
      zzxh localZzxh = (zzxh)paramZzwr;
      int i = localZzxh.zzxg();
      int j = zzvm.zze.zzbzc;
      int n = 0;
      boolean bool;
      if (i == j) {
        bool = true;
      } else {
        bool = false;
      }
      String str = localZzxh.zzxp();
      int i14 = str.length();
      i = str.charAt(0);
      int i3 = i;
      if (i >= 55296)
      {
        k = i & 0x1FFF;
        m = 1;
        j = 13;
        for (;;)
        {
          i = m + 1;
          m = str.charAt(m);
          if (m < 55296) {
            break;
          }
          k |= (m & 0x1FFF) << j;
          j += 13;
          m = i;
        }
        i3 = m << j | k;
        j = i;
      }
      else
      {
        j = 1;
      }
      i = j + 1;
      j = str.charAt(j);
      int k = j;
      if (j >= 55296)
      {
        k = j & 0x1FFF;
        j = 13;
        m = i;
        i = j;
        for (;;)
        {
          j = m + 1;
          m = str.charAt(m);
          if (m < 55296) {
            break;
          }
          k |= (m & 0x1FFF) << i;
          i += 13;
          m = j;
        }
        k |= m << i;
      }
      else
      {
        j = i;
      }
      int i4;
      int i6;
      int i5;
      int i2;
      if (k == 0)
      {
        paramClass = zzcax;
        i = 0;
        i4 = 0;
        i6 = 0;
        i5 = 0;
        m = 0;
        i2 = 0;
        k = n;
        n = i;
      }
      else
      {
        k = j + 1;
        j = str.charAt(j);
        i = j;
        if (j >= 55296)
        {
          j &= 0x1FFF;
          i = 13;
          m = k;
          k = j;
          for (;;)
          {
            j = m + 1;
            m = str.charAt(m);
            if (m < 55296) {
              break;
            }
            k |= (m & 0x1FFF) << i;
            i += 13;
            m = j;
          }
          i = m << i | k;
          k = j;
        }
        j = k + 1;
        k = str.charAt(k);
        i4 = k;
        if (k >= 55296)
        {
          m = k & 0x1FFF;
          k = 13;
          for (n = j;; n = j)
          {
            j = n + 1;
            n = str.charAt(n);
            if (n < 55296) {
              break;
            }
            m |= (n & 0x1FFF) << k;
            k += 13;
          }
          i4 = m | n << k;
          k = j;
        }
        else
        {
          k = j;
        }
        j = k + 1;
        m = str.charAt(k);
        k = m;
        if (m >= 55296)
        {
          m &= 0x1FFF;
          k = 13;
          n = j;
          j = k;
          for (;;)
          {
            k = n + 1;
            n = str.charAt(n);
            if (n < 55296) {
              break;
            }
            m |= (n & 0x1FFF) << j;
            j += 13;
            n = k;
          }
          j = n << j | m;
          m = k;
        }
        else
        {
          m = j;
          j = k;
        }
        k = m + 1;
        n = str.charAt(m);
        m = n;
        if (n >= 55296)
        {
          n &= 0x1FFF;
          m = 13;
          i1 = k;
          k = m;
          for (;;)
          {
            m = i1 + 1;
            i1 = str.charAt(i1);
            if (i1 < 55296) {
              break;
            }
            n |= (i1 & 0x1FFF) << k;
            k += 13;
            i1 = m;
          }
          k = i1 << k | n;
          i1 = m;
        }
        else
        {
          i1 = k;
          k = m;
        }
        n = i1 + 1;
        i1 = str.charAt(i1);
        m = i1;
        i2 = n;
        if (i1 >= 55296)
        {
          i1 &= 0x1FFF;
          m = 13;
          i2 = n;
          n = m;
          for (;;)
          {
            m = i2 + 1;
            i2 = str.charAt(i2);
            if (i2 < 55296) {
              break;
            }
            i1 |= (i2 & 0x1FFF) << n;
            n += 13;
            i2 = m;
          }
          n = i2 << n | i1;
          i2 = m;
          m = n;
        }
        i1 = i2 + 1;
        i5 = str.charAt(i2);
        n = i5;
        i2 = i1;
        if (i5 >= 55296)
        {
          i2 = i5 & 0x1FFF;
          n = 13;
          for (i5 = i1;; i5 = i1)
          {
            i1 = i5 + 1;
            i5 = str.charAt(i5);
            if (i5 < 55296) {
              break;
            }
            i2 |= (i5 & 0x1FFF) << n;
            n += 13;
          }
          n = i2 | i5 << n;
          i2 = i1;
        }
        i1 = i2 + 1;
        i6 = str.charAt(i2);
        i5 = i6;
        if (i6 >= 55296)
        {
          i2 = 13;
          i5 = i6 & 0x1FFF;
          i6 = i1;
          i1 = i2;
          for (;;)
          {
            i2 = i6 + 1;
            i6 = str.charAt(i6);
            if (i6 < 55296) {
              break;
            }
            i5 |= (i6 & 0x1FFF) << i1;
            i1 += 13;
            i6 = i2;
          }
          i5 |= i6 << i1;
          i1 = i2;
        }
        i6 = i1 + 1;
        i7 = str.charAt(i1);
        i1 = i7;
        i2 = i6;
        if (i7 >= 55296)
        {
          i1 = 13;
          i2 = i7 & 0x1FFF;
          i7 = i6;
          i6 = i2;
          for (;;)
          {
            i2 = i7 + 1;
            i7 = str.charAt(i7);
            if (i7 < 55296) {
              break;
            }
            i6 |= (i7 & 0x1FFF) << i1;
            i1 += 13;
            i7 = i2;
          }
          i1 = i6 | i7 << i1;
        }
        paramClass = new int[i1 + n + i5];
        i4 = (i << 1) + i4;
        i6 = j;
        j = i2;
        i2 = i4;
        i5 = k;
        k = i1;
        i4 = i;
      }
      Unsafe localUnsafe = zzcay;
      Object[] arrayOfObject1 = localZzxh.zzxq();
      Class localClass = localZzxh.zzxi().getClass();
      int[] arrayOfInt = new int[m * 3];
      Object[] arrayOfObject2 = new Object[m << 1];
      int i13 = k + n;
      n = k;
      i = i2;
      int m = 0;
      int i7 = 0;
      int i8 = i13;
      int i1 = j;
      j = n;
      while (i1 < i14)
      {
        n = i1 + 1;
        i2 = str.charAt(i1);
        int i9 = i2;
        i1 = n;
        if (i2 >= 55296)
        {
          i1 = 13;
          i2 &= 0x1FFF;
          for (i9 = n;; i9 = n)
          {
            n = i9 + 1;
            i9 = str.charAt(i9);
            if (i9 < 55296) {
              break;
            }
            i2 |= (i9 & 0x1FFF) << i1;
            i1 += 13;
          }
          i9 = i2 | i9 << i1;
          i1 = n;
        }
        n = i1 + 1;
        i2 = str.charAt(i1);
        int i11 = i2;
        if (i2 >= 55296)
        {
          i1 = 13;
          i2 &= 0x1FFF;
          i10 = n;
          n = i1;
          for (;;)
          {
            i1 = i10 + 1;
            i10 = str.charAt(i10);
            if (i10 < 55296) {
              break;
            }
            i2 |= (i10 & 0x1FFF) << n;
            n += 13;
            i10 = i1;
          }
          i11 = i2 | i10 << n;
          n = i1;
        }
        int i15 = i11 & 0xFF;
        int i10 = m;
        if ((i11 & 0x400) != 0)
        {
          paramClass[m] = i7;
          i10 = m + 1;
        }
        Field localField;
        int i12;
        if (i15 > zzvg.zzbxs.rpos())
        {
          m = n + 1;
          i1 = str.charAt(n);
          n = i1;
          if (i1 >= 55296)
          {
            i1 &= 0x1FFF;
            n = 13;
            i2 = m;
            m = n;
            for (;;)
            {
              n = i2 + 1;
              i2 = str.charAt(i2);
              if (i2 < 55296) {
                break;
              }
              i1 |= (i2 & 0x1FFF) << m;
              m += 13;
              i2 = n;
            }
            i1 |= i2 << m;
            m = n;
            n = i1;
          }
          if ((i15 != zzvg.zzbwd.rpos() + 51) && (i15 != zzvg.zzbwl.rpos() + 51))
          {
            if ((i15 == zzvg.zzbwg.rpos() + 51) && ((i3 & 0x1) == 1))
            {
              arrayOfObject2[((i7 / 3 << 1) + 1)] = arrayOfObject1[i];
              i += 1;
            }
          }
          else
          {
            i2 = i7 / 3;
            i1 = i + 1;
            arrayOfObject2[((i2 << 1) + 1)] = arrayOfObject1[i];
            i = i1;
          }
          n <<= 1;
          paramZzwr = arrayOfObject1[n];
          if ((paramZzwr instanceof Field)) {
            paramZzwr = (Field)paramZzwr;
          }
          for (;;)
          {
            break;
            localField = get(localClass, (String)paramZzwr);
            paramZzwr = localField;
            arrayOfObject1[n] = localField;
          }
          i12 = (int)localUnsafe.objectFieldOffset(paramZzwr);
          n += 1;
          paramZzwr = arrayOfObject1[n];
          if ((paramZzwr instanceof Field)) {
            paramZzwr = (Field)paramZzwr;
          }
          for (;;)
          {
            break;
            localField = get(localClass, (String)paramZzwr);
            paramZzwr = localField;
            arrayOfObject1[n] = localField;
          }
          long l = localUnsafe.objectFieldOffset(paramZzwr);
          i1 = (int)l;
          i2 = 0;
          n = j;
          j = m;
        }
        else
        {
          i1 = i + 1;
          paramZzwr = get(localClass, (String)arrayOfObject1[i]);
          if ((i15 != zzvg.zzbwd.rpos()) && (i15 != zzvg.zzbwl.rpos()))
          {
            if ((i15 != zzvg.zzbwv.rpos()) && (i15 != zzvg.zzbxr.rpos()))
            {
              if ((i15 != zzvg.zzbwg.rpos()) && (i15 != zzvg.zzbwy.rpos()) && (i15 != zzvg.zzbxm.rpos()))
              {
                if (i15 == zzvg.zzbxs.rpos())
                {
                  paramClass[j] = i7;
                  i2 = i7 / 3 << 1;
                  i = i1 + 1;
                  arrayOfObject2[i2] = arrayOfObject1[i1];
                  if ((i11 & 0x800) != 0)
                  {
                    m = i + 1;
                    arrayOfObject2[(i2 + 1)] = arrayOfObject1[i];
                    i = m;
                  }
                  m = j + 1;
                  break label2293;
                }
                m = j;
                i = i1;
                break label2293;
              }
              m = j;
              i = i1;
              if ((i3 & 0x1) != 1) {
                break label2293;
              }
              m = i7 / 3;
              i = i1 + 1;
              arrayOfObject2[((m << 1) + 1)] = arrayOfObject1[i1];
            }
            else
            {
              m = i7 / 3;
              i = i1 + 1;
              arrayOfObject2[((m << 1) + 1)] = arrayOfObject1[i1];
            }
            m = j;
          }
          else
          {
            arrayOfObject2[((i7 / 3 << 1) + 1)] = paramZzwr.getType();
            i = i1;
            m = j;
          }
          label2293:
          i12 = (int)localUnsafe.objectFieldOffset(paramZzwr);
          if (((i3 & 0x1) == 1) && (i15 <= zzvg.zzbwl.rpos()))
          {
            i1 = n + 1;
            i2 = str.charAt(n);
            n = i2;
            j = i1;
            if (i2 >= 55296)
            {
              n = i2 & 0x1FFF;
              j = 13;
              i2 = i1;
              i1 = n;
              for (;;)
              {
                n = i2 + 1;
                i2 = str.charAt(i2);
                if (i2 < 55296) {
                  break;
                }
                i1 |= (i2 & 0x1FFF) << j;
                j += 13;
                i2 = n;
              }
              i1 |= i2 << j;
              j = n;
              n = i1;
            }
            i1 = (i4 << 1) + n / 32;
            paramZzwr = arrayOfObject1[i1];
            if ((paramZzwr instanceof Field))
            {
              paramZzwr = (Field)paramZzwr;
            }
            else
            {
              localField = get(localClass, (String)paramZzwr);
              paramZzwr = localField;
              arrayOfObject1[i1] = localField;
            }
            i1 = (int)localUnsafe.objectFieldOffset(paramZzwr);
            i2 = n % 32;
            n = m;
          }
          else
          {
            i2 = 0;
            i1 = 0;
            j = n;
            n = m;
          }
        }
        m = i8;
        if (i15 >= 18)
        {
          m = i8;
          if (i15 <= 49)
          {
            paramClass[i8] = i12;
            m = i8 + 1;
          }
        }
        int i16 = i7 + 1;
        arrayOfInt[i7] = i9;
        i9 = i16 + 1;
        if ((i11 & 0x200) != 0) {
          i7 = 536870912;
        } else {
          i7 = 0;
        }
        if ((i11 & 0x100) != 0) {
          i8 = 268435456;
        } else {
          i8 = 0;
        }
        arrayOfInt[i16] = (i15 << 20 | i8 | i7 | i12);
        i7 = i9 + 1;
        arrayOfInt[i9] = (i2 << 20 | i1);
        i1 = j;
        i8 = m;
        j = n;
        m = i10;
      }
      return new zzwx(arrayOfInt, arrayOfObject2, i6, i5, localZzxh.zzxi(), bool, false, paramClass, k, i13, paramZzxa, paramZzwd, paramZzyb, paramZzva, paramZzwo);
    }
    ((zzxw)paramZzwr).zzxg();
    throw new NoSuchMethodError();
  }
  
  private final boolean f(Object paramObject1, Object paramObject2, int paramInt)
  {
    return a(paramObject1, paramInt) == a(paramObject2, paramInt);
  }
  
  private final Object get(Object paramObject1, int paramInt, Object paramObject2, zzyb paramZzyb)
  {
    int i = zzcaz[paramInt];
    paramObject1 = zzyh.get(paramObject1, zzbq(paramInt) & 0xFFFFF);
    if (paramObject1 == null) {
      return paramObject2;
    }
    zzvr localZzvr = zzbp(paramInt);
    if (localZzvr == null) {
      return paramObject2;
    }
    return sendRequest(paramInt, i, zzcbp.get(paramObject1), localZzvr, paramObject2, paramZzyb);
  }
  
  private static Field get(Class paramClass, String paramString)
  {
    try
    {
      localObject1 = paramClass.getDeclaredField(paramString);
      return localObject1;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      Object localObject1;
      int j;
      int i;
      Object localObject2;
      for (;;) {}
    }
    localObject1 = paramClass.getDeclaredFields();
    j = localObject1.length;
    i = 0;
    while (i < j)
    {
      localObject2 = localObject1[i];
      if (paramString.equals(((Field)localObject2).getName())) {
        return localObject2;
      }
      i += 1;
    }
    paramClass = paramClass.getName();
    localObject1 = Arrays.toString((Object[])localObject1);
    localObject2 = new StringBuilder(String.valueOf(paramString).length() + 40 + String.valueOf(paramClass).length() + String.valueOf(localObject1).length());
    ((StringBuilder)localObject2).append("Field ");
    ((StringBuilder)localObject2).append(paramString);
    ((StringBuilder)localObject2).append(" for ");
    ((StringBuilder)localObject2).append(paramClass);
    ((StringBuilder)localObject2).append(" not found. Known fields are ");
    ((StringBuilder)localObject2).append((String)localObject1);
    throw new RuntimeException(((StringBuilder)localObject2).toString());
  }
  
  private static List get(Object paramObject, long paramLong)
  {
    return (List)zzyh.get(paramObject, paramLong);
  }
  
  private final boolean get(Object paramObject, int paramInt1, int paramInt2)
  {
    return zzyh.getValue(paramObject, zzbr(paramInt2) & 0xFFFFF) == paramInt1;
  }
  
  private static void getDescriptor(zzyb paramZzyb, Object paramObject, zzyw paramZzyw)
    throws IOException
  {
    paramZzyb.before(paramZzyb.zzah(paramObject), paramZzyw);
  }
  
  private static int getFileIcon(zzyb paramZzyb, Object paramObject)
  {
    return paramZzyb.zzae(paramZzyb.zzah(paramObject));
  }
  
  private static long getId(Object paramObject, long paramLong)
  {
    return ((Long)zzyh.get(paramObject, paramLong)).longValue();
  }
  
  private static boolean getName(Object paramObject, long paramLong)
  {
    return ((Boolean)zzyh.get(paramObject, paramLong)).booleanValue();
  }
  
  private static int getValue(Object paramObject, long paramLong)
  {
    return ((Integer)zzyh.get(paramObject, paramLong)).intValue();
  }
  
  private static float invoke(Object paramObject, long paramLong)
  {
    return ((Float)zzyh.get(paramObject, paramLong)).floatValue();
  }
  
  private final void remove(zzyw paramZzyw, int paramInt1, Object paramObject, int paramInt2)
    throws IOException
  {
    if (paramObject != null) {
      paramZzyw.addHeaders(paramInt1, zzcbp.zzad(zzbo(paramInt2)), zzcbp.getList(paramObject));
    }
  }
  
  private final void replaceAll(Object paramObject, int paramInt, zzxi paramZzxi)
    throws IOException
  {
    if (zzbs(paramInt))
    {
      zzyh.add(paramObject, paramInt & 0xFFFFF, paramZzxi.zzun());
      return;
    }
    if (zzcbf)
    {
      zzyh.add(paramObject, paramInt & 0xFFFFF, paramZzxi.readString());
      return;
    }
    zzyh.add(paramObject, paramInt & 0xFFFFF, paramZzxi.zzuo());
  }
  
  private final void run(Object paramObject, zzyw paramZzyw)
    throws IOException
  {
    if (zzcbe)
    {
      localObject1 = zzcbo.getName(paramObject);
      if (!((zzvd)localObject1).isEmpty())
      {
        localObject1 = ((zzvd)localObject1).iterator();
        localObject3 = localObject1;
        localObject1 = (Map.Entry)((Iterator)localObject1).next();
        break label57;
      }
    }
    Object localObject3 = null;
    Object localObject1 = null;
    label57:
    int i = -1;
    int i1 = zzcaz.length;
    Unsafe localUnsafe = zzcay;
    int m = 0;
    int j = 0;
    Object localObject2;
    for (;;)
    {
      localObject2 = localObject1;
      if (m >= i1) {
        break;
      }
      int i2 = zzbq(m);
      int i3 = zzcaz[m];
      int i4 = (0xFF00000 & i2) >>> 20;
      int k;
      if ((!zzcbg) && (i4 <= 17))
      {
        int i5 = zzcaz[(m + 2)];
        int n = i5 & 0xFFFFF;
        k = i;
        if (n != i)
        {
          j = localUnsafe.getInt(paramObject, n);
          k = n;
        }
        n = 1 << (i5 >>> 20);
        i = k;
        k = n;
      }
      else
      {
        k = 0;
      }
      while ((localObject1 != null) && (zzcbo.getValue((Map.Entry)localObject1) <= i3))
      {
        zzcbo.add(paramZzyw, (Map.Entry)localObject1);
        if (localObject3.hasNext()) {
          localObject1 = (Map.Entry)localObject3.next();
        } else {
          localObject1 = null;
        }
      }
      long l = i2 & 0xFFFFF;
      switch (i4)
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (get(paramObject, i3, m))
        {
          paramZzyw.add(i3, localUnsafe.getObject(paramObject, l), zzbn(m));
          continue;
          if (get(paramObject, i3, m))
          {
            paramZzyw.deleteServer(i3, getId(paramObject, l));
            continue;
            if (get(paramObject, i3, m))
            {
              paramZzyw.a(i3, getValue(paramObject, l));
              continue;
              if (get(paramObject, i3, m))
              {
                paramZzyw.setRecurrence(i3, getId(paramObject, l));
                continue;
                if (get(paramObject, i3, m))
                {
                  paramZzyw.visitTableSwitchInsn(i3, getValue(paramObject, l));
                  continue;
                  if (get(paramObject, i3, m))
                  {
                    paramZzyw.create(i3, getValue(paramObject, l));
                    continue;
                    if (get(paramObject, i3, m))
                    {
                      paramZzyw.put(i3, getValue(paramObject, l));
                      continue;
                      if (get(paramObject, i3, m))
                      {
                        paramZzyw.b(i3, (zzud)localUnsafe.getObject(paramObject, l));
                        continue;
                        if (get(paramObject, i3, m))
                        {
                          paramZzyw.append(i3, localUnsafe.getObject(paramObject, l), zzbn(m));
                          continue;
                          if (get(paramObject, i3, m))
                          {
                            a(i3, localUnsafe.getObject(paramObject, l), paramZzyw);
                            continue;
                            if (get(paramObject, i3, m))
                            {
                              paramZzyw.add(i3, getName(paramObject, l));
                              continue;
                              if (get(paramObject, i3, m))
                              {
                                paramZzyw.valueOf(i3, getValue(paramObject, l));
                                continue;
                                if (get(paramObject, i3, m))
                                {
                                  paramZzyw.visitLocalVariable(i3, getId(paramObject, l));
                                  continue;
                                  if (get(paramObject, i3, m))
                                  {
                                    paramZzyw.getDrawable(i3, getValue(paramObject, l));
                                    continue;
                                    if (get(paramObject, i3, m))
                                    {
                                      paramZzyw.Refresh(i3, getId(paramObject, l));
                                      continue;
                                      if (get(paramObject, i3, m))
                                      {
                                        paramZzyw.a(i3, getId(paramObject, l));
                                        continue;
                                        if (get(paramObject, i3, m))
                                        {
                                          paramZzyw.add(i3, invoke(paramObject, l));
                                          continue;
                                          if (get(paramObject, i3, m))
                                          {
                                            paramZzyw.add(i3, value(paramObject, l));
                                            continue;
                                            remove(paramZzyw, i3, localUnsafe.getObject(paramObject, l), m);
                                            continue;
                                            zzxl.refresh(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, zzbn(m));
                                            continue;
                                            zzxl.visitAnnotation(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.send(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.connect(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.setParams(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.visitMethodInsn(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.deleteFiles(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.add(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.write(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.f(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.clear(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.b(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.go(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.refresh(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.move(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, true);
                                            continue;
                                            zzxl.visitAnnotation(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            continue;
                                            zzxl.send(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            continue;
                                            zzxl.connect(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            continue;
                                            zzxl.setParams(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            continue;
                                            zzxl.visitMethodInsn(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            continue;
                                            zzxl.deleteFiles(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            continue;
                                            zzxl.visitJumpInsn(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw);
                                            continue;
                                            zzxl.run(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, zzbn(m));
                                            continue;
                                            zzxl.b(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw);
                                            continue;
                                            zzxl.add(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.write(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.f(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.clear(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.b(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.go(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.refresh(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            zzxl.move(zzcaz[m], (List)localUnsafe.getObject(paramObject, l), paramZzyw, false);
                                            break;
                                            if ((k & j) != 0)
                                            {
                                              paramZzyw.add(i3, localUnsafe.getObject(paramObject, l), zzbn(m));
                                              break;
                                              if ((k & j) != 0)
                                              {
                                                paramZzyw.deleteServer(i3, localUnsafe.getLong(paramObject, l));
                                                break;
                                                if ((k & j) != 0)
                                                {
                                                  paramZzyw.a(i3, localUnsafe.getInt(paramObject, l));
                                                  break;
                                                  if ((k & j) != 0)
                                                  {
                                                    paramZzyw.setRecurrence(i3, localUnsafe.getLong(paramObject, l));
                                                    break;
                                                    if ((k & j) != 0)
                                                    {
                                                      paramZzyw.visitTableSwitchInsn(i3, localUnsafe.getInt(paramObject, l));
                                                      break;
                                                      if ((k & j) != 0)
                                                      {
                                                        paramZzyw.create(i3, localUnsafe.getInt(paramObject, l));
                                                        break;
                                                        if ((k & j) != 0)
                                                        {
                                                          paramZzyw.put(i3, localUnsafe.getInt(paramObject, l));
                                                          break;
                                                          if ((k & j) != 0)
                                                          {
                                                            paramZzyw.b(i3, (zzud)localUnsafe.getObject(paramObject, l));
                                                            break;
                                                            if ((k & j) != 0)
                                                            {
                                                              paramZzyw.append(i3, localUnsafe.getObject(paramObject, l), zzbn(m));
                                                              break;
                                                              if ((k & j) != 0)
                                                              {
                                                                a(i3, localUnsafe.getObject(paramObject, l), paramZzyw);
                                                                break;
                                                                if ((k & j) != 0)
                                                                {
                                                                  paramZzyw.add(i3, zzyh.getName(paramObject, l));
                                                                  break;
                                                                  if ((k & j) != 0)
                                                                  {
                                                                    paramZzyw.valueOf(i3, localUnsafe.getInt(paramObject, l));
                                                                    break;
                                                                    if ((k & j) != 0)
                                                                    {
                                                                      paramZzyw.visitLocalVariable(i3, localUnsafe.getLong(paramObject, l));
                                                                      break;
                                                                      if ((k & j) != 0)
                                                                      {
                                                                        paramZzyw.getDrawable(i3, localUnsafe.getInt(paramObject, l));
                                                                        break;
                                                                        if ((k & j) != 0)
                                                                        {
                                                                          paramZzyw.Refresh(i3, localUnsafe.getLong(paramObject, l));
                                                                          break;
                                                                          if ((k & j) != 0)
                                                                          {
                                                                            paramZzyw.a(i3, localUnsafe.getLong(paramObject, l));
                                                                            break;
                                                                            if ((k & j) != 0)
                                                                            {
                                                                              paramZzyw.add(i3, zzyh.invoke(paramObject, l));
                                                                              break;
                                                                              if ((k & j) != 0) {
                                                                                paramZzyw.add(i3, zzyh.a(paramObject, l));
                                                                              }
                                                                            }
                                                                          }
                                                                        }
                                                                      }
                                                                    }
                                                                  }
                                                                }
                                                              }
                                                            }
                                                          }
                                                        }
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      m += 3;
    }
    while (localObject2 != null)
    {
      zzcbo.add(paramZzyw, (Map.Entry)localObject2);
      if (localObject3.hasNext()) {
        localObject2 = (Map.Entry)localObject3.next();
      } else {
        localObject2 = null;
      }
    }
    getDescriptor(zzcbn, paramObject, paramZzyw);
  }
  
  private final Object sendRequest(int paramInt1, int paramInt2, Map paramMap, zzvr paramZzvr, Object paramObject, zzyb paramZzyb)
  {
    zzwm localZzwm = zzcbp.zzad(zzbo(paramInt1));
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (!paramZzvr.get(((Integer)localEntry.getValue()).intValue()))
      {
        paramMap = paramObject;
        if (paramObject == null) {
          paramMap = paramZzyb.zzye();
        }
        paramObject = zzud.zzam(zzwl.get(localZzwm, localEntry.getKey(), localEntry.getValue()));
        zzut localZzut = paramObject.zzuf();
        try
        {
          zzwl.setHeader(localZzut, localZzwm, localEntry.getKey(), localEntry.getValue());
          paramZzyb.read(paramMap, paramInt2, paramObject.zzue());
          localIterator.remove();
          paramObject = paramMap;
        }
        catch (IOException paramMap)
        {
          throw new RuntimeException(paramMap);
        }
      }
    }
    return paramObject;
  }
  
  private static double value(Object paramObject, long paramLong)
  {
    return ((Double)zzyh.get(paramObject, paramLong)).doubleValue();
  }
  
  private final void visitInsn(Object paramObject1, Object paramObject2, int paramInt)
  {
    int i = zzbq(paramInt);
    int j = zzcaz[paramInt];
    long l = i & 0xFFFFF;
    if (!get(paramObject2, j, paramInt)) {
      return;
    }
    Object localObject = zzyh.get(paramObject1, l);
    paramObject2 = zzyh.get(paramObject2, l);
    if ((localObject != null) && (paramObject2 != null))
    {
      zzyh.add(paramObject1, l, zzvo.add(localObject, paramObject2));
      add(paramObject1, j, paramInt);
      return;
    }
    if (paramObject2 != null)
    {
      zzyh.add(paramObject1, l, paramObject2);
      add(paramObject1, j, paramInt);
    }
  }
  
  private final void write(Object paramObject, int paramInt)
  {
    if (zzcbg) {
      return;
    }
    paramInt = zzbr(paramInt);
    long l = paramInt & 0xFFFFF;
    zzyh.a(paramObject, l, zzyh.getValue(paramObject, l) | 1 << (paramInt >>> 20));
  }
  
  private final zzxj zzbn(int paramInt)
  {
    paramInt = paramInt / 3 << 1;
    zzxj localZzxj = (zzxj)zzcba[paramInt];
    if (localZzxj != null) {
      return localZzxj;
    }
    localZzxj = zzxf.zzxn().getAttributeValue((Class)zzcba[(paramInt + 1)]);
    zzcba[paramInt] = localZzxj;
    return localZzxj;
  }
  
  private final Object zzbo(int paramInt)
  {
    return zzcba[(paramInt / 3 << 1)];
  }
  
  private final zzvr zzbp(int paramInt)
  {
    return (zzvr)zzcba[((paramInt / 3 << 1) + 1)];
  }
  
  private final int zzbq(int paramInt)
  {
    return zzcaz[(paramInt + 1)];
  }
  
  private final int zzbr(int paramInt)
  {
    return zzcaz[(paramInt + 2)];
  }
  
  private static boolean zzbs(int paramInt)
  {
    return (paramInt & 0x20000000) != 0;
  }
  
  public final void a(Object paramObject, zzyw paramZzyw)
    throws IOException
  {
    Object localObject1;
    Object localObject3;
    label78:
    int i;
    Object localObject2;
    int j;
    int k;
    if (paramZzyw.zzvj() == zzvm.zze.zzbzf)
    {
      getDescriptor(zzcbn, paramObject, paramZzyw);
      if (zzcbe)
      {
        localObject1 = zzcbo.getName(paramObject);
        if (!((zzvd)localObject1).isEmpty())
        {
          localObject1 = ((zzvd)localObject1).descendingIterator();
          localObject3 = localObject1;
          localObject1 = (Map.Entry)((Iterator)localObject1).next();
          break label78;
        }
      }
      localObject3 = null;
      localObject1 = null;
      i = zzcaz.length - 3;
      for (;;)
      {
        localObject2 = localObject1;
        if (i < 0) {
          break;
        }
        j = zzbq(i);
        k = zzcaz[i];
        while ((localObject1 != null) && (zzcbo.getValue((Map.Entry)localObject1) > k))
        {
          zzcbo.add(paramZzyw, (Map.Entry)localObject1);
          if (localObject3.hasNext()) {
            localObject1 = (Map.Entry)localObject3.next();
          } else {
            localObject1 = null;
          }
        }
        switch ((j & 0xFF00000) >>> 20)
        {
        default: 
          break;
        case 68: 
          if (get(paramObject, k, i)) {
            paramZzyw.add(k, zzyh.get(paramObject, j & 0xFFFFF), zzbn(i));
          }
          break;
        case 67: 
          if (get(paramObject, k, i)) {
            paramZzyw.deleteServer(k, getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 66: 
          if (get(paramObject, k, i)) {
            paramZzyw.a(k, getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 65: 
          if (get(paramObject, k, i)) {
            paramZzyw.setRecurrence(k, getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 64: 
          if (get(paramObject, k, i)) {
            paramZzyw.visitTableSwitchInsn(k, getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 63: 
          if (get(paramObject, k, i)) {
            paramZzyw.create(k, getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 62: 
          if (get(paramObject, k, i)) {
            paramZzyw.put(k, getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 61: 
          if (get(paramObject, k, i)) {
            paramZzyw.b(k, (zzud)zzyh.get(paramObject, j & 0xFFFFF));
          }
          break;
        case 60: 
          if (get(paramObject, k, i)) {
            paramZzyw.append(k, zzyh.get(paramObject, j & 0xFFFFF), zzbn(i));
          }
          break;
        case 59: 
          if (get(paramObject, k, i)) {
            a(k, zzyh.get(paramObject, j & 0xFFFFF), paramZzyw);
          }
          break;
        case 58: 
          if (get(paramObject, k, i)) {
            paramZzyw.add(k, getName(paramObject, j & 0xFFFFF));
          }
          break;
        case 57: 
          if (get(paramObject, k, i)) {
            paramZzyw.valueOf(k, getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 56: 
          if (get(paramObject, k, i)) {
            paramZzyw.visitLocalVariable(k, getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 55: 
          if (get(paramObject, k, i)) {
            paramZzyw.getDrawable(k, getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 54: 
          if (get(paramObject, k, i)) {
            paramZzyw.Refresh(k, getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 53: 
          if (get(paramObject, k, i)) {
            paramZzyw.a(k, getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 52: 
          if (get(paramObject, k, i)) {
            paramZzyw.add(k, invoke(paramObject, j & 0xFFFFF));
          }
          break;
        case 51: 
          if (get(paramObject, k, i)) {
            paramZzyw.add(k, value(paramObject, j & 0xFFFFF));
          }
          break;
        case 50: 
          remove(paramZzyw, k, zzyh.get(paramObject, j & 0xFFFFF), i);
          break;
        case 49: 
          zzxl.refresh(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, zzbn(i));
          break;
        case 48: 
          zzxl.visitAnnotation(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 47: 
          zzxl.send(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 46: 
          zzxl.connect(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 45: 
          zzxl.setParams(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 44: 
          zzxl.visitMethodInsn(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 43: 
          zzxl.deleteFiles(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 42: 
          zzxl.add(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 41: 
          zzxl.write(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 40: 
          zzxl.f(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 39: 
          zzxl.clear(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 38: 
          zzxl.b(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 37: 
          zzxl.go(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 36: 
          zzxl.refresh(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 35: 
          zzxl.move(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, true);
          break;
        case 34: 
          zzxl.visitAnnotation(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 33: 
          zzxl.send(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 32: 
          zzxl.connect(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 31: 
          zzxl.setParams(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 30: 
          zzxl.visitMethodInsn(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 29: 
          zzxl.deleteFiles(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 28: 
          zzxl.visitJumpInsn(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw);
          break;
        case 27: 
          zzxl.run(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, zzbn(i));
          break;
        case 26: 
          zzxl.b(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw);
          break;
        case 25: 
          zzxl.add(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 24: 
          zzxl.write(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 23: 
          zzxl.f(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 22: 
          zzxl.clear(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 21: 
          zzxl.b(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 20: 
          zzxl.go(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 19: 
          zzxl.refresh(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 18: 
          zzxl.move(zzcaz[i], (List)zzyh.get(paramObject, j & 0xFFFFF), paramZzyw, false);
          break;
        case 17: 
          if (a(paramObject, i)) {
            paramZzyw.add(k, zzyh.get(paramObject, j & 0xFFFFF), zzbn(i));
          }
          break;
        case 16: 
          if (a(paramObject, i)) {
            paramZzyw.deleteServer(k, zzyh.getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 15: 
          if (a(paramObject, i)) {
            paramZzyw.a(k, zzyh.getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 14: 
          if (a(paramObject, i)) {
            paramZzyw.setRecurrence(k, zzyh.getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 13: 
          if (a(paramObject, i)) {
            paramZzyw.visitTableSwitchInsn(k, zzyh.getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 12: 
          if (a(paramObject, i)) {
            paramZzyw.create(k, zzyh.getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 11: 
          if (a(paramObject, i)) {
            paramZzyw.put(k, zzyh.getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 10: 
          if (a(paramObject, i)) {
            paramZzyw.b(k, (zzud)zzyh.get(paramObject, j & 0xFFFFF));
          }
          break;
        case 9: 
          if (a(paramObject, i)) {
            paramZzyw.append(k, zzyh.get(paramObject, j & 0xFFFFF), zzbn(i));
          }
          break;
        case 8: 
          if (a(paramObject, i)) {
            a(k, zzyh.get(paramObject, j & 0xFFFFF), paramZzyw);
          }
          break;
        case 7: 
          if (a(paramObject, i)) {
            paramZzyw.add(k, zzyh.getName(paramObject, j & 0xFFFFF));
          }
          break;
        case 6: 
          if (a(paramObject, i)) {
            paramZzyw.valueOf(k, zzyh.getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 5: 
          if (a(paramObject, i)) {
            paramZzyw.visitLocalVariable(k, zzyh.getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 4: 
          if (a(paramObject, i)) {
            paramZzyw.getDrawable(k, zzyh.getValue(paramObject, j & 0xFFFFF));
          }
          break;
        case 3: 
          if (a(paramObject, i)) {
            paramZzyw.Refresh(k, zzyh.getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 2: 
          if (a(paramObject, i)) {
            paramZzyw.a(k, zzyh.getId(paramObject, j & 0xFFFFF));
          }
          break;
        case 1: 
          if (a(paramObject, i)) {
            paramZzyw.add(k, zzyh.invoke(paramObject, j & 0xFFFFF));
          }
          break;
        case 0: 
          if (a(paramObject, i)) {
            paramZzyw.add(k, zzyh.a(paramObject, j & 0xFFFFF));
          }
          break;
        }
        i -= 3;
      }
      while (localObject2 != null)
      {
        zzcbo.add(paramZzyw, (Map.Entry)localObject2);
        if (localObject3.hasNext()) {
          localObject2 = (Map.Entry)localObject3.next();
        } else {
          localObject2 = null;
        }
      }
      return;
    }
    if (zzcbg)
    {
      if (zzcbe)
      {
        localObject1 = zzcbo.getName(paramObject);
        if (!((zzvd)localObject1).isEmpty())
        {
          localObject1 = ((zzvd)localObject1).iterator();
          localObject3 = localObject1;
          localObject1 = (Map.Entry)((Iterator)localObject1).next();
          break label2890;
        }
      }
      localObject3 = null;
      localObject1 = null;
      label2890:
      j = zzcaz.length;
      i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= j) {
          break;
        }
        k = zzbq(i);
        int m = zzcaz[i];
        while ((localObject1 != null) && (zzcbo.getValue((Map.Entry)localObject1) <= m))
        {
          zzcbo.add(paramZzyw, (Map.Entry)localObject1);
          if (localObject3.hasNext()) {
            localObject1 = (Map.Entry)localObject3.next();
          } else {
            localObject1 = null;
          }
        }
        switch ((k & 0xFF00000) >>> 20)
        {
        default: 
          break;
        case 68: 
          if (get(paramObject, m, i)) {
            paramZzyw.add(m, zzyh.get(paramObject, k & 0xFFFFF), zzbn(i));
          }
          break;
        case 67: 
          if (get(paramObject, m, i)) {
            paramZzyw.deleteServer(m, getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 66: 
          if (get(paramObject, m, i)) {
            paramZzyw.a(m, getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 65: 
          if (get(paramObject, m, i)) {
            paramZzyw.setRecurrence(m, getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 64: 
          if (get(paramObject, m, i)) {
            paramZzyw.visitTableSwitchInsn(m, getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 63: 
          if (get(paramObject, m, i)) {
            paramZzyw.create(m, getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 62: 
          if (get(paramObject, m, i)) {
            paramZzyw.put(m, getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 61: 
          if (get(paramObject, m, i)) {
            paramZzyw.b(m, (zzud)zzyh.get(paramObject, k & 0xFFFFF));
          }
          break;
        case 60: 
          if (get(paramObject, m, i)) {
            paramZzyw.append(m, zzyh.get(paramObject, k & 0xFFFFF), zzbn(i));
          }
          break;
        case 59: 
          if (get(paramObject, m, i)) {
            a(m, zzyh.get(paramObject, k & 0xFFFFF), paramZzyw);
          }
          break;
        case 58: 
          if (get(paramObject, m, i)) {
            paramZzyw.add(m, getName(paramObject, k & 0xFFFFF));
          }
          break;
        case 57: 
          if (get(paramObject, m, i)) {
            paramZzyw.valueOf(m, getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 56: 
          if (get(paramObject, m, i)) {
            paramZzyw.visitLocalVariable(m, getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 55: 
          if (get(paramObject, m, i)) {
            paramZzyw.getDrawable(m, getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 54: 
          if (get(paramObject, m, i)) {
            paramZzyw.Refresh(m, getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 53: 
          if (get(paramObject, m, i)) {
            paramZzyw.a(m, getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 52: 
          if (get(paramObject, m, i)) {
            paramZzyw.add(m, invoke(paramObject, k & 0xFFFFF));
          }
          break;
        case 51: 
          if (get(paramObject, m, i)) {
            paramZzyw.add(m, value(paramObject, k & 0xFFFFF));
          }
          break;
        case 50: 
          remove(paramZzyw, m, zzyh.get(paramObject, k & 0xFFFFF), i);
          break;
        case 49: 
          zzxl.refresh(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, zzbn(i));
          break;
        case 48: 
          zzxl.visitAnnotation(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 47: 
          zzxl.send(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 46: 
          zzxl.connect(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 45: 
          zzxl.setParams(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 44: 
          zzxl.visitMethodInsn(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 43: 
          zzxl.deleteFiles(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 42: 
          zzxl.add(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 41: 
          zzxl.write(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 40: 
          zzxl.f(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 39: 
          zzxl.clear(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 38: 
          zzxl.b(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 37: 
          zzxl.go(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 36: 
          zzxl.refresh(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 35: 
          zzxl.move(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, true);
          break;
        case 34: 
          zzxl.visitAnnotation(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 33: 
          zzxl.send(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 32: 
          zzxl.connect(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 31: 
          zzxl.setParams(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 30: 
          zzxl.visitMethodInsn(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 29: 
          zzxl.deleteFiles(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 28: 
          zzxl.visitJumpInsn(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw);
          break;
        case 27: 
          zzxl.run(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, zzbn(i));
          break;
        case 26: 
          zzxl.b(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw);
          break;
        case 25: 
          zzxl.add(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 24: 
          zzxl.write(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 23: 
          zzxl.f(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 22: 
          zzxl.clear(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 21: 
          zzxl.b(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 20: 
          zzxl.go(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 19: 
          zzxl.refresh(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 18: 
          zzxl.move(zzcaz[i], (List)zzyh.get(paramObject, k & 0xFFFFF), paramZzyw, false);
          break;
        case 17: 
          if (a(paramObject, i)) {
            paramZzyw.add(m, zzyh.get(paramObject, k & 0xFFFFF), zzbn(i));
          }
          break;
        case 16: 
          if (a(paramObject, i)) {
            paramZzyw.deleteServer(m, zzyh.getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 15: 
          if (a(paramObject, i)) {
            paramZzyw.a(m, zzyh.getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 14: 
          if (a(paramObject, i)) {
            paramZzyw.setRecurrence(m, zzyh.getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 13: 
          if (a(paramObject, i)) {
            paramZzyw.visitTableSwitchInsn(m, zzyh.getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 12: 
          if (a(paramObject, i)) {
            paramZzyw.create(m, zzyh.getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 11: 
          if (a(paramObject, i)) {
            paramZzyw.put(m, zzyh.getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 10: 
          if (a(paramObject, i)) {
            paramZzyw.b(m, (zzud)zzyh.get(paramObject, k & 0xFFFFF));
          }
          break;
        case 9: 
          if (a(paramObject, i)) {
            paramZzyw.append(m, zzyh.get(paramObject, k & 0xFFFFF), zzbn(i));
          }
          break;
        case 8: 
          if (a(paramObject, i)) {
            a(m, zzyh.get(paramObject, k & 0xFFFFF), paramZzyw);
          }
          break;
        case 7: 
          if (a(paramObject, i)) {
            paramZzyw.add(m, zzyh.getName(paramObject, k & 0xFFFFF));
          }
          break;
        case 6: 
          if (a(paramObject, i)) {
            paramZzyw.valueOf(m, zzyh.getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 5: 
          if (a(paramObject, i)) {
            paramZzyw.visitLocalVariable(m, zzyh.getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 4: 
          if (a(paramObject, i)) {
            paramZzyw.getDrawable(m, zzyh.getValue(paramObject, k & 0xFFFFF));
          }
          break;
        case 3: 
          if (a(paramObject, i)) {
            paramZzyw.Refresh(m, zzyh.getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 2: 
          if (a(paramObject, i)) {
            paramZzyw.a(m, zzyh.getId(paramObject, k & 0xFFFFF));
          }
          break;
        case 1: 
          if (a(paramObject, i)) {
            paramZzyw.add(m, zzyh.invoke(paramObject, k & 0xFFFFF));
          }
          break;
        case 0: 
          if (a(paramObject, i)) {
            paramZzyw.add(m, zzyh.a(paramObject, k & 0xFFFFF));
          }
          break;
        }
        i += 3;
      }
      while (localObject2 != null)
      {
        zzcbo.add(paramZzyw, (Map.Entry)localObject2);
        if (localObject3.hasNext()) {
          localObject2 = (Map.Entry)localObject3.next();
        } else {
          localObject2 = null;
        }
      }
      getDescriptor(zzcbn, paramObject, paramZzyw);
      return;
    }
    run(paramObject, paramZzyw);
  }
  
  public final void a(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 != null)
    {
      int i = 0;
      while (i < zzcaz.length)
      {
        int j = zzbq(i);
        long l = 0xFFFFF & j;
        int k = zzcaz[i];
        switch ((j & 0xFF00000) >>> 20)
        {
        default: 
          break;
        case 68: 
          visitInsn(paramObject1, paramObject2, i);
          break;
        case 61: 
        case 62: 
        case 63: 
        case 64: 
        case 65: 
        case 66: 
        case 67: 
          if (get(paramObject2, k, i))
          {
            zzyh.add(paramObject1, l, zzyh.get(paramObject2, l));
            add(paramObject1, k, i);
          }
          break;
        case 60: 
          visitInsn(paramObject1, paramObject2, i);
          break;
        case 51: 
        case 52: 
        case 53: 
        case 54: 
        case 55: 
        case 56: 
        case 57: 
        case 58: 
        case 59: 
          if (get(paramObject2, k, i))
          {
            zzyh.add(paramObject1, l, zzyh.get(paramObject2, l));
            add(paramObject1, k, i);
          }
          break;
        case 50: 
          zzxl.visitMethodInsn(zzcbp, paramObject1, paramObject2, l);
          break;
        case 18: 
        case 19: 
        case 20: 
        case 21: 
        case 22: 
        case 23: 
        case 24: 
        case 25: 
        case 26: 
        case 27: 
        case 28: 
        case 29: 
        case 30: 
        case 31: 
        case 32: 
        case 33: 
        case 34: 
        case 35: 
        case 36: 
        case 37: 
        case 38: 
        case 39: 
        case 40: 
        case 41: 
        case 42: 
        case 43: 
        case 44: 
        case 45: 
        case 46: 
        case 47: 
        case 48: 
        case 49: 
          zzcbm.visitTypeInsn(paramObject1, paramObject2, l);
          break;
        case 17: 
          a(paramObject1, paramObject2, i);
          break;
        case 16: 
          if (a(paramObject2, i))
          {
            zzyh.close(paramObject1, l, zzyh.getId(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 15: 
          if (a(paramObject2, i))
          {
            zzyh.a(paramObject1, l, zzyh.getValue(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 14: 
          if (a(paramObject2, i))
          {
            zzyh.close(paramObject1, l, zzyh.getId(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 13: 
          if (a(paramObject2, i))
          {
            zzyh.a(paramObject1, l, zzyh.getValue(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 12: 
          if (a(paramObject2, i))
          {
            zzyh.a(paramObject1, l, zzyh.getValue(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 11: 
          if (a(paramObject2, i))
          {
            zzyh.a(paramObject1, l, zzyh.getValue(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 10: 
          if (a(paramObject2, i))
          {
            zzyh.add(paramObject1, l, zzyh.get(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 9: 
          a(paramObject1, paramObject2, i);
          break;
        case 8: 
          if (a(paramObject2, i))
          {
            zzyh.add(paramObject1, l, zzyh.get(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 7: 
          if (a(paramObject2, i))
          {
            zzyh.read(paramObject1, l, zzyh.getName(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 6: 
          if (a(paramObject2, i))
          {
            zzyh.a(paramObject1, l, zzyh.getValue(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 5: 
          if (a(paramObject2, i))
          {
            zzyh.close(paramObject1, l, zzyh.getId(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 4: 
          if (a(paramObject2, i))
          {
            zzyh.a(paramObject1, l, zzyh.getValue(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 3: 
          if (a(paramObject2, i))
          {
            zzyh.close(paramObject1, l, zzyh.getId(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 2: 
          if (a(paramObject2, i))
          {
            zzyh.close(paramObject1, l, zzyh.getId(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 1: 
          if (a(paramObject2, i))
          {
            zzyh.append(paramObject1, l, zzyh.invoke(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        case 0: 
          if (a(paramObject2, i))
          {
            zzyh.append(paramObject1, l, zzyh.a(paramObject2, l));
            write(paramObject1, i);
          }
          break;
        }
        i += 3;
      }
      if (!zzcbg)
      {
        zzxl.div(zzcbn, paramObject1, paramObject2);
        if (zzcbe) {
          zzxl.setTitle(zzcbo, paramObject1, paramObject2);
        }
      }
    }
    else
    {
      throw new NullPointerException();
    }
  }
  
  /* Error */
  public final boolean equals(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 58	com/google/android/android/internal/measurement/zzwx:zzcaz	[I
    //   4: arraylength
    //   5: istore 4
    //   7: iconst_0
    //   8: istore_3
    //   9: iconst_1
    //   10: istore 6
    //   12: iload_3
    //   13: iload 4
    //   15: if_icmpge +1218 -> 1233
    //   18: aload_0
    //   19: iload_3
    //   20: invokespecial 122	com/google/android/android/internal/measurement/zzwx:zzbq	(I)I
    //   23: istore 5
    //   25: iload 5
    //   27: ldc 123
    //   29: iand
    //   30: i2l
    //   31: lstore 7
    //   33: iload 5
    //   35: ldc -112
    //   37: iand
    //   38: bipush 20
    //   40: iushr
    //   41: lookupswitch	default:+563->604, 0:+1149->1190, 1:+1121->1162, 2:+1092->1133, 3:+1063->1104, 4:+1035->1076, 5:+1006->1047, 6:+978->1019, 7:+950->991, 8:+919->960, 9:+888->929, 10:+857->898, 11:+829->870, 12:+801->842, 13:+773->814, 14:+744->785, 15:+716->757, 16:+687->728, 17:+656->697, 18:+636->677, 19:+636->677, 20:+636->677, 21:+636->677, 22:+636->677, 23:+636->677, 24:+636->677, 25:+636->677, 26:+636->677, 27:+636->677, 28:+636->677, 29:+636->677, 30:+636->677, 31:+636->677, 32:+636->677, 33:+636->677, 34:+636->677, 35:+636->677, 36:+636->677, 37:+636->677, 38:+636->677, 39:+636->677, 40:+636->677, 41:+636->677, 42:+636->677, 43:+636->677, 44:+636->677, 45:+636->677, 46:+636->677, 47:+636->677, 48:+636->677, 49:+636->677, 50:+616->657, 51:+569->610, 52:+569->610, 53:+569->610, 54:+569->610, 55:+569->610, 56:+569->610, 57:+569->610, 58:+569->610, 59:+569->610, 60:+569->610, 61:+569->610, 62:+569->610, 63:+569->610, 64:+569->610, 65:+569->610, 66:+569->610, 67:+569->610, 68:+569->610
    //   604: goto +3 -> 607
    //   607: goto +612 -> 1219
    //   610: aload_0
    //   611: iload_3
    //   612: invokespecial 181	com/google/android/android/internal/measurement/zzwx:zzbr	(I)I
    //   615: ldc 123
    //   617: iand
    //   618: i2l
    //   619: lstore 9
    //   621: aload_1
    //   622: lload 9
    //   624: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   627: aload_2
    //   628: lload 9
    //   630: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   633: if_icmpne +583 -> 1216
    //   636: aload_1
    //   637: lload 7
    //   639: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   642: aload_2
    //   643: lload 7
    //   645: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   648: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   651: ifne +568 -> 1219
    //   654: goto +562 -> 1216
    //   657: aload_1
    //   658: lload 7
    //   660: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   663: aload_2
    //   664: lload 7
    //   666: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   669: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   672: istore 6
    //   674: goto +545 -> 1219
    //   677: aload_1
    //   678: lload 7
    //   680: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   683: aload_2
    //   684: lload 7
    //   686: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   689: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   692: istore 6
    //   694: goto +525 -> 1219
    //   697: aload_0
    //   698: aload_1
    //   699: aload_2
    //   700: iload_3
    //   701: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   704: ifeq +512 -> 1216
    //   707: aload_1
    //   708: lload 7
    //   710: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   713: aload_2
    //   714: lload 7
    //   716: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   719: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   722: ifne +497 -> 1219
    //   725: goto +491 -> 1216
    //   728: aload_0
    //   729: aload_1
    //   730: aload_2
    //   731: iload_3
    //   732: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   735: ifeq +481 -> 1216
    //   738: aload_1
    //   739: lload 7
    //   741: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   744: aload_2
    //   745: lload 7
    //   747: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   750: lcmp
    //   751: ifeq +468 -> 1219
    //   754: goto +462 -> 1216
    //   757: aload_0
    //   758: aload_1
    //   759: aload_2
    //   760: iload_3
    //   761: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   764: ifeq +452 -> 1216
    //   767: aload_1
    //   768: lload 7
    //   770: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   773: aload_2
    //   774: lload 7
    //   776: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   779: if_icmpeq +440 -> 1219
    //   782: goto +434 -> 1216
    //   785: aload_0
    //   786: aload_1
    //   787: aload_2
    //   788: iload_3
    //   789: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   792: ifeq +424 -> 1216
    //   795: aload_1
    //   796: lload 7
    //   798: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   801: aload_2
    //   802: lload 7
    //   804: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   807: lcmp
    //   808: ifeq +411 -> 1219
    //   811: goto +405 -> 1216
    //   814: aload_0
    //   815: aload_1
    //   816: aload_2
    //   817: iload_3
    //   818: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   821: ifeq +395 -> 1216
    //   824: aload_1
    //   825: lload 7
    //   827: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   830: aload_2
    //   831: lload 7
    //   833: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   836: if_icmpeq +383 -> 1219
    //   839: goto +377 -> 1216
    //   842: aload_0
    //   843: aload_1
    //   844: aload_2
    //   845: iload_3
    //   846: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   849: ifeq +367 -> 1216
    //   852: aload_1
    //   853: lload 7
    //   855: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   858: aload_2
    //   859: lload 7
    //   861: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   864: if_icmpeq +355 -> 1219
    //   867: goto +349 -> 1216
    //   870: aload_0
    //   871: aload_1
    //   872: aload_2
    //   873: iload_3
    //   874: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   877: ifeq +339 -> 1216
    //   880: aload_1
    //   881: lload 7
    //   883: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   886: aload_2
    //   887: lload 7
    //   889: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   892: if_icmpeq +327 -> 1219
    //   895: goto +321 -> 1216
    //   898: aload_0
    //   899: aload_1
    //   900: aload_2
    //   901: iload_3
    //   902: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   905: ifeq +311 -> 1216
    //   908: aload_1
    //   909: lload 7
    //   911: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   914: aload_2
    //   915: lload 7
    //   917: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   920: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   923: ifne +296 -> 1219
    //   926: goto +290 -> 1216
    //   929: aload_0
    //   930: aload_1
    //   931: aload_2
    //   932: iload_3
    //   933: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   936: ifeq +280 -> 1216
    //   939: aload_1
    //   940: lload 7
    //   942: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   945: aload_2
    //   946: lload 7
    //   948: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   951: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   954: ifne +265 -> 1219
    //   957: goto +259 -> 1216
    //   960: aload_0
    //   961: aload_1
    //   962: aload_2
    //   963: iload_3
    //   964: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   967: ifeq +249 -> 1216
    //   970: aload_1
    //   971: lload 7
    //   973: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   976: aload_2
    //   977: lload 7
    //   979: invokestatic 130	com/google/android/android/internal/measurement/zzyh:get	(Ljava/lang/Object;J)Ljava/lang/Object;
    //   982: invokestatic 701	com/google/android/android/internal/measurement/zzxl:eq	(Ljava/lang/Object;Ljava/lang/Object;)Z
    //   985: ifne +234 -> 1219
    //   988: goto +228 -> 1216
    //   991: aload_0
    //   992: aload_1
    //   993: aload_2
    //   994: iload_3
    //   995: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   998: ifeq +218 -> 1216
    //   1001: aload_1
    //   1002: lload 7
    //   1004: invokestatic 171	com/google/android/android/internal/measurement/zzyh:getName	(Ljava/lang/Object;J)Z
    //   1007: aload_2
    //   1008: lload 7
    //   1010: invokestatic 171	com/google/android/android/internal/measurement/zzyh:getName	(Ljava/lang/Object;J)Z
    //   1013: if_icmpeq +206 -> 1219
    //   1016: goto +200 -> 1216
    //   1019: aload_0
    //   1020: aload_1
    //   1021: aload_2
    //   1022: iload_3
    //   1023: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1026: ifeq +190 -> 1216
    //   1029: aload_1
    //   1030: lload 7
    //   1032: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   1035: aload_2
    //   1036: lload 7
    //   1038: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   1041: if_icmpeq +178 -> 1219
    //   1044: goto +172 -> 1216
    //   1047: aload_0
    //   1048: aload_1
    //   1049: aload_2
    //   1050: iload_3
    //   1051: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1054: ifeq +162 -> 1216
    //   1057: aload_1
    //   1058: lload 7
    //   1060: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1063: aload_2
    //   1064: lload 7
    //   1066: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1069: lcmp
    //   1070: ifeq +149 -> 1219
    //   1073: goto +143 -> 1216
    //   1076: aload_0
    //   1077: aload_1
    //   1078: aload_2
    //   1079: iload_3
    //   1080: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1083: ifeq +133 -> 1216
    //   1086: aload_1
    //   1087: lload 7
    //   1089: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   1092: aload_2
    //   1093: lload 7
    //   1095: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   1098: if_icmpeq +121 -> 1219
    //   1101: goto +115 -> 1216
    //   1104: aload_0
    //   1105: aload_1
    //   1106: aload_2
    //   1107: iload_3
    //   1108: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1111: ifeq +105 -> 1216
    //   1114: aload_1
    //   1115: lload 7
    //   1117: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1120: aload_2
    //   1121: lload 7
    //   1123: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1126: lcmp
    //   1127: ifeq +92 -> 1219
    //   1130: goto +86 -> 1216
    //   1133: aload_0
    //   1134: aload_1
    //   1135: aload_2
    //   1136: iload_3
    //   1137: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1140: ifeq +76 -> 1216
    //   1143: aload_1
    //   1144: lload 7
    //   1146: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1149: aload_2
    //   1150: lload 7
    //   1152: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1155: lcmp
    //   1156: ifeq +63 -> 1219
    //   1159: goto +57 -> 1216
    //   1162: aload_0
    //   1163: aload_1
    //   1164: aload_2
    //   1165: iload_3
    //   1166: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1169: ifeq +47 -> 1216
    //   1172: aload_1
    //   1173: lload 7
    //   1175: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   1178: aload_2
    //   1179: lload 7
    //   1181: invokestatic 155	com/google/android/android/internal/measurement/zzyh:getValue	(Ljava/lang/Object;J)I
    //   1184: if_icmpeq +35 -> 1219
    //   1187: goto +29 -> 1216
    //   1190: aload_0
    //   1191: aload_1
    //   1192: aload_2
    //   1193: iload_3
    //   1194: invokespecial 703	com/google/android/android/internal/measurement/zzwx:f	(Ljava/lang/Object;Ljava/lang/Object;I)Z
    //   1197: ifeq +19 -> 1216
    //   1200: aload_1
    //   1201: lload 7
    //   1203: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1206: aload_2
    //   1207: lload 7
    //   1209: invokestatic 151	com/google/android/android/internal/measurement/zzyh:getId	(Ljava/lang/Object;J)J
    //   1212: lcmp
    //   1213: ifeq +6 -> 1219
    //   1216: iconst_0
    //   1217: istore 6
    //   1219: iload 6
    //   1221: ifne +5 -> 1226
    //   1224: iconst_0
    //   1225: ireturn
    //   1226: iload_3
    //   1227: iconst_3
    //   1228: iadd
    //   1229: istore_3
    //   1230: goto -1221 -> 9
    //   1233: aload_0
    //   1234: getfield 92	com/google/android/android/internal/measurement/zzwx:zzcbn	Lcom/google/android/android/internal/measurement/zzyb;
    //   1237: aload_1
    //   1238: invokevirtual 357	com/google/android/android/internal/measurement/zzyb:zzah	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1241: aload_0
    //   1242: getfield 92	com/google/android/android/internal/measurement/zzwx:zzcbn	Lcom/google/android/android/internal/measurement/zzyb;
    //   1245: aload_2
    //   1246: invokevirtual 357	com/google/android/android/internal/measurement/zzyb:zzah	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1249: invokevirtual 704	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1252: ifne +5 -> 1257
    //   1255: iconst_0
    //   1256: ireturn
    //   1257: aload_0
    //   1258: getfield 78	com/google/android/android/internal/measurement/zzwx:zzcbe	Z
    //   1261: ifeq +23 -> 1284
    //   1264: aload_0
    //   1265: getfield 94	com/google/android/android/internal/measurement/zzwx:zzcbo	Lcom/google/android/android/internal/measurement/zzva;
    //   1268: aload_1
    //   1269: invokevirtual 428	com/google/android/android/internal/measurement/zzva:getName	(Ljava/lang/Object;)Lcom/google/android/android/internal/measurement/zzvd;
    //   1272: aload_0
    //   1273: getfield 94	com/google/android/android/internal/measurement/zzwx:zzcbo	Lcom/google/android/android/internal/measurement/zzva;
    //   1276: aload_2
    //   1277: invokevirtual 428	com/google/android/android/internal/measurement/zzva:getName	(Ljava/lang/Object;)Lcom/google/android/android/internal/measurement/zzvd;
    //   1280: invokevirtual 705	com/google/android/android/internal/measurement/zzvd:equals	(Ljava/lang/Object;)Z
    //   1283: ireturn
    //   1284: iconst_1
    //   1285: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1286	0	this	zzwx
    //   0	1286	1	paramObject1	Object
    //   0	1286	2	paramObject2	Object
    //   8	1222	3	i	int
    //   5	11	4	j	int
    //   23	15	5	k	int
    //   10	1210	6	bool	boolean
    //   31	1177	7	l1	long
    //   619	10	9	l2	long
  }
  
  public final int hashCode(Object paramObject)
  {
    int m = zzcaz.length;
    int j = 0;
    for (int k = 0; j < m; k = i)
    {
      int i1 = zzbq(j);
      int n = zzcaz[j];
      long l = 0xFFFFF & i1;
      i = 37;
      Object localObject;
      switch ((i1 & 0xFF00000) >>> 20)
      {
      default: 
        i = k;
        break;
      case 68: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzyh.get(paramObject, l).hashCode();
        }
        break;
      case 67: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.zzbf(getId(paramObject, l));
        }
        break;
      case 66: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + getValue(paramObject, l);
        }
        break;
      case 65: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.zzbf(getId(paramObject, l));
        }
        break;
      case 64: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + getValue(paramObject, l);
        }
        break;
      case 63: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + getValue(paramObject, l);
        }
        break;
      case 62: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + getValue(paramObject, l);
        }
        break;
      case 61: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzyh.get(paramObject, l).hashCode();
        }
        break;
      case 60: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzyh.get(paramObject, l).hashCode();
        }
        break;
      case 59: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + ((String)zzyh.get(paramObject, l)).hashCode();
        }
        break;
      case 58: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.hashCode(getName(paramObject, l));
        }
        break;
      case 57: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + getValue(paramObject, l);
        }
        break;
      case 56: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.zzbf(getId(paramObject, l));
        }
        break;
      case 55: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + getValue(paramObject, l);
        }
        break;
      case 54: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.zzbf(getId(paramObject, l));
        }
        break;
      case 53: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.zzbf(getId(paramObject, l));
        }
        break;
      case 52: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + Float.floatToIntBits(invoke(paramObject, l));
        }
        break;
      case 51: 
        i = k;
        if (get(paramObject, n, j)) {
          i = k * 53 + zzvo.zzbf(Double.doubleToLongBits(value(paramObject, l)));
        }
        break;
      case 50: 
        i = k * 53 + zzyh.get(paramObject, l).hashCode();
        break;
      case 18: 
      case 19: 
      case 20: 
      case 21: 
      case 22: 
      case 23: 
      case 24: 
      case 25: 
      case 26: 
      case 27: 
      case 28: 
      case 29: 
      case 30: 
      case 31: 
      case 32: 
      case 33: 
      case 34: 
      case 35: 
      case 36: 
      case 37: 
      case 38: 
      case 39: 
      case 40: 
      case 41: 
      case 42: 
      case 43: 
      case 44: 
      case 45: 
      case 46: 
      case 47: 
      case 48: 
      case 49: 
        i = k * 53 + zzyh.get(paramObject, l).hashCode();
        break;
      case 17: 
        localObject = zzyh.get(paramObject, l);
        if (localObject != null) {
          i = localObject.hashCode();
        }
        i = k * 53 + i;
        break;
      case 16: 
        i = k * 53 + zzvo.zzbf(zzyh.getId(paramObject, l));
        break;
      case 15: 
        i = k * 53 + zzyh.getValue(paramObject, l);
        break;
      case 14: 
        i = k * 53 + zzvo.zzbf(zzyh.getId(paramObject, l));
        break;
      case 13: 
        i = k * 53 + zzyh.getValue(paramObject, l);
        break;
      case 12: 
        i = k * 53 + zzyh.getValue(paramObject, l);
        break;
      case 11: 
        i = k * 53 + zzyh.getValue(paramObject, l);
        break;
      case 10: 
        i = k * 53 + zzyh.get(paramObject, l).hashCode();
        break;
      case 9: 
        localObject = zzyh.get(paramObject, l);
        if (localObject != null) {
          i = localObject.hashCode();
        }
        i = k * 53 + i;
        break;
      case 8: 
        i = k * 53 + ((String)zzyh.get(paramObject, l)).hashCode();
        break;
      case 7: 
        i = k * 53 + zzvo.hashCode(zzyh.getName(paramObject, l));
        break;
      case 6: 
        i = k * 53 + zzyh.getValue(paramObject, l);
        break;
      case 5: 
        i = k * 53 + zzvo.zzbf(zzyh.getId(paramObject, l));
        break;
      case 4: 
        i = k * 53 + zzyh.getValue(paramObject, l);
        break;
      case 3: 
        i = k * 53 + zzvo.zzbf(zzyh.getId(paramObject, l));
        break;
      case 2: 
        i = k * 53 + zzvo.zzbf(zzyh.getId(paramObject, l));
        break;
      case 1: 
        i = k * 53 + Float.floatToIntBits(zzyh.invoke(paramObject, l));
        break;
      case 0: 
        i = k * 53 + zzvo.zzbf(Double.doubleToLongBits(zzyh.a(paramObject, l)));
      }
      j += 3;
    }
    j = k * 53 + zzcbn.zzah(paramObject).hashCode();
    int i = j;
    if (zzcbe) {
      i = j * 53 + zzcbo.getName(paramObject).hashCode();
    }
    return i;
  }
  
  public final void multiply(Object paramObject)
  {
    int i = zzcbj;
    while (i < zzcbk)
    {
      long l = zzbq(zzcbi[i]) & 0xFFFFF;
      Object localObject = zzyh.get(paramObject, l);
      if (localObject != null) {
        zzyh.add(paramObject, l, zzcbp.zzab(localObject));
      }
      i += 1;
    }
    int j = zzcbi.length;
    i = zzcbk;
    while (i < j)
    {
      zzcbm.getField(paramObject, zzcbi[i]);
      i += 1;
    }
    zzcbn.setEntry(paramObject);
    if (zzcbe) {
      zzcbo.setEntry(paramObject);
    }
  }
  
  public final Object newInstance()
  {
    return zzcbl.newInstance(zzcbd);
  }
  
  public final void toFile(Object paramObject, zzxi paramZzxi, zzuz paramZzuz)
    throws IOException
  {
    zzyb localZzyb;
    if (paramZzuz != null)
    {
      localZzyb = zzcbn;
      zzva localZzva = zzcbo;
      Object localObject5 = null;
      Object localObject1 = null;
      label3368:
      label5017:
      label5100:
      do
      {
        for (;;)
        {
          Object localObject2 = localObject1;
          try
          {
            n = paramZzxi.zzve();
            localObject2 = localObject1;
            i = zzcbb;
            int m = -1;
            j = m;
            if (n >= i)
            {
              localObject2 = localObject1;
              i = zzcbc;
              j = m;
              if (n <= i)
              {
                k = 0;
                localObject2 = localObject1;
                i = zzcaz.length;
                i = i / 3 - 1;
                for (;;)
                {
                  j = m;
                  if (k > i) {
                    break;
                  }
                  int i1 = i + k >>> 1;
                  j = i1 * 3;
                  localObject2 = localObject1;
                  int i2 = zzcaz[j];
                  if (n == i2) {
                    break;
                  }
                  if (n < i2) {
                    i = i1 - 1;
                  } else {
                    k = i1 + 1;
                  }
                }
              }
            }
            if (j < 0)
            {
              if (n == Integer.MAX_VALUE)
              {
                i = zzcbj;
                while (i < zzcbk)
                {
                  localObject1 = get(paramObject, zzcbi[i], localObject1, localZzyb);
                  i += 1;
                }
                if (localObject1 == null) {
                  return;
                }
                localZzyb.operate(paramObject, localObject1);
                return;
              }
              localObject2 = localObject1;
              bool = zzcbe;
              if (!bool)
              {
                localObject3 = null;
              }
              else
              {
                localObject2 = localObject1;
                localObject3 = localZzva.read(paramZzuz, zzcbd, n);
              }
              if (localObject3 != null)
              {
                localObject4 = localObject5;
                if (localObject5 == null)
                {
                  localObject2 = localObject1;
                  localObject4 = localZzva.get(paramObject);
                }
                localObject2 = localObject1;
                localObject1 = localZzva.get(paramZzxi, localObject3, paramZzuz, (zzvd)localObject4, localObject1, localZzyb);
                localObject5 = localObject4;
                continue;
              }
              localObject2 = localObject1;
              localZzyb.equalsIgnoreCase(paramZzxi);
              localObject3 = localObject1;
              if (localObject1 == null)
              {
                localObject2 = localObject1;
                localObject3 = localZzyb.zzai(paramObject);
              }
              localObject2 = localObject3;
              bool = localZzyb.next(localObject3, paramZzxi);
              localObject1 = localObject3;
              if (bool) {
                continue;
              }
              i = zzcbj;
              while (i < zzcbk)
              {
                localObject3 = get(paramObject, zzcbi[i], localObject3, localZzyb);
                i += 1;
              }
              if (localObject3 == null) {
                return;
              }
              localZzyb.operate(paramObject, localObject3);
              return;
            }
            localObject2 = localObject1;
            i = zzbq(j);
            switch ((0xFF00000 & i) >>> 20)
            {
            default: 
              localObject3 = localObject1;
              if (localObject1 == null)
              {
                localObject2 = localObject1;
                localObject4 = localObject1;
              }
              break;
            }
          }
          catch (Throwable paramZzxi)
          {
            int n;
            int j;
            int k;
            boolean bool;
            Object localObject3;
            Object localObject4;
            long l;
            Object localObject7;
            Object localObject6;
            int i = zzcbj;
            while (i < zzcbk)
            {
              localObject2 = get(paramObject, zzcbi[i], localObject2, localZzyb);
              i += 1;
            }
            if (localObject2 != null) {
              localZzyb.operate(paramObject, localObject2);
            }
            throw paramZzxi;
          }
          try
          {
            localObject3 = localZzyb.zzye();
            break label5017;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, paramZzxi.read(zzbn(j), paramZzuz));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Long.valueOf(paramZzxi.zzuu()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Integer.valueOf(paramZzxi.zzut()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Long.valueOf(paramZzxi.zzus()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Integer.valueOf(paramZzxi.zzur()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            localObject2 = localObject1;
            localObject4 = localObject1;
            k = paramZzxi.zzuq();
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject3 = zzbp(j);
            if (localObject3 != null)
            {
              localObject2 = localObject1;
              localObject4 = localObject1;
              bool = ((zzvr)localObject3).get(k);
              if (!bool)
              {
                localObject2 = localObject1;
                localObject4 = localObject1;
                localObject1 = zzxl.read(n, k, localObject1, localZzyb);
                break label3368;
              }
            }
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Integer.valueOf(k));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Integer.valueOf(paramZzxi.zzup()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, paramZzxi.zzuo());
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            localObject2 = localObject1;
            localObject4 = localObject1;
            bool = get(paramObject, n, j);
            if (bool)
            {
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              zzyh.add(paramObject, l, zzvo.add(zzyh.get(paramObject, l), paramZzxi.get(zzbn(j), paramZzuz)));
            }
            else
            {
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              zzyh.add(paramObject, l, paramZzxi.get(zzbn(j), paramZzuz));
              localObject2 = localObject1;
              localObject4 = localObject1;
              write(paramObject, j);
            }
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            localObject2 = localObject1;
            localObject4 = localObject1;
            replaceAll(paramObject, i, paramZzxi);
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Boolean.valueOf(paramZzxi.zzum()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Integer.valueOf(paramZzxi.zzul()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Long.valueOf(paramZzxi.zzuk()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Integer.valueOf(paramZzxi.zzuj()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Long.valueOf(paramZzxi.zzuh()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Long.valueOf(paramZzxi.zzui()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Float.valueOf(paramZzxi.readFloat()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            zzyh.add(paramObject, l, Double.valueOf(paramZzxi.readDouble()));
            localObject2 = localObject1;
            localObject4 = localObject1;
            add(paramObject, n, j);
            continue;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject7 = zzbo(j);
            localObject2 = localObject1;
            localObject4 = localObject1;
            i = zzbq(j);
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject6 = zzyh.get(paramObject, l);
            localObject3 = localObject6;
            if (localObject6 == null)
            {
              localObject3 = zzcbp;
              localObject2 = localObject1;
              localObject4 = localObject1;
              localObject6 = ((zzwo)localObject3).zzac(localObject7);
              localObject3 = localObject6;
              localObject2 = localObject1;
              localObject4 = localObject1;
              zzyh.add(paramObject, l, localObject6);
            }
            else
            {
              zzwo localZzwo = zzcbp;
              localObject2 = localObject1;
              localObject4 = localObject1;
              bool = localZzwo.zzaa(localObject6);
              if (bool)
              {
                localObject2 = localObject1;
                localObject3 = zzcbp;
                localObject2 = localObject1;
                localObject4 = localObject1;
                localObject3 = ((zzwo)localObject3).zzac(localObject7);
                localZzwo = zzcbp;
                localObject2 = localObject1;
                localObject4 = localObject1;
                localZzwo.add(localObject3, localObject6);
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.add(paramObject, l, localObject3);
              }
            }
            localObject2 = localObject1;
            localObject6 = zzcbp;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject3 = ((zzwo)localObject6).get(localObject3);
            localObject6 = zzcbp;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.writeValue((Map)localObject3, ((zzwo)localObject6).zzad(localObject7), paramZzuz);
            continue;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject3 = zzbn(j);
            localObject6 = zzcbm;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.toFile(((zzwd)localObject6).read(paramObject, l), (zzxj)localObject3, paramZzuz);
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.upgradeDatabase(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.toFile(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.offset(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.getNumber(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject3 = ((zzwd)localObject3).read(paramObject, l);
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.initBook((List)localObject3);
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject1 = zzxl.read(n, (List)localObject3, zzbp(j), localObject1, localZzyb);
            break label3368;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.replace(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.addSection(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.getBytes(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.replaceAll(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.intersect(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.copyTo(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.processWays(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.read(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.readFromParcel(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.upgradeDatabase(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.toFile(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.offset(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.getNumber(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject3 = ((zzwd)localObject3).read(paramObject, l);
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.initBook((List)localObject3);
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject1 = zzxl.read(n, (List)localObject3, zzbp(j), localObject1, localZzyb);
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.replace(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject3 = zzcbm;
            l = i & 0xFFFFF;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.blur(((zzwd)localObject3).read(paramObject, l));
            continue;
            localObject2 = localObject1;
            localObject4 = localObject1;
            localObject3 = zzbn(j);
            l = i & 0xFFFFF;
            localObject6 = zzcbm;
            localObject2 = localObject1;
            localObject4 = localObject1;
            paramZzxi.readDocument(((zzwd)localObject6).read(paramObject, l), (zzxj)localObject3, paramZzuz);
            continue;
            localObject2 = localObject1;
            localObject4 = localObject1;
            bool = zzbs(i);
            if (bool)
            {
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.add(((zzwd)localObject3).read(paramObject, l));
            }
            else
            {
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.readStringList(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.addSection(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.getBytes(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.replaceAll(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.intersect(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.copyTo(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.processWays(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.read(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject3 = zzcbm;
              l = i & 0xFFFFF;
              localObject2 = localObject1;
              localObject4 = localObject1;
              paramZzxi.readFromParcel(((zzwd)localObject3).read(paramObject, l));
              continue;
              localObject2 = localObject1;
              localObject4 = localObject1;
              bool = a(paramObject, j);
              if (bool)
              {
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.add(paramObject, l, zzvo.add(zzyh.get(paramObject, l), paramZzxi.read(zzbn(j), paramZzuz)));
              }
              else
              {
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.add(paramObject, l, paramZzxi.read(zzbn(j), paramZzuz));
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.close(paramObject, l, paramZzxi.zzuu());
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.a(paramObject, l, paramZzxi.zzut());
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.close(paramObject, l, paramZzxi.zzus());
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.a(paramObject, l, paramZzxi.zzur());
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                localObject2 = localObject1;
                localObject4 = localObject1;
                k = paramZzxi.zzuq();
                localObject2 = localObject1;
                localObject4 = localObject1;
                localObject3 = zzbp(j);
                if (localObject3 != null)
                {
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  bool = ((zzvr)localObject3).get(k);
                  if (!bool)
                  {
                    localObject2 = localObject1;
                    localObject4 = localObject1;
                    localObject1 = zzxl.read(n, k, localObject1, localZzyb);
                    break label3368;
                  }
                }
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.a(paramObject, l, k);
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.a(paramObject, l, paramZzxi.zzup());
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                l = i & 0xFFFFF;
                localObject2 = localObject1;
                localObject4 = localObject1;
                zzyh.add(paramObject, l, paramZzxi.zzuo());
                localObject2 = localObject1;
                localObject4 = localObject1;
                write(paramObject, j);
                continue;
                localObject2 = localObject1;
                localObject4 = localObject1;
                bool = a(paramObject, j);
                if (bool)
                {
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.add(paramObject, l, zzvo.add(zzyh.get(paramObject, l), paramZzxi.get(zzbn(j), paramZzuz)));
                }
                else
                {
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.add(paramObject, l, paramZzxi.get(zzbn(j), paramZzuz));
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  replaceAll(paramObject, i, paramZzxi);
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.read(paramObject, l, paramZzxi.zzum());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.a(paramObject, l, paramZzxi.zzul());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.close(paramObject, l, paramZzxi.zzuk());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.a(paramObject, l, paramZzxi.zzuj());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.close(paramObject, l, paramZzxi.zzuh());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.close(paramObject, l, paramZzxi.zzui());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.append(paramObject, l, paramZzxi.readFloat());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  l = i & 0xFFFFF;
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  zzyh.append(paramObject, l, paramZzxi.readDouble());
                  localObject2 = localObject1;
                  localObject4 = localObject1;
                  write(paramObject, j);
                  continue;
                  localObject2 = localObject3;
                  localObject4 = localObject3;
                  bool = localZzyb.next(localObject3, paramZzxi);
                  localObject1 = localObject3;
                  if (!bool)
                  {
                    i = zzcbj;
                    while (i < zzcbk)
                    {
                      localObject3 = get(paramObject, zzcbi[i], localObject3, localZzyb);
                      i += 1;
                    }
                    if (localObject3 == null) {
                      return;
                    }
                    localZzyb.operate(paramObject, localObject3);
                    return;
                  }
                }
              }
            }
          }
          catch (zzvu localZzvu)
          {
            break label5100;
          }
        }
        localObject2 = localObject4;
        localZzyb.equalsIgnoreCase(paramZzxi);
        localObject3 = localObject4;
        if (localObject4 == null)
        {
          localObject2 = localObject4;
          localObject3 = localZzyb.zzai(paramObject);
        }
        localObject2 = localObject3;
        bool = localZzyb.next(localObject3, paramZzxi);
        localObject1 = localObject3;
      } while (bool);
      i = zzcbj;
      while (i < zzcbk)
      {
        localObject3 = get(paramObject, zzcbi[i], localObject3, localZzyb);
        i += 1;
      }
      if (localObject3 != null) {
        localZzyb.operate(paramObject, localObject3);
      }
    }
    else
    {
      throw new NullPointerException();
    }
  }
  
  public final int zzae(Object paramObject)
  {
    int m;
    long l;
    Object localObject;
    int i1;
    if (zzcbg)
    {
      localUnsafe = zzcay;
      j = 0;
      for (k = 0; j < zzcaz.length; k = i)
      {
        m = zzbq(j);
        i = (m & 0xFF00000) >>> 20;
        n = zzcaz[j];
        l = m & 0xFFFFF;
        if ((i >= zzvg.zzbxd.rpos()) && (i <= zzvg.zzbxq.rpos())) {
          m = zzcaz[(j + 2)] & 0xFFFFF;
        } else {
          m = 0;
        }
        switch (i)
        {
        default: 
          i = k;
          break;
        case 68: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.getSummary(n, (zzwt)zzyh.get(paramObject, l), zzbn(j));
          }
          break;
        case 67: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.deleteServer(n, getId(paramObject, l));
          }
          break;
        case 66: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.parseTrigger(n, getValue(paramObject, l));
          }
          break;
        case 65: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.accept(n, 0L);
          }
          break;
        case 64: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.getDisplayTitle(n, 0);
          }
          break;
        case 63: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.loadView(n, getValue(paramObject, l));
          }
          break;
        case 62: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.get(n, getValue(paramObject, l));
          }
          break;
        case 61: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.getShares(n, (zzud)zzyh.get(paramObject, l));
          }
          break;
        case 60: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzxl.write(n, zzyh.get(paramObject, l), zzbn(j));
          }
          break;
        case 59: 
          i = k;
          if (get(paramObject, n, j))
          {
            localObject = zzyh.get(paramObject, l);
            if ((localObject instanceof zzud)) {
              i = k + zzut.getShares(n, (zzud)localObject);
            } else {
              i = k + zzut.getDisplayTitle(n, (String)localObject);
            }
          }
          break;
        case 58: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.e(n, true);
          }
          break;
        case 57: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.getPath(n, 0);
          }
          break;
        case 56: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.a(n, 0L);
          }
          break;
        case 55: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.a(n, getValue(paramObject, l));
          }
          break;
        case 54: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.getCard(n, getId(paramObject, l));
          }
          break;
        case 53: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.createBookmark(n, getId(paramObject, l));
          }
          break;
        case 52: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.toHtml(n, 0.0F);
          }
          break;
        case 51: 
          i = k;
          if (get(paramObject, n, j)) {
            i = k + zzut.animateView(n, 0.0D);
          }
          break;
        case 50: 
          i = k + zzcbp.register(n, zzyh.get(paramObject, l), zzbo(j));
          break;
        case 49: 
          i = k + zzxl.merge(n, get(paramObject, l), zzbn(j));
          break;
        case 48: 
          i1 = zzxl.getSerializedSize((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 47: 
          i1 = zzxl.zzad((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 46: 
          i1 = zzxl.zzaf((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 45: 
          i1 = zzxl.zzae((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 44: 
          i1 = zzxl.zzaa((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 43: 
          i1 = zzxl.zzac((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 42: 
          i1 = zzxl.zzag((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 41: 
          i1 = zzxl.zzae((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 40: 
          i1 = zzxl.zzaf((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 39: 
          i1 = zzxl.zzab((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 38: 
          i1 = zzxl.trim((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 37: 
          i1 = zzxl.a((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 36: 
          i1 = zzxl.zzae((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 35: 
          i1 = zzxl.zzaf((List)localUnsafe.getObject(paramObject, l));
          i = k;
          if (i1 > 0)
          {
            if (zzcbh) {
              localUnsafe.putInt(paramObject, m, i1);
            }
            i = k + (zzut.zzbb(n) + zzut.zzbd(i1) + i1);
          }
          break;
        case 34: 
          i = k + zzxl.getMessageSize(n, get(paramObject, l), false);
          break;
        case 33: 
          i = k + zzxl.addBlock(n, get(paramObject, l), false);
          break;
        case 32: 
          i = k + zzxl.setValue(n, get(paramObject, l), false);
          break;
        case 31: 
          i = k + zzxl.get(n, get(paramObject, l), false);
          break;
        case 30: 
          i = k + zzxl.toHtml(n, get(paramObject, l), false);
          break;
        case 29: 
          i = k + zzxl.hasName(n, get(paramObject, l), false);
          break;
        case 28: 
          i = k + zzxl.get(n, get(paramObject, l));
          break;
        case 27: 
          i = k + zzxl.check(n, get(paramObject, l), zzbn(j));
          break;
        case 26: 
          i = k + zzxl.parse(n, get(paramObject, l));
          break;
        case 25: 
          i = k + zzxl.getGameId(n, get(paramObject, l), false);
          break;
        case 24: 
          i = k + zzxl.get(n, get(paramObject, l), false);
          break;
        case 23: 
          i = k + zzxl.setValue(n, get(paramObject, l), false);
          break;
        case 22: 
          i = k + zzxl.getCommand(n, get(paramObject, l), false);
          break;
        case 21: 
          i = k + zzxl.b(n, get(paramObject, l), false);
          break;
        case 20: 
          i = k + zzxl.write(n, get(paramObject, l), false);
          break;
        case 19: 
          i = k + zzxl.get(n, get(paramObject, l), false);
          break;
        case 18: 
          i = k + zzxl.setValue(n, get(paramObject, l), false);
          break;
        case 17: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.getSummary(n, (zzwt)zzyh.get(paramObject, l), zzbn(j));
          }
          break;
        case 16: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.deleteServer(n, zzyh.getId(paramObject, l));
          }
          break;
        case 15: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.parseTrigger(n, zzyh.getValue(paramObject, l));
          }
          break;
        case 14: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.accept(n, 0L);
          }
          break;
        case 13: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.getDisplayTitle(n, 0);
          }
          break;
        case 12: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.loadView(n, zzyh.getValue(paramObject, l));
          }
          break;
        case 11: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.get(n, zzyh.getValue(paramObject, l));
          }
          break;
        case 10: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.getShares(n, (zzud)zzyh.get(paramObject, l));
          }
          break;
        case 9: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzxl.write(n, zzyh.get(paramObject, l), zzbn(j));
          }
          break;
        case 8: 
          i = k;
          if (a(paramObject, j))
          {
            localObject = zzyh.get(paramObject, l);
            if ((localObject instanceof zzud)) {
              i = k + zzut.getShares(n, (zzud)localObject);
            } else {
              i = k + zzut.getDisplayTitle(n, (String)localObject);
            }
          }
          break;
        case 7: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.e(n, true);
          }
          break;
        case 6: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.getPath(n, 0);
          }
          break;
        case 5: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.a(n, 0L);
          }
          break;
        case 4: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.a(n, zzyh.getValue(paramObject, l));
          }
          break;
        case 3: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.getCard(n, zzyh.getId(paramObject, l));
          }
          break;
        case 2: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.createBookmark(n, zzyh.getId(paramObject, l));
          }
          break;
        case 1: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.toHtml(n, 0.0F);
          }
          break;
        case 0: 
          i = k;
          if (a(paramObject, j)) {
            i = k + zzut.animateView(n, 0.0D);
          }
          break;
        }
        j += 3;
      }
      return k + getFileIcon(zzcbn, paramObject);
    }
    Unsafe localUnsafe = zzcay;
    int k = 0;
    int j = 0;
    int i2 = -1;
    int n = 0;
    while (k < zzcaz.length)
    {
      int i7 = zzbq(k);
      int i6 = zzcaz[k];
      int i8 = (i7 & 0xFF00000) >>> 20;
      int i3;
      if (i8 <= 17)
      {
        i = zzcaz[(k + 2)];
        int i5 = i & 0xFFFFF;
        int i4 = 1 << (i >>> 20);
        m = i2;
        i1 = i;
        i3 = i4;
        if (i5 != i2)
        {
          n = localUnsafe.getInt(paramObject, i5);
          m = i5;
          i1 = i;
          i3 = i4;
        }
      }
      else
      {
        if ((zzcbh) && (i8 >= zzvg.zzbxd.rpos()) && (i8 <= zzvg.zzbxq.rpos())) {
          i = zzcaz[(k + 2)] & 0xFFFFF;
        } else {
          i = 0;
        }
        i3 = 0;
        i1 = i;
        m = i2;
      }
      l = i7 & 0xFFFFF;
      switch (i8)
      {
      default: 
        i = j;
        break;
      case 68: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.getSummary(i6, (zzwt)localUnsafe.getObject(paramObject, l), zzbn(k));
        }
        break;
      case 67: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.deleteServer(i6, getId(paramObject, l));
        }
        break;
      case 66: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.parseTrigger(i6, getValue(paramObject, l));
        }
        break;
      case 65: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.accept(i6, 0L);
        }
        break;
      case 64: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.getDisplayTitle(i6, 0);
        }
        break;
      case 63: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.loadView(i6, getValue(paramObject, l));
        }
        break;
      case 62: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.get(i6, getValue(paramObject, l));
        }
        break;
      case 61: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.getShares(i6, (zzud)localUnsafe.getObject(paramObject, l));
        }
        break;
      case 60: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzxl.write(i6, localUnsafe.getObject(paramObject, l), zzbn(k));
        }
        break;
      case 59: 
        i = j;
        if (get(paramObject, i6, k))
        {
          localObject = localUnsafe.getObject(paramObject, l);
          if ((localObject instanceof zzud)) {
            i = j + zzut.getShares(i6, (zzud)localObject);
          } else {
            i = j + zzut.getDisplayTitle(i6, (String)localObject);
          }
        }
        break;
      case 58: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.e(i6, true);
        }
        break;
      case 57: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.getPath(i6, 0);
        }
        break;
      case 56: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.a(i6, 0L);
        }
        break;
      case 55: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.a(i6, getValue(paramObject, l));
        }
        break;
      case 54: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.getCard(i6, getId(paramObject, l));
        }
        break;
      case 53: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.createBookmark(i6, getId(paramObject, l));
        }
        break;
      case 52: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.toHtml(i6, 0.0F);
        }
        break;
      case 51: 
        i = j;
        if (get(paramObject, i6, k)) {
          i = j + zzut.animateView(i6, 0.0D);
        }
        break;
      case 50: 
        i = j + zzcbp.register(i6, localUnsafe.getObject(paramObject, l), zzbo(k));
        break;
      case 49: 
        i = j + zzxl.merge(i6, (List)localUnsafe.getObject(paramObject, l), zzbn(k));
        break;
      case 48: 
        i2 = zzxl.getSerializedSize((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 47: 
        i2 = zzxl.zzad((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 46: 
        i2 = zzxl.zzaf((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 45: 
        i2 = zzxl.zzae((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 44: 
        i2 = zzxl.zzaa((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 43: 
        i2 = zzxl.zzac((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 42: 
        i2 = zzxl.zzag((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 41: 
        i2 = zzxl.zzae((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 40: 
        i2 = zzxl.zzaf((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 39: 
        i2 = zzxl.zzab((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 38: 
        i2 = zzxl.trim((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 37: 
        i2 = zzxl.a((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 36: 
        i2 = zzxl.zzae((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 35: 
        i2 = zzxl.zzaf((List)localUnsafe.getObject(paramObject, l));
        i = j;
        if (i2 > 0)
        {
          if (zzcbh) {
            localUnsafe.putInt(paramObject, i1, i2);
          }
          i = j + (zzut.zzbb(i6) + zzut.zzbd(i2) + i2);
        }
        break;
      case 34: 
        i = j + zzxl.getMessageSize(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 33: 
        i = j + zzxl.addBlock(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 32: 
        i = j + zzxl.setValue(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 31: 
        i = j + zzxl.get(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 30: 
        i = j + zzxl.toHtml(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 29: 
        i = j + zzxl.hasName(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 28: 
        i = j + zzxl.get(i6, (List)localUnsafe.getObject(paramObject, l));
        break;
      case 27: 
        i = j + zzxl.check(i6, (List)localUnsafe.getObject(paramObject, l), zzbn(k));
        break;
      case 26: 
        i = j + zzxl.parse(i6, (List)localUnsafe.getObject(paramObject, l));
        break;
      case 25: 
        i = j + zzxl.getGameId(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 24: 
        i = j + zzxl.get(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 23: 
        i = j + zzxl.setValue(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 22: 
        i = j + zzxl.getCommand(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 21: 
        i = j + zzxl.b(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 20: 
        i = j + zzxl.write(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 19: 
        i = j + zzxl.get(i6, (List)localUnsafe.getObject(paramObject, l), false);
        break;
      case 18: 
        i = j + zzxl.setValue(i6, (List)localUnsafe.getObject(paramObject, l), false);
      }
      for (;;)
      {
        break;
        i = j;
        if ((n & i3) != 0)
        {
          i = j + zzut.getSummary(i6, (zzwt)localUnsafe.getObject(paramObject, l), zzbn(k));
          continue;
          i = j;
          if ((n & i3) != 0)
          {
            i = j + zzut.deleteServer(i6, localUnsafe.getLong(paramObject, l));
            continue;
            i = j;
            if ((n & i3) != 0)
            {
              i = j + zzut.parseTrigger(i6, localUnsafe.getInt(paramObject, l));
              continue;
              i = j;
              if ((n & i3) != 0)
              {
                i = j + zzut.accept(i6, 0L);
                continue;
                i = j;
                if ((n & i3) != 0)
                {
                  i = j + zzut.getDisplayTitle(i6, 0);
                  continue;
                  i = j;
                  if ((n & i3) != 0)
                  {
                    i = j + zzut.loadView(i6, localUnsafe.getInt(paramObject, l));
                    continue;
                    i = j;
                    if ((n & i3) != 0)
                    {
                      i = j + zzut.get(i6, localUnsafe.getInt(paramObject, l));
                      continue;
                      i = j;
                      if ((n & i3) != 0)
                      {
                        i = j + zzut.getShares(i6, (zzud)localUnsafe.getObject(paramObject, l));
                        continue;
                        i = j;
                        if ((n & i3) != 0)
                        {
                          i = j + zzxl.write(i6, localUnsafe.getObject(paramObject, l), zzbn(k));
                          continue;
                          i = j;
                          if ((n & i3) != 0)
                          {
                            localObject = localUnsafe.getObject(paramObject, l);
                            if ((localObject instanceof zzud))
                            {
                              i = j + zzut.getShares(i6, (zzud)localObject);
                            }
                            else
                            {
                              i = j + zzut.getDisplayTitle(i6, (String)localObject);
                              continue;
                              i = j;
                              if ((n & i3) != 0)
                              {
                                i = j + zzut.e(i6, true);
                                continue;
                                i = j;
                                if ((n & i3) != 0)
                                {
                                  i = j + zzut.getPath(i6, 0);
                                  continue;
                                  i = j;
                                  if ((n & i3) != 0)
                                  {
                                    i = j + zzut.a(i6, 0L);
                                    break label6214;
                                    i = j;
                                    if ((n & i3) != 0)
                                    {
                                      i = j + zzut.a(i6, localUnsafe.getInt(paramObject, l));
                                      break label6214;
                                      i = j;
                                      if ((n & i3) != 0)
                                      {
                                        i = j + zzut.getCard(i6, localUnsafe.getLong(paramObject, l));
                                        break label6214;
                                        i = j;
                                        if ((n & i3) != 0) {
                                          i = j + zzut.createBookmark(i6, localUnsafe.getLong(paramObject, l));
                                        }
                                      }
                                    }
                                    for (;;)
                                    {
                                      label6214:
                                      break;
                                      i = j;
                                      if ((n & i3) != 0) {
                                        i = j + zzut.toHtml(i6, 0.0F);
                                      }
                                    }
                                    for (;;)
                                    {
                                      break;
                                      i = j;
                                      if ((n & i3) != 0) {
                                        i = j + zzut.animateView(i6, 0.0D);
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      k += 3;
      j = i;
      i2 = m;
    }
    j += getFileIcon(zzcbn, paramObject);
    int i = j;
    if (zzcbe) {
      i = j + zzcbo.getName(paramObject).zzvu();
    }
    return i;
  }
  
  public final boolean zzaf(Object paramObject)
  {
    int i = 0;
    int m = -1;
    int k = 0;
    for (;;)
    {
      int j = zzcbj;
      int i1 = 1;
      int i2 = 1;
      if (i >= j) {
        break;
      }
      int i5 = zzcbi[i];
      int i6 = zzcaz[i5];
      int i7 = zzbq(i5);
      int n;
      if (!zzcbg)
      {
        j = zzcaz[(i5 + 2)];
        int i3 = j & 0xFFFFF;
        int i4 = 1 << (j >>> 20);
        j = m;
        n = i4;
        if (i3 != m)
        {
          k = zzcay.getInt(paramObject, i3);
          j = i3;
          n = i4;
        }
      }
      else
      {
        n = 0;
        j = m;
      }
      if ((0x10000000 & i7) != 0) {
        m = 1;
      } else {
        m = 0;
      }
      if ((m != 0) && (!a(paramObject, i5, k, n))) {
        return false;
      }
      m = (0xFF00000 & i7) >>> 20;
      if ((m != 9) && (m != 17))
      {
        Object localObject2;
        Object localObject1;
        if (m != 27)
        {
          if ((m != 60) && (m != 68)) {}
          switch (m)
          {
          default: 
            break;
          case 50: 
            localObject2 = zzcbp.getList(zzyh.get(paramObject, i7 & 0xFFFFF));
            m = i2;
            if (!((Map)localObject2).isEmpty())
            {
              localObject1 = zzbo(i5);
              m = i2;
              if (zzcbp.zzad(localObject1).zzcat.zzyp() == zzyv.zzcet)
              {
                localObject1 = null;
                Iterator localIterator = ((Map)localObject2).values().iterator();
                Object localObject3;
                do
                {
                  m = i2;
                  if (!localIterator.hasNext()) {
                    break;
                  }
                  localObject3 = localIterator.next();
                  localObject2 = localObject1;
                  if (localObject1 == null) {
                    localObject2 = zzxf.zzxn().getAttributeValue(localObject3.getClass());
                  }
                  localObject1 = localObject2;
                } while (((zzxj)localObject2).zzaf(localObject3));
                m = 0;
              }
            }
            if (m != 0) {
              break;
            }
            return false;
            if ((!get(paramObject, i6, i5)) || (a(paramObject, i7, zzbn(i5)))) {
              break;
            }
            return false;
          }
        }
        else
        {
          localObject1 = (List)zzyh.get(paramObject, i7 & 0xFFFFF);
          m = i1;
          if (!((List)localObject1).isEmpty())
          {
            localObject2 = zzbn(i5);
            n = 0;
            for (;;)
            {
              m = i1;
              if (n >= ((List)localObject1).size()) {
                break;
              }
              if (!((zzxj)localObject2).zzaf(((List)localObject1).get(n)))
              {
                m = 0;
                break;
              }
              n += 1;
            }
          }
          if (m == 0) {
            return false;
          }
        }
      }
      else if ((a(paramObject, i5, k, n)) && (!a(paramObject, i7, zzbn(i5))))
      {
        return false;
      }
      i += 1;
      m = j;
    }
    return (!zzcbe) || (zzcbo.getName(paramObject).isInitialized());
  }
}
