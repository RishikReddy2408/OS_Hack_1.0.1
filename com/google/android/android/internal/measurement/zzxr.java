package com.google.android.android.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzxr
  implements Iterator<Object>
{
  zzxr() {}
  
  public final boolean hasNext()
  {
    return false;
  }
  
  public final Object next()
  {
    throw new NoSuchElementException();
  }
  
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}
