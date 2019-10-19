package com.google.android.android.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

@KeepForSdk
@KeepName
public final class BinderWrapper
  implements Parcelable
{
  public static final Parcelable.Creator<com.google.android.gms.common.internal.BinderWrapper> CREATOR = new Point.1();
  private IBinder zzcy = null;
  
  public BinderWrapper() {}
  
  public BinderWrapper(IBinder paramIBinder)
  {
    zzcy = paramIBinder;
  }
  
  private BinderWrapper(Parcel paramParcel)
  {
    zzcy = paramParcel.readStrongBinder();
  }
  
  public final int describeContents()
  {
    return 0;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeStrongBinder(zzcy);
  }
}
