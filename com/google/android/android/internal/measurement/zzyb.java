package com.google.android.android.internal.measurement;

import java.io.IOException;

abstract class zzyb<T, B>
{
  zzyb() {}
  
  abstract void before(Object paramObject, zzyw paramZzyw)
    throws IOException;
  
  abstract boolean equalsIgnoreCase(zzxi paramZzxi);
  
  abstract void multiply(Object paramObject1, Object paramObject2);
  
  abstract Object negate(Object paramObject1, Object paramObject2);
  
  final boolean next(Object paramObject, zzxi paramZzxi)
    throws IOException
  {
    int i = paramZzxi.getTag();
    int j = i >>> 3;
    switch (i & 0x7)
    {
    default: 
      throw zzvt.zzwo();
    case 5: 
      readTag(paramObject, j, paramZzxi.zzul());
      return true;
    case 4: 
      return false;
    case 3: 
      Object localObject;
      while ((paramZzxi.zzve() != Integer.MAX_VALUE) && (next(localObject, paramZzxi))) {}
      if ((j << 3 | 0x4) == paramZzxi.getTag())
      {
        readTag(paramObject, j, zzab(localObject));
        return true;
      }
      throw zzvt.zzwn();
    case 2: 
      read(paramObject, j, paramZzxi.zzuo());
      return true;
    case 1: 
      readTag(paramObject, j, paramZzxi.zzuk());
      return true;
    }
    updateImage(paramObject, j, paramZzxi.zzui());
    return true;
  }
  
  abstract void operate(Object paramObject1, Object paramObject2);
  
  abstract void read(Object paramObject, int paramInt, zzud paramZzud);
  
  abstract void readTag(Object paramObject, int paramInt1, int paramInt2);
  
  abstract void readTag(Object paramObject, int paramInt, long paramLong);
  
  abstract void readTag(Object paramObject1, int paramInt, Object paramObject2);
  
  abstract void remainder(Object paramObject, zzyw paramZzyw)
    throws IOException;
  
  abstract void setEntry(Object paramObject);
  
  abstract void updateImage(Object paramObject, int paramInt, long paramLong);
  
  abstract Object zzab(Object paramObject);
  
  abstract int zzae(Object paramObject);
  
  abstract Object zzah(Object paramObject);
  
  abstract Object zzai(Object paramObject);
  
  abstract int zzaj(Object paramObject);
  
  abstract Object zzye();
}
