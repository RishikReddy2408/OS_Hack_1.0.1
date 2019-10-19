package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzza;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzzb<M extends zzza<M>, T>
{
  public final int size;
  private final int type;
  private final com.google.android.gms.internal.measurement.zzvm<?, ?> zzbyp;
  protected final Class<T> zzcfd;
  protected final boolean zzcfe;
  
  private zzzb(int paramInt1, Class paramClass, int paramInt2, boolean paramBoolean)
  {
    this(11, paramClass, null, 810, false);
  }
  
  private zzzb(int paramInt1, Class paramClass, zzvm paramZzvm, int paramInt2, boolean paramBoolean)
  {
    type = paramInt1;
    zzcfd = paramClass;
    size = paramInt2;
    zzcfe = false;
    zzbyp = null;
  }
  
  public static zzzb addPlaylist(int paramInt, Class paramClass, long paramLong)
  {
    return new zzzb(11, paramClass, 810, false);
  }
  
  private final Object readData(zzyx paramZzyx)
  {
    if (zzcfe) {
      localObject1 = zzcfd.getComponentType();
    } else {
      localObject1 = zzcfd;
    }
    switch (type)
    {
    default: 
      break;
    }
    try
    {
      localObject2 = ((Class)localObject1).newInstance();
      localObject2 = (zzzg)localObject2;
      paramZzyx.digest((zzzg)localObject2);
      return localObject2;
    }
    catch (IOException paramZzyx)
    {
      int i;
      throw new IllegalArgumentException("Error reading extension field", paramZzyx);
    }
    catch (IllegalAccessException paramZzyx)
    {
      localObject1 = String.valueOf(localObject1);
      localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 33);
      ((StringBuilder)localObject2).append("Error creating instance of class ");
      ((StringBuilder)localObject2).append((String)localObject1);
      throw new IllegalArgumentException(((StringBuilder)localObject2).toString(), paramZzyx);
    }
    catch (InstantiationException paramZzyx)
    {
      localObject1 = String.valueOf(localObject1);
      Object localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 33);
      ((StringBuilder)localObject2).append("Error creating instance of class ");
      ((StringBuilder)localObject2).append((String)localObject1);
      throw new IllegalArgumentException(((StringBuilder)localObject2).toString(), paramZzyx);
    }
    localObject2 = ((Class)localObject1).newInstance();
    localObject2 = (zzzg)localObject2;
    i = size;
    paramZzyx.readFrom((zzzg)localObject2, i >>> 3);
    return localObject2;
    i = type;
    paramZzyx = new StringBuilder(24);
    paramZzyx.append("Unknown type ");
    paramZzyx.append(i);
    paramZzyx = new IllegalArgumentException(paramZzyx.toString());
    throw paramZzyx;
  }
  
  protected final void add(Object paramObject, zzyy paramZzyy)
  {
    int i = size;
    try
    {
      paramZzyy.zzca(i);
      switch (type)
      {
      default: 
        break;
      case 11: 
        paramObject = (zzzg)paramObject;
        paramZzyy.newLine(paramObject);
        return;
      case 10: 
        i = size;
        paramObject = (zzzg)paramObject;
        paramObject.multiply(paramZzyy);
        paramZzyy.add(i >>> 3, 4);
        return;
      }
      i = type;
      paramObject = new StringBuilder(24);
      paramObject.append("Unknown type ");
      paramObject.append(i);
      paramObject = new IllegalArgumentException(paramObject.toString());
      throw paramObject;
    }
    catch (IOException paramObject)
    {
      throw new IllegalStateException(paramObject);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzzb)) {
      return false;
    }
    paramObject = (zzzb)paramObject;
    return (type == type) && (zzcfd == zzcfd) && (size == size) && (zzcfe == zzcfe);
  }
  
  public final int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:632)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  final Object zzah(List paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (zzcfe)
    {
      ArrayList localArrayList = new ArrayList();
      int j = 0;
      int i = 0;
      while (i < paramList.size())
      {
        zzzi localZzzi = (zzzi)paramList.get(i);
        if (zzbug.length != 0) {
          localArrayList.add(readData(zzyx.getInputStream(zzbug)));
        }
        i += 1;
      }
      int k = localArrayList.size();
      if (k == 0) {
        return null;
      }
      paramList = zzcfd.cast(Array.newInstance(zzcfd.getComponentType(), k));
      i = j;
      while (i < k)
      {
        Array.set(paramList, i, localArrayList.get(i));
        i += 1;
      }
      return paramList;
    }
    if (paramList.isEmpty()) {
      return null;
    }
    paramList = (zzzi)paramList.get(paramList.size() - 1);
    return zzcfd.cast(readData(zzyx.getInputStream(zzbug)));
  }
  
  protected final int zzak(Object paramObject)
  {
    int i = size >>> 3;
    switch (type)
    {
    default: 
      i = type;
      paramObject = new StringBuilder(24);
      paramObject.append("Unknown type ");
      paramObject.append(i);
      throw new IllegalArgumentException(paramObject.toString());
    case 11: 
      return zzyy.addMenuItem(i, (zzzg)paramObject);
    }
    paramObject = (zzzg)paramObject;
    return (zzyy.zzbb(i) << 1) + paramObject.zzvu();
  }
}
