package com.google.android.android.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@SafeParcelable.Class(creator="EventParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzad
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<com.google.android.gms.measurement.internal.zzad> CREATOR = new zzae();
  @SafeParcelable.Field(id=2)
  public final String name;
  @SafeParcelable.Field(id=4)
  public final String origin;
  @SafeParcelable.Field(id=3)
  public final zzaa zzaid;
  @SafeParcelable.Field(id=5)
  public final long zzaip;
  
  zzad(zzad paramZzad, long paramLong)
  {
    Preconditions.checkNotNull(paramZzad);
    name = name;
    zzaid = zzaid;
    origin = origin;
    zzaip = paramLong;
  }
  
  public zzad(String paramString1, zzaa paramZzaa, String paramString2, long paramLong)
  {
    name = paramString1;
    zzaid = paramZzaa;
    origin = paramString2;
    zzaip = paramLong;
  }
  
  public final String toString()
  {
    String str1 = origin;
    String str2 = name;
    String str3 = String.valueOf(zzaid);
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str1).length() + 21 + String.valueOf(str2).length() + String.valueOf(str3).length());
    localStringBuilder.append("origin=");
    localStringBuilder.append(str1);
    localStringBuilder.append(",name=");
    localStringBuilder.append(str2);
    localStringBuilder.append(",params=");
    localStringBuilder.append(str3);
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, name, false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, zzaid, paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 4, origin, false);
    SafeParcelWriter.writeLong(paramParcel, 5, zzaip);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}
