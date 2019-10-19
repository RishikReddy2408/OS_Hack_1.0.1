package com.google.android.android.common.aimsicd;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

@KeepForSdk
public class DataBufferResponse<T, R extends com.google.android.gms.common.data.AbstractDataBuffer<T>,  extends com.google.android.gms.common.api.Result>
  extends com.google.android.gms.common.api.Response<R>
  implements DataBuffer<T>
{
  public DataBufferResponse() {}
  
  public DataBufferResponse(com.google.android.android.common.data.AbstractDataBuffer paramAbstractDataBuffer)
  {
    super((Result)paramAbstractDataBuffer);
  }
  
  public void close()
  {
    ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).close();
  }
  
  public Object get(int paramInt)
  {
    return ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).get(paramInt);
  }
  
  public int getCount()
  {
    return ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).getCount();
  }
  
  public Bundle getMetadata()
  {
    return ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).getMetadata();
  }
  
  public boolean isClosed()
  {
    return ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).isClosed();
  }
  
  public Iterator iterator()
  {
    return ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).iterator();
  }
  
  public void release()
  {
    ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).release();
  }
  
  public Iterator singleRefIterator()
  {
    return ((com.google.android.android.common.data.AbstractDataBuffer)getResult()).singleRefIterator();
  }
}
