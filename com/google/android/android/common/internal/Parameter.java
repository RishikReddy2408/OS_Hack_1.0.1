package com.google.android.android.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.common.Context;
import com.google.android.android.internal.common.IExtensionHost.Stub;

public abstract class Parameter
  extends IExtensionHost.Stub
  implements Property
{
  public Parameter()
  {
    super("com.google.android.gms.common.internal.ICertData");
  }
  
  public static Property get(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
    if ((localIInterface instanceof Property)) {
      return (Property)localIInterface;
    }
    return new IStatusBarCustomTileHolder.Stub.Proxy(paramIBinder);
  }
  
  protected final boolean execute(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      return false;
    case 2: 
      paramInt1 = getType();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      break;
    case 1: 
      paramParcel1 = get();
      paramParcel2.writeNoException();
      Context.get(paramParcel2, paramParcel1);
    }
    return true;
  }
}
