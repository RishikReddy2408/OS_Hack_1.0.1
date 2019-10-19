package com.google.android.android.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DataBufferSafeParcelable<T extends com.google.android.gms.common.internal.safeparcel.SafeParcelable>
  extends com.google.android.gms.common.data.AbstractDataBuffer<T>
{
  private static final String[] zaln = { "data" };
  private final Parcelable.Creator<T> zalo;
  
  public DataBufferSafeParcelable(DataHolder paramDataHolder, Parcelable.Creator paramCreator)
  {
    super(paramDataHolder);
    zalo = paramCreator;
  }
  
  public static void addValue(DataHolder.Builder paramBuilder, com.google.android.android.common.internal.safeparcel.SafeParcelable paramSafeParcelable)
  {
    Parcel localParcel = Parcel.obtain();
    paramSafeParcelable.writeToParcel(localParcel, 0);
    paramSafeParcelable = new ContentValues();
    paramSafeParcelable.put("data", localParcel.marshall());
    paramBuilder.withRow(paramSafeParcelable);
    localParcel.recycle();
  }
  
  public static DataHolder.Builder buildDataHolder()
  {
    return DataHolder.builder(zaln);
  }
  
  public com.google.android.android.common.internal.safeparcel.SafeParcelable get(int paramInt)
  {
    Object localObject = mDataHolder.getByteArray("data", paramInt, mDataHolder.getWindowIndex(paramInt));
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall((byte[])localObject, 0, localObject.length);
    localParcel.setDataPosition(0);
    localObject = (com.google.android.android.common.internal.safeparcel.SafeParcelable)zalo.createFromParcel(localParcel);
    localParcel.recycle();
    return localObject;
  }
}
