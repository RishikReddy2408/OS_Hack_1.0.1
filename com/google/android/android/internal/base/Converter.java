package com.google.android.android.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class Converter
  implements IInterface
{
  private final IBinder mRemote;
  private final String value;
  
  protected Converter(IBinder paramIBinder, String paramString)
  {
    mRemote = paramIBinder;
    value = paramString;
  }
  
  public IBinder asBinder()
  {
    return mRemote;
  }
  
  protected final void close(int paramInt, Parcel paramParcel)
    throws RemoteException
  {
    try
    {
      mRemote.transact(1, paramParcel, null, 1);
      paramParcel.recycle();
      return;
    }
    catch (Throwable localThrowable)
    {
      paramParcel.recycle();
      throw localThrowable;
    }
  }
  
  protected final Parcel get()
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.writeInterfaceToken(value);
    return localParcel;
  }
  
  protected final void register(int paramInt, Parcel paramParcel)
    throws RemoteException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      mRemote.transact(paramInt, paramParcel, localParcel, 0);
      localParcel.readException();
      paramParcel.recycle();
      localParcel.recycle();
      return;
    }
    catch (Throwable localThrowable)
    {
      paramParcel.recycle();
      localParcel.recycle();
      throw localThrowable;
    }
  }
  
  protected final Parcel reset(int paramInt, Parcel paramParcel)
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
