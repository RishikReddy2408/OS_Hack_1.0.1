package com.google.android.android.internal.measurement;

import java.util.Collections;
import java.util.List;

final class zzwf
  extends zzwd
{
  private static final Class<?> zzcal = Collections.unmodifiableList(Collections.emptyList()).getClass();
  
  private zzwf()
  {
    super(null);
  }
  
  private static List get(Object paramObject, long paramLong)
  {
    return (List)zzyh.get(paramObject, paramLong);
  }
  
  private static List get(Object paramObject, long paramLong, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  final void getField(Object paramObject, long paramLong)
  {
    Object localObject = (List)zzyh.get(paramObject, paramLong);
    if ((localObject instanceof zzwc))
    {
      localObject = ((zzwc)localObject).zzww();
    }
    else
    {
      if (zzcal.isAssignableFrom(localObject.getClass())) {
        return;
      }
      if (((localObject instanceof zzxe)) && ((localObject instanceof zzvs)))
      {
        paramObject = (zzvs)localObject;
        if (!paramObject.zztw()) {
          return;
        }
        paramObject.zzsm();
        return;
      }
      localObject = Collections.unmodifiableList((List)localObject);
    }
    zzyh.add(paramObject, paramLong, localObject);
  }
  
  final List read(Object paramObject, long paramLong)
  {
    return get(paramObject, paramLong, 10);
  }
  
  final void visitTypeInsn(Object paramObject1, Object paramObject2, long paramLong)
  {
    List localList2 = get(paramObject2, paramLong);
    paramObject2 = localList2;
    List localList1 = get(paramObject1, paramLong, localList2.size());
    int i = localList1.size();
    int j = localList2.size();
    if ((i > 0) && (j > 0)) {
      localList1.addAll(localList2);
    }
    if (i > 0) {
      paramObject2 = localList1;
    }
    zzyh.add(paramObject1, paramLong, paramObject2);
  }
}
