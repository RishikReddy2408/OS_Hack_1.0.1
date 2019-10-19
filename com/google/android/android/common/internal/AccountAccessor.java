package com.google.android.android.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

public class AccountAccessor
  extends IAccountAccessor.Stub
{
  public static Account getAccountBinderSafe(IAccountAccessor paramIAccountAccessor)
  {
    long l;
    if (paramIAccountAccessor != null) {
      l = Binder.clearCallingIdentity();
    }
    try
    {
      paramIAccountAccessor = paramIAccountAccessor.getAccount();
      Binder.restoreCallingIdentity(l);
      return paramIAccountAccessor;
    }
    catch (Throwable paramIAccountAccessor)
    {
      break label40;
      Log.w("AccountAccessor", "Remote account accessor probably died");
      Binder.restoreCallingIdentity(l);
      break label46;
      Binder.restoreCallingIdentity(l);
      throw paramIAccountAccessor;
      return null;
    }
    catch (RemoteException paramIAccountAccessor)
    {
      label40:
      label46:
      for (;;) {}
    }
  }
  
  public boolean equals(Object paramObject)
  {
    throw new NoSuchMethodError();
  }
  
  public final Account getAccount()
  {
    throw new NoSuchMethodError();
  }
}
