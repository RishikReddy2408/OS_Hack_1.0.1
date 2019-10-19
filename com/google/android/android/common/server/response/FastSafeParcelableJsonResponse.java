package com.google.android.android.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@KeepForSdk
@ShowFirstParty
public abstract class FastSafeParcelableJsonResponse
  extends FastJsonResponse
  implements SafeParcelable
{
  public FastSafeParcelableJsonResponse() {}
  
  public final int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!getClass().isInstance(paramObject)) {
      return false;
    }
    paramObject = (FastJsonResponse)paramObject;
    Iterator localIterator = getFieldMappings().values().iterator();
    while (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (isFieldSet(localField))
      {
        if ((!paramObject.isFieldSet(localField)) || (!getFieldValue(localField).equals(paramObject.getFieldValue(localField)))) {
          return false;
        }
      }
      else if (paramObject.isFieldSet(localField)) {
        return false;
      }
    }
    return true;
  }
  
  public Object getValueObject(String paramString)
  {
    return null;
  }
  
  public int hashCode()
  {
    Iterator localIterator = getFieldMappings().values().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (isFieldSet(localField)) {
        i = i * 31 + getFieldValue(localField).hashCode();
      }
    }
    return i;
  }
  
  public boolean isPrimitiveFieldSet(String paramString)
  {
    return false;
  }
  
  public byte[] toByteArray()
  {
    Parcel localParcel = Parcel.obtain();
    writeToParcel(localParcel, 0);
    byte[] arrayOfByte = localParcel.marshall();
    localParcel.recycle();
    return arrayOfByte;
  }
}
