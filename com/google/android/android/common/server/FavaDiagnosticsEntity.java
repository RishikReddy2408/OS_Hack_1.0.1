package com.google.android.android.common.server;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.ReflectedParcelable;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@KeepForSdk
@SafeParcelable.Class(creator="FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity
  extends AbstractSafeParcelable
  implements ReflectedParcelable
{
  @KeepForSdk
  public static final Parcelable.Creator<com.google.android.gms.common.server.FavaDiagnosticsEntity> CREATOR = new VerticalProgressBar.SavedState.1();
  @SafeParcelable.VersionField(id=1)
  private final int zale;
  @SafeParcelable.Field(id=2)
  private final String zapi;
  @SafeParcelable.Field(id=3)
  private final int zapj;
  
  public FavaDiagnosticsEntity(int paramInt1, String paramString, int paramInt2)
  {
    zale = paramInt1;
    zapi = paramString;
    zapj = paramInt2;
  }
  
  public FavaDiagnosticsEntity(String paramString, int paramInt)
  {
    zale = 1;
    zapi = paramString;
    zapj = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeString(paramParcel, 2, zapi, false);
    SafeParcelWriter.writeInt(paramParcel, 3, zapj);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
