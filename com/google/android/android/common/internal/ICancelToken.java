package com.google.android.android.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.android.internal.common.IExtensionHost.Stub;
import com.google.android.android.internal.common.Log;

public abstract interface ICancelToken
  extends IInterface
{
  public abstract void cancel()
    throws RemoteException;
  
  public abstract class Stub
    extends IExtensionHost.Stub
    implements ICancelToken
  {
    public Stub()
    {
      super();
    }
    
    public static ICancelToken asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
      if ((localIInterface instanceof ICancelToken)) {
        return (ICancelToken)localIInterface;
      }
      return new zza();
    }
    
    public final class zza
      extends Log
      implements ICancelToken
    {
      zza()
      {
        super("com.google.android.gms.common.internal.ICancelToken");
      }
      
      public final void cancel()
        throws RemoteException
      {
        setText(2, next());
      }
    }
  }
}
