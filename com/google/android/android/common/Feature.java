package com.google.android.android.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Objects.ToStringHelper;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;

@KeepForSdk
@SafeParcelable.Class(creator="FeatureCreator")
public class Feature
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<com.google.android.gms.common.Feature> CREATOR = new DiscreteSeekBar.CustomState.1();
  @SafeParcelable.Field(defaultValue="-1", getter="getVersion", id=3)
  private final long address;
  @SafeParcelable.Field(getter="getName", id=1)
  private final String name;
  @Deprecated
  @SafeParcelable.Field(getter="getOldVersion", id=2)
  private final int port;
  
  public Feature(String paramString, int paramInt, long paramLong)
  {
    name = paramString;
    port = paramInt;
    address = paramLong;
  }
  
  public Feature(String paramString, long paramLong)
  {
    name = paramString;
    address = paramLong;
    port = -1;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Feature))
    {
      paramObject = (Feature)paramObject;
      if (((getName() != null) && (getName().equals(paramObject.getName()))) || ((getName() == null) && (paramObject.getName() == null) && (getVersion() == paramObject.getVersion()))) {
        return true;
      }
    }
    return false;
  }
  
  public String getName()
  {
    return name;
  }
  
  public long getVersion()
  {
    if (address == -1L) {
      return port;
    }
    return address;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(new Object[] { getName(), Long.valueOf(getVersion()) });
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("name", getName()).add("version", Long.valueOf(getVersion())).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 1, getName(), false);
    SafeParcelWriter.writeInt(paramParcel, 2, port);
    SafeParcelWriter.writeLong(paramParcel, 3, getVersion());
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
