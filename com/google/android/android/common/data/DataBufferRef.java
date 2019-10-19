package com.google.android.android.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DataBufferRef
{
  @KeepForSdk
  protected final DataHolder mDataHolder;
  @KeepForSdk
  protected int mDataRow;
  private int zalm;
  
  public DataBufferRef(DataHolder paramDataHolder, int paramInt)
  {
    mDataHolder = ((DataHolder)Preconditions.checkNotNull(paramDataHolder));
    register(paramInt);
  }
  
  protected void copyToBuffer(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    mDataHolder.put(paramString, mDataRow, zalm, paramCharArrayBuffer);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof DataBufferRef))
    {
      paramObject = (DataBufferRef)paramObject;
      if ((Objects.equal(Integer.valueOf(mDataRow), Integer.valueOf(mDataRow))) && (Objects.equal(Integer.valueOf(zalm), Integer.valueOf(zalm))) && (mDataHolder == mDataHolder)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean getBoolean(String paramString)
  {
    return mDataHolder.getBoolean(paramString, mDataRow, zalm);
  }
  
  protected byte[] getByteArray(String paramString)
  {
    return mDataHolder.getByteArray(paramString, mDataRow, zalm);
  }
  
  protected int getDataRow()
  {
    return mDataRow;
  }
  
  protected double getDouble(String paramString)
  {
    return mDataHolder.getDouble(paramString, mDataRow, zalm);
  }
  
  protected float getFloat(String paramString)
  {
    return mDataHolder.getData(paramString, mDataRow, zalm);
  }
  
  protected int getInteger(String paramString)
  {
    return mDataHolder.getInteger(paramString, mDataRow, zalm);
  }
  
  protected long getLong(String paramString)
  {
    return mDataHolder.getLong(paramString, mDataRow, zalm);
  }
  
  protected String getString(String paramString)
  {
    return mDataHolder.getString(paramString, mDataRow, zalm);
  }
  
  public boolean hasColumn(String paramString)
  {
    return mDataHolder.hasColumn(paramString);
  }
  
  protected boolean hasNull(String paramString)
  {
    return mDataHolder.hasNull(paramString, mDataRow, zalm);
  }
  
  public int hashCode()
  {
    return Objects.hashCode(new Object[] { Integer.valueOf(mDataRow), Integer.valueOf(zalm), mDataHolder });
  }
  
  public boolean isDataValid()
  {
    return !mDataHolder.isClosed();
  }
  
  protected Uri parseUri(String paramString)
  {
    paramString = mDataHolder.getString(paramString, mDataRow, zalm);
    if (paramString == null) {
      return null;
    }
    return Uri.parse(paramString);
  }
  
  protected final void register(int paramInt)
  {
    boolean bool;
    if ((paramInt >= 0) && (paramInt < mDataHolder.getCount())) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool);
    mDataRow = paramInt;
    zalm = mDataHolder.getWindowIndex(mDataRow);
  }
}
