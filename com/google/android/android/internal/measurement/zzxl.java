package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzxl
{
  private static final Class<?> zzcbw = ;
  private static final com.google.android.gms.internal.measurement.zzyb<?, ?> zzcbx = refresh(false);
  private static final com.google.android.gms.internal.measurement.zzyb<?, ?> zzcby = refresh(true);
  private static final com.google.android.gms.internal.measurement.zzyb<?, ?> zzcbz = new zzyd();
  
  static int a(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzay(paramList.getLong(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzay(((Long)paramList.get(k)).longValue());
      k += 1;
    }
    return j;
  }
  
  public static void add(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.scan(paramInt, paramList, paramBoolean);
    }
  }
  
  static int addBlock(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return zzad(paramList) + i * zzut.zzbb(paramInt);
  }
  
  static int b(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return trim(paramList) + i * zzut.zzbb(paramInt);
  }
  
  public static void b(int paramInt, List paramList, zzyw paramZzyw)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.a(paramInt, paramList);
    }
  }
  
  public static void b(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.trim(paramInt, paramList, paramBoolean);
    }
  }
  
  static int check(int paramInt, List paramList, zzxj paramZzxj)
  {
    int j = paramList.size();
    int i = 0;
    if (j == 0) {
      return 0;
    }
    paramInt = zzut.zzbb(paramInt) * j;
    while (i < j)
    {
      Object localObject = paramList.get(i);
      if ((localObject instanceof zzwa)) {
        paramInt += zzut.min((zzwa)localObject);
      } else {
        paramInt += zzut.getLocation((zzwt)localObject, paramZzxj);
      }
      i += 1;
    }
    return paramInt;
  }
  
  public static void clear(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.deleteFiles(paramInt, paramList, paramBoolean);
    }
  }
  
  public static void connect(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.saveData(paramInt, paramList, paramBoolean);
    }
  }
  
  public static void deleteFiles(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.list(paramInt, paramList, paramBoolean);
    }
  }
  
  static void div(zzyb paramZzyb, Object paramObject1, Object paramObject2)
  {
    paramZzyb.multiply(paramObject1, paramZzyb.negate(paramZzyb.zzah(paramObject1), paramZzyb.zzah(paramObject2)));
  }
  
  static boolean eq(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static void f(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.put(paramInt, paramList, paramBoolean);
    }
  }
  
  static int get(int paramInt, List paramList)
  {
    int i = paramList.size();
    int j = 0;
    if (i == 0) {
      return 0;
    }
    i *= zzut.zzbb(paramInt);
    paramInt = j;
    while (paramInt < paramList.size())
    {
      i += zzut.min((zzud)paramList.get(paramInt));
      paramInt += 1;
    }
    return i;
  }
  
  static int get(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return i * zzut.getPath(paramInt, 0);
  }
  
  static int getCommand(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return zzab(paramList) + i * zzut.zzbb(paramInt);
  }
  
  static int getGameId(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return i * zzut.e(paramInt, true);
  }
  
  static int getMessageSize(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return getSerializedSize(paramList) + i * zzut.zzbb(paramInt);
  }
  
  static int getSerializedSize(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzba(paramList.getLong(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzba(((Long)paramList.get(k)).longValue());
      k += 1;
    }
    return j;
  }
  
  public static void go(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.contains(paramInt, paramList, paramBoolean);
    }
  }
  
  static int hasName(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return zzac(paramList) + i * zzut.zzbb(paramInt);
  }
  
  static int merge(int paramInt, List paramList, zzxj paramZzxj)
  {
    int k = paramList.size();
    int i = 0;
    if (k == 0) {
      return 0;
    }
    int j = 0;
    while (i < k)
    {
      j += zzut.getSummary(paramInt, (zzwt)paramList.get(i), paramZzxj);
      i += 1;
    }
    return j;
  }
  
  public static void move(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.insert(paramInt, paramList, paramBoolean);
    }
  }
  
  static int parse(int paramInt, List paramList)
  {
    int m = paramList.size();
    int i = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    int j = zzut.zzbb(paramInt) * m;
    paramInt = j;
    Object localObject;
    if ((paramList instanceof zzwc))
    {
      paramList = (zzwc)paramList;
      paramInt = j;
      i = k;
      for (;;)
      {
        j = paramInt;
        if (i >= m) {
          break;
        }
        localObject = paramList.getRaw(i);
        if ((localObject instanceof zzud)) {
          paramInt += zzut.min((zzud)localObject);
        } else {
          paramInt += zzut.zzfx((String)localObject);
        }
        i += 1;
      }
    }
    for (;;)
    {
      j = paramInt;
      if (i >= m) {
        break;
      }
      localObject = paramList.get(i);
      if ((localObject instanceof zzud)) {
        paramInt += zzut.min((zzud)localObject);
      } else {
        paramInt += zzut.zzfx((String)localObject);
      }
      i += 1;
    }
    return j;
  }
  
  static Object read(int paramInt1, int paramInt2, Object paramObject, zzyb paramZzyb)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = paramZzyb.zzye();
    }
    paramZzyb.updateImage(localObject, paramInt1, paramInt2);
    return localObject;
  }
  
  static Object read(int paramInt, List paramList, zzvr paramZzvr, Object paramObject, zzyb paramZzyb)
  {
    if (paramZzvr == null) {
      return paramObject;
    }
    int i;
    Object localObject;
    if ((paramList instanceof RandomAccess))
    {
      int k = paramList.size();
      i = 0;
      int j = 0;
      while (i < k)
      {
        int m = ((Integer)paramList.get(i)).intValue();
        if (paramZzvr.get(m))
        {
          if (i != j) {
            paramList.set(j, Integer.valueOf(m));
          }
          j += 1;
        }
        else
        {
          paramObject = read(paramInt, m, paramObject, paramZzyb);
        }
        i += 1;
      }
      localObject = paramObject;
      if (j != k)
      {
        paramList.subList(j, k).clear();
        return paramObject;
      }
    }
    else
    {
      paramList = paramList.iterator();
      for (;;)
      {
        localObject = paramObject;
        if (!paramList.hasNext()) {
          break;
        }
        i = ((Integer)paramList.next()).intValue();
        if (!paramZzvr.get(i))
        {
          paramObject = read(paramInt, i, paramObject, paramZzyb);
          paramList.remove();
        }
      }
    }
    return localObject;
  }
  
  private static zzyb refresh(boolean paramBoolean)
  {
    try
    {
      Object localObject = zzxv();
      if (localObject == null) {
        return null;
      }
      localObject = (zzyb)((Class)localObject).getConstructor(new Class[] { Boolean.TYPE }).newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public static void refresh(int paramInt, List paramList, zzyw paramZzyw, zzxj paramZzxj)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.getShares(paramInt, paramList, paramZzxj);
    }
  }
  
  public static void refresh(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.toArray(paramInt, paramList, paramBoolean);
    }
  }
  
  public static void run(int paramInt, List paramList, zzyw paramZzyw, zzxj paramZzxj)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.sendMail(paramInt, paramList, paramZzxj);
    }
  }
  
  public static void send(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.remap(paramInt, paramList, paramBoolean);
    }
  }
  
  public static void setObjectClass(Class paramClass)
  {
    if ((!zzvm.class.isAssignableFrom(paramClass)) && (zzcbw != null))
    {
      if (zzcbw.isAssignableFrom(paramClass)) {
        return;
      }
      throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
    }
  }
  
  public static void setParams(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.getPath(paramInt, paramList, paramBoolean);
    }
  }
  
  static void setTitle(zzva paramZzva, Object paramObject1, Object paramObject2)
  {
    paramObject2 = paramZzva.getName(paramObject2);
    if (!paramObject2.isEmpty()) {
      paramZzva.get(paramObject1).isEmpty(paramObject2);
    }
  }
  
  static int setValue(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return i * zzut.a(paramInt, 0L);
  }
  
  static int toHtml(int paramInt, List paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return 0;
    }
    return zzaa(paramList) + i * zzut.zzbb(paramInt);
  }
  
  static int trim(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzaz(paramList.getLong(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzaz(((Long)paramList.get(k)).longValue());
      k += 1;
    }
    return j;
  }
  
  public static void visitAnnotation(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.add(paramInt, paramList, paramBoolean);
    }
  }
  
  public static void visitJumpInsn(int paramInt, List paramList, zzyw paramZzyw)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.e(paramInt, paramList);
    }
  }
  
  public static void visitMethodInsn(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.getColors(paramInt, paramList, paramBoolean);
    }
  }
  
  static void visitMethodInsn(zzwo paramZzwo, Object paramObject1, Object paramObject2, long paramLong)
  {
    zzyh.add(paramObject1, paramLong, paramZzwo.add(zzyh.get(paramObject1, paramLong), zzyh.get(paramObject2, paramLong)));
  }
  
  static int write(int paramInt, Object paramObject, zzxj paramZzxj)
  {
    if ((paramObject instanceof zzwa)) {
      return zzut.toByteArray(paramInt, (zzwa)paramObject);
    }
    return zzut.check(paramInt, (zzwt)paramObject, paramZzxj);
  }
  
  static int write(int paramInt, List paramList, boolean paramBoolean)
  {
    if (paramList.size() == 0) {
      return 0;
    }
    return a(paramList) + paramList.size() * zzut.zzbb(paramInt);
  }
  
  public static void write(int paramInt, List paramList, zzyw paramZzyw, boolean paramBoolean)
    throws IOException
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramZzyw.addAll(paramInt, paramList, paramBoolean);
    }
  }
  
  static int zzaa(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzbh(paramList.getInt(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzbh(((Integer)paramList.get(k)).intValue());
      k += 1;
    }
    return j;
  }
  
  static int zzab(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzbc(paramList.getInt(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzbc(((Integer)paramList.get(k)).intValue());
      k += 1;
    }
    return j;
  }
  
  static int zzac(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzbd(paramList.getInt(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzbd(((Integer)paramList.get(k)).intValue());
      k += 1;
    }
    return j;
  }
  
  static int zzad(List paramList)
  {
    int m = paramList.size();
    int j = 0;
    int k = 0;
    if (m == 0) {
      return 0;
    }
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        i += zzut.zzbe(paramList.getInt(k));
        k += 1;
      }
    }
    int i = 0;
    k = j;
    for (;;)
    {
      j = i;
      if (k >= m) {
        break;
      }
      i += zzut.zzbe(((Integer)paramList.get(k)).intValue());
      k += 1;
    }
    return j;
  }
  
  static int zzae(List paramList)
  {
    return paramList.size() << 2;
  }
  
  static int zzaf(List paramList)
  {
    return paramList.size() << 3;
  }
  
  static int zzag(List paramList)
  {
    return paramList.size();
  }
  
  public static zzyb zzxr()
  {
    return zzcbx;
  }
  
  public static zzyb zzxs()
  {
    return zzcby;
  }
  
  public static zzyb zzxt()
  {
    return zzcbz;
  }
  
  private static Class zzxu()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.GeneratedMessage");
      return localClass;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static Class zzxv()
  {
    try
    {
      Class localClass = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
      return localClass;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
}
