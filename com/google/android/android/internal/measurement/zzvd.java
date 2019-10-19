package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

final class zzvd<FieldDescriptorType extends com.google.android.gms.internal.measurement.zzvf<FieldDescriptorType>>
{
  private static final zzvd zzbvs = new zzvd(true);
  private boolean zzbpo;
  private final com.google.android.gms.internal.measurement.zzxm<FieldDescriptorType, Object> zzbvq;
  private boolean zzbvr = false;
  
  private zzvd()
  {
    zzbvq = zzxm.zzbt(16);
  }
  
  private zzvd(boolean paramBoolean)
  {
    zzbvq = zzxm.zzbt(0);
    zzsm();
  }
  
  private final Object descend(zzvf paramZzvf)
  {
    paramZzvf = zzbvq.get(paramZzvf);
    if ((paramZzvf instanceof zzvw)) {
      return zzvw.zzwt();
    }
    return paramZzvf;
  }
  
  private static int execute(Map.Entry paramEntry)
  {
    zzvf localZzvf = (zzvf)paramEntry.getKey();
    Object localObject = paramEntry.getValue();
    if ((localZzvf.zzvx() == zzyv.zzcet) && (!localZzvf.zzvy()) && (!localZzvf.zzvz()))
    {
      if ((localObject instanceof zzvw)) {
        return zzut.get(((zzvf)paramEntry.getKey()).getValue(), (zzvw)localObject);
      }
      return zzut.Decode(((zzvf)paramEntry.getKey()).getValue(), (zzwt)localObject);
    }
    return write(localZzvf, localObject);
  }
  
  private static Object getKey(Object paramObject)
  {
    if ((paramObject instanceof zzwz)) {
      return ((zzwz)paramObject).zzxj();
    }
    if ((paramObject instanceof byte[]))
    {
      paramObject = (byte[])paramObject;
      byte[] arrayOfByte = new byte[paramObject.length];
      System.arraycopy(paramObject, 0, arrayOfByte, 0, paramObject.length);
      return arrayOfByte;
    }
    return paramObject;
  }
  
  private static boolean isInitialized(Map.Entry paramEntry)
  {
    zzvf localZzvf = (zzvf)paramEntry.getKey();
    if (localZzvf.zzvx() == zzyv.zzcet)
    {
      if (localZzvf.zzvy())
      {
        paramEntry = ((List)paramEntry.getValue()).iterator();
        do
        {
          if (!paramEntry.hasNext()) {
            break;
          }
        } while (((zzwt)paramEntry.next()).isInitialized());
        return false;
      }
      paramEntry = paramEntry.getValue();
      if ((paramEntry instanceof zzwt))
      {
        if (!((zzwt)paramEntry).isInitialized()) {
          return false;
        }
      }
      else
      {
        if ((paramEntry instanceof zzvw)) {
          return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      }
    }
    return true;
  }
  
  private final void setField(zzvf paramZzvf, Object paramObject)
  {
    if (paramZzvf.zzvy())
    {
      if ((paramObject instanceof List))
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll((List)paramObject);
        paramObject = (ArrayList)localArrayList;
        int j = paramObject.size();
        int i = 0;
        while (i < j)
        {
          Object localObject = paramObject.get(i);
          i += 1;
          setField(paramZzvf.zzvw(), localObject);
        }
        paramObject = localArrayList;
      }
      else
      {
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      }
    }
    else {
      setField(paramZzvf.zzvw(), paramObject);
    }
    if ((paramObject instanceof zzvw)) {
      zzbvr = true;
    }
    zzbvq.put(paramZzvf, paramObject);
  }
  
