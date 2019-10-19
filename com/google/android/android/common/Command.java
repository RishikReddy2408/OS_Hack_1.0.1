package com.google.android.android.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.android.common.internal.Parameter;
import com.google.android.android.common.internal.Property;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.android.dynamic.ObjectWrapper;
import com.google.android.android.internal.common.IExtensionHost.Stub;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.zzk;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator="GoogleCertificatesQueryCreator")
public final class Command
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<zzk> CREATOR = new DownloadProgressInfo.1();
  @SafeParcelable.Field(getter="getCallingPackage", id=1)
  private final String mArgs;
  @Nullable
  @SafeParcelable.Field(getter="getCallingCertificateBinder", id=2, type="android.os.IBinder")
  private final Type mId;
  @SafeParcelable.Field(getter="getAllowTestKeys", id=3)
  private final boolean zzaa;
  
  Command(String paramString, IBinder paramIBinder, boolean paramBoolean)
  {
    mArgs = paramString;
    mId = get(paramIBinder);
    zzaa = paramBoolean;
  }
  
  Command(String paramString, Type paramType, boolean paramBoolean)
  {
    mArgs = paramString;
    mId = paramType;
    zzaa = paramBoolean;
  }
  
  private static Type get(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    try
    {
      paramIBinder = Parameter.get(paramIBinder).get();
      if (paramIBinder == null) {
        paramIBinder = null;
      } else {
        paramIBinder = (byte[])ObjectWrapper.unwrap(paramIBinder);
      }
      if (paramIBinder != null) {
        return new Sha256Hash(paramIBinder);
      }
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
      return null;
    }
    catch (RemoteException paramIBinder)
    {
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", paramIBinder);
    }
    return null;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 1, mArgs, false);
    IBinder localIBinder;
    if (mId == null)
    {
      Log.w("GoogleCertificatesQuery", "certificate binder is null");
      localIBinder = null;
    }
    else
    {
      localIBinder = mId.asBinder();
    }
    SafeParcelWriter.writeIBinder(paramParcel, 2, localIBinder, false);
    SafeParcelWriter.writeBoolean(paramParcel, 3, zzaa);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}
