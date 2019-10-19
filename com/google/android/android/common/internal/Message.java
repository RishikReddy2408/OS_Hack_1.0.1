package com.google.android.android.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.internal.zzr;

@Deprecated
@SafeParcelable.Class(creator="ValidateAccountRequestCreator")
public final class Message
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<zzr> CREATOR = new SpecialListsDueExistsProperty.1();
  @SafeParcelable.VersionField(id=1)
  private final int mFlags;
  
  Message(int paramInt)
  {
    mFlags = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, mFlags);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
