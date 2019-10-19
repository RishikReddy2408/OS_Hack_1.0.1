package com.google.android.android.common.aimsicd.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.internal.base.Converter;
import com.google.android.android.internal.base.IExtensionHost.Stub;
import com.google.android.android.internal.base.Os;

public abstract interface IStatusCallback
  extends IInterface
{
  public abstract void onResult(Status paramStatus)
    throws RemoteException;
  
  public abstract class Stub
    extends IExtensionHost.Stub
    implements IStatusCallback
  {
    public Stub()
    {
      super();
    }
    
    public static IStatusCallback asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
      if ((localIInterface instanceof IStatusCallback)) {
        return (IStatusCallback)localIInterface;
      }
      return new zaa();
    }
    
    protected boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      if (paramInt1 == 1)
      {
        onResult((Status)Os.unmarshall(paramParcel1, Status.CREATOR));
        return true;
      }
      return false;
    }
    
    public final class zaa
      extends Converter
      implements IStatusCallback
    {
      zaa()
      {
        super("com.google.android.gms.common.api.internal.IStatusCallback");
      }
      
      public final void onResult(Status paramStatus)
        throws RemoteException
      {
        Parcel localParcel = get();
        Os.writeValue(localParcel, paramStatus);
        close(1, localParcel);
      }
    }
  }
}
