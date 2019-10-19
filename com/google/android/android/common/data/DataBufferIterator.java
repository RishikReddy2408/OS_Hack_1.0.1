package com.google.android.android.common.data;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;
import java.util.NoSuchElementException;

@KeepForSdk
public class DataBufferIterator<T>
  implements Iterator<T>
{
  protected final com.google.android.gms.common.data.DataBuffer<T> zalj;
  protected int zalk;
  
  public DataBufferIterator(DataBuffer paramDataBuffer)
  {
    zalj = ((DataBuffer)Preconditions.checkNotNull(paramDataBuffer));
    zalk = -1;
  }
  
  public boolean hasNext()
  {
    return zalk < zalj.getCount() - 1;
  }
  
  public Object next()
  {
    if (hasNext())
    {
      localObject = zalj;
      i = zalk + 1;
      zalk = i;
      return ((DataBuffer)localObject).get(i);
    }
    int i = zalk;
    Object localObject = new StringBuilder(46);
    ((StringBuilder)localObject).append("Cannot advance the iterator beyond ");
    ((StringBuilder)localObject).append(i);
    throw new NoSuchElementException(((StringBuilder)localObject).toString());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}
