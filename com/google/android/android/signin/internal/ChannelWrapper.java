package com.google.android.android.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.common.internal.IAccountAccessor;
import com.google.android.android.internal.base.Converter;
import com.google.android.android.internal.base.Os;

public final class ChannelWrapper
  extends Converter
  implements Channel
{
  ChannelWrapper(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.signin.internal.ISignInService");
  }
  
  public final void write(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = get();
    localParcel.writeInt(paramInt);
    register(7, localParcel);
  }
  
  public final void write(IAccountAccessor paramIAccountAccessor, int paramInt, boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = get();
    Os.rename(localParcel, paramIAccountAccessor);
    localParcel.writeInt(paramInt);
    Os.writeBoolean(localParcel, paramBoolean);
    register(9, localParcel);
  }
  
  public final void write(OperationResult paramOperationResult, Session paramSession)
    throws RemoteException
  {
    Parcel localParcel = get();
    Os.writeValue(localParcel, paramOperationResult);
    Os.rename(localParcel, paramSession);
    register(12, localParcel);
  }
}
