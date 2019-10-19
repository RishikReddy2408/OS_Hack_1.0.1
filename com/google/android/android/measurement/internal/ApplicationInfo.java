package com.google.android.android.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.measurement.internal.zzh;

@SafeParcelable.Class(creator="AppMetadataCreator")
@SafeParcelable.Reserved({1, 20})
public final class ApplicationInfo
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<zzh> CREATOR = new DiscreteSeekBar.CustomState.1();
  @SafeParcelable.Field(id=2)
  public final String packageName;
  @SafeParcelable.Field(id=6)
  public final long zzadt;
  @SafeParcelable.Field(id=3)
  public final String zzafx;
  @SafeParcelable.Field(id=12)
  public final String zzafz;
  @SafeParcelable.Field(defaultValueUnchecked="Integer.MIN_VALUE", id=11)
  public final long zzagd;
  @SafeParcelable.Field(id=5)
  public final String zzage;
  @SafeParcelable.Field(id=7)
  public final long zzagf;
  @SafeParcelable.Field(defaultValue="true", id=9)
  public final boolean zzagg;
  @SafeParcelable.Field(id=13)
  public final long zzagh;
  @SafeParcelable.Field(defaultValue="true", id=16)
  public final boolean zzagi;
  @SafeParcelable.Field(defaultValue="true", id=17)
  public final boolean zzagj;
  @SafeParcelable.Field(id=19)
  public final String zzagk;
  @SafeParcelable.Field(id=8)
  public final String zzagv;
  @SafeParcelable.Field(id=10)
  public final boolean zzagw;
  @SafeParcelable.Field(id=14)
  public final long zzagx;
  @SafeParcelable.Field(id=15)
  public final int zzagy;
  @SafeParcelable.Field(id=18)
  public final boolean zzagz;
  @SafeParcelable.Field(id=4)
  public final String zzts;
  
  ApplicationInfo(String paramString1, String paramString2, String paramString3, long paramLong1, String paramString4, long paramLong2, long paramLong3, String paramString5, boolean paramBoolean1, boolean paramBoolean2, String paramString6, long paramLong4, long paramLong5, int paramInt, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString7)
  {
    Preconditions.checkNotEmpty(paramString1);
    packageName = paramString1;
    if (TextUtils.isEmpty(paramString2)) {
      paramString2 = null;
    }
    zzafx = paramString2;
    zzts = paramString3;
    zzagd = paramLong1;
    zzage = paramString4;
    zzadt = paramLong2;
    zzagf = paramLong3;
    zzagv = paramString5;
    zzagg = paramBoolean1;
    zzagw = paramBoolean2;
    zzafz = paramString6;
    zzagh = paramLong4;
    zzagx = paramLong5;
    zzagy = paramInt;
    zzagi = paramBoolean3;
    zzagj = paramBoolean4;
    zzagz = paramBoolean5;
    zzagk = paramString7;
  }
  
  ApplicationInfo(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, String paramString5, boolean paramBoolean1, boolean paramBoolean2, long paramLong3, String paramString6, long paramLong4, long paramLong5, int paramInt, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString7)
  {
    packageName = paramString1;
    zzafx = paramString2;
    zzts = paramString3;
    zzagd = paramLong3;
    zzage = paramString4;
    zzadt = paramLong1;
    zzagf = paramLong2;
    zzagv = paramString5;
    zzagg = paramBoolean1;
    zzagw = paramBoolean2;
    zzafz = paramString6;
    zzagh = paramLong4;
    zzagx = paramLong5;
    zzagy = paramInt;
    zzagi = paramBoolean3;
    zzagj = paramBoolean4;
    zzagz = paramBoolean5;
    zzagk = paramString7;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, packageName, false);
    SafeParcelWriter.writeString(paramParcel, 3, zzafx, false);
    SafeParcelWriter.writeString(paramParcel, 4, zzts, false);
    SafeParcelWriter.writeString(paramParcel, 5, zzage, false);
    SafeParcelWriter.writeLong(paramParcel, 6, zzadt);
    SafeParcelWriter.writeLong(paramParcel, 7, zzagf);
    SafeParcelWriter.writeString(paramParcel, 8, zzagv, false);
    SafeParcelWriter.writeBoolean(paramParcel, 9, zzagg);
    SafeParcelWriter.writeBoolean(paramParcel, 10, zzagw);
    SafeParcelWriter.writeLong(paramParcel, 11, zzagd);
    SafeParcelWriter.writeString(paramParcel, 12, zzafz, false);
    SafeParcelWriter.writeLong(paramParcel, 13, zzagh);
    SafeParcelWriter.writeLong(paramParcel, 14, zzagx);
    SafeParcelWriter.writeInt(paramParcel, 15, zzagy);
    SafeParcelWriter.writeBoolean(paramParcel, 16, zzagi);
    SafeParcelWriter.writeBoolean(paramParcel, 17, zzagj);
    SafeParcelWriter.writeBoolean(paramParcel, 18, zzagz);
    SafeParcelWriter.writeString(paramParcel, 19, zzagk, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
