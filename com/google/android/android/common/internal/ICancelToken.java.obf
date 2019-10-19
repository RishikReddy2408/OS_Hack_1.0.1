package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzb;

public abstract interface ICancelToken
  extends IInterface
{
  public abstract void cancel()
    throws RemoteException;
  
  public static abstract class Stub
    extends zzb
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
      return new zza(paramIBinder);
    }
    
    public static final class zza
      extends zza
      implements ICancelToken
    {
      zza(IBinder paramIBinder)
      {
        super("com.google.android.gms.common.internal.ICancelToken");
      }
      
      public final void cancel()
        throws RemoteException
      {
        zzc(2, zza());
      }
    }
  }
}
