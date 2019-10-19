package com.google.android.android.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.measurement.IExtensionHost.Stub;
import com.google.android.android.internal.measurement.Log;

public abstract class zzah
  extends IExtensionHost.Stub
  implements zzag
{
  public zzah()
  {
    super("com.google.android.gms.measurement.internal.IMeasurementService");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      break;
    case 3: 
    case 8: 
      return false;
    case 18: 
      createShortcut((ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      break;
    case 17: 
      paramParcel1 = getFromLocationName(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(paramParcel1);
      break;
    case 16: 
      paramParcel1 = getFromLocationName(paramParcel1.readString(), paramParcel1.readString(), (ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(paramParcel1);
      break;
    case 15: 
      paramParcel1 = getFromLocationName(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), Log.readString(paramParcel1));
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(paramParcel1);
      break;
    case 14: 
      paramParcel1 = getFromLocationName(paramParcel1.readString(), paramParcel1.readString(), Log.readString(paramParcel1), (ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(paramParcel1);
      break;
    case 13: 
      toggleState((ComponentInfo)Log.unmarshall(paramParcel1, ComponentInfo.CREATOR));
      paramParcel2.writeNoException();
      break;
    case 12: 
      getPackageInfo((ComponentInfo)Log.unmarshall(paramParcel1, ComponentInfo.CREATOR), (ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      break;
    case 11: 
      paramParcel1 = getAbsoluteUrl((ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      break;
    case 10: 
      setPresence(paramParcel1.readLong(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      break;
    case 9: 
      paramParcel1 = get((zzad)Log.unmarshall(paramParcel1, zzad.CREATOR), paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeByteArray(paramParcel1);
      break;
    case 7: 
      paramParcel1 = getFromLocationName((ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR), Log.readString(paramParcel1));
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(paramParcel1);
      break;
    case 6: 
      handleResult((ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      break;
    case 5: 
      handleResult((zzad)Log.unmarshall(paramParcel1, zzad.CREATOR), paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      break;
    case 4: 
      chmod((ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      break;
    case 2: 
      write((zzfh)Log.unmarshall(paramParcel1, zzfh.CREATOR), (ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
      paramParcel2.writeNoException();
      break;
    }
    handleResult((zzad)Log.unmarshall(paramParcel1, zzad.CREATOR), (ApplicationInfo)Log.unmarshall(paramParcel1, ApplicationInfo.CREATOR));
    paramParcel2.writeNoException();
    return true;
  }
}
