package com.google.android.android.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.android.common.server.response.FastJsonResponse.FieldConverter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.converter.zaa;

@SafeParcelable.Class(creator="ConverterWrapperCreator")
public final class MapPack
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<zaa> CREATOR = new VerticalProgressBar.SavedState.1();
  @SafeParcelable.VersionField(id=1)
  private final int zale;
  @SafeParcelable.Field(getter="getStringToIntConverter", id=2)
  private final StringToIntConverter zapk;
  
  MapPack(int paramInt, StringToIntConverter paramStringToIntConverter)
  {
    zale = paramInt;
    zapk = paramStringToIntConverter;
  }
  
  private MapPack(StringToIntConverter paramStringToIntConverter)
  {
    zale = 1;
    zapk = paramStringToIntConverter;
  }
  
  public static MapPack getSize(FastJsonResponse.FieldConverter paramFieldConverter)
  {
    if ((paramFieldConverter instanceof StringToIntConverter)) {
      return new MapPack((StringToIntConverter)paramFieldConverter);
    }
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeParcelable(paramParcel, 2, zapk, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final FastJsonResponse.FieldConverter zaci()
  {
    if (zapk != null) {
      return zapk;
    }
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
}
