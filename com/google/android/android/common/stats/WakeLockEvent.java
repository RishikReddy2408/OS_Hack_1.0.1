package com.google.android.android.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@SafeParcelable.Class(creator="WakeLockEventCreator")
public final class WakeLockEvent
  extends StatsEvent
{
  public static final Parcelable.Creator<com.google.android.gms.common.stats.WakeLockEvent> CREATOR = new VerticalProgressBar.SavedState.1();
  @SafeParcelable.Field(getter="getTimeout", id=16)
  private final long mTimeout;
  @SafeParcelable.VersionField(id=1)
  private final int startMinute;
  @SafeParcelable.Field(getter="getTimeMillis", id=2)
  private final long zzfo;
  @SafeParcelable.Field(getter="getEventType", id=11)
  private int zzfp;
  @SafeParcelable.Field(getter="getWakeLockName", id=4)
  private final String zzfq;
  @SafeParcelable.Field(getter="getSecondaryWakeLockName", id=10)
  private final String zzfr;
  @SafeParcelable.Field(getter="getCodePackage", id=17)
  private final String zzfs;
  @SafeParcelable.Field(getter="getWakeLockType", id=5)
  private final int zzft;
  @SafeParcelable.Field(getter="getCallingPackages", id=6)
  private final List<String> zzfu;
  @SafeParcelable.Field(getter="getEventKey", id=12)
  private final String zzfv;
  @SafeParcelable.Field(getter="getElapsedRealtime", id=8)
  private final long zzfw;
  @SafeParcelable.Field(getter="getDeviceState", id=14)
  private int zzfx;
  @SafeParcelable.Field(getter="getHostPackage", id=13)
  private final String zzfy;
  @SafeParcelable.Field(getter="getBeginPowerPercentage", id=15)
  private final float zzfz;
  private long zzga;
  
  WakeLockEvent(int paramInt1, long paramLong1, int paramInt2, String paramString1, int paramInt3, List paramList, String paramString2, long paramLong2, int paramInt4, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5)
  {
    startMinute = paramInt1;
    zzfo = paramLong1;
    zzfp = paramInt2;
    zzfq = paramString1;
    zzfr = paramString3;
    zzfs = paramString5;
    zzft = paramInt3;
    zzga = -1L;
    zzfu = paramList;
    zzfv = paramString2;
    zzfw = paramLong2;
    zzfx = paramInt4;
    zzfy = paramString4;
    zzfz = paramFloat;
    mTimeout = paramLong3;
  }
  
  public WakeLockEvent(long paramLong1, int paramInt1, String paramString1, int paramInt2, List paramList, String paramString2, long paramLong2, int paramInt3, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5)
  {
    this(2, paramLong1, paramInt1, paramString1, paramInt2, paramList, paramString2, paramLong2, paramInt3, paramString3, paramString4, paramFloat, paramLong3, paramString5);
  }
  
  public final long getDateAsString()
  {
    return zzga;
  }
  
  public final int getEventType()
  {
    return zzfp;
  }
  
  public final String getRawText()
  {
    String str5 = zzfq;
    int i = zzft;
    String str1;
    if (zzfu == null) {
      str1 = "";
    } else {
      str1 = TextUtils.join(",", zzfu);
    }
    int j = zzfx;
    String str2;
    if (zzfr == null) {
      str2 = "";
    } else {
      str2 = zzfr;
    }
    String str3;
    if (zzfy == null) {
      str3 = "";
    } else {
      str3 = zzfy;
    }
    float f = zzfz;
    String str4;
    if (zzfs == null) {
      str4 = "";
    } else {
      str4 = zzfs;
    }
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str5).length() + 45 + String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
    localStringBuilder.append("\t");
    localStringBuilder.append(str5);
    localStringBuilder.append("\t");
    localStringBuilder.append(i);
    localStringBuilder.append("\t");
    localStringBuilder.append(str1);
    localStringBuilder.append("\t");
    localStringBuilder.append(j);
    localStringBuilder.append("\t");
    localStringBuilder.append(str2);
    localStringBuilder.append("\t");
    localStringBuilder.append(str3);
    localStringBuilder.append("\t");
    localStringBuilder.append(f);
    localStringBuilder.append("\t");
    localStringBuilder.append(str4);
    return localStringBuilder.toString();
  }
  
  public final long getTimeMillis()
  {
    return zzfo;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, startMinute);
    SafeParcelWriter.writeLong(paramParcel, 2, getTimeMillis());
    SafeParcelWriter.writeString(paramParcel, 4, zzfq, false);
    SafeParcelWriter.writeInt(paramParcel, 5, zzft);
    SafeParcelWriter.writeStringList(paramParcel, 6, zzfu, false);
    SafeParcelWriter.writeLong(paramParcel, 8, zzfw);
    SafeParcelWriter.writeString(paramParcel, 10, zzfr, false);
    SafeParcelWriter.writeInt(paramParcel, 11, getEventType());
    SafeParcelWriter.writeString(paramParcel, 12, zzfv, false);
    SafeParcelWriter.writeString(paramParcel, 13, zzfy, false);
    SafeParcelWriter.writeInt(paramParcel, 14, zzfx);
    SafeParcelWriter.writeFloat(paramParcel, 15, zzfz);
    SafeParcelWriter.writeLong(paramParcel, 16, mTimeout);
    SafeParcelWriter.writeString(paramParcel, 17, zzfs, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
