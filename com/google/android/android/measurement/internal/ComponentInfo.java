package com.google.android.android.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.measurement.internal.zzl;

@SafeParcelable.Class(creator="ConditionalUserPropertyParcelCreator")
public final class ComponentInfo
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<zzl> CREATOR = new VerticalProgressBar.SavedState.1();
  @SafeParcelable.Field(id=6)
  public boolean active;
  @SafeParcelable.Field(id=5)
  public long creationTimestamp;
  @SafeParcelable.Field(id=3)
  public String origin;
  @SafeParcelable.Field(id=2)
  public String packageName;
  @SafeParcelable.Field(id=11)
  public long timeToLive;
  @SafeParcelable.Field(id=7)
  public String triggerEventName;
  @SafeParcelable.Field(id=9)
  public long triggerTimeout;
  @SafeParcelable.Field(id=4)
  public zzfh zzahb;
  @SafeParcelable.Field(id=8)
  public zzad zzahc;
  @SafeParcelable.Field(id=10)
  public zzad zzahd;
  @SafeParcelable.Field(id=12)
  public zzad zzahe;
  
  ComponentInfo(ComponentInfo paramComponentInfo)
  {
    Preconditions.checkNotNull(paramComponentInfo);
    packageName = packageName;
    origin = origin;
    zzahb = zzahb;
    creationTimestamp = creationTimestamp;
    active = active;
    triggerEventName = triggerEventName;
    zzahc = zzahc;
    triggerTimeout = triggerTimeout;
    zzahd = zzahd;
    timeToLive = timeToLive;
    zzahe = zzahe;
  }
  
  ComponentInfo(String paramString1, String paramString2, zzfh paramZzfh, long paramLong1, boolean paramBoolean, String paramString3, zzad paramZzad1, long paramLong2, zzad paramZzad2, long paramLong3, zzad paramZzad3)
  {
    packageName = paramString1;
    origin = paramString2;
    zzahb = paramZzfh;
    creationTimestamp = paramLong1;
    active = paramBoolean;
    triggerEventName = paramString3;
    zzahc = paramZzad1;
    triggerTimeout = paramLong2;
    zzahd = paramZzad2;
    timeToLive = paramLong3;
    zzahe = paramZzad3;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, packageName, false);
    SafeParcelWriter.writeString(paramParcel, 3, origin, false);
    SafeParcelWriter.writeParcelable(paramParcel, 4, zzahb, paramInt, false);
    SafeParcelWriter.writeLong(paramParcel, 5, creationTimestamp);
    SafeParcelWriter.writeBoolean(paramParcel, 6, active);
    SafeParcelWriter.writeString(paramParcel, 7, triggerEventName, false);
    SafeParcelWriter.writeParcelable(paramParcel, 8, zzahc, paramInt, false);
    SafeParcelWriter.writeLong(paramParcel, 9, triggerTimeout);
    SafeParcelWriter.writeParcelable(paramParcel, 10, zzahd, paramInt, false);
    SafeParcelWriter.writeLong(paramParcel, 11, timeToLive);
    SafeParcelWriter.writeParcelable(paramParcel, 12, zzahe, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}
