package com.google.android.android.internal.measurement;

import java.util.List;

final class zzwg
  extends zzwd
{
  private zzwg()
  {
    super(null);
  }
  
  private static zzvs append(Object paramObject, long paramLong)
  {
    return (zzvs)zzyh.get(paramObject, paramLong);
  }
  
  final void getField(Object paramObject, long paramLong)
  {
    append(paramObject, paramLong).zzsm();
  }
  
  final List read(Object paramObject, long paramLong)
  {
    zzvs localZzvs2 = append(paramObject, paramLong);
    zzvs localZzvs1 = localZzvs2;
    if (!localZzvs2.zztw())
    {
      int i = localZzvs2.size();
      if (i == 0) {
        i = 10;
      } else {
        i <<= 1;
      }
      localZzvs1 = localZzvs2.zzak(i);
      zzyh.add(paramObject, paramLong, localZzvs1);
    }
    return localZzvs1;
  }
  
  final void visitTypeInsn(Object paramObject1, Object paramObject2, long paramLong)
  {
    zzvs localZzvs2 = append(paramObject1, paramLong);
    zzvs localZzvs1 = localZzvs2;
    zzvs localZzvs3 = append(paramObject2, paramLong);
    Object localObject = localZzvs3;
    int i = localZzvs2.size();
    int j = localZzvs3.size();
    paramObject2 = localZzvs1;
    if (i > 0)
    {
      paramObject2 = localZzvs1;
      if (j > 0)
      {
        paramObject2 = localZzvs1;
        if (!localZzvs2.zztw()) {
          paramObject2 = localZzvs2.zzak(j + i);
        }
        paramObject2.addAll(localZzvs3);
      }
    }
    if (i > 0) {
      localObject = paramObject2;
    }
    zzyh.add(paramObject1, paramLong, localObject);
  }
}
