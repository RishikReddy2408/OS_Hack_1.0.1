package com.google.android.android.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.common.Context;
import com.google.android.android.internal.common.Log;

public final class e
  extends Log
  implements IGmsCallbacks
{
  e(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
  }
  
  public final void init(int paramInt, Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = next();
    localParcel.writeInt(paramInt);
    Context.writeValue(localParcel, paramBundle);
    register(2, localParcel);
  }
  
  public final void onPostInitComplete(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = next();
    localParcel.writeInt(paramInt);
    localParcel.writeStrongBinder(paramIBinder);
    Context.writeValue(localParcel, paramBundle);
    register(1, localParcel);
  }
  
  public final void write(int paramInt, IBinder paramIBinder, Entry paramEntry)
    throws RemoteException
  {
    Parcel localParcel = next();
    localParcel.writeInt(paramInt);
    localParcel.writeStrongBinder(paramIBinder);
    Context.writeValue(localParcel, paramEntry);
    register(3, localParcel);
  }
}
