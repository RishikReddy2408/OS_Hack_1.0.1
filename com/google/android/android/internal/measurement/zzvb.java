package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.Map.Entry;

final class zzvb
  extends com.google.android.gms.internal.measurement.zzva<Object>
{
  zzvb() {}
  
  final boolean accepts(zzwt paramZzwt)
  {
    return paramZzwt instanceof zzvm.zzc;
  }
  
  final void add(zzyw paramZzyw, Map.Entry paramEntry)
    throws IOException
  {
    paramEntry.getKey();
    throw new NoSuchMethodError();
  }
  
  final zzvd get(Object paramObject)
  {
    zzvd localZzvd2 = getName(paramObject);
    zzvd localZzvd1 = localZzvd2;
    if (localZzvd2.isImmutable())
    {
      localZzvd1 = (zzvd)localZzvd2.clone();
      xor(paramObject, localZzvd1);
    }
    return localZzvd1;
  }
  
  final Object get(zzxi paramZzxi, Object paramObject1, zzuz paramZzuz, zzvd paramZzvd, Object paramObject2, zzyb paramZzyb)
    throws IOException
  {
    throw new NoSuchMethodError();
  }
  
  final zzvd getName(Object paramObject)
  {
    return zzbys;
  }
  
  final int getValue(Map.Entry paramEntry)
  {
    paramEntry.getKey();
    throw new NoSuchMethodError();
  }
  
  final Object read(zzuz paramZzuz, zzwt paramZzwt, int paramInt)
  {
    return paramZzuz.readInteger(paramZzwt, paramInt);
  }
  
  final void read(zzud paramZzud, Object paramObject, zzuz paramZzuz, zzvd paramZzvd)
    throws IOException
  {
    throw new NoSuchMethodError();
  }
  
  final void read(zzxi paramZzxi, Object paramObject, zzuz paramZzuz, zzvd paramZzvd)
    throws IOException
  {
    throw new NoSuchMethodError();
  }
  
  final void setEntry(Object paramObject)
  {
    getName(paramObject).zzsm();
  }
  
  final void xor(Object paramObject, zzvd paramZzvd)
  {
    zzbys = paramZzvd;
  }
}
