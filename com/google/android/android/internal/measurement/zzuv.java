package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzuv
  implements zzyw
{
  private final zzut zzbuf;
  
  private zzuv(zzut paramZzut)
  {
    zzbuf = ((zzut)zzvo.attribute(paramZzut, "output"));
    zzbuf.zzbuw = this;
  }
  
  public static zzuv elementAt(zzut paramZzut)
  {
    if (zzbuw != null) {
      return zzbuw;
    }
    return new zzuv(paramZzut);
  }
  
  public final void Refresh(int paramInt, long paramLong)
    throws IOException
  {
    zzbuf.trim(paramInt, paramLong);
  }
  
  public final void a(int paramInt1, int paramInt2)
    throws IOException
  {
    zzbuf.add(paramInt1, paramInt2);
  }
  
  public final void a(int paramInt, long paramLong)
    throws IOException
  {
    zzbuf.trim(paramInt, paramLong);
  }
  
  public final void a(int paramInt, Object paramObject)
    throws IOException
  {
    if ((paramObject instanceof zzud))
    {
      zzbuf.b(paramInt, (zzud)paramObject);
      return;
    }
    zzbuf.visitField(paramInt, (zzwt)paramObject);
  }
  
  public final void a(int paramInt, List paramList)
    throws IOException
  {
    boolean bool = paramList instanceof zzwc;
    int i = 0;
    int j = 0;
    if (bool)
    {
      zzwc localZzwc = (zzwc)paramList;
      i = j;
      while (i < paramList.size())
      {
        Object localObject = localZzwc.getRaw(i);
        if ((localObject instanceof String)) {
          zzbuf.b(paramInt, (String)localObject);
        } else {
          zzbuf.l(paramInt, (zzud)localObject);
        }
        i += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.b(paramInt, (String)paramList.get(i));
      i += 1;
    }
  }
  
  public final void add(int paramInt, double paramDouble)
    throws IOException
  {
    zzbuf.set(paramInt, paramDouble);
  }
  
  public final void add(int paramInt, float paramFloat)
    throws IOException
  {
    zzbuf.inc(paramInt, paramFloat);
  }
  
  public final void add(int paramInt, Object paramObject, zzxj paramZzxj)
    throws IOException
  {
    zzut localZzut = zzbuf;
    paramObject = (zzwt)paramObject;
    localZzut.append(paramInt, 3);
    paramZzxj.a(paramObject, zzbuw);
    localZzut.append(paramInt, 4);
  }
  
  public final void add(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzba(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzaw(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.remove(paramInt, ((Long)paramList.get(i)).longValue());
      i += 1;
    }
  }
  
  public final void add(int paramInt, boolean paramBoolean)
    throws IOException
  {
    zzbuf.process(paramInt, paramBoolean);
  }
  
  public final void addAll(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbf(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzba(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.put(paramInt, ((Integer)paramList.get(i)).intValue());
      i += 1;
    }
  }
  
  public final void addHeaders(int paramInt, zzwm paramZzwm, Map paramMap)
    throws IOException
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      zzbuf.append(paramInt, 2);
      zzbuf.zzay(zzwl.get(paramZzwm, localEntry.getKey(), localEntry.getValue()));
      zzwl.setHeader(zzbuf, paramZzwm, localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public final void append(int paramInt, Object paramObject, zzxj paramZzxj)
    throws IOException
  {
    zzbuf.f(paramInt, (zzwt)paramObject, paramZzxj);
  }
  
  public final void b(int paramInt, zzud paramZzud)
    throws IOException
  {
    zzbuf.l(paramInt, paramZzud);
  }
  
  public final void contains(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzay(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzav(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.trim(paramInt, ((Long)paramList.get(i)).longValue());
      i += 1;
    }
  }
  
  public final void create(int paramInt1, int paramInt2)
    throws IOException
  {
    zzbuf.parseColor(paramInt1, paramInt2);
  }
  
  public final void deleteFiles(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbc(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzax(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.parseColor(paramInt, ((Integer)paramList.get(i)).intValue());
      i += 1;
    }
  }
  
  public final void deleteServer(int paramInt, long paramLong)
    throws IOException
  {
    zzbuf.remove(paramInt, paramLong);
  }
  
  public final void e(int paramInt, List paramList)
    throws IOException
  {
    int i = 0;
    while (i < paramList.size())
    {
      zzbuf.l(paramInt, (zzud)paramList.get(i));
      i += 1;
    }
  }
  
  public final void getColors(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbh(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzax(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.parseColor(paramInt, ((Integer)paramList.get(i)).intValue());
      i += 1;
    }
  }
  
  public final void getDrawable(int paramInt1, int paramInt2)
    throws IOException
  {
    zzbuf.parseColor(paramInt1, paramInt2);
  }
  
  public final void getPath(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbg(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzba(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.put(paramInt, ((Integer)paramList.get(i)).intValue());
      i += 1;
    }
  }
  
  public final void getShares(int paramInt, List paramList, zzxj paramZzxj)
    throws IOException
  {
    int i = 0;
    while (i < paramList.size())
    {
      add(paramInt, paramList.get(i), paramZzxj);
      i += 1;
    }
  }
  
  public final void insert(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.insert(((Double)paramList.get(paramInt)).doubleValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.set(((Double)paramList.get(paramInt)).doubleValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.set(paramInt, ((Double)paramList.get(i)).doubleValue());
      i += 1;
    }
  }
  
  public final void list(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbd(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzay(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.substring(paramInt, ((Integer)paramList.get(i)).intValue());
      i += 1;
    }
  }
  
  public final void put(int paramInt1, int paramInt2)
    throws IOException
  {
    zzbuf.substring(paramInt1, paramInt2);
  }
  
  public final void put(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbb(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzax(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.get(paramInt, ((Long)paramList.get(i)).longValue());
      i += 1;
    }
  }
  
  public final void remap(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbe(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzaz(((Integer)paramList.get(paramInt)).intValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.add(paramInt, ((Integer)paramList.get(i)).intValue());
      i += 1;
    }
  }
  
  public final void saveData(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzbc(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzax(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.get(paramInt, ((Long)paramList.get(i)).longValue());
      i += 1;
    }
  }
  
  public final void scan(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.insert(((Boolean)paramList.get(paramInt)).booleanValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.set(((Boolean)paramList.get(paramInt)).booleanValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.process(paramInt, ((Boolean)paramList.get(i)).booleanValue());
      i += 1;
    }
  }
  
  public final void sendMail(int paramInt, List paramList, zzxj paramZzxj)
    throws IOException
  {
    int i = 0;
    while (i < paramList.size())
    {
      append(paramInt, paramList.get(i), paramZzxj);
      i += 1;
    }
  }
  
  public final void setRecurrence(int paramInt, long paramLong)
    throws IOException
  {
    zzbuf.get(paramInt, paramLong);
  }
  
  public final void toArray(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.write(((Float)paramList.get(paramInt)).floatValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.set(((Float)paramList.get(paramInt)).floatValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.inc(paramInt, ((Float)paramList.get(i)).floatValue());
      i += 1;
    }
  }
  
  public final void trim(int paramInt, List paramList, boolean paramBoolean)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      zzbuf.append(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size())
      {
        i += zzut.zzaz(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      zzbuf.zzay(i);
      paramInt = j;
      while (paramInt < paramList.size())
      {
        zzbuf.zzav(((Long)paramList.get(paramInt)).longValue());
        paramInt += 1;
      }
      return;
    }
    while (i < paramList.size())
    {
      zzbuf.trim(paramInt, ((Long)paramList.get(i)).longValue());
      i += 1;
    }
  }
  
  public final void valueOf(int paramInt1, int paramInt2)
    throws IOException
  {
    zzbuf.put(paramInt1, paramInt2);
  }
  
  public final void visitLocalVariable(int paramInt, long paramLong)
    throws IOException
  {
    zzbuf.get(paramInt, paramLong);
  }
  
  public final void visitTableSwitchInsn(int paramInt1, int paramInt2)
    throws IOException
  {
    zzbuf.put(paramInt1, paramInt2);
  }
  
  public final void visitTypeInsn(int paramInt, String paramString)
    throws IOException
  {
    zzbuf.b(paramInt, paramString);
  }
  
  public final void zzbk(int paramInt)
    throws IOException
  {
    zzbuf.append(paramInt, 3);
  }
  
  public final void zzbl(int paramInt)
    throws IOException
  {
    zzbuf.append(paramInt, 4);
  }
  
  public final int zzvj()
  {
    return zzvm.zze.zzbze;
  }
}
