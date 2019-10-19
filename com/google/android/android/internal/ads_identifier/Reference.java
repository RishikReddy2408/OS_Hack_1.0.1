package com.google.android.android.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class Reference
  implements IInterface
{
  private final String actual;
  private final IBinder mRemote;
  
  protected Reference(IBinder paramIBinder, String paramString)
  {
    mRemote = paramIBinder;
    actual = paramString;
  }
  
  public IBinder asBinder()
  {
    return mRemote;
  }
  
  protected final Parcel obtainAndWriteInterfaceToken()
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.writeInterfaceToken(actual);
    return localParcel;
  }
  
  protected final Parcel transactAndReadException(int paramInt, Parcel paramParcel)
    throws RemoteException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      mRemote.transact(paramInt, paramParcel, localParcel, 0);
      localParcel.readException();
      paramParcel.recycle();
      return localParcel;
    }
    catch (Throwable localThrowable) {}catch (RuntimeException localRuntimeException)
    {
      localThrowable.recycle();
      throw localRuntimeException;
    }
    paramParcel.recycle();
    throw localThrowable;
  }
}
