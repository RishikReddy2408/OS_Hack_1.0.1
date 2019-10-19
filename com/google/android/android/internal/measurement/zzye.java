package com.google.android.android.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzye
  extends AbstractList<String>
  implements com.google.android.gms.internal.measurement.zzwc, RandomAccess
{
  private final zzwc zzccq;
  
  public zzye(zzwc paramZzwc)
  {
    zzccq = paramZzwc;
  }
  
  public final void add(zzud paramZzud)
  {
    throw new UnsupportedOperationException();
  }
  
  public final Object getRaw(int paramInt)
  {
    return zzccq.getRaw(paramInt);
  }
  
  public final Iterator iterator()
  {
    return new zzyg(this);
  }
  
  public final ListIterator listIterator(int paramInt)
  {
    return new zzyf(this, paramInt);
  }
  
  public final int size()
  {
    return zzccq.size();
  }
  
  public final List zzwv()
  {
    return zzccq.zzwv();
  }
  
  public final zzwc zzww()
  {
    return this;
  }
}
