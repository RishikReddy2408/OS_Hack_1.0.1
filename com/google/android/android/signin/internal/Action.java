package com.google.android.android.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.android.common.aimsicd.Result;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.signin.internal.zaa;

@SafeParcelable.Class(creator="AuthAccountResultCreator")
public final class Action
  extends AbstractSafeParcelable
  implements Result
{
  public static final Parcelable.Creator<zaa> CREATOR = new VerticalProgressBar.SavedState.1();
  @SafeParcelable.VersionField(id=1)
  private final int zale;
  @SafeParcelable.Field(getter="getConnectionResultCode", id=2)
  private int zarw;
  @SafeParcelable.Field(getter="getRawAuthResolutionIntent", id=3)
  private Intent zarx;
  
  public Action()
  {
    this(0, null);
  }
  
  Action(int paramInt1, int paramInt2, Intent paramIntent)
  {
    zale = paramInt1;
    zarw = paramInt2;
    zarx = paramIntent;
  }
  
  private Action(int paramInt, Intent paramIntent)
  {
    this(2, 0, null);
  }
  
  public final Status getStatus()
  {
    if (zarw == 0) {
      return Status.RESULT_SUCCESS;
    }
    return Status.RESULT_CANCELED;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeInt(paramParcel, 2, zarw);
    SafeParcelWriter.writeParcelable(paramParcel, 3, zarx, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}
