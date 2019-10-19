package com.google.android.android.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;

@SafeParcelable.Class(creator="UserAttributeParcelCreator")
public final class zzfh
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<com.google.android.gms.measurement.internal.zzfh> CREATOR = new zzfi();
  @SafeParcelable.Field(id=2)
  public final String name;
  @SafeParcelable.Field(id=7)
  public final String origin;
  @SafeParcelable.Field(id=1)
  private final int versionCode;
  @SafeParcelable.Field(id=6)
  private final String zzamp;
  @SafeParcelable.Field(id=3)
  public final long zzaue;
  @SafeParcelable.Field(id=4)
  private final Long zzauf;
  @SafeParcelable.Field(id=5)
  private final Float zzaug;
  @SafeParcelable.Field(id=8)
  private final Double zzauh;
  
  zzfh(int paramInt, String paramString1, long paramLong, Long paramLong1, Float paramFloat, String paramString2, String paramString3, Double paramDouble)
  {
    versionCode = paramInt;
    name = paramString1;
    zzaue = paramLong;
    zzauf = paramLong1;
    paramString1 = null;
    zzaug = null;
    if (paramInt == 1)
    {
      if (paramFloat != null) {
        paramString1 = Double.valueOf(paramFloat.doubleValue());
      }
      zzauh = paramString1;
    }
    else
    {
      zzauh = paramDouble;
    }
    zzamp = paramString2;
    origin = paramString3;
  }
  
  zzfh(zzfj paramZzfj)
  {
    this(name, zzaue, value, origin);
  }
  
  zzfh(String paramString1, long paramLong, Object paramObject, String paramString2)
  {
    Preconditions.checkNotEmpty(paramString1);
    versionCode = 2;
    name = paramString1;
    zzaue = paramLong;
    origin = paramString2;
    if (paramObject == null)
    {
      zzauf = null;
      zzaug = null;
      zzauh = null;
      zzamp = null;
      return;
    }
    if ((paramObject instanceof Long))
    {
      zzauf = ((Long)paramObject);
      zzaug = null;
      zzauh = null;
      zzamp = null;
      return;
    }
    if ((paramObject instanceof String))
    {
      zzauf = null;
      zzaug = null;
      zzauh = null;
      zzamp = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      zzauf = null;
      zzaug = null;
      zzauh = ((Double)paramObject);
      zzamp = null;
      return;
    }
    throw new IllegalArgumentException("User attribute given of un-supported type");
  }
  
  public final Object getValue()
  {
    if (zzauf != null) {
      return zzauf;
    }
    if (zzauh != null) {
      return zzauh;
    }
    if (zzamp != null) {
      return zzamp;
    }
    return null;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, versionCode);
    SafeParcelWriter.writeString(paramParcel, 2, name, false);
    SafeParcelWriter.writeLong(paramParcel, 3, zzaue);
    SafeParcelWriter.writeLongObject(paramParcel, 4, zzauf, false);
    SafeParcelWriter.writeFloatObject(paramParcel, 5, null, false);
    SafeParcelWriter.writeString(paramParcel, 6, zzamp, false);
    SafeParcelWriter.writeString(paramParcel, 7, origin, false);
    SafeParcelWriter.writeDoubleObject(paramParcel, 8, zzauh, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
