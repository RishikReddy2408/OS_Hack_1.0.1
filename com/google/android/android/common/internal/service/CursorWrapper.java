package com.google.android.android.common.internal.service;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.base.IExtensionHost.Stub;

public abstract class CursorWrapper
  extends IExtensionHost.Stub
  implements Cursor
{
  public CursorWrapper()
  {
    super("com.google.android.gms.common.internal.service.ICommonCallbacks");
  }
  
  protected boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (paramInt1 == 1)
    {
      read(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    return false;
  }
}
