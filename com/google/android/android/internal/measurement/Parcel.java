package com.google.android.android.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public class Parcel
  implements IInterface
{
  private final IBinder zzqt;
  private final String zzqu;
  
  protected Parcel(IBinder paramIBinder, String paramString)
  {
    zzqt = paramIBinder;
    zzqu = paramString;
  }
  
  public IBinder asBinder()
  {
    return zzqt;
  }
  
  protected final android.os.Parcel obtainAndWriteInterfaceToken()
  {
    android.os.Parcel localParcel = android.os.Parcel.obtain();
    localParcel.writeInterfaceToken(zzqu);
    return localParcel;
  }
  
  protected final android.os.Parcel transactAndReadException(int paramInt, android.os.Parcel paramParcel)
    throws RemoteException
  {
    android.os.Parcel localParcel = android.os.Parcel.obtain();
    try
    {
      zzqt.transact(paramInt, paramParcel, localParcel, 0);
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
  
  protected final void transactAndReadExceptionReturnVoid(int paramInt, android.os.Parcel paramParcel)
    throws RemoteException
  {
    android.os.Parcel localParcel = android.os.Parcel.obtain();
    try
    {
      zzqt.transact(paramInt, paramParcel, localParcel, 0);
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
  
  protected final void transactOneway(int paramInt, android.os.Parcel paramParcel)
    throws RemoteException
  {
    try
    {
      zzqt.transact(1, paramParcel, null, 1);
      paramParcel.recycle();
      return;
    }
    catch (Throwable localThrowable)
    {
      paramParcel.recycle();
      throw localThrowable;
    }
  }
}
