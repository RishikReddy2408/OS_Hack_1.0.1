package com.google.android.android.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.android.internal.common.IExtensionHost.Stub;
import com.google.android.android.internal.common.Log;

public abstract interface IObjectWrapper
  extends IInterface
{
  public class Stub
    extends IExtensionHost.Stub
    implements IObjectWrapper
  {
    public Stub()
    {
      super();
    }
    
    public static IObjectWrapper asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
      if ((localIInterface instanceof IObjectWrapper)) {
        return (IObjectWrapper)localIInterface;
      }
      return new zza();
    }
    
    public final class zza
      extends Log
      implements IObjectWrapper
    {
      zza()
      {
        super("com.google.android.gms.dynamic.IObjectWrapper");
      }
    }
  }
}