  /* Error */
  private static void setField(zzyq paramZzyq, Object paramObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 169	com/google/android/android/internal/measurement/zzvo:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: getstatic 175	com/google/android/android/internal/measurement/zzve:zzbvt	[I
    //   8: aload_0
    //   9: invokevirtual 180	com/google/android/android/internal/measurement/zzyq:zzyp	()Lcom/google/android/android/internal/measurement/zzyv;
    //   12: invokevirtual 185	java/lang/Enum:ordinal	()I
    //   15: iaload
    //   16: istore_2
    //   17: iconst_0
    //   18: istore_3
    //   19: iload_2
    //   20: lookupswitch	default:+84->104, 1:+183->203, 2:+175->195, 3:+167->187, 4:+159->179, 5:+151->171, 6:+143->163, 7:+126->146, 8:+107->127, 9:+90->110
    //   104: goto +3 -> 107
    //   107: goto +101 -> 208
    //   110: aload_1
    //   111: instanceof 89
    //   114: ifne +27 -> 141
    //   117: aload_1
    //   118: instanceof 45
    //   121: ifeq +87 -> 208
    //   124: goto +17 -> 141
    //   127: aload_1
    //   128: instanceof 187
    //   131: ifne +10 -> 141
    //   134: aload_1
    //   135: instanceof 189
    //   138: ifeq +70 -> 208
    //   141: iconst_1
    //   142: istore_3
    //   143: goto +65 -> 208
    //   146: aload_1
    //   147: instanceof 191
    //   150: ifne -9 -> 141
    //   153: aload_1
    //   154: instanceof 105
    //   157: ifeq +51 -> 208
    //   160: goto -19 -> 141
    //   163: aload_1
    //   164: instanceof 193
    //   167: istore_3
    //   168: goto +40 -> 208
    //   171: aload_1
    //   172: instanceof 195
    //   175: istore_3
    //   176: goto +32 -> 208
    //   179: aload_1
    //   180: instanceof 197
    //   183: istore_3
    //   184: goto +24 -> 208
    //   187: aload_1
    //   188: instanceof 199
    //   191: istore_3
    //   192: goto +16 -> 208
    //   195: aload_1
    //   196: instanceof 201
    //   199: istore_3
    //   200: goto +8 -> 208
    //   203: aload_1
    //   204: instanceof 187
    //   207: istore_3
    //   208: iload_3
    //   209: ifeq +4 -> 213
    //   212: return
    //   213: new 133	java/lang/IllegalArgumentException
    //   216: dup
    //   217: ldc -121
    //   219: invokespecial 138	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   222: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	223	0	paramZzyq	zzyq
    //   0	223	1	paramObject	Object
    //   16	4	2	i	int
    //   18	191	3	bool	boolean
  }
  
  private final void trim(Map.Entry paramEntry)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a10 = a9\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  private static int write(zzvf paramZzvf, Object paramObject)
  {
    zzyq localZzyq = paramZzvf.zzvw();
    int k = paramZzvf.getValue();
    if (paramZzvf.zzvy())
    {
      boolean bool = paramZzvf.zzvz();
      int j = 0;
      int i = 0;
      if (bool)
      {
        paramZzvf = ((List)paramObject).iterator();
        while (paramZzvf.hasNext()) {
          i += write(localZzyq, paramZzvf.next());
        }
        return zzut.zzbb(k) + i + zzut.zzbj(i);
      }
      paramZzvf = ((List)paramObject).iterator();
      i = j;
      while (paramZzvf.hasNext()) {
        i += writeField(localZzyq, k, paramZzvf.next());
      }
      return i;
    }
    return writeField(localZzyq, k, paramObject);
  }
  
  private static int write(zzyq paramZzyq, Object paramObject)
  {
    switch (zzve.zzbuu[paramZzyq.ordinal()])
    {
    default: 
      throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
    case 18: 
      if ((paramObject instanceof zzvp)) {
        return zzut.zzbh(((zzvp)paramObject).intValue());
      }
      return zzut.zzbh(((Integer)paramObject).intValue());
    case 17: 
      return zzut.zzba(((Long)paramObject).longValue());
    case 16: 
      return zzut.zzbe(((Integer)paramObject).intValue());
    case 15: 
      return zzut.zzbc(((Long)paramObject).longValue());
    case 14: 
      return zzut.zzbg(((Integer)paramObject).intValue());
    case 13: 
      return zzut.zzbd(((Integer)paramObject).intValue());
    case 12: 
      if ((paramObject instanceof zzud)) {
        return zzut.min((zzud)paramObject);
      }
      return zzut.min((byte[])paramObject);
    case 11: 
      if ((paramObject instanceof zzud)) {
        return zzut.min((zzud)paramObject);
      }
      return zzut.zzfx((String)paramObject);
    case 10: 
      if ((paramObject instanceof zzvw)) {
        return zzut.min((zzvw)paramObject);
      }
      return zzut.getID((zzwt)paramObject);
    case 9: 
      return zzut.writeStream((zzwt)paramObject);
    case 8: 
      return zzut.insert(((Boolean)paramObject).booleanValue());
    case 7: 
      return zzut.zzbf(((Integer)paramObject).intValue());
    case 6: 
      return zzut.zzbb(((Long)paramObject).longValue());
    case 5: 
      return zzut.zzbc(((Integer)paramObject).intValue());
    case 4: 
      return zzut.zzaz(((Long)paramObject).longValue());
    case 3: 
      return zzut.zzay(((Long)paramObject).longValue());
    case 2: 
      return zzut.write(((Float)paramObject).floatValue());
    }
    return zzut.insert(((Double)paramObject).doubleValue());
  }
  
  static int writeField(zzyq paramZzyq, int paramInt, Object paramObject)
  {
    int i = zzut.zzbb(paramInt);
    paramInt = i;
    if (paramZzyq == zzyq.zzcdz)
    {
      zzvo.writeTag((zzwt)paramObject);
      paramInt = i << 1;
    }
    return paramInt + write(paramZzyq, paramObject);
  }
  
  static void writeField(zzut paramZzut, zzyq paramZzyq, int paramInt, Object paramObject)
    throws IOException
  {
    if (paramZzyq == zzyq.zzcdz)
    {
      paramZzyq = (zzwt)paramObject;
      zzvo.writeTag(paramZzyq);
      paramZzut.append(paramInt, 3);
      paramZzyq.writeValue(paramZzut);
      paramZzut.append(paramInt, 4);
      return;
    }
    paramZzut.append(paramInt, paramZzyq.zzyq());
    switch (zzve.zzbuu[paramZzyq.ordinal()])
    {
    default: 
      return;
    case 18: 
      if ((paramObject instanceof zzvp))
      {
        paramZzut.zzax(((zzvp)paramObject).intValue());
        return;
      }
      paramZzut.zzax(((Integer)paramObject).intValue());
      return;
    case 17: 
      paramZzut.zzaw(((Long)paramObject).longValue());
      return;
    case 16: 
      paramZzut.zzaz(((Integer)paramObject).intValue());
      return;
    case 15: 
      paramZzut.zzax(((Long)paramObject).longValue());
      return;
    case 14: 
      paramZzut.zzba(((Integer)paramObject).intValue());
      return;
    case 13: 
      paramZzut.zzay(((Integer)paramObject).intValue());
      return;
    case 12: 
      if ((paramObject instanceof zzud))
      {
        paramZzut.l((zzud)paramObject);
        return;
      }
      paramZzyq = (byte[])paramObject;
      paramZzut.writeTag(paramZzyq, 0, paramZzyq.length);
      return;
    case 11: 
      if ((paramObject instanceof zzud))
      {
        paramZzut.l((zzud)paramObject);
        return;
      }
      paramZzut.zzfw((String)paramObject);
      return;
    case 10: 
      paramZzut.writeField((zzwt)paramObject);
      return;
    case 9: 
      ((zzwt)paramObject).writeValue(paramZzut);
      return;
    case 8: 
      paramZzut.set(((Boolean)paramObject).booleanValue());
      return;
    case 7: 
      paramZzut.zzba(((Integer)paramObject).intValue());
      return;
    case 6: 
      paramZzut.zzax(((Long)paramObject).longValue());
      return;
    case 5: 
      paramZzut.zzax(((Integer)paramObject).intValue());
      return;
    case 4: 
      paramZzut.zzav(((Long)paramObject).longValue());
      return;
    case 3: 
      paramZzut.zzav(((Long)paramObject).longValue());
      return;
    case 2: 
      paramZzut.set(((Float)paramObject).floatValue());
      return;
    }
    paramZzut.set(((Double)paramObject).doubleValue());
  }
  
  public static zzvd zzvt()
  {
    return zzbvs;
  }
  
  final Iterator descendingIterator()
  {
    if (zzbvr) {
      return new zzvz(zzbvq.zzxy().iterator());
    }
    return zzbvq.zzxy().iterator();
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzvd)) {
      return false;
    }
    paramObject = (zzvd)paramObject;
    return zzbvq.equals(zzbvq);
  }
  
  public final int hashCode()
  {
    return zzbvq.hashCode();
  }
  
  public final void isEmpty(zzvd paramZzvd)
  {
    int i = 0;
    while (i < zzbvq.zzxw())
    {
      trim(zzbvq.zzbu(i));
      i += 1;
    }
    paramZzvd = zzbvq.zzxx().iterator();
    while (paramZzvd.hasNext()) {
      trim((Map.Entry)paramZzvd.next());
    }
  }
  
  final boolean isEmpty()
  {
    return zzbvq.isEmpty();
  }
  
  public final boolean isImmutable()
  {
    return zzbpo;
  }
  
  public final boolean isInitialized()
  {
    int i = 0;
    while (i < zzbvq.zzxw())
    {
      if (!isInitialized(zzbvq.zzbu(i))) {
        return false;
      }
      i += 1;
    }
    Iterator localIterator = zzbvq.zzxx().iterator();
    while (localIterator.hasNext()) {
      if (!isInitialized((Map.Entry)localIterator.next())) {
        return false;
      }
    }
    return true;
  }
  
  public final Iterator iterator()
  {
    if (zzbvr) {
      return new zzvz(zzbvq.entrySet().iterator());
    }
    return zzbvq.entrySet().iterator();
  }
  
  public final void zzsm()
  {
    if (zzbpo) {
      return;
    }
    zzbvq.zzsm();
    zzbpo = true;
  }
  
  public final int zzvu()
  {
    int j = 0;
    int i = 0;
    while (j < zzbvq.zzxw())
    {
      localObject = zzbvq.zzbu(j);
      i += write((zzvf)((Map.Entry)localObject).getKey(), ((Map.Entry)localObject).getValue());
      j += 1;
    }
    Object localObject = zzbvq.zzxx().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      i += write((zzvf)localEntry.getKey(), localEntry.getValue());
    }
    return i;
  }
  
  public final int zzvv()
  {
    int j = 0;
    int i = 0;
    while (j < zzbvq.zzxw())
    {
      i += execute(zzbvq.zzbu(j));
      j += 1;
    }
    Iterator localIterator = zzbvq.zzxx().iterator();
    while (localIterator.hasNext()) {
      i += execute((Map.Entry)localIterator.next());
    }
    return i;
  }
}
