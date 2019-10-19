package com.google.android.android.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.base.Converter;
import com.google.android.android.internal.base.IExtensionHost.Stub;
import com.google.android.android.internal.base.Os;

public abstract interface IResolveAccountCallbacks
  extends IInterface
{
  public abstract void onAccountResolutionComplete(ResolveAccountResponse paramResolveAccountResponse)
    throws RemoteException;
  
  public abstract class Stub
    extends IExtensionHost.Stub
    implements IResolveAccountCallbacks
  {
    public Stub()
    {
      super();
    }
    
    public static IResolveAccountCallbacks asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
      if ((localIInterface instanceof IResolveAccountCallbacks)) {
        return (IResolveAccountCallbacks)localIInterface;
      }
      return new Proxy();
    }
    
    protected boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      if (paramInt1 == 2)
      {
        onAccountResolutionComplete((ResolveAccountResponse)Os.unmarshall(paramParcel1, ResolveAccountResponse.CREATOR));
        paramParcel2.writeNoException();
        return true;
      }
      return false;
    }
    
    public class Proxy
      extends Converter
      implements IResolveAccountCallbacks
    {
      Proxy()
      {
        super("com.google.android.gms.common.internal.IResolveAccountCallbacks");
      }
      
      public void onAccountResolutionComplete(ResolveAccountResponse paramResolveAccountResponse)
        throws RemoteException
      {
        Parcel localParcel = get();
        Os.writeValue(localParcel, paramResolveAccountResponse);
        register(2, localParcel);
      }
    }
  }
}
