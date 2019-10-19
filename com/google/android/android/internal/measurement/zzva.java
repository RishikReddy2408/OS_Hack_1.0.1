package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzvf;
import java.io.IOException;
import java.util.Map.Entry;

abstract class zzva<T extends zzvf<T>>
{
  zzva() {}
  
  abstract boolean accepts(zzwt paramZzwt);
  
  abstract void add(zzyw paramZzyw, Map.Entry paramEntry)
    throws IOException;
  
  abstract zzvd get(Object paramObject);
  
  abstract Object get(zzxi paramZzxi, Object paramObject1, zzuz paramZzuz, zzvd paramZzvd, Object paramObject2, zzyb paramZzyb)
    throws IOException;
  
  abstract zzvd getName(Object paramObject);
  
  abstract int getValue(Map.Entry paramEntry);
  
  abstract Object read(zzuz paramZzuz, zzwt paramZzwt, int paramInt);
  
  abstract void read(zzud paramZzud, Object paramObject, zzuz paramZzuz, zzvd paramZzvd)
    throws IOException;
  
  abstract void read(zzxi paramZzxi, Object paramObject, zzuz paramZzuz, zzvd paramZzvd)
    throws IOException;
  
  abstract void setEntry(Object paramObject);
  
  abstract void xor(Object paramObject, zzvd paramZzvd);
}
