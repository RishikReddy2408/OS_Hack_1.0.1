package com.google.android.android.common.data;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T>
  implements DataBuffer<T>
{
  protected final DataHolder mDataHolder;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder)
  {
    mDataHolder = paramDataHolder;
  }
  
  public final void close()
  {
    release();
  }
  
  public abstract Object get(int paramInt);
  
  public int getCount()
  {
    if (mDataHolder == null) {
      return 0;
    }
    return mDataHolder.getCount();
  }
  
  public Bundle getMetadata()
  {
    return mDataHolder.getMetadata();
  }
  
  public boolean isClosed()
  {
    return (mDataHolder == null) || (mDataHolder.isClosed());
  }
  
  public Iterator iterator()
  {
    return new DataBufferIterator(this);
  }
  
  public void release()
  {
    if (mDataHolder != null) {
      mDataHolder.close();
    }
  }
  
  public Iterator singleRefIterator()
  {
    return new SingleRefDataBufferIterator(this);
  }
}
