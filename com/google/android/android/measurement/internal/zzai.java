package com.google.android.android.measurement.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.android.internal.measurement.Log;
import java.util.List;

public final class zzai
  extends com.google.android.android.internal.measurement.Parcel
  implements zzag
{
  zzai(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
  }
  
  public final void chmod(ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramApplicationInfo);
    transactAndReadExceptionReturnVoid(4, localParcel);
  }
  
  public final void createShortcut(ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramApplicationInfo);
    transactAndReadExceptionReturnVoid(18, localParcel);
  }
  
  public final byte[] get(zzad paramZzad, String paramString)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramZzad);
    localParcel.writeString(paramString);
    paramZzad = transactAndReadException(9, localParcel);
    paramString = paramZzad.createByteArray();
    paramZzad.recycle();
    return paramString;
  }
  
  public final String getAbsoluteUrl(ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    Object localObject = obtainAndWriteInterfaceToken();
    Log.writeString((android.os.Parcel)localObject, paramApplicationInfo);
    paramApplicationInfo = transactAndReadException(11, (android.os.Parcel)localObject);
    localObject = paramApplicationInfo.readString();
    paramApplicationInfo.recycle();
    return localObject;
  }
  
  public final List getFromLocationName(ApplicationInfo paramApplicationInfo, boolean paramBoolean)
    throws RemoteException
  {
    Object localObject = obtainAndWriteInterfaceToken();
    Log.writeString((android.os.Parcel)localObject, paramApplicationInfo);
    Log.writeBoolean((android.os.Parcel)localObject, paramBoolean);
    paramApplicationInfo = transactAndReadException(7, (android.os.Parcel)localObject);
    localObject = paramApplicationInfo.createTypedArrayList(zzfh.CREATOR);
    paramApplicationInfo.recycle();
    return localObject;
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    localParcel.writeString(paramString1);
    localParcel.writeString(paramString2);
    Log.writeString(localParcel, paramApplicationInfo);
    paramString1 = transactAndReadException(16, localParcel);
    paramString2 = paramString1.createTypedArrayList(ComponentInfo.CREATOR);
    paramString1.recycle();
    return paramString2;
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, String paramString3)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    localParcel.writeString(paramString1);
    localParcel.writeString(paramString2);
    localParcel.writeString(paramString3);
    paramString1 = transactAndReadException(17, localParcel);
    paramString2 = paramString1.createTypedArrayList(ComponentInfo.CREATOR);
    paramString1.recycle();
    return paramString2;
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    localParcel.writeString(paramString1);
    localParcel.writeString(paramString2);
    localParcel.writeString(paramString3);
    Log.writeBoolean(localParcel, paramBoolean);
    paramString1 = transactAndReadException(15, localParcel);
    paramString2 = paramString1.createTypedArrayList(zzfh.CREATOR);
    paramString1.recycle();
    return paramString2;
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, boolean paramBoolean, ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    localParcel.writeString(paramString1);
    localParcel.writeString(paramString2);
    Log.writeBoolean(localParcel, paramBoolean);
    Log.writeString(localParcel, paramApplicationInfo);
    paramString1 = transactAndReadException(14, localParcel);
    paramString2 = paramString1.createTypedArrayList(zzfh.CREATOR);
    paramString1.recycle();
    return paramString2;
  }
  
  public final void getPackageInfo(ComponentInfo paramComponentInfo, ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramComponentInfo);
    Log.writeString(localParcel, paramApplicationInfo);
    transactAndReadExceptionReturnVoid(12, localParcel);
  }
  
  public final void handleResult(ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramApplicationInfo);
    transactAndReadExceptionReturnVoid(6, localParcel);
  }
  
  public final void handleResult(zzad paramZzad, ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramZzad);
    Log.writeString(localParcel, paramApplicationInfo);
    transactAndReadExceptionReturnVoid(1, localParcel);
  }
  
  public final void handleResult(zzad paramZzad, String paramString1, String paramString2)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramZzad);
    localParcel.writeString(paramString1);
    localParcel.writeString(paramString2);
    transactAndReadExceptionReturnVoid(5, localParcel);
  }
  
  public final void setPresence(long paramLong, String paramString1, String paramString2, String paramString3)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    localParcel.writeLong(paramLong);
    localParcel.writeString(paramString1);
    localParcel.writeString(paramString2);
    localParcel.writeString(paramString3);
    transactAndReadExceptionReturnVoid(10, localParcel);
  }
  
  public final void toggleState(ComponentInfo paramComponentInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramComponentInfo);
    transactAndReadExceptionReturnVoid(13, localParcel);
  }
  
  public final void write(zzfh paramZzfh, ApplicationInfo paramApplicationInfo)
    throws RemoteException
  {
    android.os.Parcel localParcel = obtainAndWriteInterfaceToken();
    Log.writeString(localParcel, paramZzfh);
    Log.writeString(localParcel, paramApplicationInfo);
    transactAndReadExceptionReturnVoid(2, localParcel);
  }
}
