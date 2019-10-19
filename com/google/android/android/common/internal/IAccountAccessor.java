package com.google.android.android.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.common.Context;
import com.google.android.android.internal.common.IExtensionHost.Stub;
import com.google.android.android.internal.common.Log;

public abstract interface IAccountAccessor
  extends IInterface
{
  public abstract Account getAccount()
    throws RemoteException;
  
  public abstract class Stub
    extends IExtensionHost.Stub
    implements IAccountAccessor
  {
    public Stub()
    {
      super();
    }
    
    public static IAccountAccessor asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
      if ((localIInterface instanceof IAccountAccessor)) {
        return (IAccountAccessor)localIInterface;
      }
      return new zza();
    }
    
    protected final boolean execute(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      if (paramInt1 == 2)
      {
        paramParcel1 = getAccount();
        paramParcel2.writeNoException();
        Context.marshall(paramParcel2, paramParcel1);
        return true;
      }
      return false;
    }
    
    public final class zza
      extends Log
      implements IAccountAccessor
    {
      zza()
      {
        super("com.google.android.gms.common.internal.IAccountAccessor");
      }
      
      public final Account getAccount()
        throws RemoteException
      {
        Parcel localParcel = get(2, next());
        Account localAccount = (Account)Context.get(localParcel, Account.CREATOR);
        localParcel.recycle();
        return localAccount;
      }
    }
  }
}
